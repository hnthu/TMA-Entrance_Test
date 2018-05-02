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


DROP DATABASE IF EXISTS productDatabase;
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
  `categoryId` int(64) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT INTO `category` (`categoryId`, `categoryName`) VALUES
(1, 'Java');

  
  
--
-- Table structure for table `Type`
--

CREATE TABLE IF NOT EXISTS `kind` (
  `kindId` int(64) NOT NULL AUTO_INCREMENT,
  `kindName` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`kindId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `kind` (`kindId`, `kindName`) VALUES
(1, 'YesNo');


--
-- Table structure for table `Interview`
--
  
CREATE TABLE `interview` (
  `interviewId` int(64) NOT NULL AUTO_INCREMENT,
  `questionId` int(64) NOT NULL,
  `interviewName` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`interviewId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- INSERT INTO `interview` (`interviewid`, `interviewname` , `questionlist`) VALUES
-- (1, "Kiem tra nhan vien A", "aaaaaaaaa");

  
--
-- Table structure for table `Question`
--
  
CREATE TABLE `question` (
  `questionId` int(64) NOT NULL AUTO_INCREMENT,
  `categoryId` int(64) NOT NULL,  
  `kindId` int(64) NOT NULL,
  `questionText` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  `correctAnswer` int(64) NOT NULL,
  `level` int(64) NOT NULL,
  PRIMARY KEY (`questionId`),
  CONSTRAINT  fk_category FOREIGN KEY (categoryId) REFERENCES category(categoryId),
  CONSTRAINT  fk_kind FOREIGN KEY (kindId) REFERENCES kind(kindId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- INSERT INTO `question` (`questionid`, `categoryid` , `questiontypeid`, `questiontext`, `correctanswer`) VALUES
-- (1, 1, 1, "Một câu hỏi", 1 );
--
-- Table structure for table `Answer`
--
CREATE TABLE `answer` (
  `answerId` int(64) NOT NULL AUTO_INCREMENT,
  `questionId` int(64) NOT NULL,
  `answerList` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`answerId`),
  CONSTRAINT  fk_question FOREIGN KEY (questionId) REFERENCES question(questionId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- INSERT INTO `answer` (`answerid`, `questionid` , `answer`) VALUES
-- (1, 1, "111111", "Một câu hỏi


COMMIT;