-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: test1
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `author_id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'张三','中国','法外狂徒，熟读《刑法》，抢过劫，被抢过，杀过人，被杀过，坐过牢，制过绿色海洛因，喜欢看小姑娘洗澡，和李四相爱相杀，数次给李四下过色泽鲜艳的毒药，强奸过自己。'),(2,'李华','中国','中高考英语作文常现人物，最早出现在1995年全国高考卷甚至更早的个别城市模拟卷中。他通常是某中学的学生，担任该校学生会主席。'),(3,'Rick Astley','英国','英国著名流行情歌和舞曲男歌手，代表作为《Never Gonna Give You Up》。'),(4,'Jules Gabriel Verne','法国','19世纪法国小说家、剧作家及诗人。代表作为三部曲（《格兰特船长的儿女》《海底两万里》《神秘岛》），以及《气球上的五星期》《地心游记》等。他的作品对科幻文学流派有着重要的影响，因此他与赫伯特·乔治·威尔斯一道，被称作“科幻小说之父”，还被誉为“科学时代的预言家。'),(5,'Isaac Asimov','美国','俄罗斯犹太裔美国科幻小说作家、科普作家、文学评论家，美国科幻小说黄金时代的代表人物之一。其作品中以《基地系列》《银河帝国三部曲》和《机器人系列》三大系列被誉为“科幻圣经”。曾获代表科幻界最高荣誉的雨果奖和星云终身成就大师奖。'),(6,'刘慈欣','中国','1963年6月出生于北京，祖籍河南省信阳市罗山，山西人 ，中国科幻小说代表作家之一。代表作有长篇小说《超新星纪元》《球状闪电》《三体》三部曲等，中短篇小说《流浪地球》《乡村教师》《朝闻道》《不能共存的节日》 [6]  《全频带阻塞干扰》等。其中《三体》三部曲被普遍认为是中国科幻文学的里程碑之作。'),(7,'Arthur Charles Clarke','英国','英国作家、发明家，与艾萨克·阿西莫夫、罗伯特·海因莱茵并称为二十世纪三大科幻小说家。其科幻作品多以科学为依据，小说里的许多预测都已成现实。尤其是他的卫星通讯的描写，与实际发展惊人的一致，地球同步卫星轨道因此命名为“克拉克轨道”。作品包括《童年的终结》（1953）、《月尘飘落》（1961）、《来自天穹的声音》（1965）、《帝国大地》（1976）和《2001》等。还与人合作拍摄富有创新的科学幻想片《2001年太空漫游》。'),(8,'左特','圣巢','伟大的左特（Zote the Mighty）是《空洞骑士》中的一名 NPC。他是来自圣巢之外的旅行者。自诩是骑士，但没有名望。挥舞着由壳木雕刻出来的骨钉，还给骨钉取名“生命终结者”。代表作为《左特的五十七条戒律》');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-23 15:50:27
