-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-02-2024 a las 17:14:35
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `travel_agency`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `flight`
--

CREATE TABLE `flight` (
  `id` bigint(20) NOT NULL,
  `date_of_flight` date DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `flight_code` varchar(255) DEFAULT NULL,
  `num_booked_seats` int(11) DEFAULT NULL,
  `num_booked_seats_business` int(11) DEFAULT NULL,
  `num_booked_seats_economy` int(11) DEFAULT NULL,
  `num_seats` int(11) DEFAULT NULL,
  `num_seats_business` int(11) DEFAULT NULL,
  `num_seats_economy` int(11) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `price_business` double DEFAULT NULL,
  `price_economy` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `flight`
--

INSERT INTO `flight` (`id`, `date_of_flight`, `destination`, `flight_code`, `num_booked_seats`, `num_booked_seats_business`, `num_booked_seats_economy`, `num_seats`, `num_seats_business`, `num_seats_economy`, `origin`, `price_business`, `price_economy`) VALUES
(1, '2024-02-21', 'Madrid', 'vc-123', 64, 14, 50, 150, 30, 120, 'Barcelona', 55, 150);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

CREATE TABLE `hotel` (
  `id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `direction` varchar(255) DEFAULT NULL,
  `hotel_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number_of_rooms` int(11) DEFAULT NULL,
  `open` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hotel`
--

INSERT INTO `hotel` (`id`, `city`, `country`, `direction`, `hotel_code`, `name`, `number_of_rooms`, `open`) VALUES
(1, 'New York', 'United States', '123 Main St', 'H123', 'Hotel Plaza', 100, b'1'),
(2, 'New York', 'United States', '321 Main St', 'H321', 'Hilton Hotel', 150, b'1'),
(3, 'Benidorm', 'Spain ', '654 Main St', 'H999', 'Bali', 122, b'0'),
(4, 'test', 'test', 'test', 'test', 'test', 999, b'1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ocupation`
--

CREATE TABLE `ocupation` (
  `id` bigint(20) NOT NULL,
  `check_in` date DEFAULT NULL,
  `check_out` date DEFAULT NULL,
  `ocupation_code` varchar(255) DEFAULT NULL,
  `room_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ocupation`
--

INSERT INTO `ocupation` (`id`, `check_in`, `check_out`, `ocupation_code`, `room_id`) VALUES
(1, '2024-02-23', '2024-02-29', '1234', 121),
(2, '2024-02-12', '2024-02-23', '456321', 2),
(3, '2024-02-13', '2024-02-29', '741258', 1),
(5, '2024-02-23', '2024-02-24', '987456', 121),
(6, '2024-04-04', '2024-04-06', '951456', 1),
(7, '1111-11-11', '1111-11-11', 'testedit', 2),
(8, '2024-02-23', '2024-02-23', 'test', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `person`
--

CREATE TABLE `person` (
  `id` bigint(20) NOT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surnames` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `person`
--

INSERT INTO `person` (`id`, `dni`, `name`, `surnames`) VALUES
(1, '123456', 'Isa', 'Horcajada'),
(2, '654321', 'David', 'Martin'),
(3, '741258', 'Pepe', 'Silvia'),
(4, '963258', 'John', 'Smith');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservation`
--

CREATE TABLE `reservation` (
  `id` bigint(20) NOT NULL,
  `check_in_date` date DEFAULT NULL,
  `check_out_date` date DEFAULT NULL,
  `numpeople` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `reservation_code` varchar(255) DEFAULT NULL,
  `hotel_booked_id` bigint(20) DEFAULT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  `reservation_id` bigint(20) DEFAULT NULL,
  `num_people` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reservation`
--

INSERT INTO `reservation` (`id`, `check_in_date`, `check_out_date`, `numpeople`, `price`, `reservation_code`, `hotel_booked_id`, `person_id`, `reservation_id`, `num_people`) VALUES
(1, '2024-02-23', '2024-02-29', 1, 750, '1234', 1, 1, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `room`
--

CREATE TABLE `room` (
  `id` bigint(20) NOT NULL,
  `available` bit(1) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `room_num` int(11) DEFAULT NULL,
  `room_type` varchar(255) DEFAULT NULL,
  `hotel_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `room`
--

INSERT INTO `room` (`id`, `available`, `price`, `room_num`, `room_type`, `hotel_id`) VALUES
(1, b'1', 123.12, 34, 'Single', NULL),
(2, b'1', 50, 1, 'single', NULL),
(121, b'0', 50, 2, 'Single', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticket`
--

CREATE TABLE `ticket` (
  `id` bigint(20) NOT NULL,
  `num_passengers` int(11) DEFAULT NULL,
  `num_seats_business` int(11) DEFAULT NULL,
  `num_seats_economy` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `ticket_code` varchar(255) DEFAULT NULL,
  `flight_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ticket`
--

INSERT INTO `ticket` (`id`, `num_passengers`, `num_seats_business`, `num_seats_economy`, `price`, `ticket_code`, `flight_id`) VALUES
(1, 1, 0, 1, 150, '123456', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticket_passenger`
--

CREATE TABLE `ticket_passenger` (
  `ticket_id` bigint(20) NOT NULL,
  `person_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ticket_passenger`
--

INSERT INTO `ticket_passenger` (`ticket_id`, `person_id`) VALUES
(1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ocupation`
--
ALTER TABLE `ocupation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6uvj3ll74qx3mjuomoe26lhp2` (`room_id`);

--
-- Indices de la tabla `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnilpgpwtiic6kbm4ya7oe8efd` (`hotel_booked_id`),
  ADD KEY `FKif7cbbdjfxjdtu7dnqks3r8lt` (`person_id`),
  ADD KEY `FKca8c2rmdqoiqxjnejtjyd8emm` (`reservation_id`);

--
-- Indices de la tabla `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdosq3ww4h9m2osim6o0lugng8` (`hotel_id`);

--
-- Indices de la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfju27cbcbl1w16qeora1r636q` (`flight_id`);

--
-- Indices de la tabla `ticket_passenger`
--
ALTER TABLE `ticket_passenger`
  ADD KEY `FK9we05j5fsn19runfhj50201cw` (`person_id`),
  ADD KEY `FKlte86qk8brk0ighfufbspd1g8` (`ticket_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `flight`
--
ALTER TABLE `flight`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `ocupation`
--
ALTER TABLE `ocupation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `person`
--
ALTER TABLE `person`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `room`
--
ALTER TABLE `room`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=122;

--
-- AUTO_INCREMENT de la tabla `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ocupation`
--
ALTER TABLE `ocupation`
  ADD CONSTRAINT `FK6uvj3ll74qx3mjuomoe26lhp2` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`);

--
-- Filtros para la tabla `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FKca8c2rmdqoiqxjnejtjyd8emm` FOREIGN KEY (`reservation_id`) REFERENCES `room` (`id`),
  ADD CONSTRAINT `FKif7cbbdjfxjdtu7dnqks3r8lt` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FKnilpgpwtiic6kbm4ya7oe8efd` FOREIGN KEY (`hotel_booked_id`) REFERENCES `hotel` (`id`);

--
-- Filtros para la tabla `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `FKdosq3ww4h9m2osim6o0lugng8` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`);

--
-- Filtros para la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `FKfju27cbcbl1w16qeora1r636q` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`);

--
-- Filtros para la tabla `ticket_passenger`
--
ALTER TABLE `ticket_passenger`
  ADD CONSTRAINT `FK9we05j5fsn19runfhj50201cw` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FKlte86qk8brk0ighfufbspd1g8` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
