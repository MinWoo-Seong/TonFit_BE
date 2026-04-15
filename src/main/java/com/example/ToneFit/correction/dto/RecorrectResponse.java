package com.example.ToneFit.correction.dto;

import java.time.LocalDateTime;
import java.util.List;

public record RecorrectResponse(
        Long sessionId,
        int round,
        int remainingRecorrections,
        List<ChangeItem> changes,
        LocalDateTime createdAt
) {
    public record ChangeItem(
            int sentenceIndex,
            int start,
            int end,
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
