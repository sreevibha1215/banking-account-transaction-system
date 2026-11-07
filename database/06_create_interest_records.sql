
CREATE TABLE interest_records (
    interest_record_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

    account_id BIGINT NOT NULL
        REFERENCES accounts(account_id),

    interest_month DATE NOT NULL
        CHECK (
            EXTRACT(DAY FROM interest_month) = 1
        ),

    eligible_balance NUMERIC(19,2) NOT NULL
        CHECK (eligible_balance >= 0),

    annual_rate NUMERIC(5,2) NOT NULL
        CHECK (annual_rate >= 0 AND annual_rate <= 100),

    interest_amount NUMERIC(19,2) NOT NULL
        CHECK (interest_amount >= 0),

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT interest_records_account_month_unique
        UNIQUE (account_id, interest_month)
);
