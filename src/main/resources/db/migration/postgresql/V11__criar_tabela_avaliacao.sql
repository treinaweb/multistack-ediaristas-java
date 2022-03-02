CREATE SEQUENCE avaliacao_seq;

CREATE TABLE avaliacao (
  id bigint NOT NULL DEFAULT NEXTVAL ('avaliacao_seq'),
  descricao varchar(255) NOT NULL,
  nota double precision NOT NULL,
  visibilidade boolean(1) NOT NULL,
  avaliado_id bigint NOT NULL,
  avaliador_id bigint DEFAULT NULL,
  diaria_id bigint NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (avaliado_id) REFERENCES usuario (id),
  FOREIGN KEY (diaria_id) REFERENCES diaria (id),
  FOREIGN KEY (avaliador_id) REFERENCES usuario (id)
);