-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2021 at 09:59 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `racunari`
--

-- --------------------------------------------------------

--
-- Table structure for table `racunar`
--

CREATE TABLE `racunar` (
  `id_racunara` int(5) NOT NULL,
  `vrsta` varchar(10) NOT NULL,
  `cena` int(20) NOT NULL,
  `status_nov` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `racunar`
--

INSERT INTO `racunar` (`id_racunara`, `vrsta`, `cena`, `status_nov`) VALUES
(1, 'Desktop', 78000, 0),
(2, 'Laptop', 66000, 1),
(3, 'Laptop', 122000, 0),
(4, 'Laptop', 45000, 1),
(5, 'Laptop', 23000, 0),
(6, 'Laptop', 23000, 0),
(7, 'Desktop', 98450, 1),
(9, 'Laptop', 65700, 1),
(10, 'Laptop', 65220, 0),
(11, 'Laptop', 118000, 1),
(12, 'Laptop', 78500, 1),
(13, 'Laptop', 23000, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `racunar`
--
ALTER TABLE `racunar`
  ADD PRIMARY KEY (`id_racunara`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `racunar`
--
ALTER TABLE `racunar`
  MODIFY `id_racunara` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
