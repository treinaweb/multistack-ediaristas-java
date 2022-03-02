CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `nome_completo` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `tipo_usuario` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`email`)
);