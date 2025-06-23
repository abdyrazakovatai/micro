package dev.userService.service.Impl;

import dev.commonlib.dto.AuthRequest;
import dev.commonlib.excetption.AlreadyExists;
import dev.userService.model.User;
import dev.userService.repo.UserRepository;
import dev.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;

    @Override
    public User register(AuthRequest authRequest) {
        if (userRepository.existsUserByEmail(authRequest.getEmail())) {
            throw new AlreadyExists("User with email " + authRequest.getEmail() + " already exists");
        }

        User user = User.builder()
                .email(authRequest.getEmail())
                .password(authRequest.getPassword())
                .build();
        return userRepository.save(user);
    }

    @Override
    public String signIn(AuthRequest request) {
        User user = userRepository.getUsersByEmail(request.getEmail());
        if (user == null) {
            return "User not found";
        }
        if (!user.getPassword().equals(request.getPassword())) {
            return "Wrong Password";
        }
        return "Success";

    }
}
