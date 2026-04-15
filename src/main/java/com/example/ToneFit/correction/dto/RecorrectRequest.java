package com.example.ToneFit.correction.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record RecorrectRequest(
        @NotEmpty List<RejectItem> rejects
) {
    public record RejectItem(int index) {
    }
}
