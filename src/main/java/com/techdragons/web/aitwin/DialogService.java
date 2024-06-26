package com.techdragons.web.aitwin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class DialogService {

    @Autowired
    private MistralService mistralService;
    @Autowired
    private DialogRepository dialogRepository;

    public String handleMessage(String messageText, Long studentId, Sender sender) {
        // Find an existing dialog or create a new one
        Dialog dialog = dialogRepository.findByStudent(studentId)
                .orElseGet(() -> new Dialog(studentId, new ArrayList<>()));

        // Forming context from previous messages
        String context = dialog.getMessages().stream()
                .map(Message::getMessage)
                .reduce("", (acc, message) -> acc + "\n" + message);

        // Wait for the CompletableFuture to complete and get the response
        CompletableFuture<String> futureResponse = mistralService.sendAndReceiveMessageAsync(context.trim() + "\n" + messageText);
        String jsonResponse = futureResponse.join(); // This will wait for the future to complete

        // Process the JSON response as needed
        String reply =mistralService.parseResponses(jsonResponse);

        // Add the current user message and system response to the dialog
        dialog.addNewMessage(messageText, Sender.USER);
        dialog.addNewMessage(reply, Sender.SYSTEM);

        // Save the updated dialog
        dialogRepository.save(dialog);

        return reply;
    }
}

