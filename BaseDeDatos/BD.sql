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


-- Volcando estructura de base de datos para pizzeria
CREATE DATABASE IF NOT EXISTS `pizzeria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pizzeria`;

-- Volcando estructura para tabla pizzeria.comentarios
CREATE TABLE IF NOT EXISTS `comentarios` (
  `id_comentario` int NOT NULL AUTO_INCREMENT,
  `puntuacion` int NOT NULL,
  `fecha` timestamp NOT NULL,
  `id_usuario` int NOT NULL,
  `id_pizza` int NOT NULL,
  PRIMARY KEY (`id_comentario`),
  KEY `FK_comentarios_usuarios` (`id_usuario`),
  KEY `FK_comentarios_pizzas` (`id_pizza`),
  CONSTRAINT `FK_comentarios_pizzas` FOREIGN KEY (`id_pizza`) REFERENCES `pizzas` (`id_pizza`),
  CONSTRAINT `FK_comentarios_usuarios` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.comentarios: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.direcciones
CREATE TABLE IF NOT EXISTS `direcciones` (
  `id_direccion` int NOT NULL AUTO_INCREMENT,
  `calle` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ciudad` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pais` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_usuario` int NOT NULL,
  PRIMARY KEY (`id_direccion`),
  KEY `FK_direcciones_usuarios` (`id_usuario`),
  CONSTRAINT `FK_direcciones_usuarios` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.direcciones: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `direcciones` DISABLE KEYS */;
INSERT INTO `direcciones` (`id_direccion`, `calle`, `ciudad`, `pais`, `id_usuario`) VALUES
	(1, 'C/ La Panoplia', 'Murcia', 'España', 1),
	(2, 'C/ La Gioconda', 'Murcia', 'España', 2),
	(3, 'C/ El membrillo', 'Murcia', 'España', 1),
	(4, 'C/ Picasso', 'Murcia', 'España', 2);
/*!40000 ALTER TABLE `direcciones` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.funciones
CREATE TABLE IF NOT EXISTS `funciones` (
  `id_funcion` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `rol` enum('usuario','tienda','repartidor','gerente') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_funcion`),
  KEY `FK_funciones_usuarios` (`id_usuario`),
  CONSTRAINT `FK_funciones_usuarios` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.funciones: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `funciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `funciones` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.ingredientes
CREATE TABLE IF NOT EXISTS `ingredientes` (
  `id_ingrediente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tipo` enum('base','salsa','otros') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`id_ingrediente`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.ingredientes: ~18 rows (aproximadamente)
/*!40000 ALTER TABLE `ingredientes` DISABLE KEYS */;
INSERT INTO `ingredientes` (`id_ingrediente`, `nombre`, `tipo`, `precio`) VALUES
	(1, 'cebolla', 'otros', 0.3),
	(2, 'bacon', 'otros', 0.5),
	(3, 'fina', 'base', 0.5),
	(4, 'barbacoa', 'salsa', 0.5),
	(5, 'gruesa', 'base', 0.7),
	(6, 'ultra fina', 'base', 0.4),
	(7, 'queso', 'salsa', 0.5),
	(8, 'nata', 'salsa', 0.5),
	(9, 'jamón york', 'otros', 1),
	(10, 'bacon', 'otros', 1),
	(11, 'jamón', 'otros', 1),
	(12, 'queso', 'otros', 1),
	(13, 'champiñones', 'otros', 1),
	(14, 'aceitunas', 'otros', 0.7),
	(15, 'boloñesa', 'salsa', 0.6),
	(16, 'queso azul', 'salsa', 0.8),
	(17, 'yogur', 'salsa', 0.4),
	(18, 'pesto', 'salsa', 0.6);
/*!40000 ALTER TABLE `ingredientes` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.ingredientes por pizza
CREATE TABLE IF NOT EXISTS `ingredientes por pizza` (
  `id_ingredientes_por_pizza` int NOT NULL AUTO_INCREMENT,
  `id_pizza` int NOT NULL,
  `id_ingrediente` int NOT NULL,
  `cantidad` int NOT NULL,
  PRIMARY KEY (`id_ingredientes_por_pizza`),
  KEY `FK_ingredientes por pizza_pizzas` (`id_pizza`),
  KEY `FK_ingredientes por pizza_ingredientes` (`id_ingrediente`),
  CONSTRAINT `FK_ingredientes por pizza_ingredientes` FOREIGN KEY (`id_ingrediente`) REFERENCES `ingredientes` (`id_ingrediente`),
  CONSTRAINT `FK_ingredientes por pizza_pizzas` FOREIGN KEY (`id_pizza`) REFERENCES `pizzas` (`id_pizza`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.ingredientes por pizza: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ingredientes por pizza` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingredientes por pizza` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.pedidos
CREATE TABLE IF NOT EXISTS `pedidos` (
  `id_pedido` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fecha` datetime NOT NULL,
  `direccion_entrega` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `preparado_por` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fecha_entrega` datetime DEFAULT NULL,
  `entregado_por` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `importe` decimal(9,2) NOT NULL DEFAULT '0.00',
  `estado` enum('solicitado','elaborandose','preparado','enviado','recibido','cancelado') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_pedido`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.pedidos: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` (`id_pedido`, `usuario`, `fecha`, `direccion_entrega`, `preparado_por`, `fecha_entrega`, `entregado_por`, `importe`, `estado`) VALUES
	(1, 'Juan', '2022-03-28 16:20:48', 'C/ Churruca', NULL, NULL, NULL, 99.00, 'recibido'),
	(2, 'Antonio', '2022-03-28 16:20:48', 'C/ Churruca', NULL, NULL, NULL, 44.00, 'cancelado'),
	(3, 'Federico', '2022-03-28 16:20:48', 'C/ Churruca', NULL, NULL, NULL, 50.00, 'preparado'),
	(4, 'Roberto', '2022-03-28 16:20:48', 'C/ Churruca', NULL, NULL, NULL, 70.00, 'solicitado');
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.pizzas
CREATE TABLE IF NOT EXISTS `pizzas` (
  `id_pizza` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `descripcion` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `foto_url` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `base` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `salsa` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `precio` double NOT NULL,
  `gusta` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_pizza`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.pizzas: ~8 rows (aproximadamente)
/*!40000 ALTER TABLE `pizzas` DISABLE KEYS */;
INSERT INTO `pizzas` (`id_pizza`, `nombre`, `descripcion`, `foto_url`, `base`, `salsa`, `precio`, `gusta`) VALUES
	(1, 'Margarita', 'Pizza a la piedra de tomate y queso', NULL, 'fina', 'tomate', 5.8, 20),
	(2, 'Caprichosa', 'Pizza a la piedra con tomates cherries, pimiento, bacón, y queso', NULL, 'fina', 'tomate', 6.2, 50),
	(3, 'Barbacoa', 'Pizza la piedra de barbacoa a la piedra con pollo, ternera, maíz y queso', NULL, 'ultra fina', 'barbacoa', 7.2, 100),
	(4, 'Jamón y Queso', 'Pizza Pizza la piedra de  Jamón y Queso', NULL, 'fina', 'tomate', 6.8, 80),
	(5, 'Vegetariana', 'Pizza Pizza la piedra con rucula, tomate, tofu, queso', NULL, 'gorda', 'tomate', 6.5, 15),
	(6, 'Cuatro Quesos', 'Pizza la piedra con queso azul, elemental, mozarella, de cabra', NULL, 'ultra fina', 'queso', 6.8, 40),
	(7, 'Carbonara', 'Pizza a la piedra con bacon, champiñones, pollo y queso', NULL, 'fina', 'nata', 7, 85),
	(8, 'Peperoni', 'Pizza a la piedra de peperoni, pimiento y queso', NULL, 'gorda', 'tomate', 7, 70);
/*!40000 ALTER TABLE `pizzas` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.pizzas por pedido
CREATE TABLE IF NOT EXISTS `pizzas por pedido` (
  `id_pedido` int NOT NULL,
  `id_pizza` int NOT NULL,
  `cantidad` int NOT NULL,
  `precio` decimal(9,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_pedido`,`id_pizza`),
  KEY `id_pedido` (`id_pedido`),
  KEY `id_pizza` (`id_pizza`),
  CONSTRAINT `FK_pizzas por pedido_pedidos` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`),
  CONSTRAINT `FK_pizzas por pedido_pizzas` FOREIGN KEY (`id_pizza`) REFERENCES `pizzas` (`id_pizza`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.pizzas por pedido: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `pizzas por pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pizzas por pedido` ENABLE KEYS */;

-- Volcando estructura para tabla pizzeria.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `primer_apellido` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `segundo_apellido` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefono` int DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.usuarios: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`id_usuario`, `nombre`, `primer_apellido`, `segundo_apellido`, `password`, `telefono`, `email`) VALUES
	(1, 'admin', NULL, NULL, 'texto', NULL, ''),
	(2, 'pepe', 'García', 'Garcia', 'texto', NULL, '');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
