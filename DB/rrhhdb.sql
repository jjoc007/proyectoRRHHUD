-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 20-06-2015 a las 00:35:49
-- Versión del servidor: 5.1.37
-- Versión de PHP: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de datos: `rrhhdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_cargo`
--

CREATE TABLE IF NOT EXISTS `tbl_cargo` (
  `car_codigo` varchar(15) NOT NULL,
  `car_nombre` varchar(100) DEFAULT NULL,
  `car_salario` double DEFAULT NULL,
  PRIMARY KEY (`car_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `tbl_cargo`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_parametro`
--

CREATE TABLE IF NOT EXISTS `tbl_parametro` (
  `par_codigo` bigint(11) NOT NULL,
  `par_nombre` varchar(255) NOT NULL,
  `par_descripcion` text NOT NULL,
  `valor` varchar(200) NOT NULL,
  `par_tipo` int(2) NOT NULL,
  PRIMARY KEY (`par_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `tbl_parametro`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbl_usuario`
--

CREATE TABLE IF NOT EXISTS `tbl_usuario` (
  `usu_usuario` varchar(100) NOT NULL,
  `usu_nombre` varchar(200) DEFAULT NULL,
  `usu_clave` varchar(200) DEFAULT NULL,
  `usu_foto` blob,
  `usu_estado` varchar(2) DEFAULT NULL,
  `usu_correo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`usu_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `tbl_usuario`
--

