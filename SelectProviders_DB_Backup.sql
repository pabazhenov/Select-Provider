-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 05, 2019 at 06:10 PM
-- Server version: 10.3.14-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `SelectProviders`
--

-- --------------------------------------------------------

--
-- Table structure for table `Address`
--

CREATE TABLE `Address` (
  `id` int(11) NOT NULL,
  `mail_index` varchar(8) NOT NULL,
  `region` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `comment` text NOT NULL,
  `latitude` varchar(20) NOT NULL,
  `longitude` varchar(20) NOT NULL,
  `id_organisation` int(11) NOT NULL,
  `id_provider` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `Address`
--

INSERT INTO `Address` (`id`, `mail_index`, `region`, `city`, `comment`, `latitude`, `longitude`, `id_organisation`, `id_provider`) VALUES
(4, '614000', 'Пермский край', 'Пермь', 'комментарий', '56.18', '58.16', -1, 1),
(5, '618426', 'Пермский край', 'Березники', 'коммент', '56', '58', 1, -1);

-- --------------------------------------------------------

--
-- Table structure for table `Adjustment`
--

CREATE TABLE `Adjustment` (
  `id` int(11) NOT NULL,
  `danger_level` int(11) NOT NULL,
  `percentage_speed` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Adjustment`
--

INSERT INTO `Adjustment` (`id`, `danger_level`, `percentage_speed`) VALUES
(1, 1, 100),
(2, 2, 80),
(3, 3, 50),
(4, 4, 20);

-- --------------------------------------------------------

--
-- Table structure for table `AdjustmentToWeather`
--

CREATE TABLE `AdjustmentToWeather` (
  `id` int(11) NOT NULL,
  `id_weather` int(11) NOT NULL,
  `id_adjustment` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `APIKeys`
--

CREATE TABLE `APIKeys` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `api_key` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `Criteria`
--

CREATE TABLE `Criteria` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `binarytype` int(11) NOT NULL,
  `importance` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Criteria`
--

INSERT INTO `Criteria` (`id`, `title`, `binarytype`, `importance`) VALUES
(1, 'Качество поставляемой продукции', 0, 2),
(2, 'Своевременность доставки', 0, 2),
(3, 'Цена продукции', 0, 2),
(4, 'Наличие сертификатов качества', 1, 1),
(5, 'Послепродажное обслуживание', 1, 1),
(6, 'Производственная мощность', 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `CriteriaToProvider`
--

CREATE TABLE `CriteriaToProvider` (
  `id` int(11) NOT NULL,
  `id_provider` int(11) NOT NULL,
  `id_criteria` int(11) NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `CriteriaToProvider`
--

INSERT INTO `CriteriaToProvider` (`id`, `id_provider`, `id_criteria`, `value`) VALUES
(1, 1, 1, 10),
(2, 1, 2, 5),
(3, 1, 3, 6),
(4, 1, 4, 10),
(5, 1, 5, 0),
(6, 1, 6, 4);

-- --------------------------------------------------------

--
-- Table structure for table `Organisation`
--

CREATE TABLE `Organisation` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Organisation`
--

INSERT INTO `Organisation` (`id`, `title`) VALUES
(1, 'ООО \"Рога и Копыта\"');

-- --------------------------------------------------------

--
-- Table structure for table `Products`
--

CREATE TABLE `Products` (
  `id` int(11) NOT NULL,
  `title` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Products`
--

INSERT INTO `Products` (`id`, `title`) VALUES
(25, 'Говядина'),
(13, 'Желтохвост'),
(18, 'Кальмар'),
(17, 'Лосось'),
(14, 'Луциан'),
(16, 'Макрель'),
(22, 'Молюски'),
(21, 'Морской еж'),
(19, 'Осьминог'),
(20, 'Рыбная икра'),
(24, 'Свинина'),
(12, 'Тунец'),
(15, 'Угорь'),
(23, 'Яица');

-- --------------------------------------------------------

--
-- Table structure for table `ProductsToProvider`
--

CREATE TABLE `ProductsToProvider` (
  `id` int(11) NOT NULL,
  `id_provider` int(11) NOT NULL,
  `id_products` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ProductsToProvider`
--

INSERT INTO `ProductsToProvider` (`id`, `id_provider`, `id_products`) VALUES
(6, 1, 17);

-- --------------------------------------------------------

--
-- Table structure for table `Provider`
--

CREATE TABLE `Provider` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `orgn` varchar(15) NOT NULL,
  `inn` varchar(15) NOT NULL,
  `kpp` varchar(15) NOT NULL,
  `okpo` varchar(15) NOT NULL,
  `oktmo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Provider`
--

INSERT INTO `Provider` (`id`, `title`, `orgn`, `inn`, `kpp`, `okpo`, `oktmo`) VALUES
(1, 'Поставщик лосося', 'тест', 'тест', 'тест', 'тест', 'тест');

-- --------------------------------------------------------

--
-- Table structure for table `Weather`
--

CREATE TABLE `Weather` (
  `id` int(11) NOT NULL,
  `skyinfo` int(11) NOT NULL,
  `temperature` int(11) NOT NULL,
  `humidity` int(11) NOT NULL,
  `rainfall` int(11) NOT NULL,
  `snowfall` int(11) NOT NULL,
  `windspeed` int(11) NOT NULL,
  `visibility` int(11) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Address`
--
ALTER TABLE `Address`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Adjustment`
--
ALTER TABLE `Adjustment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `AdjustmentToWeather`
--
ALTER TABLE `AdjustmentToWeather`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `APIKeys`
--
ALTER TABLE `APIKeys`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Criteria`
--
ALTER TABLE `Criteria`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `title` (`title`);

--
-- Indexes for table `CriteriaToProvider`
--
ALTER TABLE `CriteriaToProvider`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Organisation`
--
ALTER TABLE `Organisation`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `title` (`title`);

--
-- Indexes for table `Products`
--
ALTER TABLE `Products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `title` (`title`);

--
-- Indexes for table `ProductsToProvider`
--
ALTER TABLE `ProductsToProvider`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Provider`
--
ALTER TABLE `Provider`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Title` (`title`(25));

--
-- Indexes for table `Weather`
--
ALTER TABLE `Weather`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Address`
--
ALTER TABLE `Address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `Adjustment`
--
ALTER TABLE `Adjustment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `AdjustmentToWeather`
--
ALTER TABLE `AdjustmentToWeather`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `APIKeys`
--
ALTER TABLE `APIKeys`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Criteria`
--
ALTER TABLE `Criteria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `CriteriaToProvider`
--
ALTER TABLE `CriteriaToProvider`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `Organisation`
--
ALTER TABLE `Organisation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `Products`
--
ALTER TABLE `Products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `ProductsToProvider`
--
ALTER TABLE `ProductsToProvider`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `Provider`
--
ALTER TABLE `Provider`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Weather`
--
ALTER TABLE `Weather`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
