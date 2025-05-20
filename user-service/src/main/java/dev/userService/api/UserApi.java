package dev.userService.api;

import dev.userService.model.User;
import dev.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApi {
    final KafkaTemplate<String, User> kafkaTemplate;
    final UserService userService;

    @PostMapping("/add")
    public String registerUser(@RequestBody User user) {
        userService.add(user);
        kafkaTemplate.send("user-topic", user);
        return "Пользователь отправлен в Kafka!";
    }
}
