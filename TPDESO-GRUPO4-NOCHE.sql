-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: mysql_db
-- Tiempo de generación: 13-11-2024 a las 02:07:55
-- Versión del servidor: 8.0.40
-- Versión de PHP: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `exampledb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Bebida`
--

CREATE TABLE `Bebida` (
  `id` Integer NOT NULL,
  `tamano` enum('CHICA','MEDIANA','GRANDE') DEFAULT NULL,
  `volumen` Double DEFAULT NULL,
  `graduacion_alcoholica` Integer DEFAULT NULL,
  `tipoBebida` Integer DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Categoria`
--

CREATE TABLE `Categoria` (
  `id` Integer NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Cliente`
--

CREATE TABLE `Cliente` (
  `id` Integer NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `cuit` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `coordenada_id` Integer DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ContextoPago`
--

CREATE TABLE `ContextoPago` (
  `id` Integer NOT NULL,
  `estrategia_pago_id` Integer DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Coordenada`
--

CREATE TABLE `Coordenada` (
  `id` Integer NOT NULL,
  `lat` Double DEFAULT NULL,
  `lgn` Double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Efectivo`
--

CREATE TABLE `Efectivo` (
  `id` Integer NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ItemMenu`
--

CREATE TABLE `ItemMenu` (
  `id` Integer NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` Double DEFAULT NULL,
  `categoria_id` Integer DEFAULT NULL,
  `peso` Double DEFAULT NULL,
  `vendedor_id` Integer DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ItemPedido`
--

CREATE TABLE `ItemPedido` (
  `id` Integer NOT NULL,
  `item_menu_id` Integer DEFAULT NULL,
  `cantidad` Integer DEFAULT NULL,
  `precio` Double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `MercadoPago`
--

CREATE TABLE `MercadoPago` (
  `id` Integer NOT NULL,
  `alias` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Pago`
--

CREATE TABLE `Pago` (
  `id` Integer NOT NULL,
  `tipo_estrategia` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Pedido`
--

CREATE TABLE `Pedido` (
  `id` Integer NOT NULL,
  `cliente_id` Integer DEFAULT NULL,
  `estado_pedido` enum('RECIBIDO','PREPARANDO','CANCELADO','ENVIADO','ENTREGADO') DEFAULT NULL,
  `precio_total` Double DEFAULT NULL,
  `contexto_pago_id` Integer DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Plato`
--

CREATE TABLE `Plato` (
  `id` Integer NOT NULL,
  `calorias` Integer DEFAULT NULL,
  `apto_celiaco` tinyint(1) DEFAULT NULL,
  `apto_vegano` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Transferencia`
--

CREATE TABLE `Transferencia` (
  `id` Integer NOT NULL,
  `cuit` varchar(255) DEFAULT NULL,
  `cbu` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Vendedor`
--

CREATE TABLE `Vendedor` (
  `id` Integer NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `coordenada_id` Integer DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Bebida`
--
ALTER TABLE `Bebida`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Categoria`
--
ALTER TABLE `Categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Cliente`
--
ALTER TABLE `Cliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `coordenada_id` (`coordenada_id`);

--
-- Indices de la tabla `ContextoPago`
--
ALTER TABLE `ContextoPago`
  ADD PRIMARY KEY (`id`),
  ADD KEY `estrategia_pago_id` (`estrategia_pago_id`);

--
-- Indices de la tabla `Coordenada`
--
ALTER TABLE `Coordenada`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Efectivo`
--
ALTER TABLE `Efectivo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ItemMenu`
--
ALTER TABLE `ItemMenu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoria_id` (`categoria_id`),
  ADD KEY `vendedor_id` (`vendedor_id`);

--
-- Indices de la tabla `ItemPedido`
--
ALTER TABLE `ItemPedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `item_menu_id` (`item_menu_id`);

--
-- Indices de la tabla `MercadoPago`
--
ALTER TABLE `MercadoPago`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Pago`
--
ALTER TABLE `Pago`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Pedido`
--
ALTER TABLE `Pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cliente_id` (`cliente_id`),
  ADD KEY `contexto_pago_id` (`contexto_pago_id`);

--
-- Indices de la tabla `Plato`
--
ALTER TABLE `Plato`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Transferencia`
--
ALTER TABLE `Transferencia`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Vendedor`
--
ALTER TABLE `Vendedor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `coordenada_id` (`coordenada_id`);


ALTER TABLE `Bebida`
  MODIFY COLUMN `id` INT AUTO_INCREMENT;

ALTER TABLE `Categoria`
  MODIFY COLUMN `id` INT AUTO_INCREMENT;

ALTER TABLE `Cliente` 
  MODIFY COLUMN `id` INT AUTO_INCREMENT;

ALTER TABLE `ContextoPago` 
  MODIFY COLUMN `id` INT AUTO_INCREMENT;

ALTER TABLE `Coordenada` 
  MODIFY COLUMN `id` INT AUTO_INCREMENT;

ALTER TABLE `Efectivo` 
  MODIFY COLUMN `id` INT AUTO_INCREMENT;

ALTER TABLE `ItemMenu` 
  MODIFY COLUMN `id` INT AUTO_INCREMENT;

ALTER TABLE `ItemPedido` 
  MODIFY COLUMN `id` INT AUTO_INCREMENT;

ALTER TABLE `MercadoPago` 
  MODIFY COLUMN `id` INT AUTO_INCREMENT;

ALTER TABLE `Pago` 
  MODIFY COLUMN `id` INT AUTO_INCREMENT;

ALTER TABLE `Pedido` 
  MODIFY COLUMN `id` INT AUTO_INCREMENT;

ALTER TABLE `Plato` 
  MODIFY COLUMN `id` INT AUTO_INCREMENT;

ALTER TABLE `Transferencia` 
  MODIFY COLUMN `id` INT AUTO_INCREMENT;

ALTER TABLE `Vendedor` 
  MODIFY COLUMN `id` INT AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Bebida`
--
ALTER TABLE `Bebida`
  ADD CONSTRAINT `Bebida_ibfk_1` FOREIGN KEY (`id`) REFERENCES `ItemMenu` (`id`);

--
-- Filtros para la tabla `Cliente`
--
ALTER TABLE `Cliente`
  ADD CONSTRAINT `Cliente_ibfk_1` FOREIGN KEY (`coordenada_id`) REFERENCES `Coordenada` (`id`);

--
-- Filtros para la tabla `ContextoPago`
--
ALTER TABLE `ContextoPago`
  ADD CONSTRAINT `ContextoPago_ibfk_1` FOREIGN KEY (`estrategia_pago_id`) REFERENCES `Pago` (`id`);

--
-- Filtros para la tabla `ItemMenu`
--
ALTER TABLE `ItemMenu`
  ADD CONSTRAINT `ItemMenu_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `Categoria` (`id`),
  ADD CONSTRAINT `ItemMenu_ibfk_2` FOREIGN KEY (`vendedor_id`) REFERENCES `Vendedor` (`id`);

--
-- Filtros para la tabla `ItemPedido`
--
ALTER TABLE `ItemPedido`
  ADD CONSTRAINT `ItemPedido_ibfk_1` FOREIGN KEY (`item_menu_id`) REFERENCES `ItemMenu` (`id`),
  ADD CONSTRAINT `ItemPedido_ibfk_2` FOREIGN KEY (`id`) REFERENCES `Pedido` (`id`);

--
-- Filtros para la tabla `Efectivo`
--
ALTER TABLE `Efectivo`
  ADD CONSTRAINT `Efectivo_ibfk_1` FOREIGN KEY (`id`) REFERENCES `Pago` (`id`);


--
-- Filtros para la tabla `MercadoPago`
--
ALTER TABLE `MercadoPago`
  ADD CONSTRAINT `MercadoPago_ibfk_1` FOREIGN KEY (`id`) REFERENCES `Pago` (`id`);

--
-- Filtros para la tabla `Transferencia`
--
ALTER TABLE `Transferencia`
  ADD CONSTRAINT `Transferencia_ibfk_1` FOREIGN KEY (`id`) REFERENCES `Pago` (`id`);


--
-- Filtros para la tabla `Pedido`
--
ALTER TABLE `Pedido`
  ADD CONSTRAINT `Pedido_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `Cliente` (`id`),
  ADD CONSTRAINT `Pedido_ibfk_2` FOREIGN KEY (`contexto_pago_id`) REFERENCES `ContextoPago` (`id`);

--
-- Filtros para la tabla `Plato`
--
ALTER TABLE `Plato`
  ADD CONSTRAINT `Plato_ibfk_1` FOREIGN KEY (`id`) REFERENCES `ItemMenu` (`id`);

--
-- Filtros para la tabla `Vendedor`
--
ALTER TABLE `Vendedor`
  ADD CONSTRAINT `Vendedor_ibfk_1` FOREIGN KEY (`coordenada_id`) REFERENCES `Coordenada` (`id`);


COMMIT;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
