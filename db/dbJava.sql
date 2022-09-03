-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-02-2022 a las 19:19:06
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.12

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
-- Volcado de datos para la tabla `barber_local`
--

INSERT INTO `barber_local` (`id`, `barber_id`, `local_id`, `day_of_week`, `start_time`, `end_time`) VALUES
(1, 3, 1, 'Lunes', '09:00:00', '18:00:00'),
(2, 5, 2, 'Lunes', '09:00:00', '18:00:00');

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
-- Volcado de datos para la tabla `locals`
--

INSERT INTO `locals` (`local_id`, `name`, `coordenates`, `address`, `is_enable`) VALUES
(1, 'Huang HQ', '-32.950950302538295, -60.633802846593554', 'San Juan 712', 1),
(2, 'PeluKids', '151562626', 'Cordoba 1200', 1);

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
-- Volcado de datos para la tabla `publications`
--

INSERT INTO `publications` (`publication_id`, `barber_id`, `title`, `text`, `date`, `image`) VALUES
(1, 1, 'Mi primer posteo', 'Hola, queremos darte la bienvenida a nuestros centros estéticos de primer nivel. Saludos', '2022-02-20 23:05:27', 0x506f722061686f7261206e6f206861792e2067726163696173);

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
-- Volcado de datos para la tabla `role_user`
--

INSERT INTO `role_user` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 3),
(3, 2),
(4, 2),
(5, 2);

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
-- Volcado de datos para la tabla `services`
--

INSERT INTO `services` (`service_id`, `name`, `description`, `price`, `duration`, `is_enable`) VALUES
(1, 'Corte Hombre', 'Corte cortito con maquina', '600', '00:30:00', 1),
(2, 'Alisado', 'Alisado de pelo', '1000', '01:00:00', 1),
(3, 'Tintura', 'Tintura permanente', '1200', '01:30:00', 1),
(4, 'Peinado', 'Peinado fiesta', '1500', '01:00:00', 1);

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
-- Volcado de datos para la tabla `service_barber`
--

INSERT INTO `service_barber` (`service_barber_id`, `barber_id`, `service_id`, `is_enable`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 1),
(3, 3, 1, 1),
(4, 3, 2, 1),
(5, 3, 3, 1),
(6, 3, 4, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turns`
--

DROP TABLE IF EXISTS `turns`;
CREATE TABLE `turns` (
  `turn_id` int(11) NOT NULL,
  `schedule_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `hour` time DEFAULT NULL,
  `duration` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `turns`
--

INSERT INTO `turns` (`turn_id`, `schedule_id`, `client_id`, `date`, `hour`, `duration`) VALUES
(27, 1, 2, '2022-02-28', '10:00:00', '00:30:00'),
(28, 1, 2, '2022-02-28', '12:00:00', '01:00:00'),
(29, 1, 2, '2022-02-28', '09:00:00', '00:30:00'),
(30, 1, 2, '2022-02-28', '14:00:00', '01:00:00'),
(31, 1, 2, '2022-02-28', '10:30:00', '01:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `turns_services`
--

DROP TABLE IF EXISTS `turns_services`;
CREATE TABLE `turns_services` (
  `turn_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `turns_services`
--

INSERT INTO `turns_services` (`turn_id`, `service_id`) VALUES
(21, 1),
(22, 1),
(23, 1),
(24, 2),
(25, 2),
(26, 1),
(27, 1),
(28, 2),
(29, 1),
(30, 2),
(31, 2);

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
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `dni`, `phone`, `email`, `password`, `is_enable`) VALUES
(1, 'Admin', 'Admin', 11111111, NULL, 'admin@admin.com', 'admin', 1),
(2, 'Cliente', 'Cliente', 1234567, '111111', 'user@user.com', 'useruser', 1),
(3, 'Pelu', 'Quero', 111111, '1234', 'pelu@pelu.com', 'pelupelu', 1),
(4, 'Pablo', 'Pelu', 0, '151515', 'pablo@pablo.com', 'pablopablo', 1),
(5, 'Martin', 'Martinez', 0, '787878', 'martin@martin.com', 'martinmartin', 1);

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
-- Indices de la tabla `service_barber`
--
ALTER TABLE `service_barber`
  ADD PRIMARY KEY (`service_barber_id`);

--
-- Indices de la tabla `turns`
--
ALTER TABLE `turns`
  ADD PRIMARY KEY (`turn_id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `comments`
--
ALTER TABLE `comments`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `locals`
--
ALTER TABLE `locals`
  MODIFY `local_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `service_barber`
--
ALTER TABLE `service_barber`
  MODIFY `service_barber_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `turns`
--
ALTER TABLE `turns`
  MODIFY `turn_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
