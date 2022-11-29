SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `barber_local` (
  `id` int(11) NOT NULL,
  `barber_id` int(11) NOT NULL,
  `local_id` int(11) NOT NULL,
  `day_of_week` varchar(15) CHARACTER SET utf8mb4 NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

INSERT INTO `barber_local` (`id`, `barber_id`, `local_id`, `day_of_week`, `start_time`, `end_time`) VALUES
(1, 1, 1, 'Lunes', '09:00:00', '18:00:00'),
(6, 1, 2, 'Martes', '09:00:00', '18:00:00'),
(7, 4, 1, 'Lunes', '09:00:00', '18:00:00'),
(8, 4, 1, 'Viernes', '09:00:00', '18:00:00'),
(9, 4, 1, 'Sabado', '09:00:00', '13:00:00'),
(10, 4, 1, 'Martes', '09:00:00', '18:00:00');

CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL,
  `text` text NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp(),
  `post_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `comments` (`comment_id`, `text`, `date`, `post_id`, `client_id`) VALUES
(1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', '2022-09-03', 4, 1),
(2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Aliquam id diam maecenas ultricies mi eget mauris pharetra et.', '2022-09-02', 4, 2),
(3, 'Prueba agregando', '2022-09-03', 4, 1),
(4, 'Prueba agregando', '2022-09-03', 4, 1),
(5, 'Mi primer comentario', '2022-09-03', 5, 1),
(6, 'Mi segundo comentario', '2022-09-03', 5, 1),
(7, 'Mi segundo comentario', '2022-09-03', 5, 1),
(8, 'Otro comentario de otro', '2022-09-03', 5, 2);

CREATE TABLE `locals` (
  `local_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `coordenates` text NOT NULL,
  `address` varchar(255) NOT NULL,
  `is_enable` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `locals` (`local_id`, `name`, `coordenates`, `address`, `is_enable`) VALUES
(1, 'Huang HQ', '-32.950950302538295, -60.633802846593554', 'San Juan 712', 1),
(2, 'pelu2', '-32.950950302338295, -60.633802846593554', 'Corrientes 1304', 1);

CREATE TABLE `publications` (
  `publication_id` int(11) NOT NULL,
  `barber_id` int(11) NOT NULL,
  `title` text CHARACTER SET utf8mb4 NOT NULL,
  `text` text CHARACTER SET utf8mb4 NOT NULL,
  `date` text CHARACTER SET utf8mb4 NOT NULL DEFAULT current_timestamp(),
  `image` mediumblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `roles` (`id`, `role`) VALUES
(1, 'Administrador'),
(2, 'Peluquero'),
(3, 'Cliente');

CREATE TABLE `role_user` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `role_user` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 2);

CREATE TABLE `services` (
  `service_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `duration` time DEFAULT NULL,
  `is_enable` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `services` (`service_id`, `name`, `description`, `price`, `duration`, `is_enable`) VALUES
(1, 'Corte Hombre', 'Corte cortito con maquina', '600', '00:30:00', 1),
(2, 'corte dama', 'corte super corte re corte', '1200', '01:00:00', 1);

CREATE TABLE `service_barber` (
  `service_barber_id` int(11) NOT NULL,
  `barber_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `is_enable` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

INSERT INTO `service_barber` (`service_barber_id`, `barber_id`, `service_id`, `is_enable`) VALUES
(3, 3, 1, 1),
(4, 3, 2, 1),
(7, 2, 1, 1),
(8, 2, 2, 1),
(9, 4, 1, 1);

CREATE TABLE `turns` (
  `turn_id` int(11) NOT NULL,
  `schedule_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `hour` time NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT '1',
  `date` date NOT NULL,
  `duration` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `turns` (`turn_id`, `schedule_id`, `client_id`, `hour`, `status`, `date`, `duration`) VALUES
(1, 10, 5, '12:00:00', 'Pendiente', '2022-11-28', '00:30:00'),
(2, 10, 5, '09:00:00', 'Finalizado', '2022-11-29', '00:30:00'),
(5, 10, 5, '17:30:00', 'Pendiente', '2022-12-01', '00:30:00');

CREATE TABLE `turns_services` (
  `service_id` int(11) NOT NULL,
  `turn_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `turns_services` (`service_id`, `turn_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1);

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

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `dni`, `phone`, `email`, `password`, `is_enable`) VALUES
(1, 'Admin', 'Admin', 11111111, NULL, 'admin@admin.com', 'admin', 1),
(2, 'Florencio', 'Randazzo', 0, '2141341', 'barber1@gmail.com', '21321421431', 1),
(3, 'Florencia', 'kirchner', 0, '123123', 'barber2@gmail.com', 'barber2@gmail.com', 1),
(4, 'pelu', 'pelu', 0, '555555555555', 'pelu@pelu.com', 'pelupelu', 1),
(5, 'user', 'user', 0, '444444444444444', 'user@user.com', 'useruser', 0);


ALTER TABLE `barber_local`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `comments`
  ADD PRIMARY KEY (`comment_id`);

ALTER TABLE `locals`
  ADD PRIMARY KEY (`local_id`);

ALTER TABLE `publications`
  ADD PRIMARY KEY (`publication_id`);

ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `role_user`
  ADD KEY `user_id` (`user_id`),
  ADD KEY `role_id` (`role_id`);

ALTER TABLE `services`
  ADD PRIMARY KEY (`service_id`);

ALTER TABLE `service_barber`
  ADD PRIMARY KEY (`service_barber_id`);

ALTER TABLE `turns`
  ADD PRIMARY KEY (`turn_id`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);


ALTER TABLE `barber_local`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

ALTER TABLE `comments`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

ALTER TABLE `locals`
  MODIFY `local_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE `publications`
  MODIFY `publication_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

ALTER TABLE `services`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE `service_barber`
  MODIFY `service_barber_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

ALTER TABLE `turns`
  MODIFY `turn_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;