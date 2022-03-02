CREATE SEQUENCE foto_seq;

CREATE TABLE foto (
  id bigint NOT NULL DEFAULT NEXTVAL ('foto_seq'),
  content_length bigint NOT NULL,
  content_type varchar(255) NOT NULL,
  filename varchar(255) NOT NULL,
  url varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (filename)
);