package com.techdragons.web.artificial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dialogs") // Определение базового пути для всех методов этого контроллера
public class DialogController {

    @Autowired
    private DialogService dialogService;

    @PostMapping("/chat")
    public MessageResponseDTO chat(@RequestBody MessageRequestDTO request) {
        // Обрабатываем сообщение пользователя
        String reply = dialogService.handleMessage(request.getMessage(), request.getStudentId(), Sender.USER);
        // Возвращаем ответ
        return new MessageResponseDTO(reply);
    }
}