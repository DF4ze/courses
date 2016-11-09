-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Sam 17 Octobre 2015 à 01:15
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `courses2_0`
--

-- --------------------------------------------------------

--
-- Structure de la table `cote`
--

CREATE TABLE IF NOT EXISTS `cote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `num_chv` int(11) NOT NULL,
  `cote` float NOT NULL DEFAULT '0',
  `enjeux` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `paire_cheval_numcourse` (`course_id`,`num_chv`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=473549 ;

-- --------------------------------------------------------

--
-- Structure de la table `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `id` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `hippodrome` varchar(255) NOT NULL,
  `reunion` int(11) NOT NULL,
  `course` int(11) NOT NULL,
  `prix` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `prime` varchar(15) DEFAULT NULL,
  `depart` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `coursecomplete`
--

CREATE TABLE IF NOT EXISTS `coursecomplete` (
  `id` int(11) NOT NULL,
  `agesexe_chvl_premier` varchar(5) NOT NULL,
  `depart` varchar(5) NOT NULL,
  `date` varchar(20) NOT NULL,
  `hippodrome` varchar(50) NOT NULL,
  `musique_premier` varchar(20) DEFAULT NULL,
  `nb_chv_inf_cinq` int(11) NOT NULL,
  `nb_chv_fav_place` int(11) NOT NULL,
  `nb_partant` int(11) NOT NULL,
  `nom_chv_premier` varchar(100) DEFAULT NULL,
  `num_chv_deuxieme` int(11) NOT NULL,
  `num_chv_premier` int(11) NOT NULL,
  `num_chv_troisieme` int(11) DEFAULT NULL,
  `num_course` int(11) NOT NULL,
  `num_fav_deuxieme` int(11) DEFAULT NULL,
  `num_fav_premier` int(11) DEFAULT NULL,
  `num_fav_troisieme` int(11) DEFAULT NULL,
  `num_reunion` int(11) NOT NULL,
  `pourcent_fav_deuxieme` float DEFAULT NULL,
  `pourcent_fav_premier` float DEFAULT NULL,
  `pourcent_fav_troisieme` float DEFAULT NULL,
  `prime` varchar(20) DEFAULT NULL,
  `rap_gagnant` double NOT NULL,
  `rap_place_deuxieme` double NOT NULL,
  `rap_place_premier` double NOT NULL,
  `rap_place_troisieme` double DEFAULT NULL,
  `total_pourcent` float NOT NULL,
  `type_course` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='les pourcentages et cotes des 2 et 3 sont parfois null ';

-- --------------------------------------------------------

--
-- Structure de la table `partant`
--

CREATE TABLE IF NOT EXISTS `partant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `num_chv` int(11) NOT NULL,
  `nom_chv` varchar(100) NOT NULL,
  `age_sexe` varchar(5) NOT NULL,
  `musique` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `paire_cheval_numcourse` (`course_id`,`num_chv`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=495180 ;

-- --------------------------------------------------------

--
-- Structure de la table `rapport`
--

CREATE TABLE IF NOT EXISTS `rapport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `num_chv` int(11) NOT NULL,
  `arrivee` int(11) NOT NULL DEFAULT '0',
  `place` double DEFAULT '0',
  `gagnant` double DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `paire_cheval_numcourse` (`course_id`,`num_chv`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=114150 ;
