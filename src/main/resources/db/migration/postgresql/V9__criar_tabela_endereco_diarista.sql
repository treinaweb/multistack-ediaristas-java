CREATE SEQUENCE endereco_diarista_seq;

CREATE TABLE endereco_diarista (
  id bigint NOT NULL DEFAULT NEXTVAL ('endereco_diarista_seq'),
  bairro varchar(30) NOT NULL,
  cep varchar(8) NOT NULL,
  cidade varchar(30) NOT NULL,
  complemento varchar(255) DEFAULT NULL,
  estado varchar(2) NOT NULL,
  logradouro varchar(60) NOT NULL,
  numero varchar(10) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE usuario
  ADD endereco_id bigint DEFAULT NULL,
  ADD KEY (endereco_id),
  ADD CONSTRAINT FOREIGN KEY (endereco_id) REFERENCES `endereco_diarista` (id);