package com.qgenius.qgenius_backend.core.domain.entity;

import com.qgenius.qgenius_backend.core.domain.valueobject.Email;
import com.qgenius.qgenius_backend.core.domain.valueobject.Plan;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class User {
    private UUID id;
    private String name;
    private Email email;
    private String passwordHash;
    private Plan plan;
    private LocalDateTime createdAt;

    public boolean canGenerateQuestions(int requestedQuantity) {
        return plan.hasAvailableQuota(requestedQuantity);
    }

    public void upgradeToPremium() {
        this.plan = Plan.PREMIUM;
    }

}
