--changeset sevastopall:1
ALTER TABLE users_aud
DROP CONSTRAINT users_aud_username_key;

--changeset sevastopall:2
ALTER TABLE users_aud
ALTER COLUMN username DROP NOT NULL;