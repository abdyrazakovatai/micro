package dev.userService.service.Impl;

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
    public void add(User user) {
        userRepository.save(user);
        System.out.println("user saved");
    }
}
