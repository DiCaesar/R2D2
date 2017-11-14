/*
MySQL Backup
Source Server Version: 5.7.17
Source Database: test
Date: 2017/11/14 17:56:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `t_job_config`
-- ----------------------------
DROP TABLE IF EXISTS `t_job_config`;
CREATE TABLE `t_job_config` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `JOB_NO` varchar(10) NOT NULL,
  `JOB_NAME` varchar(64) NOT NULL,
  `JOB_GROUP` varchar(64) NOT NULL,
  `JOB_CLASS` varchar(64) NOT NULL,
  `TRADE_DATE` date NOT NULL,
  `STATUS` varchar(16) NOT NULL,
  `JOB_CRON_EXPRESS` varchar(64) NOT NULL,
  `JOB_CRON_EXPRESS_DESC` varchar(128) DEFAULT NULL,
  `JOB_EXEC_COUNT` int(10) NOT NULL,
  `RETRY_TIMES` int(10) NOT NULL,
  `LAST_EXEC_TIME` date DEFAULT NULL,
  `NEXT_EXEC_TIME` date DEFAULT NULL,
  `JOB_USED_TIME` int(10) DEFAULT NULL,
  `JOB_DESC` varchar(64) DEFAULT NULL,
  `CREATED_AT` date NOT NULL,
  `CREATED_BY` varchar(16) NOT NULL,
  `UPDATED_AT` date NOT NULL,
  `UPDATED_BY` varchar(16) NOT NULL,
  `DELETE_FLAG` varchar(16) DEFAULT NULL,
  UNIQUE KEY `inx_jobconfig_id` (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
