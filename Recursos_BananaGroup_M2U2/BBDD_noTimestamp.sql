-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.21-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win32
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para bananagroupmaven
CREATE DATABASE IF NOT EXISTS `bananagroupmaven` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bananagroupmaven`;

-- Volcando estructura para tabla bananagroupmaven.proyecto
CREATE TABLE IF NOT EXISTS `proyecto` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `descripcion` varchar(300) NOT NULL,
  `fechaI` date NOT NULL,
  `responsable` int(11) NOT NULL,
  `activo` varchar(45) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `pid_UNIQUE` (`pid`),
  KEY `fk_proyecto_usuario_idx` (`responsable`),
  CONSTRAINT `fk_proyecto_usuario` FOREIGN KEY (`responsable`) REFERENCES `usuario` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bananagroupmaven.proyecto: ~9 rows (aproximadamente)
/*!40000 ALTER TABLE `proyecto` DISABLE KEYS */;
INSERT INTO `proyecto` (`pid`, `titulo`, `descripcion`, `fechaI`, `responsable`, `activo`) VALUES
	(1, 'Proyecto VinRun', 'DiseÃ±o pruebas preproducto VinRun 3,0', '2017-02-03', 1111, 'Si'),
	(2, 'Proyecto VinRun', 'Nuevas funcionalidades VinRun 3,0', '2017-02-15', 3333, 'No'),
	(3, 'Proyecto Front', 'DiseÃ±o capas html', '2017-02-01', 1111, 'No'),
	(4, 'Proyecto Back', 'Nuevo controlador ', '2017-02-27', 2222, 'Si'),
	(5, 'Proyecto Descargas', 'Descarga parche 3,0001', '2017-02-10', 3333, 'Si'),
	(6, 'Proyecto VinDown', 'DefiniciÃ³n equipos de ataque', '2017-02-03', 2222, 'Si'),
	(7, 'Proyecto VinDown', 'DiseÃ±o Roadmap VinDown 4,0', '2017-02-04', 2222, 'No'),
	(8, 'Proyecto clientes Francia', 'GestiÃ³n bugs VinRun 3,0', '2017-02-07', 1111, 'Si'),
	(9, 'Proyecto Refactor', 'Nueva clase de bbdd MySql', '2017-02-24', 3333, 'No');
/*!40000 ALTER TABLE `proyecto` ENABLE KEYS */;

-- Volcando estructura para tabla bananagroupmaven.tarea
CREATE TABLE IF NOT EXISTS `tarea` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(150) NOT NULL,
  `responsable` int(11) NOT NULL,
  `proyecto_padre` int(11) NOT NULL,
  PRIMARY KEY (`tid`),
  UNIQUE KEY `idtarea_UNIQUE` (`tid`),
  KEY `fk_tarea_usuario1_idx` (`responsable`),
  KEY `fk_tarea_proyecto1_idx` (`proyecto_padre`),
  CONSTRAINT `fk_tarea_proyecto` FOREIGN KEY (`proyecto_padre`) REFERENCES `proyecto` (`pid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tarea_usuario` FOREIGN KEY (`responsable`) REFERENCES `usuario` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla bananagroupmaven.tarea: ~18 rows (aproximadamente)
/*!40000 ALTER TABLE `tarea` DISABLE KEYS */;
INSERT INTO `tarea` (`tid`, `descripcion`, `responsable`, `proyecto_padre`) VALUES
	(11, 'Pruebas regresiÃ³n', 1111, 1),
	(22, 'Pruebas aceptaciÃ³n', 3333, 1),
	(33, 'Barra lateral', 2222, 3),
	(44, 'ImplementaciÃ³n css', 3333, 5),
	(55, 'Descarga web y msi', 2222, 6),
	(66, 'Reportar bug mantis', 2222, 2),
	(77, 'Modelaje mysqlbench', 1111, 3),
	(88, 'Timing por versiones', 1111, 7),
	(99, 'Analisis versiones anteriores', 3333, 8),
	(111, 'Reporting incidencias clientes', 2222, 8),
	(222, 'Desarrollo nuevo controlador', 1111, 9),
	(333, 'ImplementaciÃ³n nueva clase', 2222, 9),
	(444, 'RestructuraciÃ³n base', 3333, 1),
	(555, 'Update bbdd', 3333, 3),
	(666, 'ConstrucciÃ³n controladores', 1111, 5),
	(777, 'RevisiÃ³n proyecto', 2222, 6),
	(888, 'EvaluaciÃ³n proyecto', 3333, 2),
	(999, 'EdiciÃ³n mockers', 3333, 7);
/*!40000 ALTER TABLE `tarea` ENABLE KEYS */;

-- Volcando estructura para tabla bananagroupmaven.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `idusuario_UNIQUE` (`uid`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3334 DEFAULT CHARSET=utf8 COMMENT='				';

-- Volcando datos para la tabla bananagroupmaven.usuario: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`uid`, `nombre`, `email`, `password`) VALUES
	(1111, 'Ricardo', 'ricardo@r.es', 'r123456'),
	(2222, 'Juana', 'juana@j.es', 'j123456'),
	(3333, 'Luis', 'luis@l.es', 'l123456');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
