-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Jan 26, 2018 at 04:39 AM
-- Server version: 5.7.21
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `productDatabase`
--
CREATE DATABASE IF NOT EXISTS `productDatabase` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `productDatabase`;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(64) NOT NULL auto_increment,
  `username` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `role`) VALUES
(1, 'admin', 'admin', 'ROLE_ADMIN');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


--
-- Table structure for table `Category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `categoryname` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `category` (`id`, `categoryname`) VALUES
(1, 'Java');

  
  
--
-- Table structure for table `Question type`
--


CREATE TABLE IF NOT EXISTS `questionType` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `typename` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `questionType` (`id`, `typename`) VALUES
(1, 'YesNo');
  
--
-- Table structure for table `Question`
--
  
CREATE TABLE `question` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `categoryid` int(64) NOT NULL,
  `questiontypeid` int(64) NOT NULL,
  `question` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `correctanswer` int(64) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT  fk_category FOREIGN KEY (categoryid) REFERENCES category(id),
  CONSTRAINT  fk_questionType FOREIGN KEY (questiontypeid) REFERENCES questionType(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Table structure for table `Answer`
--
CREATE TABLE `answer` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `questionid` int(64) NOT NULL,
  `answer1` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `answer2` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `answer3` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `answer4` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT  fk_question FOREIGN KEY (questionid) REFERENCES question(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


COMMIT;