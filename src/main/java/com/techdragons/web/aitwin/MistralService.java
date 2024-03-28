package com.techdragons.web.aitwin;

import com.techdragons.web.education.individ.Answer;
import com.techdragons.web.education.individ.AnswerList;
import com.techdragons.web.education.individ.PersonalizedCourse;
import com.techdragons.web.education.individ.PersonalizedLesson;
import com.techdragons.web.education.individ.repository.PersonalizedCourseRepository;
import com.techdragons.web.education.individ.service.PersonalizedLessonService;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MistralService {

    private final String mistralApiUrl;
    private final CloseableHttpAsyncClient httpclient;
    private final PersonalizedCourseRepository personalizedCourseRepository;
    private final PersonalizedLessonService personalizedLessonService;
    public MistralService(@Value("${mistral.api.url}")String mistralApiUrl, PersonalizedCourseRepository personalizedCourseRepository, PersonalizedLessonService personalizedLessonService) {
        this.mistralApiUrl = mistralApiUrl;
        this.personalizedCourseRepository = personalizedCourseRepository;
        this.personalizedLessonService = personalizedLessonService;
        this.httpclient = HttpAsyncClients.createDefault();
    }

    public CompletableFuture<String> sendAndReceiveMessageAsync(String prompt) {
        CompletableFuture<String> responseFuture = new CompletableFuture<>();
        this.httpclient.start();

        HttpPost request = new HttpPost(mistralApiUrl + "/api/generate");
        request.setHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity(new JSONObject().put("model", "mistral").put("prompt", prompt).toString(), "UTF-8"));

        httpclient.execute(request, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse result) {
                try {
                    String responseString = EntityUtils.toString(result.getEntity());
                    responseFuture.complete(responseString);
                } catch (IOException e) {
                    responseFuture.completeExceptionally(e);
                }
            }

            @Override
            public void failed(Exception ex) {
                responseFuture.completeExceptionally(ex);
            }

            @Override
            public void cancelled() {
                responseFuture.cancel(true);
            }
        });

        return responseFuture;
    }
    public void genLesson(String theme , Long courseId){
        CompletableFuture<String> futureResponse = sendAndReceiveMessageAsync("Напиши новый урок по теме " + theme + " с уровнем сложности 3 из 5 в этом формате <Теория: текстТеории;  ПрактическийПример:  текстПрактическогоПримера; КонтрольныйВопрос: текстКонтрольногоВопроса; ОтветНаКонтрольныйВопрос: текстОтветаНаКонтрольныйВопрос;>");
        String jsonResponse = futureResponse.join();


        String reply =parseResponses(jsonResponse);
        PersonalizedLesson personalizedLesson = parseLesson(reply);
        personalizedLesson.setTheme(theme);
        personalizedLesson.setPersonalizedCourseId(courseId);
        personalizedLessonService.createPersonalizedLesson(personalizedLesson);
        PersonalizedCourse personalizedCourse = personalizedCourseRepository.getById(courseId);
    }
    private PersonalizedLesson parseLesson(String lessonText){

        String[] parts = lessonText.split("<Теория: |;  ПрактическийПример: |; КонтрольныйВопрос: |; ОтветНаКонтрольныйВопрос: >");
        if (parts.length < 5) {
            throw new IllegalArgumentException("The lesson text is not in the expected format.");
        }


        String theory = parts[1].trim();
        String practicalExample = parts[2].trim();
        String controlQuestion = parts[3].trim();
        String answerToControlQuestion = parts[4].trim();
        PersonalizedLesson personalizedLesson = new PersonalizedLesson();
        personalizedLesson.setTheory(theory);
        personalizedLesson.setExercise(controlQuestion);
        personalizedLesson.setPracticalExample(practicalExample);
        personalizedLesson.setExerciseAnswer(answerToControlQuestion);
        return personalizedLesson;
    }
    public String parseResponses(String jsonResponse) {
        StringBuilder fullResponse = new StringBuilder();
        String[] lines = jsonResponse.split("\n");

        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            JSONObject jsonObject = new JSONObject(line);
            if (!jsonObject.optBoolean("done", true)) {
                fullResponse.append(jsonObject.optString("response", ""));
            }
        }

        return fullResponse.toString();
    }

    public void genCourse(AnswerList answerList){
        PersonalizedCourse personalizedCourse =new PersonalizedCourse();
        personalizedCourse.setStudentId(answerList.getStudentId());
        List<Answer> answerList1 = answerList.getAnswers();
        for (Answer answer : answerList1){
            genLesson(answer.getTheme(),personalizedCourse.getId());
        }
    }
}
