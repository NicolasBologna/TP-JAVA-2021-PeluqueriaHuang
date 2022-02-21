-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-02-2022 a las 03:09:39
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `peluquerias`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `barber_local`
--

DROP TABLE IF EXISTS `barber_local`;
CREATE TABLE `barber_local` (
  `id` int(11) NOT NULL,
  `barber_id` int(11) NOT NULL,
  `local_id` int(11) NOT NULL,
  `day_of_week` varchar(15) NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncar tablas antes de insertar `barber_local`
--

TRUNCATE TABLE `barber_local`;
--
-- Volcado de datos para la tabla `barber_local`
--

INSERT INTO `barber_local` (`id`, `barber_id`, `local_id`, `day_of_week`, `start_time`, `end_time`) VALUES
(1, 1, 1, 'Lunes', '09:00:00', '18:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comments`
--

DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL,
  `text` text NOT NULL,
  `date` date NOT NULL,
  `post_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncar tablas antes de insertar `comments`
--

TRUNCATE TABLE `comments`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `locals`
--

DROP TABLE IF EXISTS `locals`;
CREATE TABLE `locals` (
  `local_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `coordenates` text NOT NULL,
  `address` varchar(255) NOT NULL,
  `is_enable` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncar tablas antes de insertar `locals`
--

TRUNCATE TABLE `locals`;
--
-- Volcado de datos para la tabla `locals`
--

INSERT INTO `locals` (`local_id`, `name`, `coordenates`, `address`, `is_enable`) VALUES
(1, 'Huang HQ', '-32.950950302538295, -60.633802846593554', 'San Juan 712', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publications`
--

DROP TABLE IF EXISTS `publications`;
CREATE TABLE `publications` (
  `publication_id` int(11) NOT NULL,
  `barber_id` int(11) NOT NULL,
  `title` text NOT NULL,
  `text` text NOT NULL,
  `date` text NOT NULL DEFAULT current_timestamp(),
  `image` blob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncar tablas antes de insertar `publications`
--

TRUNCATE TABLE `publications`;
--
-- Volcado de datos para la tabla `publications`
--

INSERT INTO `publications` (`publication_id`, `barber_id`, `title`, `text`, `date`, `image`) VALUES
(1, 1, 'Mi primer posteo', 'Hola, queremos darte la bienvenida a nuestros centros estÃ©ticos de primer nivel. Saludos', '2022-02-20 23:05:27', 0x506f722061686f7261206e6f206861792e2067726163696173);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncar tablas antes de insertar `roles`
--

TRUNCATE TABLE `roles`;
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

DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncar tablas antes de insertar `role_user`
--

TRUNCATE TABLE `role_user`;
--
-- Volcado de datos para la tabla `role_user`
--

INSERT INTO `role_user` (`user_id`, `role_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `services`
--

DROP TABLE IF EXISTS `services`;
CREATE TABLE `services` (
  `service_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `duration` time DEFAULT NULL,
  `is_enable` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncar tablas antes de insertar `services`
--

TRUNCATE TABLE `services`;
--
-- Volcado de datos para la tabla `services`
--

INSERT INTO `services` (`service_id`, `name`, `description`, `price`, `duration`, `is_enable`) VALUES
(1, 'Corte Hombre', 'Corte cortito con maquina', '600', '00:30:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `service_barber`
--

DROP TABLE IF EXISTS `service_barber`;
CREATE TABLE `service_barber` (
  `service_barber_id` int(11) NOT NULL,
  `barber_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `is_enable` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Truncar tablas antes de insertar `service_barber`
--

TRUNCATE TABLE `service_barber`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turns`
--

DROP TABLE IF EXISTS `turns`;
CREATE TABLE `turns` (
  `turn_id` int(11) NOT NULL,
  `local_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `barber_id` int(11) NOT NULL,
  `init_date` datetime NOT NULL,
  `finish_date` datetime DEFAULT NULL,
  `opinion` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncar tablas antes de insertar `turns`
--

TRUNCATE TABLE `turns`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `dni` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` text NOT NULL,
  `is_enable` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncar tablas antes de insertar `users`
--

TRUNCATE TABLE `users`;
--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `dni`, `phone`, `email`, `password`, `is_enable`) VALUES
(1, 'Admin', 'Admin', 11111111, NULL, 'admin@admin.com', 'admin', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `barber_local`
--
ALTER TABLE `barber_local`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`comment_id`);

--
-- Indices de la tabla `locals`
--
ALTER TABLE `locals`
  ADD PRIMARY KEY (`local_id`);

--
-- Indices de la tabla `publications`
--
ALTER TABLE `publications`
  ADD PRIMARY KEY (`publication_id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `role_user`
--
ALTER TABLE `role_user`
  ADD KEY `user_id` (`user_id`),
  ADD KEY `role_id` (`role_id`);

--
-- Indices de la tabla `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`service_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `barber_local`
--
ALTER TABLE `barber_local`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `comments`
--
ALTER TABLE `comments`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `locals`
--
ALTER TABLE `locals`
  MODIFY `local_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `publications`
--
ALTER TABLE `publications`
  MODIFY `publication_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `services`
--
ALTER TABLE `services`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
