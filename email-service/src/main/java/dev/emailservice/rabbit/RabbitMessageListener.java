package dev.emailservice.rabbit;

import dev.emailservice.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessageListener {

    @RabbitListener(queues = RabbitConfig.EMAIL_QUEUE)
    public void handleMessage(String message) {
        System.out.println("📨 Получено сообщение в email-service: " + message);
    }
}
