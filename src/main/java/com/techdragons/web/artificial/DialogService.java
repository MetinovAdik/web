package com.techdragons.web.artificial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class DialogService {

    @Autowired
    private OpenaiService openaiService;
    @Autowired
    private DialogRepository dialogRepository;

    public String handleMessage(String messageText, Long studentId, Sender sender) {
        // Поиск существующего диалога или создание нового
        Dialog dialog = dialogRepository.findByStudent(studentId)
                .orElseGet(() -> new Dialog(studentId, new ArrayList<>()));

        // Формирование контекста из предыдущих сообщений
        String context = dialog.getMessages().stream()
                .map(Message::getMessage)
                .reduce("", (acc, message) -> acc + "\n" + message);

        String reply;
        try {
            // Отправка сообщения в OpenAI с контекстом
            reply = openaiService.sendMessage(messageText, context.trim());
            // Добавление текущего сообщения пользователя и ответа системы в диалог
            dialog.addNewMessage(messageText, Sender.USER);
            dialog.addNewMessage(reply, Sender.SYSTEM);
            // Сохранение обновленного диалога
            dialogRepository.save(dialog);
        } catch (IOException e) {
            reply = "Произошла ошибка при обработке вашего запроса";
        }

        return reply;
    }
}