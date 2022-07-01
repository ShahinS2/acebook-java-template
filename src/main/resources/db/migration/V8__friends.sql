DROP TABLE IF EXISTS friends;

CREATE TABLE friends (
  id bigserial PRIMARY KEY NOT NULL,
  first_user_id BIGINT NOT NULL,
  second_user_id BIGINT NOT NULL,
  created_date date
);