package dev.userService.producer;

import dev.commonlib.dto.UserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserProducer {
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public void send(UserEvent user) {
        kafkaTemplate.send("user-topic", user);
        System.out.println("📤 Отправлено в Kafka: " + user);
    }
}
