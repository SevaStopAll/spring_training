--changeset sevastopall:1
ALTER TABLE users
ADD COLUMN password VARCHAR(128) DEFAULT '{noop}123';

--changeset sevastopall:2
ALTER TABLE users_aud
ADD COLUMN password VARCHAR(128);