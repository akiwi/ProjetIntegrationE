-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- GÃ©nÃ©rÃ© le :  Mer 16 Novembre 2016 Ã  14:32
-- Version du serveur :  5.7.11
-- Version de PHP :  5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de donnÃ©es :  `bdd_carewatch`
--

-- --------------------------------------------------------

--
-- Structure de la table `alarme`
--

CREATE TABLE `alarme` (
  `idAlarme` int(10) UNSIGNED NOT NULL,
  `heure` int(11) NOT NULL,
  `minute` int(11) NOT NULL,
  `raison` varchar(45) DEFAULT NULL,
  `port` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Contenu de la table `alarme`
--

INSERT INTO `alarme` (`idAlarme`, `heure`, `minute`, `raison`, `port`) VALUES
(1, 22, 0, 'eau', 5005),
(2, 15, 0, 'repas', 5004),
(3, 1, 57, 'eau', 5005),
(4, 1, 58, 'eau', 5005),
(5, 22, 16, 'repas', 5004);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `idClient` int(11) NOT NULL,
  `nameUser` varchar(55) NOT NULL,
  `pwdUser` varchar(55) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`idClient`, `nameUser`, `pwdUser`) VALUES
(1, 'julien', 'julien'),
(2, 'aymeric', 'aymeric');

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `port` int(11) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `estPresent` tinyint(1) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Contenu de la table `patient`
--

INSERT INTO `patient` (`port`, `nom`, `prenom`, `estPresent`, `note`) VALUES
(5003, 'Christiaens', 'Fabiola', 0, 'a un fils trop chiant lol \r\n'),
(5004, 'Ousuije', 'Jeanne', 1, 'Rien a declarer'),
(5005, 'Jsaisplus', 'Yvette', 1, 'S ennuie beaucoup'),
(5006, 'brouette', 'brigitte', 0, 'voici un peut de contenu histoire de voir comment si sa s\'affiche correctement, sinon j aime beaucoup manger des patates sa reste une activite tres agreable et benefique '),
(5007, 'laSoupe', 'auPoivre', 1, 'ancienne cow-girl'),
(5008, 'cornemuse', 'etienne', 0, 'aime les framboises');

-- --------------------------------------------------------

--
-- Structure de la table `sortie`
--

CREATE TABLE `sortie` (
  `idSortie` smallint(6) NOT NULL,
  `DateSortie` datetime NOT NULL,
  `DateRentrer` datetime NOT NULL,
  `raison` longtext,
  `port` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=UTF8;

--
-- Contenu de la table `sortie`
--

INSERT INTO `sortie` (`idSortie`, `DateSortie`, `DateRentrer`, `raison`, `port`) VALUES
(1, '2016-11-13 10:00:00', '2016-11-14 07:00:00', 'doit faire pipi ', 5004),
(2, '2016-11-15 00:00:00', '2016-11-17 00:00:00', 'doit faire caca ', 5005),
(3, '2016-11-22 00:00:00', '2016-11-23 00:00:00', 'a une envie tres pressante', 5004);

--
-- Index pour les tables exportÃ©es
--

--
-- Index pour la table `alarme`
--
ALTER TABLE `alarme`
  ADD PRIMARY KEY (`idAlarme`),
  ADD KEY `port` (`port`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`port`);

--
-- Index pour la table `sortie`
--
ALTER TABLE `sortie`
  ADD PRIMARY KEY (`idSortie`),
  ADD KEY `port` (`port`);

--
-- AUTO_INCREMENT pour les tables exportÃ©es
--

--
-- AUTO_INCREMENT pour la table `alarme`
--
ALTER TABLE `alarme`
  MODIFY `idAlarme` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `idClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `sortie`
--
ALTER TABLE `sortie`
  MODIFY `idSortie` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Contraintes pour les tables exportÃ©es
--

--
-- Contraintes pour la table `alarme`
--
ALTER TABLE `alarme`
  ADD CONSTRAINT `Alarme_ibfk_1` FOREIGN KEY (`port`) REFERENCES `patient` (`port`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
