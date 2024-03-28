package com.techdragons.web.education.individ.service;

import com.techdragons.web.aitwin.MistralService;
import com.techdragons.web.education.individ.Test;
import com.techdragons.web.education.individ.Question;
import com.techdragons.web.education.individ.repository.TestRepository;
import com.techdragons.web.education.individ.repository.QuestionRepository;
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
    private TestRepository testRepository;
    @Autowired
    private MistralService mistralService;
    @Autowired
    private QuestionRepository questionRepository;


    public Test generateTestForCourse(Long courseId, String courseTitle, List<String> themes) throws IOException {
        Test test = new Test();
        test.setCourseId(courseId);
        test.setCourseTitle(courseTitle);
        test.setCreatedAt(LocalDateTime.now());
        List<Question> questions = new ArrayList<>();

        for (String theme : themes) {
            String questionText = generateQuestionForTheme(theme);
            Question question = formatAnswer(questionText);
            question.setTheme(theme);
            questions.add(question);
        }

        test.setQuestions(questions);
        Test savedTest = testRepository.save(test);

        // Link the questions to the test
        questions.forEach(q -> q.setTest(savedTest));
        questionRepository.saveAll(questions);

        return savedTest;
    }
    private Question formatAnswer(String answerText) {
        Question question = new Question();

        String[] parts = answerText.split("ПравильныйОтвет: ");
        if (parts.length < 2) {
            throw new IllegalArgumentException("The answer text is not in the expected format.");
        }
        String questionText = parts[0].substring("Вопрос:".length()).trim();
        String correctAnswer = parts[1].substring("ПравильныйОтвет:".length()).trim();

        question.setQuestion(questionText);
        question.setCorrectAnswer(correctAnswer);

        return question;
    }

    private String generateQuestionForTheme(String theme) throws IOException {
        String prompt = "Создай вопрос по теме: " + theme;
        CompletableFuture<String> futureResponse = mistralService.sendAndReceiveMessageAsync(prompt+"Создайте вопрос на заданную тему и оформите его следующим образом <Вопрос: текст вопроса. ПравильныйОтвет: текст правильного ответа>.");
        String jsonResponse = futureResponse.join();

        // Process the JSON response as needed
        String reply =mistralService.parseResponses(jsonResponse);
        return reply;
    }
}
