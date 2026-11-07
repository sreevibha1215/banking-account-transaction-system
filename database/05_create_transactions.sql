
CREATE TABLE transactions (
    transaction_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    operation_id BIGINT NOT NULL
        REFERENCES banking_operations(operation_id),

    account_id BIGINT NOT NULL
        REFERENCES accounts(account_id),

    transaction_type VARCHAR(10) NOT NULL
        CHECK (transaction_type IN ('DEBIT', 'CREDIT')),

    amount NUMERIC(19,2) NOT NULL
        CHECK (amount > 0),

    balance_after NUMERIC(19,2) NOT NULL
        CHECK (balance_after >= 0),

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);
