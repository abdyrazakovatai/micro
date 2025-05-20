package dev.emailservice.service;

import dev.emailservice.config.RabbitConfig;
import dev.emailservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaToRabbitService {
    final RabbitTemplate rabbitTemplate;
    final EmailSender emailSender;

    @KafkaListener(
            topics = "user-topic",
            groupId = "email-service-v2",
            containerFactory = "userKafkaListenerFactory"
    )
    public void consumeUser(User user) {
        System.out.println("📥 Получено из Kafka: " + user);
        rabbitTemplate.convertAndSend(RabbitConfig.EMAIL_QUEUE, "Пользователь: " + user.getName());
        System.out.println("📤 Отправлено в RabbitMQ");

        emailSender.sendEmail(
                user.getEmail(),
                "Hello " + user.getName(),
                "Welcome " + user.getName()
        );

    }
}