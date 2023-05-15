-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Gazdă: localhost
-- Timp de generare: mai 15, 2023 la 10:24 AM
-- Versiune server: 10.4.27-MariaDB
-- Versiune PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Bază de date: `Shop`
--

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `Bill`
--

CREATE TABLE `Bill` (
  `id` int(11) NOT NULL,
  `clientId` int(11) DEFAULT NULL,
  `orderId` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Eliminarea datelor din tabel `Bill`
--

INSERT INTO `Bill` (`id`, `clientId`, `orderId`, `quantity`) VALUES
(4, 3, 564, 1),
(5, 3, 4232, 4),
(6, 3, 2345, 1),
(7, 3, 14, 34);

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `Client`
--

CREATE TABLE `Client` (
  `clientId` int(30) NOT NULL,
  `name` varchar(40) NOT NULL,
  `surname` varchar(40) NOT NULL,
  `phoneNumber` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Eliminarea datelor din tabel `Client`
--

INSERT INTO `Client` (`clientId`, `name`, `surname`, `phoneNumber`) VALUES
(3, 'eefr', 'cdshcsch', '4738'),
(4, 'pbcuw', 'hjdbcjwbc', '45643'),
(12, 'cbcvssjk', 'dcjaskakab', '374828'),
(23, 'cncscs', 'dcnscsnc', '483894298'),
(24, 'cvcx', 'fvvvsdvds', '4535'),
(25, 'cjsbsjcbs', 'hjcbcjhb', '4388387'),
(26, 'jsdcdskjcksj', 'dcjkscsckj', '48372492'),
(55, 'fgxhx', 'vncncn', '534434'),
(56, 'vvv', 'sdfsfsvf', '543'),
(78, 'fjvskv', 'vdjsksn', '478392'),
(222, 'hcbvjhsb', 'vdvbsmvbms', '56789'),
(300, 'cbsdjcbsckjs', 'jcdscjksbckjsbc', '9834'),
(345, 'dbvsdhjcsbcs', 'cdscbsdjckbck', '743289429'),
(400, 'cbsdjcbsckjs', 'jcdscjksbckjsbc', '9834'),
(457, 'chcghchgc', 'cfchgc', '657755'),
(545, 'fvsv', 'gggdgdg', '234'),
(567, 'fjvskv', 'vdjsksn', '478392'),
(3453, 'dsvfsfs', 'fbbrhtgewfs', '656789');

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `Comanda`
--

CREATE TABLE `Comanda` (
  `id` int(30) NOT NULL,
  `idclient` int(30) NOT NULL,
  `idprodus` int(30) NOT NULL,
  `quantity` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `Orders`
--

CREATE TABLE `Orders` (
  `id` int(11) NOT NULL,
  `clientId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Eliminarea datelor din tabel `Orders`
--

INSERT INTO `Orders` (`id`, `clientId`, `productId`, `quantity`) VALUES
(2, 3, 4, 3),
(3, 3, 2, 5),
(4, 12, 12, 5),
(5, 12, 12, 3),
(6, 3, 12, 1),
(7, 3, 12, 1),
(8, 3, 12, 1),
(9, 3, 12, 1),
(10, 3, 12, 1),
(11, 3, 12, 1),
(12, 3, 12, 1),
(13, 3, 12, 1),
(14, 3, 12, 34),
(32, 3, 12, 1),
(67, 3, 12, 1),
(100, 3, 12, 1),
(101, 3, 12, 1),
(102, 3, 12, 1),
(123, 3, 12, 1),
(124, 3, 12, 1),
(323, 3, 12, 1),
(564, 3, 12, 1),
(656, 3, 12, 1),
(657, 3, 12, 1),
(700, 3, 12, 1),
(701, 3, 12, 1),
(738, 3, 12, 1),
(1000, 3, 12, 10),
(2131, 3, 12, 1),
(3200, 3, 12, 20),
(4232, 3, 12, 4),
(4553, 3, 12, 1),
(23131, 3, 12, 1),
(32312, 3, 12, 23);

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `Product`
--

CREATE TABLE `Product` (
  `productId` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `stock` int(11) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Eliminarea datelor din tabel `Product`
--

INSERT INTO `Product` (`productId`, `name`, `stock`, `price`) VALUES
(2, 'dfdsvvs', 0, 100),
(3, 'ghfhgfhg', 0, 100),
(4, 'ghfhg', 10, 9),
(12, 'hcjdbsc', 13, 400),
(23, 'cvvvsvds', 7889, 56),
(42, 'fgdgdgd', 767, 43),
(100, 'vjcksc', 899, 48),
(678, 'cvjsdbvjsc', 222, 32);

--
-- Indexuri pentru tabele eliminate
--

--
-- Indexuri pentru tabele `Bill`
--
ALTER TABLE `Bill`
  ADD PRIMARY KEY (`id`);

--
-- Indexuri pentru tabele `Client`
--
ALTER TABLE `Client`
  ADD PRIMARY KEY (`clientId`);

--
-- Indexuri pentru tabele `Comanda`
--
ALTER TABLE `Comanda`
  ADD PRIMARY KEY (`id`);

--
-- Indexuri pentru tabele `Orders`
--
ALTER TABLE `Orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_ClientId` (`clientId`),
  ADD KEY `FK_ProductId` (`productId`);

--
-- Indexuri pentru tabele `Product`
--
ALTER TABLE `Product`
  ADD PRIMARY KEY (`productId`);

--
-- AUTO_INCREMENT pentru tabele eliminate
--

--
-- AUTO_INCREMENT pentru tabele `Bill`
--
ALTER TABLE `Bill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pentru tabele `Client`
--
ALTER TABLE `Client`
  MODIFY `clientId` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5435346;

--
-- AUTO_INCREMENT pentru tabele `Comanda`
--
ALTER TABLE `Comanda`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pentru tabele `Orders`
--
ALTER TABLE `Orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32313;

--
-- AUTO_INCREMENT pentru tabele `Product`
--
ALTER TABLE `Product`
  MODIFY `productId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=679;

--
-- Constrângeri pentru tabele eliminate
--

--
-- Constrângeri pentru tabele `Orders`
--
ALTER TABLE `Orders`
  ADD CONSTRAINT `FK_ClientId` FOREIGN KEY (`clientId`) REFERENCES `Client` (`clientId`),
  ADD CONSTRAINT `FK_ProductId` FOREIGN KEY (`productId`) REFERENCES `Product` (`productId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
