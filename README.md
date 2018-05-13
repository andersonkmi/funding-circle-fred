# Funding Circle Data Engineering Coding Quiz

## Introduction 
For this exercise, we would like you to build a simple application for pulling and storing different kinds of
macroeconomic data using the Federal Reserve’s FRED API. The FRED API provides a RESTful means of
accessing many datasets published by the Federal Reserve – unemployment rates, interest rates, GDP etc.

The data are modelled as a ‘series’ of ‘observations’ with each observation having a date and value. For more
details on the API and datasets, visit https://research.stlouisfed.org/docs/api/fred/ The endpoint you will be
most interested in is fred/series/observations, so you may want to read the docs for that one in particular.

You’ll need to create a FRED user account and API key for yourself, which you can do here:
https://research.stlouisfed.org/useraccount/register/step1 Once you have an account, request your API key and
use it in your requests.

## Requirements

1. Your application should fetch the following FRED series in their entirety:
    * Real Gross Domestic Product (GDPC1)
    * University of Michigan Customer Sentiment Index (UMCSENT)
    * US Civilian Unemployment Rate (UNRATE)

2. Your application should store the observations in a relational database running on a localhost.

3. Your application should allow for two data flows: initial and incremental. The initial data flow should
   clear up the data persisted in the database (if any) and load it afresh. The incremental data flow should
   only load the data that is not present in the database yet.
   
4. Please answer the following question using the observations stored in your database:
   What was the average rate of unemployment for each year starting in 1980 and going up to 2015?
   
## Technical requirements

In order to build and run this coding quiz, there are some requirements that must be met. They are
described as follows.

### Build requirements

In order to build this project the following requirements must be met in the machine:

1. Docker 18 or later

2. Docker composer 1.21.1 or later

3. Java SDK 1.8.0_172

4. Gradle 4.8 or later

### Federal Reserve Bank of St. Louis APIs key configuration

In order to access the APIs of the Federal Reserve Bank, it is recommended to change the key value
located at *src/main/resources/fred.properties* prior to build the application.

That file contains the endpoints URLs for each of the time series and the it has a key
already in place, but you must use your own key.

~~~
gdpc_url=https://api.stlouisfed.org/fred/series/observations?series_id=GDPC1&api_key=[key_must_be_here]&file_type=json
umcsent_url=https://api.stlouisfed.org/fred/series/observations?series_id=UMCSENT&api_key=[key_must_be_here]&file_type=json
unrate_url=https://api.stlouisfed.org/fred/series/observations?series_id=UNRATE&api_key=[key_must_be_here]&file_type=json
~~~

### How to build the application

In order to build the project, the following command must be executed in the root folder:
~~~
$ docker build docker
~~~

The above command will compile the source code and creates the application JAR file. This application is built upon Spring
Boot framework. Once the JAR file is created, a Docker image is created and registered locally.

### How to bring up the application

Once the Docker image is created with the application, in order to execute it the following command should be executed:
~~~
$ docker-compose up
~~~

The docker composer will start the application container as well the MySQL container associated with it. It also runs the 
DDL script creating the required tables. Such script can be found at the path sql/initialize_database_0_0_1.sql. Its 
contents are:
```sql
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
    obs_date DATETIME NOT NULL,
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
    obs_date DATETIME NOT NULL,
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
    obs_date DATETIME NOT NULL,
	value DECIMAL(18, 4),
	PRIMARY KEY (`obs_date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `unemployment_rate` WRITE;
/*!40000 ALTER TABLE `unemployment_rate` DISABLE KEYS */;
/*!40000 ALTER TABLE `unemployment_rate` ENABLE KEYS */;
UNLOCK TABLES;
```

After docker composer is executed, the following messages will be displayed indicating the application and its associated
MySQL database is ready for use.

~~~
fred-db          | 2018-05-13T02:17:58.851780Z 0 [Note] InnoDB: File './ibtmp1' size is now 12 MB.
fred-db          | 2018-05-13T02:17:58.854467Z 0 [Note] InnoDB: 96 redo rollback segment(s) found. 96 redo rollback segment(s) are active.
fred-db          | 2018-05-13T02:17:58.854666Z 0 [Note] InnoDB: 32 non-redo rollback segment(s) are active.
fred-db          | 2018-05-13T02:17:58.856449Z 0 [Note] InnoDB: Waiting for purge to start
fred-db          | 2018-05-13T02:17:58.906861Z 0 [Note] InnoDB: 5.7.22 started; log sequence number 12372777
fred-db          | 2018-05-13T02:17:58.907478Z 0 [Note] InnoDB: Loading buffer pool(s) from /var/lib/mysql/ib_buffer_pool
fred-db          | 2018-05-13T02:17:58.909262Z 0 [Note] Plugin 'FEDERATED' is disabled.
fred-db          | 2018-05-13T02:17:58.914650Z 0 [Note] InnoDB: Buffer pool(s) load completed at 180513  2:17:58
fred-db          | 2018-05-13T02:17:58.921518Z 0 [Note] Found ca.pem, server-cert.pem and server-key.pem in data directory. Trying to enable SSL support using them.
fred-db          | 2018-05-13T02:17:58.922180Z 0 [Warning] CA certificate ca.pem is self signed.
fred-db          | 2018-05-13T02:17:58.929388Z 0 [Note] Server hostname (bind-address): '*'; port: 3306
fred-db          | 2018-05-13T02:17:58.929610Z 0 [Note] IPv6 is available.
fred-db          | 2018-05-13T02:17:58.929678Z 0 [Note]   - '::' resolves to '::';
fred-db          | 2018-05-13T02:17:58.930159Z 0 [Note] Server socket created on IP: '::'.
fred-db          | 2018-05-13T02:17:59.023117Z 0 [Warning] Insecure configuration for --pid-file: Location '/var/run/mysqld' in the path is accessible to all OS users. Consider choosing a different directory.
fred-db          | 2018-05-13T02:17:59.025935Z 0 [Warning] 'user' entry 'root@localhost' ignored in --skip-name-resolve mode.
fred-db          | 2018-05-13T02:17:59.026219Z 0 [Warning] 'user' entry 'mysql.session@localhost' ignored in --skip-name-resolve mode.
fred-db          | 2018-05-13T02:17:59.026422Z 0 [Warning] 'user' entry 'mysql.sys@localhost' ignored in --skip-name-resolve mode.
fred-db          | 2018-05-13T02:17:59.026599Z 0 [Warning] 'db' entry 'performance_schema mysql.session@localhost' ignored in --skip-name-resolve mode.
fred-db          | 2018-05-13T02:17:59.026841Z 0 [Warning] 'db' entry 'sys mysql.sys@localhost' ignored in --skip-name-resolve mode.
fred-db          | 2018-05-13T02:17:59.026947Z 0 [Warning] 'proxies_priv' entry '@ root@localhost' ignored in --skip-name-resolve mode.
fred-db          | 2018-05-13T02:17:59.031872Z 0 [Warning] 'tables_priv' entry 'user mysql.session@localhost' ignored in --skip-name-resolve mode.
fred-db          | 2018-05-13T02:17:59.031988Z 0 [Warning] 'tables_priv' entry 'sys_config mysql.sys@localhost' ignored in --skip-name-resolve mode.
fred-db          | 2018-05-13T02:17:59.045184Z 0 [Note] Event Scheduler: Loaded 0 events
fred-db          | 2018-05-13T02:17:59.046233Z 0 [Note] mysqld: ready for connections.
fred-db          | Version: '5.7.22'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server (GPL)
~~~

It is recommended to wait 1 to 3 minutes for complete application and MySQL start-up for the 
first time.

### How to bring down the application

In order to shutdown the containers, the following command should be executed:
~~~
$ docker-compose down
~~~

This command will shutdown the containers and remove the internal network created such environment. It is important
to note that the images must be removed manually later.

## Executing the data loads

In order to load the data initially and incrementally, some endpoints were created to make it easy
to perform such operations. They are described as follows.

### Real Gross Domestic Product (GDPC1)

#### Initial data load

~~~
http://localhost:8080/gdpc/performInitialLoad
~~~

#### Incremental data load

~~~
http://localhost:8080/gdpc/performIncrementalLoad
~~~

### University of Michigan Customer Sentiment Index (UMCSENT)

#### Initial data load

~~~
http://localhost:8080/umcsent/performInitialLoad
~~~

#### Incremental data load

~~~
http://localhost:8080/umcsent/performIncrementalLoad
~~~

### US Civilian Unemployment Rate (UNRATE)

#### Initial data load

~~~
http://localhost:8080/unrate/performInitialLoad
~~~

#### Incremental data load

~~~
http://localhost:8080/unrate/performIncrementalLoad
~~~

At the end of each execution the resulting message is displayed:
```json
{
"timeSeries": "[Name of the time series]",
"code": 0,
"message": "Inserted 844 observations"
}
```

## Verifying the data loads

Once the environment is up and running, it is possible to access the MySQL database using a client program, i.e. MySQL
Workbench from the localhost using the following credentials:
~~~
Hostname: localhost or 127.0.0.1
Port: 3306
User: dbuser
Password: dbp4ss
Schema: demo-fred
~~~

## What was the average rate of unemployment for each year starting in 1980 and going up to 2015?

In order to answer this question the following SQL script was created:
```mysql-sql
select year(obs_date) as year, avg(value) as average from unemployment_rate where year(obs_date) >= 1980 and year(obs_date) <= 2015 group by year(obs_date);
```

It can also be found in the file *sql/average-unemployment-rate.sql* inside the project.

It was also created an endpoint to see such results:
~~~
http://localhost:8080/unrate/unemploymenRates
~~~