-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2017-01-08 12:29:27
-- 服务器版本： 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `mis`
--
CREATE DATABASE IF NOT EXISTS `mis` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `mis`;

-- --------------------------------------------------------

--
-- 表的结构 `student`
--
-- 创建时间： 2017-01-03 07:58:10
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `stu_id` int(10) unsigned NOT NULL,
  `stu_name` varchar(20) DEFAULT NULL,
  `sex` char(2) DEFAULT '男',
  `age` int(3) DEFAULT NULL,
  `stu_class` varchar(20) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`stu_id`),
  UNIQUE KEY `stu_id` (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `student`
--

INSERT INTO `student` (`stu_id`, `stu_name`, `sex`, `age`, `stu_class`, `phone`, `address`) VALUES
(1449, '春', '女', 18, '计算机1409班', '10086', '上海'),
(1569, '夏', '男', 21, '软件1409班', '10010', '广州'),
(1668, '冬', '女', 21, '管理1409班', '13579869987', '西安'),
(1998, '秋', '女', 18, '公关1001班', '10000', '杭州'),
(3964, '小明', '男', 18, '计科1402班', '18864', '美国'),
(5568, '李四', '男', 29, '软件1409班', '9999', '合肥'),
(10086, '中国移动', '女', 18, '计科1503班', '10086', '中国移动总部'),
(123456, '张三', '男', 23, '计科1402班', '10058', '长沙'),
(1998888, '王五', '男', 18, '软件1409班', '8888', '天津'),
(140930037, '小二', '男', 23, '计科1503班', '13899998888', '北京');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--
-- 创建时间： 2016-12-27 11:28:18
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `authority` tinyint(4) DEFAULT '1' COMMENT '权限（值大的权限大）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `authority`) VALUES
(1, 'admin', '271196047404924797283808617552923845213', 2),
(9, 'fu', '51970679560241363929572019794406153796', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
