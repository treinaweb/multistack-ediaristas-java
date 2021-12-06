CREATE TABLE `cidade_atendida` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cidade` varchar(255) NOT NULL,
  `codigo_ibge` varchar(255) NOT NULL,
  `estado` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `cidades_atendidas_usuarios` (
  `cidade_atendida_id` bigint NOT NULL,
  `usuario_id` bigint NOT NULL,
  KEY (`usuario_id`),
  KEY (`cidade_atendida_id`),
  CONSTRAINT FOREIGN KEY (`cidade_atendida_id`) REFERENCES `cidade_atendida` (`id`),
  CONSTRAINT FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
);