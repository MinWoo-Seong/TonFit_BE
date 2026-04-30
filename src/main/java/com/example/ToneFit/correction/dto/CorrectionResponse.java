package com.example.ToneFit.correction.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CorrectionResponse(
        Long sessionId,
        int round,
        String correctedEmail,
        List<ChangeItem> changes,
        LocalDateTime createdAt
) {
    public record ChangeItem(
            int index,
            int start,
            int end,
            String original,
            String corrected,
            String reason,
            String label,
            String action
    ) {
    }
}
