CREATE TABLE accounts (
    account_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    account_number VARCHAR(20) NOT NULL UNIQUE,

    account_type VARCHAR(10) NOT NULL
        CHECK (account_type IN ('SAVINGS', 'CURRENT', 'NRE', 'NRO')),

    ownership_type VARCHAR(10) NOT NULL
        CHECK (ownership_type IN ('INDIVIDUAL', 'JOINT')),

    balance NUMERIC(19,2) NOT NULL DEFAULT 0.00
        CHECK (balance >= 0),

    status VARCHAR(10) NOT NULL DEFAULT 'ACTIVE'
        CHECK (status IN ('ACTIVE', 'FROZEN', 'CLOSED')),

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT accounts_nri_ownership_check
        CHECK (
            account_type NOT IN ('NRE', 'NRO')
            OR ownership_type = 'INDIVIDUAL'
        )
);