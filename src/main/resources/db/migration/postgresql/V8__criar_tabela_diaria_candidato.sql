CREATE TABLE diaria_canditado (
  diaria_id bigint NOT NULL,
  candidato_id bigint NOT NULL
  CREATE INDEX (candidato_id)
  CREATE INDEX (diaria_id),
  CONSTRAINT FOREIGN CREATE INDEX (candidato_id) REFERENCES `usuario` (id),
  CONSTRAINT FOREIGN KEY (diaria_id) REFERENCES `diaria` (id)
);