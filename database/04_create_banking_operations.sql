
CREATE TABLE banking_operations (
    operation_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    request_id VARCHAR(100) NOT NULL UNIQUE,

    operation_type VARCHAR(20) NOT NULL
        CHECK (operation_type IN ('DEPOSIT', 'WITHDRAWAL', 'TRANSFER')),

    status VARCHAR(20) NOT NULL DEFAULT 'PENDING'
        CHECK (status IN ('PENDING', 'SUCCESS', 'FAILED')),

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    completed_at TIMESTAMPTZ
);
