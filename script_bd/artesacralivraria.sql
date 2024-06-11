CREATE DATABASE  IF NOT EXISTS `artesacralivraria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `artesacralivraria`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: artesacralivraria
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `categoria_profissional`
--

DROP TABLE IF EXISTS `categoria_profissional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria_profissional` (
  `id_categoria_profissional` int NOT NULL AUTO_INCREMENT,
  `nome_categoria_profissional` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_categoria_profissional`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_profissional`
--

LOCK TABLES `categoria_profissional` WRITE;
/*!40000 ALTER TABLE `categoria_profissional` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoria_profissional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `numero_contribuinte` varchar(45) DEFAULT NULL,
  `nome_cliente` varchar(45) DEFAULT NULL,
  `sobrenome_cliente` varchar(45) DEFAULT NULL,
  `sexo_cliente` varchar(45) DEFAULT NULL,
  `email_cliente` varchar(45) DEFAULT NULL,
  `telefone_cliente` varchar(45) DEFAULT NULL,
  `casa_cliente` varchar(45) DEFAULT NULL,
  `rua_cliente` varchar(45) DEFAULT NULL,
  `bairro_cliente` varchar(45) DEFAULT NULL,
  `distrito_cliente` varchar(45) DEFAULT NULL,
  `id_municipio` int NOT NULL,
  PRIMARY KEY (`id_cliente`),
  KEY `fk_cliente_municipio1_idx` (`id_municipio`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (4,NULL,'Vários','','','M','','','','','',95),(5,NULL,'Vários','','','M','','','','','',95),(6,NULL,'Chefe Maniha','f','F',NULL,'938059553','21','5','Nelito ',NULL,1),(7,NULL,'Chefe Mania ','s','F',NULL,'938059553','21','5','Nelito ',NULL,1),(8,NULL,'Maria ','Hangalo','F',NULL,'922385878','21','5','kibango',NULL,5),(9,NULL,'Dona ','Ana','F',NULL,'','5','34','São Paulo',NULL,3),(10,NULL,'Teresa','Suárez','F',NULL,'','23','8','São Paulo',NULL,95),(11,NULL,'Bilson ','Ramos','M',NULL,'955490324','9','34','Zango 1',NULL,97),(12,NULL,'Lucilio Vera ','Cruz','M',NULL,'941138707','54','3','São Paulo',NULL,95),(13,NULL,'Maliet','f','F',NULL,'','9','3','Nelito Suares',NULL,93),(14,NULL,'Margareth','Borges','F',NULL,'','7','8','Nelito ',NULL,95);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura_venda_produto`
--

DROP TABLE IF EXISTS `factura_venda_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura_venda_produto` (
  `id_factura_venda` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int NOT NULL,
  `data_factura_venda` date DEFAULT NULL,
  `valor_total` double DEFAULT NULL,
  `id_forma_de_pagamento` int NOT NULL,
  `data_hora_registo` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_profissional` int DEFAULT NULL,
  PRIMARY KEY (`id_factura_venda`),
  KEY `fk_factura_venda_produto_forma_de_pagamento1_idx` (`id_forma_de_pagamento`),
  KEY `fk_factura_venda_produto_cliente1_idx` (`id_cliente`),
  KEY `fk_factira_venda_produto_profissional_idx` (`id_profissional`),
  CONSTRAINT `fk_factira_venda_produto_profissional` FOREIGN KEY (`id_profissional`) REFERENCES `profissional` (`id_profissional`),
  CONSTRAINT `fk_factura_venda_produto_cliente1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `fk_factura_venda_produto_forma_de_pagamento1` FOREIGN KEY (`id_forma_de_pagamento`) REFERENCES `forma_de_pagamento` (`id_forma_de_pagamento`)
) ENGINE=InnoDB AUTO_INCREMENT=2344 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_venda_produto`
--

LOCK TABLES `factura_venda_produto` WRITE;
/*!40000 ALTER TABLE `factura_venda_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura_venda_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura_venda_produto_detalhes`
--

DROP TABLE IF EXISTS `factura_venda_produto_detalhes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura_venda_produto_detalhes` (
  `id_detalhes_venda` int NOT NULL AUTO_INCREMENT,
  `id_produto` int NOT NULL,
  `id_factura_venda` int NOT NULL,
  `quantidade_vendida` int DEFAULT NULL,
  `preco_venda` double DEFAULT NULL,
  `desconto` double DEFAULT NULL,
  PRIMARY KEY (`id_detalhes_venda`),
  KEY `fk_factura_venda_produto_detalhes_produto1_idx` (`id_produto`),
  KEY `fk_factura_venda_produto_detalhes_factura_venda_produto1_idx` (`id_factura_venda`),
  CONSTRAINT `fk_factura_venda_produto_detalhes_factura_venda_produto1` FOREIGN KEY (`id_factura_venda`) REFERENCES `factura_venda_produto` (`id_factura_venda`),
  CONSTRAINT `fk_factura_venda_produto_detalhes_produto1` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`)
) ENGINE=InnoDB AUTO_INCREMENT=4843 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_venda_produto_detalhes`
--

LOCK TABLES `factura_venda_produto_detalhes` WRITE;
/*!40000 ALTER TABLE `factura_venda_produto_detalhes` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura_venda_produto_detalhes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `familia_do_produto`
--

DROP TABLE IF EXISTS `familia_do_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `familia_do_produto` (
  `id_familia_do_produto` int NOT NULL AUTO_INCREMENT,
  `descricao_familia_do_produto` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_familia_do_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familia_do_produto`
--

LOCK TABLES `familia_do_produto` WRITE;
/*!40000 ALTER TABLE `familia_do_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `familia_do_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forma_de_pagamento`
--

DROP TABLE IF EXISTS `forma_de_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forma_de_pagamento` (
  `id_forma_de_pagamento` int NOT NULL AUTO_INCREMENT,
  `descricao_forma_de_pagamento` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_forma_de_pagamento`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forma_de_pagamento`
--

LOCK TABLES `forma_de_pagamento` WRITE;
/*!40000 ALTER TABLE `forma_de_pagamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `forma_de_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materia_prima`
--

DROP TABLE IF EXISTS `materia_prima`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materia_prima` (
  `id_materia_prima` int NOT NULL AUTO_INCREMENT,
  `nome_materia_prima` varchar(45) DEFAULT NULL,
  `data_expira_materia_prima` date DEFAULT NULL,
  `quantidade_stock_materia_prima` int DEFAULT '0',
  `id_tipo_de_produto` int NOT NULL,
  PRIMARY KEY (`id_materia_prima`),
  KEY `fk_materia_prima_tipo_de_produto1_idx` (`id_tipo_de_produto`),
  CONSTRAINT `fk_materia_prima_tipo_de_produto1` FOREIGN KEY (`id_tipo_de_produto`) REFERENCES `tipo_de_produto` (`id_tipo_de_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materia_prima`
--

LOCK TABLES `materia_prima` WRITE;
/*!40000 ALTER TABLE `materia_prima` DISABLE KEYS */;
/*!40000 ALTER TABLE `materia_prima` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `municipio`
--

DROP TABLE IF EXISTS `municipio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `municipio` (
  `id_municipio` int NOT NULL AUTO_INCREMENT,
  `nome_municipio` varchar(45) DEFAULT NULL,
  `id_provincia` int NOT NULL,
  PRIMARY KEY (`id_municipio`),
  KEY `fk_municipio_provincia_idx` (`id_provincia`),
  CONSTRAINT `fk_municipio_provincia` FOREIGN KEY (`id_provincia`) REFERENCES `provincia` (`id_provincia`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `municipio`
--

LOCK TABLES `municipio` WRITE;
/*!40000 ALTER TABLE `municipio` DISABLE KEYS */;
INSERT INTO `municipio` VALUES (1,'Ambriz',1),(2,'Bula Atumba',1),(3,'Dande',1),(4,'Dembos',1),(5,'Nambuangongo',1),(6,'Pango Aluquém',1),(7,'Balombo',2),(8,'Baia Farta',2),(9,'Benguela',2),(10,'Bocoio',2),(11,'Caimbambo',2),(12,'Catumbela',2),(13,'Chongoroi',2),(14,'Cubal',2),(15,'Ganda',2),(16,'Lobito',2),(17,'Andulo',3),(18,'Camacupa',3),(19,'Catabola',3),(20,'Chinguar',3),(21,'Chitembo',3),(22,'Cuemba',3),(23,'Cunhinga',3),(24,'Kuito',3),(25,'Nharea',3),(26,'Belize',4),(27,'Buco-Zau',4),(28,'Cabinda',4),(29,'Cacongo',4),(30,'Calai',5),(31,'Cuangar',5),(32,'Cuchi',5),(33,'Cuito Cuanavale',5),(34,'Dirico',5),(35,'Mavinga',5),(36,'Menongue',5),(37,'Nancova',5),(38,'Rivungo',5),(39,'Cahama',8),(40,'Cuanhama',8),(41,'Curoca',8),(42,'Cuvelai',8),(43,'Namacunde',8),(44,'Ombadja',8),(45,'Bailundo',9),(46,'Catchiungo',9),(47,'Caala',9),(48,'Ekuma',9),(49,'Huambo',9),(50,'Londuimbale',9),(51,'Longonjo',9),(52,'Mungo',9),(53,'Tchicala-Tchiloanga',9),(54,'Tchindjenje',9),(55,'Ucuma',9),(56,'Caconda',10),(57,'Cacula',10),(58,'Caluquembe',10),(59,'Gambos',10),(60,'Chibia',10),(61,'Chicomba',10),(62,'Chipindo',10),(63,'Cuvango',10),(64,'Humpata',10),(65,'Jamba',10),(66,'Lubango',10),(67,'Matala',10),(68,'Quilengues',10),(69,'Quipungo',10),(70,'Ambaca',6),(71,'Banga',6),(72,'Bolongongo',6),(73,'Cambambe',6),(74,'Cazengo',6),(75,'Golungo Alto',6),(76,'Gonguembo',6),(77,'Lucala',6),(78,'Quiculungo',6),(79,'Samba Caju',6),(80,'Cassongue',7),(81,'Conda',7),(82,'Ebo',7),(83,'Libolo',7),(84,'Mussende',7),(85,'Porto Amboin',7),(86,'Quibala',7),(87,'Quilenda',7),(88,'Seles',7),(89,'Sumbe',7),(90,'Waku Kungo',7),(91,'Belas',11),(92,'Cacuaco',11),(93,'Cazenga',11),(94,'Icolo e Bengo',11),(95,'Luanda',11),(96,'Quiçama',11),(97,'Viana',11),(98,'Cambulo',12),(99,'Capenda-Camulemba',12),(100,'Caungula',12),(101,'Chitato',12),(102,'Cuango',12),(103,'Cuilo',12),(104,'Lubalo',12),(105,'Lukapa',12),(106,'Xá-Muteba',12),(107,'Cacolo',13),(108,'Dala',13),(109,'Muconda',13),(110,'Saurimo',13),(111,'Cacuso',14),(112,'Calandula',14),(113,'Cambundi-Catembo',14),(114,'Cangandala',14),(115,'Caombo',14),(116,'Cuaba Nzogo',14),(117,'Cunda-Dia-Baze',14),(118,'Luquembo',14),(119,'Malange',14),(120,'Marimba',14),(121,'Massango',14),(122,'Mucari',14),(123,'Quela',14),(124,'Quirima',14),(125,'Alto Zambeze',15),(126,'Bundas',15),(127,'Camanongue',15),(128,'Léua',15),(129,'Luau',15),(130,'Luacano',15),(131,'Luchazes',15),(132,'Lumeje',15),(133,'Moxico',15),(134,'Bibala',16),(135,'Camucuio',16),(136,'Namibe',16),(137,'Tômbua',16),(138,'Virei',16),(139,'Alto Cauale',17),(140,'Ambuila',17),(141,'Bembe',17),(142,'Buengas',17),(143,'Bungo',17),(144,'Damba',17),(145,'Macocola',17),(146,'Mucaba',17),(147,'Negage',17),(148,'Puri',17),(149,'Quimbele',17),(150,'Quitexe',17),(151,'Sanza Pombo',17),(152,'Songo',17),(153,'Uige',17),(154,'Zombo',17),(155,'Cuimba',18),(156,'M\'Banza Kongo',18),(157,'Noqui',18),(158,'N\'Zeto',18),(159,'Soyo',18),(160,'Tomboco',18);
/*!40000 ALTER TABLE `municipio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `id_produto` int NOT NULL AUTO_INCREMENT,
  `nome_produto` varchar(45) DEFAULT NULL,
  `data_expira_produto` date DEFAULT NULL,
  `preco_venda_produto` double DEFAULT NULL,
  `quantidade_stock_produto` int DEFAULT '0',
  `colocacao_localizacao` varchar(45) DEFAULT NULL,
  `id_tipo_de_produto` int NOT NULL,
  `id_unidade_de_medida` int NOT NULL,
  PRIMARY KEY (`id_produto`),
  KEY `fk_produto_tipo_de_produto1_idx` (`id_tipo_de_produto`),
  KEY `fk_produto_unidade_de_medida1_idx` (`id_unidade_de_medida`),
  CONSTRAINT `fk_produto_tipo_de_produto1` FOREIGN KEY (`id_tipo_de_produto`) REFERENCES `tipo_de_produto` (`id_tipo_de_produto`),
  CONSTRAINT `fk_produto_unidade_de_medida1` FOREIGN KEY (`id_unidade_de_medida`) REFERENCES `unidade_de_medida` (`id_unidade_de_medida`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profissional`
--

DROP TABLE IF EXISTS `profissional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profissional` (
  `id_profissional` int NOT NULL AUTO_INCREMENT,
  `nome_profissional` varchar(45) DEFAULT NULL,
  `sobrenome_profissional` varchar(45) DEFAULT NULL,
  `data_nascimento_profissional` date DEFAULT NULL,
  `sexo_profissional` char(20) DEFAULT NULL,
  `email_profissional` varchar(45) DEFAULT NULL,
  `telefone_profissional` varchar(45) DEFAULT NULL,
  `rua_profissional` varchar(45) DEFAULT NULL,
  `casa_profissional` varchar(45) DEFAULT NULL,
  `bairro_profissional` varchar(45) DEFAULT NULL,
  `distritito_profissional` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `id_municipio` int NOT NULL,
  `id_categoria_profissional` int NOT NULL,
  PRIMARY KEY (`id_profissional`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_enfermeiro_municipio1_idx` (`id_municipio`),
  KEY `fk_profissional_categoria_profissional1_idx` (`id_categoria_profissional`),
  CONSTRAINT `fk_enfermeiro_municipio1` FOREIGN KEY (`id_municipio`) REFERENCES `municipio` (`id_municipio`),
  CONSTRAINT `fk_profissional_categoria_profissional1` FOREIGN KEY (`id_categoria_profissional`) REFERENCES `categoria_profissional` (`id_categoria_profissional`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profissional`
--

LOCK TABLES `profissional` WRITE;
/*!40000 ALTER TABLE `profissional` DISABLE KEYS */;
/*!40000 ALTER TABLE `profissional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provincia` (
  `id_provincia` int NOT NULL AUTO_INCREMENT,
  `nome_provincia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_provincia`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincia`
--

LOCK TABLES `provincia` WRITE;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
INSERT INTO `provincia` VALUES (1,'Bengo'),(2,'Benguela'),(3,'Bié'),(4,'Cabinda'),(5,'Cuando Cubango'),(6,'Cuanza Norte'),(7,'Cuanza Sul'),(8,'Cunene'),(9,'Huambo'),(10,'Huila'),(11,'Luanda'),(12,'Lunda Norte'),(13,'Lunda Sul'),(14,'Malange'),(15,'Moxico'),(16,'Namibe'),(17,'Uige'),(18,'Zaire');
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saida_materia_prima`
--

DROP TABLE IF EXISTS `saida_materia_prima`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saida_materia_prima` (
  `id_saida_materia_prima` int NOT NULL AUTO_INCREMENT,
  `id_profissional` int DEFAULT NULL,
  `id_sector_producao` int DEFAULT NULL,
  `data_saida_materia_prima` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `data_registo` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_saida_materia_prima`),
  KEY `fk_saida_materia_prima_profissional1_idx` (`id_profissional`),
  KEY `fk_saida_materia_prima_sector_producao1_idx` (`id_sector_producao`),
  CONSTRAINT `fk_saida_materia_prima_profissional1` FOREIGN KEY (`id_profissional`) REFERENCES `profissional` (`id_profissional`),
  CONSTRAINT `fk_saida_materia_prima_sector_producao1` FOREIGN KEY (`id_sector_producao`) REFERENCES `sector_producao` (`id_sector_producao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saida_materia_prima`
--

LOCK TABLES `saida_materia_prima` WRITE;
/*!40000 ALTER TABLE `saida_materia_prima` DISABLE KEYS */;
/*!40000 ALTER TABLE `saida_materia_prima` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saida_materia_prima_detalhes`
--

DROP TABLE IF EXISTS `saida_materia_prima_detalhes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saida_materia_prima_detalhes` (
  `id_saida_materia_prima` int NOT NULL,
  `id_materia_prima` int NOT NULL,
  `quantidade_saida_materia_prima` int NOT NULL,
  PRIMARY KEY (`id_saida_materia_prima`,`id_materia_prima`),
  KEY `fk_saida_materia_prima_detalhes_saida_materia_prima1_idx` (`id_saida_materia_prima`),
  KEY `fk_saida_materia_prima_detalhes_materia_prima1_idx` (`id_materia_prima`),
  CONSTRAINT `fk_saida_materia_prima_detalhes_materia_prima1` FOREIGN KEY (`id_materia_prima`) REFERENCES `materia_prima` (`id_materia_prima`),
  CONSTRAINT `fk_saida_materia_prima_detalhes_saida_materia_prima1` FOREIGN KEY (`id_saida_materia_prima`) REFERENCES `saida_materia_prima` (`id_saida_materia_prima`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saida_materia_prima_detalhes`
--

LOCK TABLES `saida_materia_prima_detalhes` WRITE;
/*!40000 ALTER TABLE `saida_materia_prima_detalhes` DISABLE KEYS */;
/*!40000 ALTER TABLE `saida_materia_prima_detalhes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sector_producao`
--

DROP TABLE IF EXISTS `sector_producao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sector_producao` (
  `id_sector_producao` int NOT NULL AUTO_INCREMENT,
  `nome_sector_producao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_sector_producao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sector_producao`
--

LOCK TABLES `sector_producao` WRITE;
/*!40000 ALTER TABLE `sector_producao` DISABLE KEYS */;
/*!40000 ALTER TABLE `sector_producao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_materia_prima`
--

DROP TABLE IF EXISTS `stock_materia_prima`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_materia_prima` (
  `id_stock` int NOT NULL AUTO_INCREMENT,
  `data_da_compra` date DEFAULT NULL,
  `data_atualizacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `quantidade` int DEFAULT '0',
  `preco_de_compra` double DEFAULT NULL,
  `colocacao_localizacao` varchar(45) DEFAULT NULL,
  `id_materia_prima` int NOT NULL,
  `id_profissional` int DEFAULT NULL,
  PRIMARY KEY (`id_stock`),
  KEY `fk_stock_profissional_idx` (`id_profissional`),
  KEY `fk_stock_materia_prima_materia_prima1_idx` (`id_materia_prima`),
  CONSTRAINT `fk_stock_materia_prima_materia_prima1` FOREIGN KEY (`id_materia_prima`) REFERENCES `materia_prima` (`id_materia_prima`),
  CONSTRAINT `fk_stock_profissional0` FOREIGN KEY (`id_profissional`) REFERENCES `profissional` (`id_profissional`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_materia_prima`
--

LOCK TABLES `stock_materia_prima` WRITE;
/*!40000 ALTER TABLE `stock_materia_prima` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock_materia_prima` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_produto`
--

DROP TABLE IF EXISTS `stock_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_produto` (
  `id_stock` int NOT NULL AUTO_INCREMENT,
  `data_da_compra` date DEFAULT NULL,
  `data_atualizacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `quantidade` int DEFAULT NULL,
  `preco_de_compra` double DEFAULT NULL,
  `colocacao_localizacao` varchar(45) DEFAULT NULL,
  `precentagem_preco_venda` double DEFAULT NULL,
  `id_produto` int NOT NULL,
  `id_profissional` int DEFAULT NULL,
  PRIMARY KEY (`id_stock`),
  KEY `fk_stock_produto_produto1_idx` (`id_produto`),
  KEY `fk_stock_profissional_idx` (`id_profissional`),
  CONSTRAINT `fk_stock_produto_produto1` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`),
  CONSTRAINT `fk_stock_profissional` FOREIGN KEY (`id_profissional`) REFERENCES `profissional` (`id_profissional`)
) ENGINE=InnoDB AUTO_INCREMENT=527 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_produto`
--

LOCK TABLES `stock_produto` WRITE;
/*!40000 ALTER TABLE `stock_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_de_produto`
--

DROP TABLE IF EXISTS `tipo_de_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_de_produto` (
  `id_tipo_de_produto` int NOT NULL AUTO_INCREMENT,
  `descricao_tipo_de_produto` varchar(45) DEFAULT NULL,
  `id_familia_do_produto` int NOT NULL,
  PRIMARY KEY (`id_tipo_de_produto`),
  KEY `fk_tipo_de_produto_familia_do_produto1_idx` (`id_familia_do_produto`),
  CONSTRAINT `fk_tipo_de_produto_familia_do_produto1` FOREIGN KEY (`id_familia_do_produto`) REFERENCES `familia_do_produto` (`id_familia_do_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_de_produto`
--

LOCK TABLES `tipo_de_produto` WRITE;
/*!40000 ALTER TABLE `tipo_de_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_de_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidade_de_medida`
--

DROP TABLE IF EXISTS `unidade_de_medida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidade_de_medida` (
  `id_unidade_de_medida` int NOT NULL AUTO_INCREMENT,
  `descricao_unidade_de_medida` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_unidade_de_medida`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidade_de_medida`
--

LOCK TABLES `unidade_de_medida` WRITE;
/*!40000 ALTER TABLE `unidade_de_medida` DISABLE KEYS */;
INSERT INTO `unidade_de_medida` VALUES (1,'Caixa'),(2,'Garrafa'),(3,'Saco'),(4,'Grama'),(5,'Quilo'),(6,'Pacote'),(7,'Bidon'),(8,'Latinha'),(9,'Embalagem');
/*!40000 ALTER TABLE `unidade_de_medida` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-11  5:01:43
