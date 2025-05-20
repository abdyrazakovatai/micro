package dev.userService.producer;

import dev.userService.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserProducer {
    private final KafkaTemplate<String, User> kafkaTemplate;

    public void send(User user) {
        kafkaTemplate.send("user-topic", user);
        System.out.println("📤 Отправлено в Kafka: " + user);
    }
}
