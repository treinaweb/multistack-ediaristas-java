CREATE SEQUENCE pagamento_seq;

CREATE TABLE pagamento (
  id bigint NOT NULL DEFAULT NEXTVAL ('pagamento_seq'),
  created_at timestamp(6) NOT NULL,
  updated_at timestamp(6) NOT NULL,
  status varchar(20) NOT NULL,
  transacao_id varchar(255) NOT NULL,
  valor decimal(19,2) NOT NULL,
  diaria_id bigint NOT NULL,
  PRIMARY KEY (id)
  CREATE INDEX (diaria_id),
  CONSTRAINT FOREIGN CREATE INDEX (diaria_id) REFERENCES `diaria` (id)
);