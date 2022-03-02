CREATE SEQUENCE token_black_list_seq;

CREATE TABLE token_black_list (
  id bigint NOT NULL DEFAULT NEXTVAL ('token_black_list_seq'),
  token varchar(255) NOT NULL,
  PRIMARY KEY (id)
);