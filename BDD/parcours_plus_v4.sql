-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Lun 02 Mai 2016 à 22:39
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `parcours_plus`
--

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

CREATE TABLE IF NOT EXISTS `module` (
  `id_module` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) DEFAULT NULL,
  `a_la_carte` varchar(255) DEFAULT NULL,
  `id_parcours` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_module`),
  UNIQUE KEY `libelle` (`libelle`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4852 ;

-- --------------------------------------------------------

--
-- Structure de la table `parcours`
--

CREATE TABLE IF NOT EXISTS `parcours` (
  `id_parcours` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_parcours`),
  UNIQUE KEY `libelle` (`libelle`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4754 ;

--
-- Contenu de la table `parcours`
--

INSERT INTO `parcours` (`id_parcours`, `libelle`) VALUES
(1, 'parcours_raj');

-- --------------------------------------------------------

--
-- Structure de la table `sequence`
--

CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '50');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) NOT NULL,
  `login` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `role` varchar(20) DEFAULT NULL,
  `mdp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `login`, `email`, `role`, `mdp`) VALUES
(1, 'raj', 'raj', 'raj', 'responsable', 'raj'),
(2, 'ke', 'ke', 'ke', 'responsable', 'ke'),
(3, 'etudiant', 'etudiant', 'etudiant', 'etudiant', 'etudiant');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur_module`
--

CREATE TABLE IF NOT EXISTS `utilisateur_module` (
  `id` int(11) NOT NULL,
  `id_module` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_module`),
  KEY `FK_utilisateur_module_id_module` (`id_module`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur_parcours`
--

CREATE TABLE IF NOT EXISTS `utilisateur_parcours` (
  `id` int(11) NOT NULL,
  `id_parcours` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_parcours`),
  KEY `FK_utilisateur_parcours_id_parcours` (`id_parcours`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `utilisateur_parcours`
--

INSERT INTO `utilisateur_parcours` (`id`, `id_parcours`) VALUES
(3, 1),
(1, 1);


-- --------------------------------------------------------

--
-- Structure de la table `profile`
--

CREATE TABLE IF NOT EXISTS `profile` (
  `id_profile` int(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `promotion` varchar(50) NOT NULL,
  `parcours` varchar(20) DEFAULT NULL,
  `image` longblob,
  PRIMARY KEY (`id_profile`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `profile`
--

INSERT INTO `profile` (`id_profile`, `nom`, `prenom`, `promotion`, `parcours`, `image`) VALUES
(1, 'raj', 'raj', 'A2', 'GL', NULL);

--
-- Structure de la table `utilisateur_profile`
--

CREATE TABLE IF NOT EXISTS `utilisateur_profile` (
  `id` int(11) NOT NULL,
  `id_profile` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_profile`),
  KEY `FK_utilisateur_parcours_id_profile` (`id_profile`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- contenu utilisateur_profile
INSERT INTO `utilisateur_profile` (`id`, `id_profile`) VALUES
(1, '1');


-- à tester si vous voulez comprendre 
--SELECT nom FROM profile NATURAL JOIN utilisateur_profile WHERE utilisateur_profile.id ='1';


-- --------------------------------------------------------

--
-- Structure de la table `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `id_status` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) NOT NULL,
  PRIMARY KEY (`id_status`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `status`
--

INSERT INTO `status` (`id_status`, `libelle`) VALUES
(1, 'Nouvelle'),
(2, 'Accepté'),
(3, 'Refusé');

-- --------------------------------------------------------

--
-- Structure de la table `parcours_status`
--

CREATE TABLE IF NOT EXISTS `parcours_status` (
  `id_parcours_status` int(11) NOT NULL AUTO_INCREMENT,
  `id_utilisateur` int(11) NOT NULL,
  `id_parcours` int(11) NOT NULL,
  `id_status` int(11) NOT NULL,
  `priorite_choix_parcours` int(11) NOT NULL,
  PRIMARY KEY (`id_parcours_status`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `parcours_status`
--

INSERT INTO `parcours_status` (`id_parcours_status`, `id_utilisateur`, `id_parcours`, `id_status`, `priorite_choix_parcours`) VALUES
(1, 3, 1, 1, 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `utilisateur_module`
--
ALTER TABLE `utilisateur_module`
  ADD CONSTRAINT `FK_utilisateur_module_id_module` FOREIGN KEY (`id_module`) REFERENCES `module` (`id_module`),
  ADD CONSTRAINT `FK_utilisateur_module_id` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `utilisateur_parcours`
--
ALTER TABLE `utilisateur_parcours`
  ADD CONSTRAINT `FK_utilisateur_parcours_id_parcours` FOREIGN KEY (`id_parcours`) REFERENCES `parcours` (`id_parcours`),
  ADD CONSTRAINT `FK_utilisateur_parcours_id` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);


--
-- Contraintes pour la table `utilisateur_profile`
--
ALTER TABLE `utilisateur_profile`
  ADD CONSTRAINT `FK_utilisateur_profile_id_profile` FOREIGN KEY (`id_profile`) REFERENCES `profile` (`id_profile`),
  ADD CONSTRAINT `FK_utilisateur_profile_id` FOREIGN KEY (`id`) REFERENCES `utilisateur` (`id`);    


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



