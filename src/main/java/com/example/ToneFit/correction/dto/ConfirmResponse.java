package com.example.ToneFit.correction.dto;

import java.time.LocalDateTime;

public record ConfirmResponse(
        Long sessionId,
        LocalDateTime copiedAt
) {
}
