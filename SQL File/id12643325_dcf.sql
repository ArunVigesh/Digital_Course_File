-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 08, 2020 at 04:44 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.12

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id12643325_dcf`
--
CREATE DATABASE IF NOT EXISTS `id12643325_dcf` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `id12643325_dcf`;

-- --------------------------------------------------------

--
-- Table structure for table `ActivityLog`
--

DROP TABLE IF EXISTS `ActivityLog`;
CREATE TABLE IF NOT EXISTS `ActivityLog` (
  `Time` timestamp(6) NOT NULL DEFAULT current_timestamp(6),
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `courseID` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `log` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Announcement`
--

DROP TABLE IF EXISTS `Announcement`;
CREATE TABLE IF NOT EXISTS `Announcement` (
  `Time` timestamp(6) NOT NULL DEFAULT current_timestamp(6),
  `announcement` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `CourseRegistration`
--

DROP TABLE IF EXISTS `CourseRegistration`;
CREATE TABLE IF NOT EXISTS `CourseRegistration` (
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `courseID` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `approval` varchar(5) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`courseID`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Courses`
--

DROP TABLE IF EXISTS `Courses`;
CREATE TABLE IF NOT EXISTS `Courses` (
  `courseID` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `courseName` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`courseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Files`
--

DROP TABLE IF EXISTS `Files`;
CREATE TABLE IF NOT EXISTS `Files` (
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `courseID` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `fileName` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `fileType` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `fileUrl` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`username`,`courseID`,`fileName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `UserFeedback`
--

DROP TABLE IF EXISTS `UserFeedback`;
CREATE TABLE IF NOT EXISTS `UserFeedback` (
  `username` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `courseID` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `feedback` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`username`,`courseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
CREATE TABLE IF NOT EXISTS `Users` (
  `username` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `firstName` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `lastName` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `department` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `phoneNo` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `approval` varchar(5) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
