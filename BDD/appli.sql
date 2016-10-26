-- MySQL Script generated by MySQL Workbench
-- 10/23/16 18:58:22
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Personnes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Personnes` (
  `nom` VARCHAR(255) NOT NULL,
  `prenom` VARCHAR(255) NOT NULL,
  `dateNaissance` DATETIME NULL,
  PRIMARY KEY (`nom`, `prenom`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Patient` (
  `idPatient` INT NOT NULL,
  `adresseMac` VARCHAR(18) NOT NULL,
  `frequenceBoire` DATETIME NOT NULL,
  `rappelRepas` DATETIME NOT NULL,
  `nom` VARCHAR(255) NOT NULL,
  `prenom` VARCHAR(255) NOT NULL,
  `AbscenceMidi` DATETIME NULL,
  `AbscenceSoir` DATETIME NULL,
  `rdv` DATETIME NULL,
  `note` LONGTEXT NULL,
  PRIMARY KEY (`idPatient`),
  INDEX `nom` (),
  INDEX `prenom` (),
  CONSTRAINT `nom`
    FOREIGN KEY ()
    REFERENCES `mydb`.`Personnes` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `prenom`
    FOREIGN KEY ()
    REFERENCES `mydb`.`Personnes` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Infermiere`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Infermiere` (
  `idInfermiere` INT NOT NULL,
  `niveau` VARCHAR(85) NULL,
  `nom` VARCHAR(255) NOT NULL,
  `prenom` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idInfermiere`),
  CONSTRAINT `nom`
    FOREIGN KEY ()
    REFERENCES `mydb`.`Personnes` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `prenom`
    FOREIGN KEY ()
    REFERENCES `mydb`.`Personnes` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
