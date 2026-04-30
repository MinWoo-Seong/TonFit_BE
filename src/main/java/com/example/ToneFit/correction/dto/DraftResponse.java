package com.example.ToneFit.correction.dto;

import java.time.LocalDateTime;

public record DraftResponse(
        Long sessionId,
        String receiverType,
        String purpose,
        String subject,
        String originalEmail,
        String context,
        String status,
        LocalDateTime updatedAt
) {
}
