package dev.commonlib.event;

import lombok.Data;

@Data
public class EmailNotification {
    private String email;
    private String massage;
    private String subject;
}
