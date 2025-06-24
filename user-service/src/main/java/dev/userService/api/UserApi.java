package dev.userService.api;

import dev.commonlib.dto.AuthRequest;
import dev.commonlib.event.EmailNotification;
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
    final KafkaTemplate<String, Object> kafkaTemplate;
    final UserService userService;

    @PostMapping("/sign-up")
    public String registerUser(@RequestBody AuthRequest user) {

        User registeredUser = userService.register(user);

        EmailNotification notification = new EmailNotification();
        notification.setEmail(registeredUser.getEmail());
        notification.setMassage("Добро пожаловать в наш сервис!\"");
        notification.setSubject("Регистрация прошла успешно!\"");

        kafkaTemplate.send("user-topic", notification);
        return "Пользователь отправлен в Kafka!";
    }

    @PostMapping("/sign-in")
    public String loginUser(@RequestBody AuthRequest request) {
        return userService.signIn(request);
    }
}
