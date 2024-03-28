package com.techdragons.web.aitwin;

import com.techdragons.web.entity.User;
import com.techdragons.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/dialogs")
public class DialogController {

    private final UserRepository userRepository;
    @Autowired
    private DialogService dialogService;

    public DialogController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/chat")
    public MessageResponseDTO chat(@AuthenticationPrincipal UserDetails userDetails , @RequestBody MessageRequestDTO request) {
        Optional<User> user =  userRepository.findByEmail(userDetails.getUsername());
        String reply = dialogService.handleMessage(request.getMessage(), user.get().getId(), Sender.USER);
        return new MessageResponseDTO(reply);
    }
}