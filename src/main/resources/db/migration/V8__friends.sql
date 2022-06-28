DROP TABLE IF EXISTS friends;

CREATE TABLE friends (
  id integer PRIMARY KEY,
  first_user_id BIGINT NOT NULL,
  second_user_id BIGINT NOT NULL,
  firstUser varchar(50) NOT NULL UNIQUE,
  secondUser varchar(50) NOT NULL UNIQUE,
  created_date date
);