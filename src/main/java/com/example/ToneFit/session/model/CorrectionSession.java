package com.example.ToneFit.session.model;

import com.example.ToneFit.prompt.model.PromptVersion;
import com.example.ToneFit.user.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "correction_session")
public class CorrectionSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prompt_ver_id", nullable = false)
    private PromptVersion promptVersion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Receiver receiver;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Purpose purpose;

    private String subject;

    @Column(columnDefinition = "text")
    private String context;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<Range> protectedRanges;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(columnDefinition = "text", nullable = false)
    private String original;

    @Column(name = "final", columnDefinition = "text")
    private String finalText;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public CorrectionSession(User user, PromptVersion promptVersion, Receiver receiver,
                             Purpose purpose, String subject, String context,
                             List<Range> protectedRanges, Status status, String original) {
        this.user = user;
        this.promptVersion = promptVersion;
        this.receiver = receiver;
        this.purpose = purpose;
        this.subject = subject;
        this.context = context;
        this.protectedRanges = protectedRanges;
        this.status = status;
        this.original = original;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void updateFinalText(String finalText) {
        this.finalText = finalText;
    }
}
