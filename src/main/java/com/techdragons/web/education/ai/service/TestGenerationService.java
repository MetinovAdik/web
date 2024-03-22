package com.techdragons.web.education.ai.service;

import com.techdragons.web.artificial.MistralService;
import com.techdragons.web.artificial.OpenaiService;
import com.techdragons.web.education.ai.AITest;
import com.techdragons.web.education.ai.Question;
import com.techdragons.web.education.ai.repository.AITestRepository;
import com.techdragons.web.education.ai.repository.QuestionRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TestGenerationService {

    @Autowired
    private OpenaiService openaiService;

    @Autowired
    private AITestRepository aiTestRepository;
    @Autowired
    private MistralService mistralService;
    @Autowired
    private QuestionRepository questionRepository;


    public AITest generateTestForCourse(Long courseId, String courseTitle, List<String> themes) throws IOException {
        AITest aiTest = new AITest();
        aiTest.setCourseId(courseId);
        aiTest.setCourseTitle(courseTitle);
        aiTest.setCreatedAt(LocalDateTime.now());
        List<Question> questions = new ArrayList<>();

        for (String theme : themes) {
            String questionText = generateQuestionForTheme(theme);
            Question question = formatAnswer(questionText);
            question.setTheme(theme);
            questions.add(question);
        }

        aiTest.setQuestions(questions);
        AITest savedTest = aiTestRepository.save(aiTest);

        // Link the questions to the test
        questions.forEach(q -> q.setTest(savedTest));
        questionRepository.saveAll(questions);

        return savedTest;
    }
    private Question formatAnswer(String answerText) {
        Question question = new Question();

        String[] parts = answerText.split("CorrectAnswer: ");
        if (parts.length < 2) {
            throw new IllegalArgumentException("The answer text is not in the expected format.");
        }
        String questionText = parts[0].substring("Question: ".length()).trim();
        String correctAnswer = parts[1].substring("CorrectAnswer:".length()).trim();

        question.setQuestion(questionText);
        question.setCorrectAnswer(correctAnswer);

        return question;
    }

    private String generateQuestionForTheme(String theme) throws IOException {
        String prompt = "Create a question for the theme: " + theme;
        CompletableFuture<String> futureResponse = mistralService.sendAndReceiveMessageAsync(prompt+"Please generate a question based on the given theme and format it like this <Question: question text. CorrectAnswer: correct answer text>.");
        String jsonResponse = futureResponse.join(); // This will wait for the future to complete

        // Process the JSON response as needed
        String reply =parseResponses(jsonResponse);
        return reply;
    }
    private String parseResponses(String jsonResponse) {
        StringBuilder fullResponse = new StringBuilder();
        String[] lines = jsonResponse.split("\n");

        for (String line : lines) {
            if (line.trim().isEmpty()) continue; // Skip empty lines

            JSONObject jsonObject = new JSONObject(line);
            if (!jsonObject.optBoolean("done", true)) { // Only append if not done
                fullResponse.append(jsonObject.optString("response", ""));
            }
        }

        return fullResponse.toString();
    }
}
