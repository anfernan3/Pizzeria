-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.28 - MySQL Community Server - GPL
-- SO del servidor:              Linux
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

-- !40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT  --;
-- !40101 SET NAMES utf8  --;
-- !50503 SET NAMES utf8mb4  --;
-- !40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0  --;
-- !40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO'  --;
-- !40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0  --;

-- Volcando estructura para tabla pizzeria.comentarios
CREATE TABLE IF NOT EXISTS `comentarios` (
  `id_comentario` int NOT NULL AUTO_INCREMENT,
  `puntuacion` int unsigned NOT NULL,
  `fecha` timestamp NOT NULL,
  `id_usuario` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
  `id_pizza` int NOT NULL DEFAULT '0',
  `texto` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_comentario`),
  KEY `comentario_pizza` (`id_pizza`),
  KEY `comentario_usuario` (`id_usuario`),
  CONSTRAINT `comentario_pizza` FOREIGN KEY (`id_pizza`) REFERENCES `pizzas` (`id_pizza`),
  CONSTRAINT `comentario_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.comentarios: ~0 rows (aproximadamente)
-- !40000 ALTER TABLE `comentarios` DISABLE KEYS  --;
REPLACE INTO `comentarios` (`id_comentario`, `puntuacion`, `fecha`, `id_usuario`, `id_pizza`, `texto`) VALUES
	(2, 5, '2022-03-04 00:18:49', '1', 8, 'Muy rica'),
	(3, 4, '2022-03-04 00:19:24', '2', 8, 'Bien');
-- !40000 ALTER TABLE `comentarios` ENABLE KEYS  --;

-- Volcando estructura para tabla pizzeria.direcciones
CREATE TABLE IF NOT EXISTS `direcciones` (
  `id_direccion` int NOT NULL AUTO_INCREMENT,
  `calle` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ciudad` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `pais` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_usuario` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `es_de_entrega` enum('S','N') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'S',
  PRIMARY KEY (`id_direccion`),
  KEY `FK_direcciones_usuarios` (`id_usuario`),
  CONSTRAINT `direccion_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.direcciones: ~4 rows (aproximadamente)
-- !40000 ALTER TABLE `direcciones` DISABLE KEYS  --;
REPLACE INTO `direcciones` (`id_direccion`, `calle`, `ciudad`, `pais`, `id_usuario`, `es_de_entrega`) VALUES
	(1, 'C/ La Panoplia', 'Murcia', 'España', '1', 'S'),
	(2, 'C/ La Gioconda', 'Murcia', 'España', '2', 'N'),
	(3, 'C/ El membrillo', 'Murcia', 'España', '1', 'N'),
	(4, 'C/ Picasso', 'Murcia', 'España', '2', 'S');
-- !40000 ALTER TABLE `direcciones` ENABLE KEYS  --;

-- Volcando estructura para tabla pizzeria.fotos
CREATE TABLE IF NOT EXISTS `fotos` (
  `id_foto` int NOT NULL AUTO_INCREMENT,
  `id_pizza` int NOT NULL,
  `foto` varchar(250) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id_foto`),
  KEY `FK__pizzas` (`id_pizza`),
  CONSTRAINT `foto_pizza` FOREIGN KEY (`id_pizza`) REFERENCES `pizzas` (`id_pizza`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla pizzeria.fotos: ~0 rows (aproximadamente)
-- !40000 ALTER TABLE `fotos` DISABLE KEYS  --;
REPLACE INTO `fotos` (`id_foto`, `id_pizza`, `foto`) VALUES
	(1, 8, 'C:\\fotopizzas\\hawaiana.jpg'),
	(2, 8, 'C:\\fotopizzas\\hawaiana2.jpg');
-- !40000 ALTER TABLE `fotos` ENABLE KEYS  --;

-- Volcando estructura para tabla pizzeria.funciones
CREATE TABLE IF NOT EXISTS `funciones` (
  `id_funcion` int NOT NULL AUTO_INCREMENT,
  `id_usuario` varchar(125) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
  `rol` enum('usuario','tienda','repartidor','gerente') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_funcion`),
  KEY `funciones_usuario` (`id_usuario`),
  CONSTRAINT `funciones_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.funciones: ~0 rows (aproximadamente)
-- !40000 ALTER TABLE `funciones` DISABLE KEYS  --;
REPLACE INTO `funciones` (`id_funcion`, `id_usuario`, `rol`) VALUES
	(1, '1', 'gerente'),
	(2, '2', 'usuario');
-- !40000 ALTER TABLE `funciones` ENABLE KEYS  --;

-- Volcando estructura para tabla pizzeria.ingredientes
CREATE TABLE IF NOT EXISTS `ingredientes` (
  `id_ingrediente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tipo` enum('base','salsa','otros') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `precio` decimal(4,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id_ingrediente`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.ingredientes: ~6 rows (aproximadamente)
-- !40000 ALTER TABLE `ingredientes` DISABLE KEYS  --;
REPLACE INTO `ingredientes` (`id_ingrediente`, `nombre`, `tipo`, `precio`) VALUES
	(1, 'Base Fina', 'base', 1.00),
	(2, 'Base Gruesa', 'base', 1.00),
	(3, 'Queso', 'otros', 1.25),
	(4, 'Champiñones', 'otros', 0.75),
	(5, 'Jamon York', 'otros', 1.00),
	(6, 'Salsa Barbacoa', 'salsa', 0.50),
	(7, 'Salsa Ketchup', 'salsa', 0.50);
-- !40000 ALTER TABLE `ingredientes` ENABLE KEYS  --;

-- Volcando estructura para tabla pizzeria.ingredientes por pizza
CREATE TABLE IF NOT EXISTS `ingredientes por pizza` (
  `id_ingrediente` int NOT NULL,
  `id_pizza` int NOT NULL,
  `cantidad` int unsigned NOT NULL,
  KEY `ingrediente_por_pizza_Ingrediente` (`id_ingrediente`),
  KEY `ingrediente_por_pizza_Pizza` (`id_pizza`),
  CONSTRAINT `ingrediente_por_pizza_Ingrediente` FOREIGN KEY (`id_ingrediente`) REFERENCES `ingredientes` (`id_ingrediente`),
  CONSTRAINT `ingrediente_por_pizza_Pizza` FOREIGN KEY (`id_pizza`) REFERENCES `pizzas` (`id_pizza`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.ingredientes por pizza: ~2 rows (aproximadamente)
-- !40000 ALTER TABLE `ingredientes por pizza` DISABLE KEYS  --;
REPLACE INTO `ingredientes por pizza` (`id_ingrediente`, `id_pizza`, `cantidad`) VALUES
	(1, 8, 1),
	(5, 8, 1);
-- !40000 ALTER TABLE `ingredientes por pizza` ENABLE KEYS  --;

-- Volcando estructura para tabla pizzeria.pedidos
CREATE TABLE IF NOT EXISTS `pedidos` (
  `id_pedido` int NOT NULL AUTO_INCREMENT,
  `numero_pedido` int NOT NULL,
  `id_usuario` varchar(125) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
  `fecha_pedido` datetime NOT NULL,
  `direccion_entrega` int NOT NULL DEFAULT '0',
  `preparado_por` varchar(125) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
  `fecha_entrega` datetime NOT NULL,
  `entregado_por` varchar(125) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0',
  `importe` double NOT NULL,
  `estado` enum('solicitado','elaborandose','preparado','enviado','recibido','cancelado') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_pedido`),
  UNIQUE KEY `numero_pedido` (`numero_pedido`),
  KEY `pedido_direccion_entrega` (`direccion_entrega`),
  KEY `pedido_usuario` (`id_usuario`),
  KEY `pedido_preparado_por` (`preparado_por`),
  KEY `pedido_entregado_por` (`entregado_por`),
  CONSTRAINT `pedido_direccion_entrega` FOREIGN KEY (`direccion_entrega`) REFERENCES `direcciones` (`id_direccion`),
  CONSTRAINT `pedido_entregado_por` FOREIGN KEY (`entregado_por`) REFERENCES `usuarios` (`email`),
  CONSTRAINT `pedido_preparado_por` FOREIGN KEY (`preparado_por`) REFERENCES `usuarios` (`email`),
  CONSTRAINT `pedido_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.pedidos: ~0 rows (aproximadamente)
-- !40000 ALTER TABLE `pedidos` DISABLE KEYS  --;
REPLACE INTO `pedidos` (`id_pedido`, `numero_pedido`, `id_usuario`, `fecha_pedido`, `direccion_entrega`, `preparado_por`, `fecha_entrega`, `entregado_por`, `importe`, `estado`) VALUES
	(2, 1, '1', '2022-03-02 13:49:34', 4, '1', '2022-03-02 13:49:51', '2', 9, 'recibido');
-- !40000 ALTER TABLE `pedidos` ENABLE KEYS  --;

-- Volcando estructura para tabla pizzeria.pizzas
CREATE TABLE IF NOT EXISTS `pizzas` (
  `id_pizza` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `descripcion` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_base` int NOT NULL,
  `id_otros_ingr` int NOT NULL,
  `id_salsa` int DEFAULT NULL,
  `precio` double NOT NULL,
  `gusta` enum('Si','No') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_pizza`),
  KEY `pizza_base` (`id_base`),
  KEY `pizza_salsa` (`id_salsa`),
  KEY `pizza_otros_ingredientes` (`id_otros_ingr`),
  CONSTRAINT `pizza_base` FOREIGN KEY (`id_base`) REFERENCES `ingredientes` (`id_ingrediente`),
  CONSTRAINT `pizza_otros_ingredientes` FOREIGN KEY (`id_otros_ingr`) REFERENCES `ingredientes` (`id_ingrediente`),
  CONSTRAINT `pizza_salsa` FOREIGN KEY (`id_salsa`) REFERENCES `ingredientes` (`id_ingrediente`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.pizzas: ~1 rows (aproximadamente)
-- !40000 ALTER TABLE `pizzas` DISABLE KEYS  --;
REPLACE INTO `pizzas` (`id_pizza`, `nombre`, `descripcion`, `id_base`, `id_otros_ingr`, `id_salsa`, `precio`, `gusta`) VALUES
	(8, 'Hawaiana', 'Pizza Hawaiana', 1, 5, 6, 9, 'Si');
-- !40000 ALTER TABLE `pizzas` ENABLE KEYS  --;

-- Volcando estructura para tabla pizzeria.pizzas por pedido
CREATE TABLE IF NOT EXISTS `pizzas por pedido` (
  `id_pizza_por_pedido` int NOT NULL AUTO_INCREMENT,
  `id_pedido` int NOT NULL,
  `id_pizza` int NOT NULL DEFAULT '0',
  `cantidad` int NOT NULL,
  `precio` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_pizza_por_pedido`),
  KEY `id_pizza_por_pedido_pedido` (`id_pedido`),
  KEY `id_pizza_por_pedido_pizza` (`id_pizza`),
  CONSTRAINT `id_pizza_por_pedido_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`),
  CONSTRAINT `id_pizza_por_pedido_pizza` FOREIGN KEY (`id_pizza`) REFERENCES `pizzas` (`id_pizza`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.pizzas por pedido: ~0 rows (aproximadamente)
-- !40000 ALTER TABLE `pizzas por pedido` DISABLE KEYS  --;
REPLACE INTO `pizzas por pedido` (`id_pizza_por_pedido`, `id_pedido`, `id_pizza`, `cantidad`, `precio`) VALUES
	(1, 2, 8, 1, 9);
-- !40000 ALTER TABLE `pizzas por pedido` ENABLE KEYS  --;

-- Volcando estructura para tabla pizzeria.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'AUTO_INCREMENT',
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `apellidos` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `contraseña` blob NOT NULL,
  PRIMARY KEY (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Volcando datos para la tabla pizzeria.usuarios: ~2 rows (aproximadamente)
-- !40000 ALTER TABLE `usuarios` DISABLE KEYS  --;
REPLACE INTO `usuarios` (`email`, `nombre`, `apellidos`, `contraseña`) VALUES
	('1', 'admin', NULL, _binary 0x746578746f),
	('2', 'pepe', 'García', _binary 0x746578746f);
-- !40000 ALTER TABLE `usuarios` ENABLE KEYS  --;

-- !40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '')  --;
-- !40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1)  --;
-- !40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT  --;
-- !40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1)  --;
