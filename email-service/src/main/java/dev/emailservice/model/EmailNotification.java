package dev.emailservice.model;

import lombok.Data;

@Data
public class EmailNotification {
    private String email;
    private String massage;
    private String subject;
}
