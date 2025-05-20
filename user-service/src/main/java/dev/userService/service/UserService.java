package dev.userService.service;

import dev.userService.model.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    void add(User user);
}
