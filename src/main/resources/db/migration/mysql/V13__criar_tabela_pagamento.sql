CREATE TABLE `pagamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `status` varchar(20) NOT NULL,
  `transacao_id` varchar(255) NOT NULL,
  `valor` decimal(19,2) NOT NULL,
  `diaria_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY (`diaria_id`),
  CONSTRAINT FOREIGN KEY (`diaria_id`) REFERENCES `diaria` (`id`)
);