package dev.commonlib.dto;

import lombok.Setter;

@Setter
public class UserEvent {
    private Long userId;
    private String email;
    private String password;
}
