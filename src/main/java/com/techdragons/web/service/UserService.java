package com.techdragons.web.service;
import com.techdragons.web.entity.User;
import com.techdragons.web.exception.NotFoundException;
import com.techdragons.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            return userRepository.findByEmail(email).get();
        } else throw new NotFoundException("Не могу найти пользователя");
    }

    public List<User> findByName(String string) {
        List<User> i = new ArrayList<>();
        i.addAll(userRepository.findByFirstNameContains(string));
        i.addAll(userRepository.findByLastNameContains(string));
        return i;
    }

    public void registerUser(User user){
        userRepository.save(user);
    }
}
