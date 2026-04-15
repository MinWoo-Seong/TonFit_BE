package com.example.ToneFit.correction.dto;

import java.time.LocalDateTime;

public record SessionSummary(
        Long sessionId,
        String receiverType,
        String purpose,
        String subject,
        String status,
        String originalPreview,
        LocalDateTime createdAt
) {
}
