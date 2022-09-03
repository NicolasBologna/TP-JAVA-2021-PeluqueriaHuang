-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-09-2022 a las 01:02:08
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Base de datos: `peluquerias`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `barber_local`
--

CREATE TABLE IF NOT EXISTS `barber_local` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `barber_id` int(11) NOT NULL,
  `local_id` int(11) NOT NULL,
  `day_of_week` varchar(15) CHARACTER SET utf8mb4 NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `barber_local`
--

INSERT INTO `barber_local` (`id`, `barber_id`, `local_id`, `day_of_week`, `start_time`, `end_time`) VALUES
(1, 1, 1, 'Lunes', '09:00:00', '18:00:00'),
(2, 3, 2, 'Lunes', '09:00:00', '18:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comments`
--

CREATE TABLE IF NOT EXISTS `comments` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp(),
  `post_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `comments`
--

INSERT INTO `comments` (`comment_id`, `text`, `date`, `post_id`, `client_id`) VALUES
(1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '2022-09-03', 4, 1),
(2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Aliquam id diam maecenas ultricies mi eget mauris pharetra et.', '2022-09-02', 4, 2),
(3, 'Prueba agregando', '2022-09-03', 4, 1),
(4, 'Prueba agregando', '2022-09-03', 4, 1),
(5, 'Mi primer comentario', '2022-09-03', 5, 1),
(6, 'Mi segundo comentario', '2022-09-03', 5, 1),
(7, 'Mi segundo comentario', '2022-09-03', 5, 1),
(8, 'Otro comentario de otro', '2022-09-03', 5, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `locals`
--

CREATE TABLE IF NOT EXISTS `locals` (
  `local_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `coordenates` text NOT NULL,
  `address` varchar(255) NOT NULL,
  `is_enable` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`local_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `locals`
--

INSERT INTO `locals` (`local_id`, `name`, `coordenates`, `address`, `is_enable`) VALUES
(1, 'Huang HQ', '-32.950950302538295, -60.633802846593554', 'San Juan 712', 1),
(2, 'pelu2', '-32.950950302338295, -60.633802846593554', 'Corrientes 1304', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publications`
--

CREATE TABLE IF NOT EXISTS `publications` (
  `publication_id` int(11) NOT NULL AUTO_INCREMENT,
  `barber_id` int(11) NOT NULL,
  `title` text CHARACTER SET utf8mb4 NOT NULL,
  `text` text CHARACTER SET utf8mb4 NOT NULL,
  `date` text CHARACTER SET utf8mb4 NOT NULL DEFAULT current_timestamp(),
  `image` mediumblob DEFAULT NULL,
  PRIMARY KEY (`publication_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `publications`
--

INSERT INTO `publications` (`publication_id`, `barber_id`, `title`, `text`, `date`, `image`) VALUES
(4, 2, 'sadhgdsdf', 'sfdfsdfs', '2022-08-20 20:10:05', NULL),
(5, 2, 'sadsd', 'asddas', '2022-09-03 13:15:00', NULL),
(6, 2, 'sdada', '32534636', '2022-09-03 14:23:22', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `role`) VALUES
(1, 'Administrador'),
(2, 'Peluquero'),
(3, 'Cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role_user`
--

CREATE TABLE IF NOT EXISTS `role_user` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `role_user`
--

INSERT INTO `role_user` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(3, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `services`
--

CREATE TABLE IF NOT EXISTS `services` (
  `service_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `duration` time DEFAULT NULL,
  `is_enable` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `services`
--

INSERT INTO `services` (`service_id`, `name`, `description`, `price`, `duration`, `is_enable`) VALUES
(1, 'Corte Hombre', 'Corte cortito con maquina', '600', '00:30:00', 1),
(2, 'corte dama', 'corte super corte re corte', '1200', '01:00:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `service_barber`
--

CREATE TABLE IF NOT EXISTS `service_barber` (
  `service_barber_id` int(11) NOT NULL AUTO_INCREMENT,
  `barber_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `is_enable` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`service_barber_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `service_barber`
--

INSERT INTO `service_barber` (`service_barber_id`, `barber_id`, `service_id`, `is_enable`) VALUES
(3, 3, 1, 1),
(4, 3, 2, 1),
(7, 2, 1, 1),
(8, 2, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turns`
--

CREATE TABLE IF NOT EXISTS `turns` (
  `turn_id` int(11) NOT NULL,
  `local_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `barber_id` int(11) NOT NULL,
  `init_date` datetime NOT NULL,
  `finish_date` datetime DEFAULT NULL,
  `opinion` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `dni` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` text NOT NULL,
  `is_enable` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `dni`, `phone`, `email`, `password`, `is_enable`) VALUES
(1, 'Admin', 'Admin', 11111111, NULL, 'admin@admin.com', 'admin', 1),
(2, 'Florencio', 'Randazzo', 0, '2141341', 'barber1@gmail.com', '21321421431', 1),
(3, 'Florencia', 'kirchner', 0, '123123', 'barber2@gmail.com', 'barber2@gmail.com', 1);
COMMIT;
