CREATE DATABASE  IF NOT EXISTS `hospitalx` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hospitalx`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: hospitalx
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
-- Table structure for table `agenda_analise`
--

DROP TABLE IF EXISTS `agenda_analise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agenda_analise` (
  `id_agenda_analise` int NOT NULL AUTO_INCREMENT,
  `data_analise` varchar(45) DEFAULT NULL,
  `realizada` tinyint DEFAULT NULL,
  `data_realizacao` date DEFAULT NULL,
  `id_paciente` int NOT NULL,
  `id_profissional` int NOT NULL,
  PRIMARY KEY (`id_agenda_analise`),
  KEY `fk_agenda_analise_paciente1_idx` (`id_paciente`),
  KEY `fk_agenda_analise_profissional1_idx` (`id_profissional`),
  CONSTRAINT `fk_agenda_analise_paciente1` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id_paciente`),
  CONSTRAINT `fk_agenda_analise_profissional1` FOREIGN KEY (`id_profissional`) REFERENCES `profissional` (`id_profissional`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agenda_analise`
--

LOCK TABLES `agenda_analise` WRITE;
/*!40000 ALTER TABLE `agenda_analise` DISABLE KEYS */;
INSERT INTO `agenda_analise` VALUES (1,'2023-07-30',1,'2023-08-11',1,4),(2,'2023-07-30',1,'2023-08-14',1,5),(3,'2023-08-02',NULL,NULL,1,5),(4,'2023-08-31',NULL,NULL,1,3),(5,'2023-08-31',NULL,NULL,1,2);
/*!40000 ALTER TABLE `agenda_analise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agenda_consulta`
--

DROP TABLE IF EXISTS `agenda_consulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agenda_consulta` (
  `id_agenda_consulta` int NOT NULL AUTO_INCREMENT,
  `id_especialidade` int NOT NULL,
  `id_medico` int NOT NULL,
  `data_agenda` date DEFAULT NULL,
  PRIMARY KEY (`id_agenda_consulta`),
  KEY `fk_agenda_consulta_especialidade1_idx` (`id_especialidade`),
  KEY `fk_agenda_consulta_medico1_idx` (`id_medico`),
  CONSTRAINT `fk_agenda_consulta_especialidade1` FOREIGN KEY (`id_especialidade`) REFERENCES `especialidade` (`id_especialidade`),
  CONSTRAINT `fk_agenda_consulta_medico1` FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id_medico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agenda_consulta`
--

LOCK TABLES `agenda_consulta` WRITE;
/*!40000 ALTER TABLE `agenda_consulta` DISABLE KEYS */;
/*!40000 ALTER TABLE `agenda_consulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agenda_exame`
--

DROP TABLE IF EXISTS `agenda_exame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agenda_exame` (
  `id_agenda_exame` int NOT NULL AUTO_INCREMENT,
  `data_exame` varchar(45) DEFAULT NULL,
  `id_paciente` int NOT NULL,
  `id_profissional` int NOT NULL,
  `realizado` tinyint DEFAULT NULL,
  `data_realizacao` date DEFAULT NULL,
  PRIMARY KEY (`id_agenda_exame`),
  KEY `fk_agenda_exame_paciente1_idx` (`id_paciente`),
  KEY `fk_agenda_exame_profissional1_idx` (`id_profissional`),
  CONSTRAINT `fk_agenda_exame_paciente1` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id_paciente`),
  CONSTRAINT `fk_agenda_exame_profissional1` FOREIGN KEY (`id_profissional`) REFERENCES `profissional` (`id_profissional`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agenda_exame`
--

LOCK TABLES `agenda_exame` WRITE;
/*!40000 ALTER TABLE `agenda_exame` DISABLE KEYS */;
INSERT INTO `agenda_exame` VALUES (1,'2023-08-09',1,5,NULL,NULL),(2,'2023-08-09',1,3,1,'2023-08-11'),(3,'2023-08-10',1,6,NULL,NULL),(4,'2023-08-10',1,5,NULL,NULL),(5,'2023-08-10',1,5,NULL,NULL),(6,'2023-08-11',1,4,NULL,NULL);
/*!40000 ALTER TABLE `agenda_exame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `analise`
--

DROP TABLE IF EXISTS `analise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `analise` (
  `id_analise` int NOT NULL AUTO_INCREMENT,
  `descricao_analise` varchar(45) DEFAULT NULL,
  `preco_analise` double DEFAULT NULL,
  `id_tipo_de_analise` int NOT NULL,
  PRIMARY KEY (`id_analise`),
  KEY `fk_analise_tipo_de_analise1_idx` (`id_tipo_de_analise`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `analise`
--

LOCK TABLES `analise` WRITE;
/*!40000 ALTER TABLE `analise` DISABLE KEYS */;
INSERT INTO `analise` VALUES (1,'Analise de sangue',100,1),(2,'Analise de sangue',100,1),(3,'Analise 3',400,2),(4,'Analise 4',300,2),(5,'Analise 5',150,1),(6,'Nova análise 20082023',4000,2),(7,'Nova análise 20082023',4000,2),(8,'Nova análise III 20082023',2000,5),(9,'Nova análise III 20082023',2000,5),(10,'Nova análise VI 20082023',1000,2),(11,'Nova análise VI 20082023',1000,2);
/*!40000 ALTER TABLE `analise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cama`
--

DROP TABLE IF EXISTS `cama`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cama` (
  `id_cama` int NOT NULL AUTO_INCREMENT,
  `numero_cama` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_cama`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cama`
--

LOCK TABLES `cama` WRITE;
/*!40000 ALTER TABLE `cama` DISABLE KEYS */;
/*!40000 ALTER TABLE `cama` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_profissional`
--

LOCK TABLES `categoria_profissional` WRITE;
/*!40000 ALTER TABLE `categoria_profissional` DISABLE KEYS */;
INSERT INTO `categoria_profissional` VALUES (1,'Medico'),(2,'Enfermeiro(a)'),(3,'Tecnico(a) de laboratório');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'23231212','Ana','Maria','F','ddd@gamila','3030030','34','223','33','33',1),(2,NULL,'Joaquim','Hangalo','joajoshang@gmail.com','M','3351641820','1212','1212','1212','1212',3),(3,NULL,'Joaquim','Hangalo','joajoshang@gmail.com','M','3351641820','1212','1212','1212','1212',3);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consulta`
--

DROP TABLE IF EXISTS `consulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consulta` (
  `id_consulta` int NOT NULL AUTO_INCREMENT,
  `descricao_consulta` varchar(45) DEFAULT NULL,
  `preco_consulta` double DEFAULT NULL,
  `id_tipo_de_consulta` int NOT NULL,
  PRIMARY KEY (`id_consulta`),
  KEY `fk_consulta_tipo_de_consulta1_idx` (`id_tipo_de_consulta`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consulta`
--

LOCK TABLES `consulta` WRITE;
/*!40000 ALTER TABLE `consulta` DISABLE KEYS */;
INSERT INTO `consulta` VALUES (1,'Consulta 1',200,1),(2,'Consulta 2',150,2),(3,'Consulta 3',50,1),(4,'Nova consulta',350,2),(5,'Nova consulta',350,2);
/*!40000 ALTER TABLE `consulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalhes_agenda_analise`
--

DROP TABLE IF EXISTS `detalhes_agenda_analise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalhes_agenda_analise` (
  `id_agenda_analise` int NOT NULL,
  `id_analise` int NOT NULL,
  `quantidade` int DEFAULT NULL,
  `preco_analise` double DEFAULT NULL,
  PRIMARY KEY (`id_agenda_analise`,`id_analise`),
  KEY `fk_detalhes_agenda_analise_analise1_idx` (`id_analise`),
  KEY `fk_detalhes_agenda_analise_agenda_analise1_idx` (`id_agenda_analise`),
  CONSTRAINT `fk_detalhes_agenda_analise_agenda_analise1` FOREIGN KEY (`id_agenda_analise`) REFERENCES `agenda_analise` (`id_agenda_analise`),
  CONSTRAINT `fk_detalhes_agenda_analise_analise1` FOREIGN KEY (`id_analise`) REFERENCES `analise` (`id_analise`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalhes_agenda_analise`
--

LOCK TABLES `detalhes_agenda_analise` WRITE;
/*!40000 ALTER TABLE `detalhes_agenda_analise` DISABLE KEYS */;
INSERT INTO `detalhes_agenda_analise` VALUES (2,1,1,100),(2,4,1,300),(2,5,1,150),(3,1,1,100),(3,4,1,300),(3,5,1,150),(4,1,4,100),(4,2,3,100),(4,5,2,150),(5,1,4,100),(5,2,2,100),(5,5,1,150);
/*!40000 ALTER TABLE `detalhes_agenda_analise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalhes_agenda_exame`
--

DROP TABLE IF EXISTS `detalhes_agenda_exame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalhes_agenda_exame` (
  `id_agenda_exame` int NOT NULL,
  `id_exame` int NOT NULL,
  `quantidade` int DEFAULT NULL,
  `preco_examente` double DEFAULT NULL,
  PRIMARY KEY (`id_agenda_exame`,`id_exame`),
  KEY `fk_detalhes_agenda_exame_agenda_exame1_idx` (`id_agenda_exame`),
  KEY `fk_detalhes_agenda_exame_exame1_idx` (`id_exame`),
  CONSTRAINT `fk_detalhes_agenda_exame_agenda_exame1` FOREIGN KEY (`id_agenda_exame`) REFERENCES `agenda_exame` (`id_agenda_exame`),
  CONSTRAINT `fk_detalhes_agenda_exame_exame1` FOREIGN KEY (`id_exame`) REFERENCES `exame` (`id_exame`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalhes_agenda_exame`
--

LOCK TABLES `detalhes_agenda_exame` WRITE;
/*!40000 ALTER TABLE `detalhes_agenda_exame` DISABLE KEYS */;
INSERT INTO `detalhes_agenda_exame` VALUES (5,1,1,100),(6,1,1,100),(6,2,3,50),(6,3,1,60),(6,4,1,70),(6,5,1,60);
/*!40000 ALTER TABLE `detalhes_agenda_exame` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalhes_internamento_enfermeiro`
--

DROP TABLE IF EXISTS `detalhes_internamento_enfermeiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalhes_internamento_enfermeiro` (
  `id_detalhes_internamento_enfermeiro` int NOT NULL AUTO_INCREMENT,
  `data_visita` date DEFAULT NULL,
  `cura_feita` varchar(45) DEFAULT NULL,
  `id_internamento` int NOT NULL,
  `id_enfermeiro` int NOT NULL,
  PRIMARY KEY (`id_detalhes_internamento_enfermeiro`),
  KEY `fk_detalhes_internamento_internamento1_idx` (`id_internamento`),
  KEY `fk_detalhes_internamento_enfermeiro_enfermeiro1_idx` (`id_enfermeiro`),
  CONSTRAINT `fk_detalhes_internamento_enfermeiro_enfermeiro1` FOREIGN KEY (`id_enfermeiro`) REFERENCES `profissional` (`id_profissional`),
  CONSTRAINT `fk_detalhes_internamento_internamento1` FOREIGN KEY (`id_internamento`) REFERENCES `internamento` (`id_internamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalhes_internamento_enfermeiro`
--

LOCK TABLES `detalhes_internamento_enfermeiro` WRITE;
/*!40000 ALTER TABLE `detalhes_internamento_enfermeiro` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalhes_internamento_enfermeiro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalhes_internamento_medico`
--

DROP TABLE IF EXISTS `detalhes_internamento_medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalhes_internamento_medico` (
  `iddetalhes_internamento_medico` int NOT NULL AUTO_INCREMENT,
  `data_visita` date DEFAULT NULL,
  `observacao_feita` varchar(45) DEFAULT NULL,
  `id_internamento` int NOT NULL,
  `id_medico` int NOT NULL,
  PRIMARY KEY (`iddetalhes_internamento_medico`),
  KEY `fk_detalhes_internamento_medico_internamento1_idx` (`id_internamento`),
  KEY `fk_detalhes_internamento_medico_medico1_idx` (`id_medico`),
  CONSTRAINT `fk_detalhes_internamento_medico_internamento1` FOREIGN KEY (`id_internamento`) REFERENCES `internamento` (`id_internamento`),
  CONSTRAINT `fk_detalhes_internamento_medico_medico1` FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id_medico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalhes_internamento_medico`
--

LOCK TABLES `detalhes_internamento_medico` WRITE;
/*!40000 ALTER TABLE `detalhes_internamento_medico` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalhes_internamento_medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalhes_receita`
--

DROP TABLE IF EXISTS `detalhes_receita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalhes_receita` (
  `id_detalhes_receita` int NOT NULL AUTO_INCREMENT,
  `id_receita` int NOT NULL,
  `preco_medicamento` double DEFAULT NULL,
  `indicacoes` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_detalhes_receita`),
  KEY `fk_detalhes_receita_receita1_idx` (`id_receita`),
  CONSTRAINT `fk_detalhes_receita_receita1` FOREIGN KEY (`id_receita`) REFERENCES `receita` (`id_receita`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalhes_receita`
--

LOCK TABLES `detalhes_receita` WRITE;
/*!40000 ALTER TABLE `detalhes_receita` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalhes_receita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidade`
--

DROP TABLE IF EXISTS `especialidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidade` (
  `id_especialidade` int NOT NULL AUTO_INCREMENT,
  `especialidade` varchar(45) DEFAULT NULL,
  `custo` double DEFAULT NULL,
  PRIMARY KEY (`id_especialidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidade`
--

LOCK TABLES `especialidade` WRITE;
/*!40000 ALTER TABLE `especialidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `especialidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exame`
--

DROP TABLE IF EXISTS `exame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exame` (
  `id_exame` int NOT NULL AUTO_INCREMENT,
  `descricao_exame` varchar(45) DEFAULT NULL,
  `preco_exame` double DEFAULT NULL,
  `id_tipo_de_exame` int NOT NULL,
  PRIMARY KEY (`id_exame`),
  KEY `fk_exame_tipo_de_exame1_idx` (`id_tipo_de_exame`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exame`
--

LOCK TABLES `exame` WRITE;
/*!40000 ALTER TABLE `exame` DISABLE KEYS */;
INSERT INTO `exame` VALUES (1,'Exame de sangue',100,1),(2,'Exame 1',50,2),(3,'Exame 2',60,3),(4,'Examne 3',70,1),(5,'Exame 4',60,1),(6,'Novo exame',50,4);
/*!40000 ALTER TABLE `exame` ENABLE KEYS */;
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
  `data_factura_venda` datetime DEFAULT CURRENT_TIMESTAMP,
  `valor_total` double DEFAULT NULL,
  `id_forma_de_pagamento` int NOT NULL,
  PRIMARY KEY (`id_factura_venda`),
  KEY `fk_factura_venda_produto_forma_de_pagamento1_idx` (`id_forma_de_pagamento`),
  KEY `fk_factura_venda_produto_cliente1_idx` (`id_cliente`),
  CONSTRAINT `fk_factura_venda_produto_cliente1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `fk_factura_venda_produto_forma_de_pagamento1` FOREIGN KEY (`id_forma_de_pagamento`) REFERENCES `forma_de_pagamento` (`id_forma_de_pagamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `descricao_familia_do_produto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_familia_do_produto`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `familia_do_produto`
--

LOCK TABLES `familia_do_produto` WRITE;
/*!40000 ALTER TABLE `familia_do_produto` DISABLE KEYS */;
INSERT INTO `familia_do_produto` VALUES (1,'Familia1'),(2,'Familia2'),(3,'Familia3');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forma_de_pagamento`
--

LOCK TABLES `forma_de_pagamento` WRITE;
/*!40000 ALTER TABLE `forma_de_pagamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `forma_de_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `internamento`
--

DROP TABLE IF EXISTS `internamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `internamento` (
  `id_internamento` int NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `id_quarto` int NOT NULL,
  `id_cama` int NOT NULL,
  `id_paciente` int NOT NULL,
  `id_medico` int NOT NULL,
  PRIMARY KEY (`id_internamento`),
  KEY `fk_internamento_quarto1_idx` (`id_quarto`),
  KEY `fk_internamento_cama1_idx` (`id_cama`),
  KEY `fk_internamento_paciente1_idx` (`id_paciente`),
  KEY `fk_internamento_medico1_idx` (`id_medico`),
  CONSTRAINT `fk_internamento_cama1` FOREIGN KEY (`id_cama`) REFERENCES `cama` (`id_cama`),
  CONSTRAINT `fk_internamento_medico1` FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id_medico`),
  CONSTRAINT `fk_internamento_paciente1` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id_paciente`),
  CONSTRAINT `fk_internamento_quarto1` FOREIGN KEY (`id_quarto`) REFERENCES `quarto` (`id_quarto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internamento`
--

LOCK TABLES `internamento` WRITE;
/*!40000 ALTER TABLE `internamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `internamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medico`
--

DROP TABLE IF EXISTS `medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medico` (
  `id_medico` int NOT NULL AUTO_INCREMENT,
  `nome_medico` varchar(45) DEFAULT NULL,
  `sobrenom_medico` varchar(45) DEFAULT NULL,
  `data_nascimento_medico` date DEFAULT NULL,
  `sexo_medico` char(20) DEFAULT NULL,
  `email_medico` varchar(45) DEFAULT NULL,
  `telefone_medico` varchar(45) DEFAULT NULL,
  `rua_medico` varchar(45) DEFAULT NULL,
  `casa_medico` varchar(45) DEFAULT NULL,
  `bairro_medico` varchar(45) DEFAULT NULL,
  `distritito_medico` varchar(45) DEFAULT NULL,
  `id_municipio` int NOT NULL,
  PRIMARY KEY (`id_medico`),
  KEY `fk_medico_municipio1_idx` (`id_municipio`),
  CONSTRAINT `fk_medico_municipio1` FOREIGN KEY (`id_municipio`) REFERENCES `municipio` (`id_municipio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medico`
--

LOCK TABLES `medico` WRITE;
/*!40000 ALTER TABLE `medico` DISABLE KEYS */;
/*!40000 ALTER TABLE `medico` ENABLE KEYS */;
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
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paciente` (
  `id_paciente` int NOT NULL AUTO_INCREMENT,
  `numero_contribuinte` varchar(45) DEFAULT NULL,
  `nome_paciente` varchar(45) DEFAULT NULL,
  `sobrenome_paciente` varchar(45) DEFAULT NULL,
  `data_nascimento_paciente` date DEFAULT NULL,
  `sexo_paciente` char(20) DEFAULT NULL,
  `email_paciente` varchar(45) DEFAULT NULL,
  `telefone_paciente` varchar(45) DEFAULT NULL,
  `rua_paciente` varchar(45) DEFAULT NULL,
  `casa_paciente` varchar(45) DEFAULT NULL,
  `bairro_paciente` varchar(45) DEFAULT NULL,
  `distritito_paciente` varchar(45) DEFAULT NULL,
  `id_municipio` int NOT NULL,
  PRIMARY KEY (`id_paciente`),
  KEY `fk_paciente_municipio1_idx` (`id_municipio`),
  CONSTRAINT `fk_paciente_municipio1` FOREIGN KEY (`id_municipio`) REFERENCES `municipio` (`id_municipio`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES (1,'123333','Joaquim','Hangalo','2000-07-12','M','joajoshang@gmail.com','3351641820','1212','1212','1212','asas',3),(2,'122222','Bartolomeu','Hangalo','2020-07-12','M','joajoshang@gmail.com','3351641820','1212','1212','1212','asas',3);
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pais` (
  `id_pais` tinyint unsigned NOT NULL AUTO_INCREMENT,
  `nome_pais` varchar(50) NOT NULL,
  `name_pais` varchar(50) NOT NULL,
  PRIMARY KEY (`id_pais`)
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'AFEGANISTÃO','AFGHANISTAN'),(2,'ACROTÍRI E DECELIA','AKROTIRI E DEKÉLIA'),(3,'ÁFRICA DO SUL','SOUTH AFRICA'),(4,'ALBÂNIA','ALBANIA'),(5,'ALEMANHA','GERMANY'),(6,'AMERICAN SAMOA','AMERICAN SAMOA'),(7,'ANDORRA','ANDORRA'),(8,'ANGOLA','ANGOLA'),(9,'ANGUILLA','ANGUILLA'),(10,'ANTÍGUA E BARBUDA','ANTIGUA AND BARBUDA'),(11,'ANTILHAS NEERLANDESAS','NETHERLANDS ANTILLES'),(12,'ARÁBIA SAUDITA','SAUDI ARABIA'),(13,'ARGÉLIA','ALGERIA'),(14,'ARGENTINA','ARGENTINA'),(15,'ARMÉNIA','ARMENIA'),(16,'ARUBA','ARUBA'),(17,'AUSTRÁLIA','AUSTRALIA'),(18,'ÁUSTRIA','AUSTRIA'),(19,'AZERBAIJÃO','AZERBAIJAN'),(20,'BAHAMAS','BAHAMAS, THE'),(21,'BANGLADECHE','BANGLADESH'),(22,'BARBADOS','BARBADOS'),(23,'BARÉM','BAHRAIN'),(24,'BASSAS DA ÍNDIA','BASSAS DA INDIA'),(25,'BÉLGICA','BELGIUM'),(26,'BELIZE','BELIZE'),(27,'BENIM','BENIN'),(28,'BERMUDAS','BERMUDA'),(29,'BIELORRÚSSIA','BELARUS'),(30,'BOLÍVIA','BOLIVIA'),(31,'BÓSNIA E HERZEGOVINA','BOSNIA AND HERZEGOVINA'),(32,'BOTSUANA','BOTSWANA'),(33,'BRASIL','BRAZIL'),(34,'BRUNEI DARUSSALAM','BRUNEI DARUSSALAM'),(35,'BULGÁRIA','BULGARIA'),(36,'BURQUINA FASO','BURKINA FASO'),(37,'BURUNDI','BURUNDI'),(38,'BUTÃO','BHUTAN'),(39,'CABO VERDE','CAPE VERDE'),(40,'CAMARÕES','CAMEROON'),(41,'CAMBOJA','CAMBODIA'),(42,'CANADÁ','CANADA'),(43,'CATAR','QATAR'),(44,'CAZAQUISTÃO','KAZAKHSTAN'),(45,'CENTRO-AFRICANA REPÚBLICA','CENTRAL AFRICAN REPUBLIC'),(46,'CHADE','CHAD'),(47,'CHILE','CHILE'),(48,'CHINA','CHINA'),(49,'CHIPRE','CYPRUS'),(50,'COLÔMBIA','COLOMBIA'),(51,'COMORES','COMOROS'),(52,'CONGO','CONGO'),(53,'CONGO REPÚBLICA DEMOCRÁTICA','CONGO DEMOCRATIC REPUBLIC'),(54,'COREIA DO NORTE','KOREA NORTH'),(55,'COREIA DO SUL','KOREA SOUTH'),(56,'COSTA DO MARFIM','IVORY COAST'),(57,'COSTA RICA','COSTA RICA'),(58,'CROÁCIA','CROATIA'),(59,'CUBA','CUBA'),(60,'DINAMARCA','DENMARK'),(61,'DOMÍNICA','DOMINICA'),(62,'EGIPTO','EGYPT'),(63,'EMIRADOS ÁRABES UNIDOS','UNITED ARAB EMIRATES'),(64,'EQUADOR','ECUADOR'),(65,'ERITREIA','ERITREA'),(66,'ESLOVÁQUIA','SLOVAKIA'),(67,'ESLOVÉNIA','SLOVENIA'),(68,'ESPANHA','SPAIN'),(69,'ESTADOS UNIDOS','UNITED STATES'),(70,'ESTÓNIA','ESTONIA'),(71,'ETIÓPIA','ETHIOPIA'),(72,'FAIXA DE GAZA','GAZA STRIP'),(73,'FIJI','FIJI'),(74,'FILIPINAS','PHILIPPINES'),(75,'FINLÂNDIA','FINLAND'),(76,'FRANÇA','FRANCE'),(77,'GABÃO','GABON'),(78,'GÂMBIA','GAMBIA'),(79,'GANA','GHANA'),(80,'GEÓRGIA','GEORGIA'),(81,'GIBRALTAR','GIBRALTAR'),(82,'GRANADA','GRENADA'),(83,'GRÉCIA','GREECE'),(84,'GRONELÂNDIA','GREENLAND'),(85,'GUADALUPE','GUADELOUPE'),(86,'GUAM','GUAM'),(87,'GUATEMALA','GUATEMALA'),(88,'GUERNSEY','GUERNSEY'),(89,'GUIANA','GUYANA'),(90,'GUIANA FRANCESA','FRENCH GUIANA'),(91,'GUINÉ','GUINEA'),(92,'GUINÉ EQUATORIAL','EQUATORIAL GUINEA'),(93,'GUINÉ-BISSAU','GUINEA-BISSAU'),(94,'HAITI','HAITI'),(95,'HONDURAS','HONDURAS'),(96,'HONG KONG','HONG KONG'),(97,'HUNGRIA','HUNGARY'),(98,'IÉMEN','YEMEN'),(99,'ILHA BOUVET','BOUVET ISLAND'),(100,'ILHA CHRISTMAS','CHRISTMAS ISLAND'),(101,'ILHA DE CLIPPERTON','CLIPPERTON ISLAND'),(102,'ILHA DE JOÃO DA NOVA','JUAN DE NOVA ISLAND'),(103,'ILHA DE MAN','ISLE OF MAN'),(104,'ILHA DE NAVASSA','NAVASSA ISLAND'),(105,'ILHA EUROPA','EUROPA ISLAND'),(106,'ILHA NORFOLK','NORFOLK ISLAND'),(107,'ILHA TROMELIN','TROMELIN ISLAND'),(108,'ILHAS ASHMORE E CARTIER','ASHMORE AND CARTIER ISLANDS'),(109,'ILHAS CAIMAN','CAYMAN ISLANDS'),(110,'ILHAS COCOS (KEELING)','COCOS (KEELING) ISLANDS'),(111,'ILHAS COOK','COOK ISLANDS'),(112,'ILHAS DO MAR DE CORAL','CORAL SEA ISLANDS'),(113,'ILHAS FALKLANDS (ILHAS MALVINAS)','FALKLAND ISLANDS (ISLAS MALVINAS)'),(114,'ILHAS FEROE','FAROE ISLANDS'),(115,'ILHAS GEÓRGIA DO SUL E SANDWICH DO SUL','SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS'),(116,'ILHAS MARIANAS DO NORTE','NORTHERN MARIANA ISLANDS'),(117,'ILHAS MARSHALL','MARSHALL ISLANDS'),(118,'ILHAS PARACEL','PARACEL ISLANDS'),(119,'ILHAS PITCAIRN','PITCAIRN ISLANDS'),(120,'ILHAS SALOMÃO','SOLOMON ISLANDS'),(121,'ILHAS SPRATLY','SPRATLY ISLANDS'),(122,'ILHAS VIRGENS AMERICANAS','UNITED STATES VIRGIN ISLANDS'),(123,'ILHAS VIRGENS BRITÂNICAS','BRITISH VIRGIN ISLANDS'),(124,'ÍNDIA','INDIA'),(125,'INDONÉSIA','INDONESIA'),(126,'IRÃO','IRAN'),(127,'IRAQUE','IRAQ'),(128,'IRLANDA','IRELAND'),(129,'ISLÂNDIA','ICELAND'),(130,'ISRAEL','ISRAEL'),(131,'ITÁLIA','ITALY'),(132,'JAMAICA','JAMAICA'),(133,'JAN MAYEN','JAN MAYEN'),(134,'JAPÃO','JAPAN'),(135,'JERSEY','JERSEY'),(136,'JIBUTI','DJIBOUTI'),(137,'JORDÂNIA','JORDAN'),(138,'KIRIBATI','KIRIBATI'),(139,'KOWEIT','KUWAIT'),(140,'LAOS','LAOS'),(141,'LESOTO','LESOTHO'),(142,'LETÓNIA','LATVIA'),(143,'LÍBANO','LEBANON'),(144,'LIBÉRIA','LIBERIA'),(145,'LÍBIA','LIBYAN ARAB JAMAHIRIYA'),(146,'LISTENSTAINE','LIECHTENSTEIN'),(147,'LITUÂNIA','LITHUANIA'),(148,'LUXEMBURGO','LUXEMBOURG'),(149,'MACAU','MACAO'),(150,'MACEDÓNIA','MACEDONIA'),(151,'MADAGÁSCAR','MADAGASCAR'),(152,'MALÁSIA','MALAYSIA'),(153,'MALAVI','MALAWI'),(154,'MALDIVAS','MALDIVES'),(155,'MALI','MALI'),(156,'MALTA','MALTA'),(157,'MARROCOS','MOROCCO'),(158,'MARTINICA','MARTINIQUE'),(159,'MAURÍCIA','MAURITIUS'),(160,'MAURITÂNIA','MAURITANIA'),(161,'MAYOTTE','MAYOTTE'),(162,'MÉXICO','MEXICO'),(163,'MIANMAR','MYANMAR BURMA'),(164,'MICRONÉSIA','MICRONESIA'),(165,'MOÇAMBIQUE','MOZAMBIQUE'),(166,'MOLDÁVIA','MOLDOVA'),(167,'MÓNACO','MONACO'),(168,'MONGÓLIA','MONGOLIA'),(169,'MONTENEGRO','MONTENEGRO'),(170,'MONTSERRAT','MONTSERRAT'),(171,'NAMÍBIA','NAMIBIA'),(172,'NAURU','NAURU'),(173,'NEPAL','NEPAL'),(174,'NICARÁGUA','NICARAGUA'),(175,'NÍGER','NIGER'),(176,'NIGÉRIA','NIGERIA'),(177,'NIUE','NIUE'),(178,'NORUEGA','NORWAY'),(179,'NOVA CALEDÓNIA','NEW CALEDONIA'),(180,'NOVA ZELÂNDIA','NEW ZEALAND'),(181,'OMÃ','OMAN'),(182,'PAÍSES BAIXOS','NETHERLANDS'),(183,'PALAU','PALAU'),(184,'PALESTINA','PALESTINE'),(185,'PANAMÁ','PANAMA'),(186,'PAPUÁSIA-NOVA GUINÉ','PAPUA NEW GUINEA'),(187,'PAQUISTÃO','PAKISTAN'),(188,'PARAGUAI','PARAGUAY'),(189,'PERU','PERU'),(190,'POLINÉSIA FRANCESA','FRENCH POLYNESIA'),(191,'POLÓNIA','POLAND'),(192,'PORTO RICO','PUERTO RICO'),(193,'PORTUGAL','PORTUGAL'),(194,'QUÉNIA','KENYA'),(195,'QUIRGUIZISTÃO','KYRGYZSTAN'),(196,'REINO UNIDO','UNITED KINGDOM'),(197,'REPÚBLICA CHECA','CZECH REPUBLIC'),(198,'REPÚBLICA DOMINICANA','DOMINICAN REPUBLIC'),(199,'ROMÉNIA','ROMANIA'),(200,'RUANDA','RWANDA'),(201,'RÚSSIA','RUSSIAN FEDERATION'),(202,'SAHARA OCCIDENTAL','WESTERN SAHARA'),(203,'SALVADOR','EL SALVADOR'),(204,'SAMOA','SAMOA'),(205,'SANTA HELENA','SAINT HELENA'),(206,'SANTA LÚCIA','SAINT LUCIA'),(207,'SANTA SÉ','HOLY SEE'),(208,'SÃO CRISTÓVÃO E NEVES','SAINT KITTS AND NEVIS'),(209,'SÃO MARINO','SAN MARINO'),(210,'SÃO PEDRO E MIQUELÃO','SAINT PIERRE AND MIQUELON'),(211,'SÃO TOMÉ E PRÍNCIPE','SAO TOME AND PRINCIPE'),(212,'SÃO VICENTE E GRANADINAS','SAINT VINCENT AND THE GRENADINES'),(213,'SEICHELES','SEYCHELLES'),(214,'SENEGAL','SENEGAL'),(215,'SERRA LEOA','SIERRA LEONE'),(216,'SÉRVIA','SERBIA'),(217,'SINGAPURA','SINGAPORE'),(218,'SÍRIA','SYRIA'),(219,'SOMÁLIA','SOMALIA'),(220,'SRI LANCA','SRI LANKA'),(221,'SUAZILÂNDIA','SWAZILAND'),(222,'SUDÃO','SUDAN'),(223,'SUÉCIA','SWEDEN'),(224,'SUÍÇA','SWITZERLAND'),(225,'SURINAME','SURINAME'),(226,'SVALBARD','SVALBARD'),(227,'TAILÂNDIA','THAILAND'),(228,'TAIWAN','TAIWAN'),(229,'TAJIQUISTÃO','TAJIKISTAN'),(230,'TANZÂNIA','TANZANIA'),(231,'TERRITÓRIO BRITÂNICO DO OCEANO ÍNDICO','BRITISH INDIAN OCEAN TERRITORY'),(232,'TERRITÓRIO DAS ILHAS HEARD E MCDONALD','HEARD ISLAND AND MCDONALD ISLANDS'),(233,'TIMOR-LESTE','TIMOR-LESTE'),(234,'TOGO','TOGO'),(235,'TOKELAU','TOKELAU'),(236,'TONGA','TONGA'),(237,'TRINDADE E TOBAGO','TRINIDAD AND TOBAGO'),(238,'TUNÍSIA','TUNISIA'),(239,'TURKS E CAICOS','TURKS AND CAICOS ISLANDS'),(240,'TURQUEMENISTÃO','TURKMENISTAN'),(241,'TURQUIA','TURKEY'),(242,'TUVALU','TUVALU'),(243,'UCRÂNIA','UKRAINE'),(244,'UGANDA','UGANDA'),(245,'URUGUAI','URUGUAY'),(246,'USBEQUISTÃO','UZBEKISTAN'),(247,'VANUATU','VANUATU'),(248,'VENEZUELA','VENEZUELA'),(249,'VIETNAME','VIETNAM'),(250,'WALLIS E FUTUNA','WALLIS AND FUTUNA'),(251,'ZÂMBIA','ZAMBIA'),(252,'ZIMBABUÉ','ZIMBABWE');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `id_produto` int NOT NULL AUTO_INCREMENT,
  `nome_tecnico_produto` varchar(45) DEFAULT NULL,
  `nome_comum_produto` varchar(45) DEFAULT NULL,
  `codigo_produto` varchar(45) DEFAULT NULL,
  `data_expira_produto` date DEFAULT NULL,
  `preco_venda_produto` double DEFAULT NULL,
  `quantidade_stock_produto` int DEFAULT NULL,
  `colocacao_localizacao` varchar(45) DEFAULT NULL,
  `id_tipo_de_produto` int NOT NULL,
  `id_unidade_de_medida` int NOT NULL,
  PRIMARY KEY (`id_produto`),
  KEY `fk_produto_tipo_de_produto1_idx` (`id_tipo_de_produto`),
  KEY `fk_produto_unidade_de_medida1_idx` (`id_unidade_de_medida`),
  CONSTRAINT `fk_produto_tipo_de_produto1` FOREIGN KEY (`id_tipo_de_produto`) REFERENCES `tipo_de_produto` (`id_tipo_de_produto`),
  CONSTRAINT `fk_produto_unidade_de_medida1` FOREIGN KEY (`id_unidade_de_medida`) REFERENCES `unidade_de_medida` (`id_unidade_de_medida`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Produto1','Produto1','111111111','2022-03-03',90,200,'xxxx',1,1),(2,'Produto2','Produto2','2222222222','2023-03-03',200,300,'yyyyyy',2,2),(3,'Produto Novo','Produto Novo','1222','2023-08-07',12,1212,'Ax',1,1);
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
  `id_municipio` int NOT NULL,
  `id_categoria_profissional` int NOT NULL,
  PRIMARY KEY (`id_profissional`),
  KEY `fk_enfermeiro_municipio1_idx` (`id_municipio`),
  KEY `fk_profissional_categoria_profissional1_idx` (`id_categoria_profissional`),
  CONSTRAINT `fk_enfermeiro_municipio1` FOREIGN KEY (`id_municipio`) REFERENCES `municipio` (`id_municipio`),
  CONSTRAINT `fk_profissional_categoria_profissional1` FOREIGN KEY (`id_categoria_profissional`) REFERENCES `categoria_profissional` (`id_categoria_profissional`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profissional`
--

LOCK TABLES `profissional` WRITE;
/*!40000 ALTER TABLE `profissional` DISABLE KEYS */;
INSERT INTO `profissional` VALUES (1,'Joaquim','Hangalo','2023-07-18','M','joajoshang@gmail.com','3351641820','1212','4545','1212','1212',1,1),(2,'Zita','Hangalo','2023-07-18','M','joajoshang@gmail.com','3351641820','1212','4545','1212','1212',1,2),(3,'Luzia','Hangalo','2023-07-18','M','joajoshang@gmail.com','3351641820','1212','4545','1212','1212',1,1),(4,'Jamba','Hangalo','2023-07-18','M','joajoshang@gmail.com','3351641820','1212','4545','1212','1212',1,2),(5,'Andre','Hangalo','2023-07-04','M','joajoshang@gmail.com','3351641820','1212','4545','1212','1212',1,3),(6,'Bartolomeu','Hangalo','2023-07-04','M','joajoshang@gmail.com','3351641820','1212','4545','1212','1212',1,3);
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
-- Table structure for table `quarto`
--

DROP TABLE IF EXISTS `quarto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quarto` (
  `id_quarto` int NOT NULL AUTO_INCREMENT,
  `sigla_quarto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_quarto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quarto`
--

LOCK TABLES `quarto` WRITE;
/*!40000 ALTER TABLE `quarto` DISABLE KEYS */;
/*!40000 ALTER TABLE `quarto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receita`
--

DROP TABLE IF EXISTS `receita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receita` (
  `id_receita` int NOT NULL AUTO_INCREMENT,
  `data_receita` varchar(45) DEFAULT NULL,
  `id_medico` int NOT NULL,
  `id_paciente` int NOT NULL,
  PRIMARY KEY (`id_receita`),
  KEY `fk_receita_medico1_idx` (`id_medico`),
  KEY `fk_receita_paciente1_idx` (`id_paciente`),
  CONSTRAINT `fk_receita_medico1` FOREIGN KEY (`id_medico`) REFERENCES `medico` (`id_medico`),
  CONSTRAINT `fk_receita_paciente1` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id_paciente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receita`
--

LOCK TABLES `receita` WRITE;
/*!40000 ALTER TABLE `receita` DISABLE KEYS */;
/*!40000 ALTER TABLE `receita` ENABLE KEYS */;
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
  PRIMARY KEY (`id_stock`),
  KEY `fk_stock_produto_produto1_idx` (`id_produto`),
  CONSTRAINT `fk_stock_produto_produto1` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_produto`
--

LOCK TABLES `stock_produto` WRITE;
/*!40000 ALTER TABLE `stock_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_de_analise`
--

DROP TABLE IF EXISTS `tipo_de_analise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_de_analise` (
  `id_tipo_de_analise` int NOT NULL AUTO_INCREMENT,
  `descricao_tipo_de_analise` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_de_analise`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_de_analise`
--

LOCK TABLES `tipo_de_analise` WRITE;
/*!40000 ALTER TABLE `tipo_de_analise` DISABLE KEYS */;
INSERT INTO `tipo_de_analise` VALUES (1,'Tipo analise 1'),(2,'Tipo analise 2'),(3,'Tipo  analise 3'),(4,'Tipo  analise 4'),(5,'Tipo  analise 5'),(6,'Tipo  Análise'),(7,'Tipo  Análise 2'),(8,'TipoAnálise777');
/*!40000 ALTER TABLE `tipo_de_analise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_de_consulta`
--

DROP TABLE IF EXISTS `tipo_de_consulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_de_consulta` (
  `id_tipo_de_consulta` int NOT NULL AUTO_INCREMENT,
  `descricao_tipo_de_consulta` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_de_consulta`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_de_consulta`
--

LOCK TABLES `tipo_de_consulta` WRITE;
/*!40000 ALTER TABLE `tipo_de_consulta` DISABLE KEYS */;
INSERT INTO `tipo_de_consulta` VALUES (1,'Tipo Consulta 1'),(2,'Tipo Consulta 2'),(3,'Tipo Consulta 3'),(4,'Tipo Consulta 4'),(5,'Tipo Consulta 5'),(6,'Tipo de Consulta 6');
/*!40000 ALTER TABLE `tipo_de_consulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_de_exame`
--

DROP TABLE IF EXISTS `tipo_de_exame`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_de_exame` (
  `id_tipo_de_exame` int NOT NULL AUTO_INCREMENT,
  `descricao_tipo_de_exame` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_de_exame`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_de_exame`
--

LOCK TABLES `tipo_de_exame` WRITE;
/*!40000 ALTER TABLE `tipo_de_exame` DISABLE KEYS */;
INSERT INTO `tipo_de_exame` VALUES (1,'Tipo  exame 1'),(2,'Tipo  exame 2'),(3,'Tipo  exame 3'),(4,'Tipo  exame 4'),(5,'Tipo exame 5'),(6,'Novo tipo'),(7,'Novo tipo2'),(8,'Novo tipo2'),(9,'Novo tipo2'),(10,'Novo tipo3'),(11,'Novo tipo4'),(12,'tipo exame 4 novo');
/*!40000 ALTER TABLE `tipo_de_exame` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_de_produto`
--

LOCK TABLES `tipo_de_produto` WRITE;
/*!40000 ALTER TABLE `tipo_de_produto` DISABLE KEYS */;
INSERT INTO `tipo_de_produto` VALUES (1,'Tipo de prodiuto 1',1),(2,'Tipo de prodiuto 2',2),(3,'Tipo de prodiuto 3',1),(4,'Tipo de prodiuto 4',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidade_de_medida`
--

LOCK TABLES `unidade_de_medida` WRITE;
/*!40000 ALTER TABLE `unidade_de_medida` DISABLE KEYS */;
INSERT INTO `unidade_de_medida` VALUES (1,'Media 1'),(2,'Media 2'),(3,'Medida 3'),(4,'Medida nova');
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

-- Dump completed on 2023-08-31 11:11:52
