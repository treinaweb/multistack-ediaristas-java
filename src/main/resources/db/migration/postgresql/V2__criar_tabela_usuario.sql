CREATE SEQUENCE usuario_seq;

CREATE TABLE usuario (
  id bigint NOT NULL DEFAULT NEXTVAL ('usuario_seq'),
  email varchar(255) NOT NULL,
  nome_completo varchar(255) NOT NULL,
  senha varchar(255) NOT NULL,
  tipo_usuario varchar(8) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (email)
);