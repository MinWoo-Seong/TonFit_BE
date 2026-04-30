CREATE TABLE users (
    id    BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE prompt_version (
    id         BIGSERIAL PRIMARY KEY,
    purpose    VARCHAR(64)  NOT NULL,
    version    VARCHAR(64)  NOT NULL,
    content    TEXT         NOT NULL,
    is_active  BOOLEAN      NOT NULL,
    created_at TIMESTAMP,
    CONSTRAINT uk_prompt_version_purpose_version UNIQUE (purpose, version)
);

CREATE TABLE correction_session (
    id                    BIGSERIAL PRIMARY KEY,
    user_id               BIGINT       NOT NULL,
    initial_prompt_ver_id BIGINT,
    final_prompt_ver_id   BIGINT,
    receiver_type         VARCHAR(64),
    purpose               VARCHAR(64),
    subject               VARCHAR(255),
    protected_ranges      JSONB,
    status                VARCHAR(32)  NOT NULL,
    original              TEXT,
    ai_final              TEXT,
    user_final            TEXT,
    ai_subject            VARCHAR(255),
    user_subject          VARCHAR(255),
    created_at            TIMESTAMP,
    updated_at            TIMESTAMP,
    CONSTRAINT fk_correction_session_user
        FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_correction_session_initial_prompt
        FOREIGN KEY (initial_prompt_ver_id) REFERENCES prompt_version (id),
    CONSTRAINT fk_correction_session_final_prompt
        FOREIGN KEY (final_prompt_ver_id) REFERENCES prompt_version (id)
);

CREATE INDEX idx_correction_session_user ON correction_session (user_id);
CREATE INDEX idx_correction_session_status ON correction_session (status);

CREATE TABLE correction_feedback (
    id               BIGSERIAL PRIMARY KEY,
    user_id          BIGINT            NOT NULL,
    session_id       BIGINT            NOT NULL,
    "index"          INT               NOT NULL,
    start            INT               NOT NULL,
    "end"            INT               NOT NULL,
    original         TEXT              NOT NULL,
    corrected        TEXT              NOT NULL,
    reason           TEXT              NOT NULL,
    label            VARCHAR(32)       NOT NULL,
    confidence       DOUBLE PRECISION  NOT NULL,
    applied_rules    JSONB,
    action           VARCHAR(32),
    reason_primary   VARCHAR(64),
    reason_secondary VARCHAR(64),
    reason_text      VARCHAR(255),
    created_at       TIMESTAMP,
    updated_at       TIMESTAMP,
    CONSTRAINT fk_correction_feedback_user
        FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_correction_feedback_session
        FOREIGN KEY (session_id) REFERENCES correction_session (id)
);

CREATE INDEX idx_correction_feedback_session ON correction_feedback (session_id);
CREATE INDEX idx_correction_feedback_user ON correction_feedback (user_id);
