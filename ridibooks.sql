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

-- 테이블 ridibooks.marketinginfo 구조 내보내기
CREATE TABLE IF NOT EXISTS `marketinginfo` (
  `marketing_num` int(11) NOT NULL AUTO_INCREMENT,
  `memberInfo_num` int(11) NOT NULL,
  `subEmail` varchar(50) DEFAULT NULL COMMENT '구독 받을 이메일',
  `emailagree` bit(1) DEFAULT NULL COMMENT '이메일 구독',
  `appagree` bit(1) DEFAULT NULL COMMENT '앱 푸시 수신',
  `appnightagree` bit(1) DEFAULT NULL COMMENT '야간 앱 푸시 수신',
  PRIMARY KEY (`marketing_num`),
  KEY `fk_memberInfo_marketInfo` (`memberInfo_num`),
  CONSTRAINT `fk_memberInfo_marketInfo` FOREIGN KEY (`memberInfo_num`) REFERENCES `memberinfo` (`member_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3 COMMENT='마케팅 정보 수신 관리';

-- 테이블 데이터 ridibooks.marketinginfo:~5 rows (대략적) 내보내기
DELETE FROM `marketinginfo`;
/*!40000 ALTER TABLE `marketinginfo` DISABLE KEYS */;
INSERT INTO `marketinginfo` (`marketing_num`, `memberInfo_num`, `subEmail`, `emailagree`, `appagree`, `appnightagree`) VALUES
	(15, 59, 'estelle1@gmail.com', b'0', b'0', b'1'),
	(16, 60, 'estelle2@gmail.com', b'0', b'0', b'1'),
	(17, 61, 'estelle3@gmail.com', b'0', b'0', b'1'),
	(18, 62, 'estelle4@gmail.com', b'0', b'0', b'1'),
	(19, 63, 'estelle5@gmail.com', b'0', b'0', b'1');
/*!40000 ALTER TABLE `marketinginfo` ENABLE KEYS */;

-- 테이블 ridibooks.memberinfo 구조 내보내기
CREATE TABLE IF NOT EXISTS `memberinfo` (
  `member_num` int(11) NOT NULL AUTO_INCREMENT COMMENT '회원 pk',
  `id` varchar(20) NOT NULL DEFAULT '' COMMENT '회원  id',
  `pw` varchar(20) NOT NULL DEFAULT '' COMMENT '회원 비밀번호',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '회원 이메일',
  `name` varchar(10) NOT NULL DEFAULT '' COMMENT '회원 이름',
  `birthdate` int(11) DEFAULT 0 COMMENT '회원 출생년도',
  `gender` varchar(50) DEFAULT NULL COMMENT '회원 성별',
  `agree` bit(1) NOT NULL DEFAULT b'0' COMMENT '이용약관 동의(필수)',
  `event` bit(1) NOT NULL DEFAULT b'0' COMMENT '이벤트, 혜택 알림 수신 동의(선택)',
  `info` bit(1) NOT NULL DEFAULT b'0' COMMENT '성별, 생년 정보 제공 동의(선택)',
  `personal` bit(1) NOT NULL DEFAULT b'0' COMMENT '개인 정보 수집 및 이용 동의(필수)',
  `indate` datetime NOT NULL DEFAULT current_timestamp() COMMENT '회원 가입 시간',
  `ismember` bit(1) NOT NULL DEFAULT b'0' COMMENT '가입되어 있으면 : 0\r\n탈퇴 했으면 : 1',
  PRIMARY KEY (`member_num`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb3 COMMENT='회원정보테이블\r\n\r\n- 체크박스 -\r\n동의: 0 / 동의안함: 1';

-- 테이블 데이터 ridibooks.memberinfo:~5 rows (대략적) 내보내기
DELETE FROM `memberinfo`;
/*!40000 ALTER TABLE `memberinfo` DISABLE KEYS */;
INSERT INTO `memberinfo` (`member_num`, `id`, `pw`, `email`, `name`, `birthdate`, `gender`, `agree`, `event`, `info`, `personal`, `indate`, `ismember`) VALUES
	(59, 'estelle1', 'hello007', 'estelle1@gmail.com', '에스텔', 1990, 'f', b'0', b'0', b'0', b'0', '2022-02-06 16:30:51', b'0'),
	(60, 'estelle2', 'hello007', 'estelle2@gmail.com', '에스텔', 1990, 'f', b'0', b'0', b'0', b'0', '2022-02-06 22:40:45', b'0'),
	(61, 'estelle3', 'hello007', 'estelle3@gmail.com', '에스텔', 1990, 'f', b'0', b'0', b'0', b'0', '2022-02-06 22:41:04', b'1'),
	(62, 'estelle4', 'hello007', 'estelle4@gmail.com', '에스텔', 1990, 'f', b'0', b'1', b'1', b'0', '2022-02-06 22:41:40', b'1'),
	(63, 'estelle5', 'hello007', 'estelle5@gmail.com', '에스텔', 1990, 'f', b'0', b'1', b'1', b'0', '2022-02-06 22:41:58', b'0');
/*!40000 ALTER TABLE `memberinfo` ENABLE KEYS */;

-- 테이블 ridibooks.withdrawalinfo 구조 내보내기
CREATE TABLE IF NOT EXISTS `withdrawalinfo` (
  `withdrawal_num` int(11) NOT NULL AUTO_INCREMENT COMMENT '회원탈퇴 pk',
  `memberInfo_num` int(11) NOT NULL COMMENT 'memberInfo pk 값 ',
  `noBook` bit(1) DEFAULT NULL COMMENT '원하는 책이 부족해서',
  `noBenefit` bit(1) DEFAULT NULL COMMENT '회원 혜택이 부족해서',
  `systemError` bit(1) DEFAULT NULL COMMENT '시스템 오류가 잦아서',
  `lateResponse` bit(1) DEFAULT NULL COMMENT '불만, 불편 사항에 대한 응대가 늦어서',
  `noUse` bit(1) DEFAULT NULL COMMENT '자주 사용하지 않아서',
  `concernedPs` bit(1) DEFAULT NULL COMMENT '개인 정보 및 보안이 우려되어서',
  `etc` bit(1) DEFAULT NULL COMMENT '기타',
  `pw` varchar(50) NOT NULL COMMENT '본인 확인을 위해 비밀번호를 입력해주세요.',
  `withdrawalAgree` bit(1) NOT NULL COMMENT '위 내용을 이해했으며, 모두 동의합니다.',
  `outdate` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`withdrawal_num`),
  KEY `fk_memberInfo_withdrawlInfo` (`memberInfo_num`),
  CONSTRAINT `fk_memberInfo_withdrawlInfo` FOREIGN KEY (`memberInfo_num`) REFERENCES `memberinfo` (`member_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='회원 탈퇴 로그';

-- 테이블 데이터 ridibooks.withdrawalinfo:~2 rows (대략적) 내보내기
DELETE FROM `withdrawalinfo`;
/*!40000 ALTER TABLE `withdrawalinfo` DISABLE KEYS */;
INSERT INTO `withdrawalinfo` (`withdrawal_num`, `memberInfo_num`, `noBook`, `noBenefit`, `systemError`, `lateResponse`, `noUse`, `concernedPs`, `etc`, `pw`, `withdrawalAgree`, `outdate`) VALUES
	(1, 61, b'1', b'1', b'1', b'0', b'0', b'1', b'0', 'hello007', b'0', '2022-02-06 22:47:53'),
	(2, 62, b'0', b'1', b'1', b'1', b'1', b'1', b'0', 'hello007', b'0', '2022-02-06 22:57:46');
/*!40000 ALTER TABLE `withdrawalinfo` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
