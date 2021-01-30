-- MySQL dump 10.13  Distrib 5.7.29, for Win64 (x86_64)
--
-- Host: localhost    Database: db3
-- ------------------------------------------------------
-- Server version	5.7.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(30) NOT NULL,
  `balance` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'zhangsan',1000),(2,'lisi',1000);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dept` (
  `id` int(11) NOT NULL,
  `dname` varchar(50) DEFAULT NULL,
  `loc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` VALUES (10,'鏁欑爺閮?,'鍖椾含'),(20,'瀛﹀伐閮?,'涓婃捣'),(30,'閿€鍞儴','骞垮窞'),(40,'璐㈠姟閮?,'娣卞湷');
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp`
--

DROP TABLE IF EXISTS `emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emp` (
  `id` int(11) NOT NULL,
  `ename` varchar(50) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  `mgr` int(11) DEFAULT NULL,
  `joindate` date DEFAULT NULL,
  `salary` decimal(7,2) DEFAULT NULL,
  `bonus` decimal(7,2) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `emp_jobid_ref_job_id_fk` (`job_id`),
  KEY `emp_deptid_ref_dept_id_fk` (`dept_id`),
  CONSTRAINT `emp_deptid_ref_dept_id_fk` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`),
  CONSTRAINT `emp_jobid_ref_job_id_fk` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp`
--

LOCK TABLES `emp` WRITE;
/*!40000 ALTER TABLE `emp` DISABLE KEYS */;
INSERT INTO `emp` VALUES (1001,'瀛欐偀绌?,4,1004,'2000-12-17',8000.00,NULL,20),(1002,'鍗繆涔?,3,1006,'2001-02-20',16000.00,3000.00,30),(1003,'鏋楀啿',3,1006,'2001-02-22',12500.00,5000.00,30),(1004,'鍞愬儳',2,1009,'2001-04-02',29750.00,NULL,20),(1005,'鏉庨€?,4,1006,'2001-09-28',12500.00,14000.00,30),(1006,'瀹嬫睙',2,1009,'2001-05-01',28500.00,NULL,30),(1007,'鍒樺',2,1009,'2001-09-01',24500.00,NULL,10),(1008,'鐚叓鎴?,4,1004,'2007-04-19',30000.00,NULL,20),(1009,'缃楄疮涓?,1,NULL,'2001-11-17',50000.00,NULL,10),(1010,'鍚寸敤',3,1006,'2001-09-08',15000.00,0.00,30),(1011,'娌欏儳',4,1004,'2007-05-23',11000.00,NULL,20),(1012,'鏉庨€?,4,1006,'2001-12-03',9500.00,NULL,30),(1013,'灏忕櫧榫?,4,1004,'2001-12-03',30000.00,NULL,20),(1014,'鍏崇窘',4,1007,'2002-01-23',13000.00,NULL,10);
/*!40000 ALTER TABLE `emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job` (
  `id` int(11) NOT NULL,
  `jname` varchar(20) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,'钁ｄ簨闀?,'绠＄悊鏁翠釜鍏徃锛屾帴鍗?),(2,'缁忕悊','绠＄悊閮ㄩ棬鍛樺伐'),(3,'閿€鍞憳','鍚戝浜烘帹閿€浜у搧'),(4,'鏂囧憳','浣跨敤鍔炲叕杞欢');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salarygrade`
--

DROP TABLE IF EXISTS `salarygrade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salarygrade` (
  `grade` int(11) NOT NULL,
  `losalary` int(11) DEFAULT NULL,
  `hisalary` int(11) DEFAULT NULL,
  PRIMARY KEY (`grade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salarygrade`
--

LOCK TABLES `salarygrade` WRITE;
/*!40000 ALTER TABLE `salarygrade` DISABLE KEYS */;
INSERT INTO `salarygrade` VALUES (1,7000,12000),(2,12010,14000),(3,14010,20000),(4,20010,30000),(5,30010,99990);
/*!40000 ALTER TABLE `salarygrade` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-30 17:35:34
