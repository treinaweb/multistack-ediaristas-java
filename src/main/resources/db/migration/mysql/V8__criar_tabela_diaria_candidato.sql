CREATE TABLE `diaria_canditado` (
  `diaria_id` bigint NOT NULL,
  `candidato_id` bigint NOT NULL,
  KEY (`candidato_id`),
  KEY (`diaria_id`),
  CONSTRAINT FOREIGN KEY (`candidato_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT FOREIGN KEY (`diaria_id`) REFERENCES `diaria` (`id`)
);