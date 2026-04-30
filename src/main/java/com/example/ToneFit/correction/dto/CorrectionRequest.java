package com.example.ToneFit.correction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CorrectionRequest(
        @NotNull String receiverType,
        @NotNull String purpose,
        String subject,
        @NotBlank String originalEmail,
        String context,
        List<ProtectedRange> protectedRanges
) {
    public record ProtectedRange(int start, int end) {
    }
}
