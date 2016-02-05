-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2016-02-05 23:44:28
-- 服务器版本： 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ws_td5`
--

-- --------------------------------------------------------

--
-- 表的结构 `office`
--

CREATE TABLE IF NOT EXISTS `office` (
  `ID` int(255) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `manager_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `year_founded` int(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- 转存表中的数据 `office`
--

INSERT INTO `office` (`ID`, `city`, `manager_name`, `email`, `year_founded`) VALUES
(1, 'paris', 'Jean Doe', 'paris@rest.fr', 1998),
(2, 'paris', 'Yishuo LYU', 'yishuo.lyu@qq.com', 2016),
(3, 'London', 'Xiao Hua', 'xiaohuan@gmail.com', 2001),
(4, 'NY', 'Jack Chen', 'jack@hotmail.com', 2010),
(5, 'Hong Kong', 'Jian Hua', 'jianhua@hotmail.com', 2015),
(7, 'Beijing', 'xiaohua', 'xiaohua@gmail.com', 1991),
(8, 'Xi''an', 'hui', 'hui@hui.com', 1781),
(9, 'Luoyang', 'yishuo', 'yishuo.lyu@gmail.com', 1991),
(10, 'Lyon', 'lisa', 'lisa@hotmail.com', 1909),
(11, 'Lyon', 'manager3', 'Lyon@rest.fr', 2007),
(12, 'Lyon', 'manager3', 'Lyon@rest.fr', 2007),
(13, 'Lyon', 'manager3', 'Lyon@rest.fr', 2007),
(14, 'Lyon', 'manager3', 'Lyon@rest.fr', 2007),
(15, 'Lyon', 'manager3', 'Lyon@rest.fr', 2007);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
