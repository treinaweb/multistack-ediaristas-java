ALTER TABLE `usuario`
  ADD `chave_pix` varchar(255) DEFAULT NULL,
  ADD `cpf` varchar(11) DEFAULT NULL,
  ADD `nascimento` date DEFAULT NULL,
  ADD `reputacao` double DEFAULT NULL,
  ADD `telefone` varchar(11) DEFAULT NULL,
  ADD `foto_documento` bigint DEFAULT NULL,
  ADD `foto_usuario` bigint DEFAULT NULL,
  ADD UNIQUE KEY (`cpf`),
  ADD UNIQUE KEY (`chave_pix`),
  ADD FOREIGN KEY (`foto_usuario`) REFERENCES `foto` (`id`),
  ADD FOREIGN KEY (`foto_documento`) REFERENCES `foto` (`id`);