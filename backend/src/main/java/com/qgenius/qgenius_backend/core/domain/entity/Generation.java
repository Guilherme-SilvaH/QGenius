package com.qgenius.qgenius_backend.core.domain.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Generation {
    private UUID id;
    private User user;
    private String theme;
    private String difficulty;
    private String type;
    private Integer quantity;
    private String status;
    private List<Questions> questionsList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}