-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.6.5-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- ridibooks 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `ridibooks` /*!40100 DEFAULT CHARACTER SET utf8mb3 */;
USE `ridibooks`;

-- 테이블 ridibooks.memberinfo 구조 내보내기
CREATE TABLE IF NOT EXISTS `memberinfo` (
  `member_num` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(20) NOT NULL DEFAULT '',
  `pw` varchar(20) NOT NULL DEFAULT '',
  `email` varchar(50) NOT NULL DEFAULT '',
  `name` varchar(10) NOT NULL DEFAULT '',
  `birthdate` int(11) NOT NULL DEFAULT 0,
  `gender` char(50) NOT NULL DEFAULT '',
  `agree` bit(1) NOT NULL DEFAULT b'0',
  `event` bit(1) NOT NULL DEFAULT b'0',
  `info` bit(1) NOT NULL DEFAULT b'0',
  `personal` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`member_num`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COMMENT='회원정보테이블';

-- 테이블 데이터 ridibooks.memberinfo:~3 rows (대략적) 내보내기
DELETE FROM `memberinfo`;
/*!40000 ALTER TABLE `memberinfo` DISABLE KEYS */;
INSERT INTO `memberinfo` (`member_num`, `id`, `pw`, `email`, `name`, `birthdate`, `gender`, `agree`, `event`, `info`, `personal`) VALUES
	(1, 'happyrich', 'happyrich', 'myloverich@gmail.com', '권리치', 2009, 'w', b'0', b'0', b'0', b'0'),
	(2, 'victoria', 'vicky', 'mylovevictoria@gmail.com', '빅토리', 2010, 'w', b'0', b'0', b'0', b'0'),
	(3, 'woonmyung', 'destiny', 'whenieathappy@gmail.com', '운', 2011, 'm', b'0', b'0', b'0', b'0');
/*!40000 ALTER TABLE `memberinfo` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
