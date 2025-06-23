package dev.userService.api;

import dev.commonlib.dto.UserEvent;
import dev.userService.model.User;
import dev.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.commonlib.dto.AuthRequest;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApi {
    final KafkaTemplate<String, UserEvent> kafkaTemplate;
    final UserService userService;

    @PostMapping("/sign-up")
    public String registerUser(@RequestBody AuthRequest user) {

        User registeredUser = userService.register(user);

        UserEvent event = new UserEvent();
        event.setUserId(registeredUser.getId());
        event.setEmail(registeredUser.getEmail());

        kafkaTemplate.send("user-topic", event);
        return "Пользователь отправлен в Kafka!";
    }

    @PostMapping("/sign-in")
    public String loginUser(@RequestBody AuthRequest request) {
        return userService.signIn(request);
    }
}
