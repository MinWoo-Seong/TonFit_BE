package com.example.ToneFit.correction.dto;

import jakarta.validation.constraints.NotBlank;

public record ConfirmRequest(
        @NotBlank String finalEmail
) {
}
