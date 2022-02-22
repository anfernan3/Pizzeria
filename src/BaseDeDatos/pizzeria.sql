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

-- Volcando estructura para tabla pizzeria.comentario
CREATE TABLE IF NOT EXISTS `comentario` (
  `id_comentario` int NOT NULL AUTO_INCREMENT,
  `puntuacion` int NOT NULL,
  `fecha` timestamp NOT NULL,
  `usuario` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pizza` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_comentario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.comentario: ~0 rows (aproximadamente)
DELETE FROM `comentario`;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.funcion
CREATE TABLE IF NOT EXISTS `funcion` (
  `id_funcion` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rol` enum('usuario','tienda','repartidor','gerente') COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_funcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.funcion: ~0 rows (aproximadamente)
DELETE FROM `funcion`;
/*!40000 ALTER TABLE `funcion` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcion` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.ingrediente
CREATE TABLE IF NOT EXISTS `ingrediente` (
  `id_ingrediente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tipo` enum('base','salsa','otros') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`id_ingrediente`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.ingrediente: ~0 rows (aproximadamente)
DELETE FROM `ingrediente`;
/*!40000 ALTER TABLE `ingrediente` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingrediente` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.ingredientes por pizza
CREATE TABLE IF NOT EXISTS `ingredientes por pizza` (
  `id_ingredientes_por_pizza` int NOT NULL AUTO_INCREMENT,
  `pizza` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ingrediente` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cantidad` int NOT NULL,
  PRIMARY KEY (`id_ingredientes_por_pizza`),
  CONSTRAINT `FK_ingredientes por pizza_ingrediente` FOREIGN KEY (`id_ingredientes_por_pizza`) REFERENCES `ingrediente` (`id_ingrediente`),
  CONSTRAINT `FK_ingredientes por pizza_pizza` FOREIGN KEY (`id_ingredientes_por_pizza`) REFERENCES `pizza` (`id_pizza`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.ingredientes por pizza: ~0 rows (aproximadamente)
DELETE FROM `ingredientes por pizza`;
/*!40000 ALTER TABLE `ingredientes por pizza` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingredientes por pizza` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.pedido
CREATE TABLE IF NOT EXISTS `pedido` (
  `id_pedido` int NOT NULL AUTO_INCREMENT,
  `numero_pedido` int NOT NULL,
  `usuario` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha` datetime NOT NULL,
  `direccion_entrega` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `preparado_por` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha_entrega` datetime NOT NULL,
  `entregado_por` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `importe` double NOT NULL,
  `estado` enum('solicitado','elaborandose','preparado','enviado','recibido','cancelado') COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_pedido`),
  UNIQUE KEY `numero_pedido` (`numero_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.pedido: ~0 rows (aproximadamente)
DELETE FROM `pedido`;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.pizza
CREATE TABLE IF NOT EXISTS `pizza` (
  `id_pizza` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descripcion` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `foto` binary(50) DEFAULT NULL,
  `base` enum('fina','gruesa','rellena') COLLATE utf8mb4_unicode_ci NOT NULL,
  `salsa` enum('queso','burguer','barbacoa') COLLATE utf8mb4_unicode_ci NOT NULL,
  `precio` double NOT NULL,
  `gusta` enum('Si','No') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Si',
  PRIMARY KEY (`id_pizza`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.pizza: ~1 rows (aproximadamente)
DELETE FROM `pizza`;
/*!40000 ALTER TABLE `pizza` DISABLE KEYS */;
INSERT INTO `pizza` (`id_pizza`, `nombre`, `descripcion`, `foto`, `base`, `salsa`, `precio`, `gusta`) VALUES
	(6, 'Barbacoa', 'pizza barbacoa', NULL, 'fina', 'barbacoa', 5.7, 'No');
/*!40000 ALTER TABLE `pizza` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.pizzas por pedido
CREATE TABLE IF NOT EXISTS `pizzas por pedido` (
  `id_pizza_por_pedido` int NOT NULL AUTO_INCREMENT,
  `pedido` int NOT NULL,
  `pizza` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cantidad` int NOT NULL,
  `precio` int NOT NULL,
  PRIMARY KEY (`id_pizza_por_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.pizzas por pedido: ~0 rows (aproximadamente)
DELETE FROM `pizzas por pedido`;
/*!40000 ALTER TABLE `pizzas por pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pizzas por pedido` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `primer_apellido` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `segundo_apellido` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `direccion` varchar(350) COLLATE utf8mb4_unicode_ci NOT NULL,
  `contraseña` enum('texto','sha1','md5') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_usuario`),
  CONSTRAINT `FK_usuario_comentario` FOREIGN KEY (`id_usuario`) REFERENCES `comentario` (`id_comentario`),
  CONSTRAINT `FK_usuario_funcion` FOREIGN KEY (`id_usuario`) REFERENCES `funcion` (`id_funcion`),
  CONSTRAINT `FK_usuario_pedido` FOREIGN KEY (`id_usuario`) REFERENCES `pedido` (`id_pedido`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.usuario: ~0 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
