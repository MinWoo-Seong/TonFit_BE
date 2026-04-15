package com.example.ToneFit.correction.model;

import com.example.ToneFit.session.model.CorrectionSession;
import com.example.ToneFit.user.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "correction_feedback")
public class CorrectionFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private CorrectionSession session;

    @Column(name = "\"index\"", nullable = false)
    private int index;

    @Column(nullable = false)
    private int round;

    @Column(nullable = false)
    private int start;

    @Column(name = "\"end\"", nullable = false)
    private int end;

    @Column(columnDefinition = "text", nullable = false)
    private String original;

    @Column(columnDefinition = "text", nullable = false)
    private String corrected;

    @Column(columnDefinition = "text", nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Label label;

    @Enumerated(EnumType.STRING)
    private Action action;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public CorrectionFeedback(User user, CorrectionSession session, int index, int round,
                              int start, int end, String original, String corrected,
                              String reason, Label label) {
        this.user = user;
        this.session = session;
        this.index = index;
        this.round = round;
        this.start = start;
        this.end = end;
        this.original = original;
        this.corrected = corrected;
        this.reason = reason;
        this.label = label;
    }

    public void updateAction(Action action) {
        this.action = action;
    }
}
