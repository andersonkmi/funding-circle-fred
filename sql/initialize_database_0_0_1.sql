CREATE DATABASE  IF NOT EXISTS `demo-fred` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `demo-fred`;

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

DROP TABLE IF EXISTS `gross_domestic_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gross_domestic_product` (
    obs_date DATE NOT NULL,
	value DECIMAL(18, 4),
	PRIMARY KEY (`obs_date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `gross_domestic_product` WRITE;
/*!40000 ALTER TABLE `gross_domestic_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `gross_domestic_product` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `customer_sentiment_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_sentiment_index` (
    obs_date DATE NOT NULL,
	value DECIMAL(18, 4),
	PRIMARY KEY (`obs_date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `customer_sentiment_index` WRITE;
/*!40000 ALTER TABLE `customer_sentiment_index` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_sentiment_index` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `unemployment_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unemployment_rate` (
    obs_date DATE NOT NULL,
	value DECIMAL(18, 4),
	PRIMARY KEY (`obs_date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `unemployment_rate` WRITE;
/*!40000 ALTER TABLE `unemployment_rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `unemployment_rate` ENABLE KEYS */;
UNLOCK TABLES;
