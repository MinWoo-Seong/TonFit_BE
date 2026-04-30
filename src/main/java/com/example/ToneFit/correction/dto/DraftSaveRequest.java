package com.example.ToneFit.correction.dto;

public record DraftSaveRequest(
        String receiverType,
        String purpose,
        String subject,
        String originalEmail,
        String context
) {
}
