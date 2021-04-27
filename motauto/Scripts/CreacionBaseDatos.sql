-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-03-2021 a las 21:41:37
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `motauto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulos`
--

CREATE TABLE `articulos` (
  `codigo` varchar(30) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `precio` float NOT NULL,
  `iva` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `telefono` int(12) NOT NULL,
  `direccion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas_filas`
--

CREATE TABLE `facturas_filas` (
  `num_factura` int(11) NOT NULL,
  `codigo_articulo` varchar(50) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `num_fila` int(11) NOT NULL,
  `descuento` float NOT NULL,
  `precio_total` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas_header`
--

CREATE TABLE `facturas_header` (
  `cif_empresa` varchar(20) NOT NULL,
  `num_factura` int(20) NOT NULL,
  `dni_cliente` varchar(9) NOT NULL,
  `fecha` date NOT NULL,
  `forma_pago` varchar(20) NOT NULL,
  `matricula` varchar(15) NOT NULL,
  `estado` int(11) NOT NULL,
  `total_base_imponible` float NOT NULL,
  `descuento` float NOT NULL,
  `total_iva` float NOT NULL,
  `total_factura` float NOT NULL,
  `observaciones` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `informacion_empresa`
--

CREATE TABLE `informacion_empresa` (
  `cif` varchar(15) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `direccion` varchar(40) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefono` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `dni` varchar(9) NOT NULL,
  `matricula` varchar(10) NOT NULL,
  `color` varchar(20) NOT NULL,
  `tipo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulos`
--
ALTER TABLE `articulos`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `facturas_filas`
--
ALTER TABLE `facturas_filas`
  ADD PRIMARY KEY (`num_fila`),
  ADD KEY `num_factura` (`num_factura`),
  ADD KEY `codigo_articulo` (`codigo_articulo`);

--
-- Indices de la tabla `facturas_header`
--
ALTER TABLE `facturas_header`
  ADD PRIMARY KEY (`num_factura`),
  ADD KEY `dni_cliente` (`dni_cliente`),
  ADD KEY `matricula` (`matricula`);

--
-- Indices de la tabla `informacion_empresa`
--
ALTER TABLE `informacion_empresa`
  ADD PRIMARY KEY (`cif`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`matricula`),
  ADD KEY `dni` (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `facturas_filas`
--
ALTER TABLE `facturas_filas`
  MODIFY `num_fila` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `facturas_header`
--
ALTER TABLE `facturas_header`
  MODIFY `num_factura` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `facturas_filas`
--
ALTER TABLE `facturas_filas`
  ADD CONSTRAINT `facturas_filas_ibfk_1` FOREIGN KEY (`num_factura`) REFERENCES `facturas_header` (`num_factura`),
  ADD CONSTRAINT `facturas_filas_ibfk_2` FOREIGN KEY (`codigo_articulo`) REFERENCES `articulos` (`codigo`);

--
-- Filtros para la tabla `facturas_header`
--
ALTER TABLE `facturas_header`
  ADD CONSTRAINT `facturas_header_ibfk_1` FOREIGN KEY (`dni_cliente`) REFERENCES `clientes` (`dni`),
  ADD CONSTRAINT `facturas_header_ibfk_2` FOREIGN KEY (`matricula`) REFERENCES `vehiculo` (`matricula`);

--
-- Filtros para la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD CONSTRAINT `vehiculo_ibfk_1` FOREIGN KEY (`dni`) REFERENCES `clientes` (`dni`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
