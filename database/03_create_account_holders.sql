CREATE TABLE account_holders (
    account_holder_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    account_id BIGINT NOT NULL
        REFERENCES accounts(account_id),

    customer_id BIGINT NOT NULL
        REFERENCES customers(customer_id),

    holder_role VARCHAR(10) NOT NULL
        CHECK (holder_role IN ('PRIMARY', 'SECONDARY')),

    consent_given BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT account_holders_account_customer_unique
        UNIQUE (account_id, customer_id),

    CONSTRAINT account_holders_account_role_unique
        UNIQUE (account_id, holder_role)
);