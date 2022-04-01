-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.28 - MySQL Community Server - GPL
-- SO del servidor:              Linux
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para usuarios_pizzeria
CREATE DATABASE IF NOT EXISTS `usuarios_pizzeria` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `usuarios_pizzeria`;

-- Volcando estructura para tabla usuarios_pizzeria.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `primer_apellido` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `segundo_apellido` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefono` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rol` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla usuarios_pizzeria.usuarios: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`username`, `nombre`, `primer_apellido`, `segundo_apellido`, `password`, `telefono`, `rol`) VALUES
	('admin1@admin1.com', 'admin', 'admin', 'admin', '$2a$10$H0C9Y7lkR.E2XVPMsI0Ej.jFfjL6zyhZKMAfmLnWUB119L9jCKAk.', '123456789', 'ROL_GERENTE'),
	('antonio@antonio.com', 'Antonio', 'Gonzalez', 'Marquez', '$2a$10$H0C9Y7lkR.E2XVPMsI0Ej.jFfjL6zyhZKMAfmLnWUB119L9jCKAk.', '685987123', 'ROL_USUARIO'),
	('antonio2@antonio2.com', 'Antonio', 'Perez', 'López', '$2a$10$H0C9Y7lkR.E2XVPMsI0Ej.jFfjL6zyhZKMAfmLnWUB119L9jCKAk.', '987654321', 'ROL_TIENDA'),
	('javier@javier.com', 'Javier', 'González', 'Avellaneda', '$2a$10$H0C9Y7lkR.E2XVPMsI0Ej.jFfjL6zyhZKMAfmLnWUB119L9jCKAk.', '987564231', 'ROL_USUARIO'),
	('patxi@patxi.com', 'Patxi', 'López', 'García', '$2a$10$H0C9Y7lkR.E2XVPMsI0Ej.jFfjL6zyhZKMAfmLnWUB119L9jCKAk.', '987456321', 'ROL_USUARIO'),
	('pepe@pep.com', 'Pepe', 'Narváez', 'Covadonga', '$2a$10$H0C9Y7lkR.E2XVPMsI0Ej.jFfjL6zyhZKMAfmLnWUB119L9jCKAk.', '345123789', 'ROL_REPARTIDOR');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
