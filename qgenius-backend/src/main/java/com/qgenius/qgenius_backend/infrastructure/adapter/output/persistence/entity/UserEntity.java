package com.qgenius.qgenius_backend.infrastructure.adapter.output.persistence.entity;


import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

@Builder()
@Getter
@Setter
@AllArgsConstructor
public class UserEntity {
    private String id;
    private String name;
    private String email;
    private String passwordHash;
    private String plan;
    @CurrentTimestamp
    private String createdAt;
}
