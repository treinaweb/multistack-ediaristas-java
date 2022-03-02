CREATE TABLE `foto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content_length` bigint NOT NULL,
  `content_type` varchar(255) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`filename`)
);