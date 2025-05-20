package dev.userService.consumer;

import dev.userService.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {
    @KafkaListener(
            topics = "user-topic",
            groupId = "email-service",
            containerFactory = "userKafkaListenerFactory")
    public void consume(User user) {
        System.out.println("📥 Получено из Kafka: " + user);
        System.out.println("✉️ Отправка письма на: " + user.getEmail());
    }
}
