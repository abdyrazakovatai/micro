package dev.userService.service;

import dev.userService.model.User;
import org.springframework.stereotype.Service;
import dev.commonlib.dto.AuthRequest;

@Service
public interface UserService {
    User register(AuthRequest user);

    String signIn(AuthRequest request);
}
