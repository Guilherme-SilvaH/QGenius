package com.qgenius.qgenius_backend.infrastructure.rest.response;

import com.qgenius.qgenius_backend.core.domain.valueobject.Plan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private Plan plan;
    private LocalDateTime createdAt;
}
