package com.qgenius.qgenius_backend.core.domain.valueobject;


import lombok.AllArgsConstructor;
import lombok.Getter;



@Getter
@AllArgsConstructor
public enum Plan {
    FREE(5, 50),
    PREMIUM(100, 1000);

    private final int dailyLimit;
    private final int monthlyLimit;

    public boolean hasAvailableQuota(int requestedQuantity) {
        return requestedQuantity <= dailyLimit;
    }

}
