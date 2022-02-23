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

-- Volcando estructura para tabla pizzeria.comentarios
CREATE TABLE IF NOT EXISTS `comentarios` (
  `id_comentario` int NOT NULL AUTO_INCREMENT,
  `puntuacion` int NOT NULL,
  `fecha` timestamp NOT NULL,
  `usuario` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pizza` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_comentario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.comentarios: ~0 rows (aproximadamente)
DELETE FROM `comentarios`;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.direcciones
CREATE TABLE IF NOT EXISTS `direcciones` (
  `id_direccion` int NOT NULL AUTO_INCREMENT,
  `calle` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ciudad` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pais` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_usuario` int DEFAULT NULL,
  PRIMARY KEY (`id_direccion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.direcciones: ~0 rows (aproximadamente)
DELETE FROM `direcciones`;
/*!40000 ALTER TABLE `direcciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `direcciones` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.funciones
CREATE TABLE IF NOT EXISTS `funciones` (
  `id_funcion` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rol` enum('usuario','tienda','repartidor','gerente') COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_funcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.funciones: ~0 rows (aproximadamente)
DELETE FROM `funciones`;
/*!40000 ALTER TABLE `funciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `funciones` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.ingredientes
CREATE TABLE IF NOT EXISTS `ingredientes` (
  `id_ingrediente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tipo` enum('base','salsa','otros') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`id_ingrediente`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.ingredientes: ~0 rows (aproximadamente)
DELETE FROM `ingredientes`;
/*!40000 ALTER TABLE `ingredientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingredientes` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.ingredientes por pizza
CREATE TABLE IF NOT EXISTS `ingredientes por pizza` (
  `id_ingredientes_por_pizza` int NOT NULL AUTO_INCREMENT,
  `pizza` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ingrediente` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cantidad` int NOT NULL,
  PRIMARY KEY (`id_ingredientes_por_pizza`),
  CONSTRAINT `FK_ingredientes por pizza_ingrediente` FOREIGN KEY (`id_ingredientes_por_pizza`) REFERENCES `ingredientes` (`id_ingrediente`),
  CONSTRAINT `FK_ingredientes por pizza_pizza` FOREIGN KEY (`id_ingredientes_por_pizza`) REFERENCES `pizzas` (`id_pizza`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.ingredientes por pizza: ~0 rows (aproximadamente)
DELETE FROM `ingredientes por pizza`;
/*!40000 ALTER TABLE `ingredientes por pizza` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingredientes por pizza` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.pedidos
CREATE TABLE IF NOT EXISTS `pedidos` (
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

-- Volcando datos para la tabla pizzeria.pedidos: ~0 rows (aproximadamente)
DELETE FROM `pedidos`;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.pizzas
CREATE TABLE IF NOT EXISTS `pizzas` (
  `id_pizza` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `descripcion` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `foto` binary(50) DEFAULT NULL,
  `base` enum('fina','gruesa','rellena') COLLATE utf8mb4_unicode_ci NOT NULL,
  `salsa` enum('queso','burguer','barbacoa') COLLATE utf8mb4_unicode_ci NOT NULL,
  `precio` double NOT NULL,
  `gusta` enum('Si','No') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Si',
  PRIMARY KEY (`id_pizza`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.pizzas: ~0 rows (aproximadamente)
DELETE FROM `pizzas`;
/*!40000 ALTER TABLE `pizzas` DISABLE KEYS */;
INSERT INTO `pizzas` (`id_pizza`, `nombre`, `descripcion`, `foto`, `base`, `salsa`, `precio`, `gusta`) VALUES
	(1, 'Barbacoa', 'pizza barbacoa', NULL, 'fina', 'barbacoa', 5.7, 'No'),
	(7, 'Barbacoa', 'pizza barbacoa', NULL, 'gruesa', 'barbacoa', 5.9, 'Si');
/*!40000 ALTER TABLE `pizzas` ENABLE KEYS */;

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

-- Volcando estructura para tabla pizzeria.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `primer_apellido` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `segundo_apellido` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_direccion` int DEFAULT NULL,
  `contraseña` enum('texto','sha1','md5') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `FK_usuario_direccion` (`id_direccion`),
  CONSTRAINT `FK_usuario_direccion` FOREIGN KEY (`id_direccion`) REFERENCES `direcciones` (`id_direccion`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.usuarios: ~0 rows (aproximadamente)
DELETE FROM `usuarios`;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `primer_apellido`, `segundo_apellido`, `id_direccion`, `contraseña`) VALUES
	(1, 'admin', NULL, NULL, NULL, 'texto');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
