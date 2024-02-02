
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE IF NOT EXISTS wallets
(
    wallet_id        UUID    DEFAULT uuid_generate_v4()  PRIMARY KEY,
    amount           bigint  NOT NULL check(amount>=0)
);
