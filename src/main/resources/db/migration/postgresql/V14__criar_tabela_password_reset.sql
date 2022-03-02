CREATE SEQUENCE password_reset_seq;

CREATE TABLE password_reset (
  id bigint NOT NULL DEFAULT NEXTVAL ('password_reset_seq'),
  created_at timestamp(6) NOT NULL,
  updated_at timestamp(6) NOT NULL,
  email varchar(255) NOT NULL,
  token varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (token)
);