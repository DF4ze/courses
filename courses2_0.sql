-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Dim 13 Novembre 2016 à 22:22
-- Version du serveur :  5.7.13-0ubuntu0.16.04.2
-- Version de PHP :  7.0.8-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `courses2_0`
--

-- --------------------------------------------------------

--
-- Structure de la table `arrivee`
--

CREATE TABLE `arrivee` (
  `id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `num_chv` int(11) NOT NULL,
  `num_arrivee` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `cote`
--

CREATE TABLE `cote` (
  `id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `num_chv` int(11) NOT NULL,
  `cote` float NOT NULL DEFAULT '0',
  `enjeux` float NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `course`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `hippodrome` varchar(255) NOT NULL,
  `reunion` int(11) NOT NULL,
  `course` int(11) NOT NULL,
  `prix` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `prime` varchar(15) DEFAULT NULL,
  `depart` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `coursecomplete`
--

CREATE TABLE `coursecomplete` (
  `id` int(11) NOT NULL,
  `agesexe_chvl_premier` varchar(5) DEFAULT NULL,
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
  `gains_chv_premier` varchar(50) DEFAULT NULL,
  `total_pourcent` float NOT NULL,
  `type_course` varchar(30) NOT NULL,
  `numero_meilleur_gain` int(11) DEFAULT NULL,
  `musique_meilleur_gain` varchar(20) DEFAULT NULL,
  `1er` int(11) NOT NULL,
  `2eme` int(11) NOT NULL,
  `3eme` int(11) DEFAULT NULL,
  `4eme` int(11) DEFAULT NULL,
  `5eme` int(11) DEFAULT NULL,
  `6eme` int(11) DEFAULT NULL,
  `7eme` int(11) DEFAULT NULL,
  `8eme` int(11) DEFAULT NULL,
  `9eme` int(11) DEFAULT NULL,
  `10eme` int(11) DEFAULT NULL,
  `11eme` int(11) DEFAULT NULL,
  `12eme` int(11) DEFAULT NULL,
  `13eme` int(11) DEFAULT NULL,
  `14eme` int(11) DEFAULT NULL,
  `15eme` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='les pourcentages et cotes des 2 et 3 sont parfois null ';

-- --------------------------------------------------------

--
-- Structure de la table `partant`
--

CREATE TABLE `partant` (
  `id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `num_chv` int(11) NOT NULL,
  `nom_chv` varchar(100) DEFAULT NULL,
  `age_sexe` varchar(5) DEFAULT NULL,
  `musique` varchar(20) DEFAULT NULL,
  `gains` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `rapport`
--

CREATE TABLE `rapport` (
  `id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `num_chv` int(11) NOT NULL,
  `arrivee` int(11) NOT NULL DEFAULT '0',
  `place` double DEFAULT '0',
  `gagnant` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `arrivee`
--
ALTER TABLE `arrivee`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `cote`
--
ALTER TABLE `cote`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `paire_cheval_numcourse` (`course_id`,`num_chv`),
  ADD KEY `course_id` (`course_id`);

--
-- Index pour la table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `coursecomplete`
--
ALTER TABLE `coursecomplete`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `partant`
--
ALTER TABLE `partant`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `paire_cheval_numcourse` (`course_id`,`num_chv`),
  ADD KEY `course_id` (`course_id`);

--
-- Index pour la table `rapport`
--
ALTER TABLE `rapport`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `paire_cheval_numcourse` (`course_id`,`num_chv`),
  ADD KEY `course_id` (`course_id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `arrivee`
--
ALTER TABLE `arrivee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=861171;
--
-- AUTO_INCREMENT pour la table `cote`
--
ALTER TABLE `cote`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=301118;
--
-- AUTO_INCREMENT pour la table `partant`
--
ALTER TABLE `partant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=314029;
--
-- AUTO_INCREMENT pour la table `rapport`
--
ALTER TABLE `rapport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73345;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
