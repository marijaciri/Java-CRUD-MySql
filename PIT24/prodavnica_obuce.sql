-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2021 at 09:58 AM
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
-- Database: `prodavnica_obuce`
--

-- --------------------------------------------------------

--
-- Table structure for table `obuca`
--

CREATE TABLE `obuca` (
  `id_obuca` int(11) NOT NULL,
  `cena` int(25) NOT NULL,
  `broj_komada` int(10) NOT NULL,
  `vrsta` varchar(20) NOT NULL,
  `muska` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `obuca`
--

INSERT INTO `obuca` (`id_obuca`, `cena`, `broj_komada`, `vrsta`, `muska`) VALUES
(1, 4500, 10, 'Cipele', 0),
(2, 6500, 25, 'Cizme', 0),
(3, 16500, 10, 'Sandale', 0),
(4, 10999, 45, 'Patike', 1),
(5, 2200, 22, 'Cipele', 0),
(6, 7800, 45, 'Patike', 1),
(7, 4650, 50, 'Sandale', 0),
(12, 3550, 23, 'Patike', 0),
(13, 4600, 25, 'Cizme', 0),
(14, 4600, 25, 'Cizme', 0),
(15, 4600, 25, 'Cizme', 0),
(16, 3300, 10, 'Sandale', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `obuca`
--
ALTER TABLE `obuca`
  ADD PRIMARY KEY (`id_obuca`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `obuca`
--
ALTER TABLE `obuca`
  MODIFY `id_obuca` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
