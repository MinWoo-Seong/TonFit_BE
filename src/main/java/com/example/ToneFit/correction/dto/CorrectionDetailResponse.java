package com.example.ToneFit.correction.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CorrectionDetailResponse(
        Long sessionId,
        String receiverType,
        String purpose,
        String subject,
        String originalEmail,
        String context,
        String finalEmail,
        String status,
        int totalRounds,
        List<FeedbackItem> feedbacks,
        LocalDateTime createdAt,
        LocalDateTime copiedAt
) {
    public record FeedbackItem(
            int index,
            int start,
            int end,
            List<RoundDetail> rounds
    ) {
    }

    public record RoundDetail(
            int round,
            String original,
            String corrected,
            String reason,
            String label,
            double confidence,
            List<String> appliedRules,
            String action
    ) {
    }
}
