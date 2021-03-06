use pupilbooktests;


-- MySQL Script generated by MySQL Workbench
-- 03/17/15 10:01:51
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema pupilbook
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pupilbook
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pupilbooktests` DEFAULT CHARACTER SET utf8 ;
USE `pupilbooktests` ;

-- -----------------------------------------------------
-- Table `pupilbook`.`schoolyear`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pupilbooktests`.`schoolyear` (
  `idSchoolYear` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `isactualyear` TINYINT(1) NOT NULL,
  `StartDate` DATE NOT NULL,
  `EndDate` DATE NOT NULL,
  PRIMARY KEY (`idSchoolYear`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pupilbook`.`studygroup`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pupilbooktests`.`studygroup` (
  `idStudyGroup` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `SchoolYear_idSchoolYear` INT(11) NOT NULL,
  PRIMARY KEY (`idStudyGroup`),
  INDEX `fk_StudyGroup_SchoolYear_idx` (`SchoolYear_idSchoolYear` ASC),
  CONSTRAINT `fk_StudyGroup_SchoolYear`
    FOREIGN KEY (`SchoolYear_idSchoolYear`)
    REFERENCES `pupilbooktests`.`schoolyear` (`idSchoolYear`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 101
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pupilbook`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pupilbooktests`.`users` (
  `FirstName` VARCHAR(45) NOT NULL,
  `MiddleName` VARCHAR(45) NULL DEFAULT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `Phone` VARCHAR(15) NULL DEFAULT NULL,
  `Email` VARCHAR(100) NULL DEFAULT NULL,
  `Login` VARCHAR(255) NOT NULL,
  `Password` VARCHAR(255) NOT NULL,
  `BirthDate` DATE NULL DEFAULT NULL,
  `StudyGroup_idStudyGroup` INT(11) NULL DEFAULT NULL,
  `Role` CHAR(1) NOT NULL,
  `deleted` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`Login`),
  UNIQUE INDEX `Login_UNIQUE` (`Login` ASC),
  INDEX `fk_Users_StudyGroup1_idx` (`StudyGroup_idStudyGroup` ASC),
  CONSTRAINT `fk_Users_StudyGroup1`
    FOREIGN KEY (`StudyGroup_idStudyGroup`)
    REFERENCES `pupilbooktests`.`studygroup` (`idStudyGroup`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pupilbook`.`attendance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pupilbooktests`.`attendance` (
  `idAttendance` INT(11) NOT NULL AUTO_INCREMENT,
  `MissingStart` DATETIME NOT NULL,
  `MissingEnd` DATETIME NULL DEFAULT NULL,
  `Excussed` TINYINT(1) NOT NULL,
  `Users_Login` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idAttendance`),
  INDEX `fk_Attendance_Users1_idx` (`Users_Login` ASC),
  CONSTRAINT `fk_Attendance_Users1`
    FOREIGN KEY (`Users_Login`)
    REFERENCES `pupilbooktests`.`users` (`Login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 509
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pupilbook`.`informations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pupilbooktests`.`informations` (
  `idinformations` INT(11) NOT NULL AUTO_INCREMENT,
  `Description` VARCHAR(100) NOT NULL,
  `InfoForParrents` TINYINT(1) NOT NULL,
  `SomeMessage` VARCHAR(2000) NOT NULL,
  `CreateDate` DATETIME NOT NULL,
  `StudyGroup_idStudyGroup` INT(11) NULL DEFAULT NULL,
  `Users_Login` VARCHAR(255) NULL DEFAULT NULL,
  `Teacher_Login` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idinformations`),
  INDEX `fk_informations_StudyGroup1_idx` (`StudyGroup_idStudyGroup` ASC),
  INDEX `fk_informations_Users1_idx` (`Users_Login` ASC),
  INDEX `Teacher_Login` (`Teacher_Login` ASC),
  CONSTRAINT `fk_informations_StudyGroup1`
    FOREIGN KEY (`StudyGroup_idStudyGroup`)
    REFERENCES `pupilbooktests`.`studygroup` (`idStudyGroup`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_informations_Users1`
    FOREIGN KEY (`Users_Login`)
    REFERENCES `pupilbooktests`.`users` (`Login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `informations_ibfk_1`
    FOREIGN KEY (`Teacher_Login`)
    REFERENCES `pupilbooktests`.`users` (`Login`))
ENGINE = InnoDB
AUTO_INCREMENT = 101
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pupilbook`.`parrentstudent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pupilbooktests`.`parrentstudent` (
  `idParrentStudent` INT(11) NOT NULL AUTO_INCREMENT,
  `Student_Login` VARCHAR(255) NOT NULL,
  `Parent_Login` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idParrentStudent`),
  INDEX `fk_ParrentStudent_Users1_idx` (`Student_Login` ASC),
  INDEX `fk_ParrentStudent_Users2_idx` (`Parent_Login` ASC),
  CONSTRAINT `fk_ParrentStudent_Users1`
    FOREIGN KEY (`Student_Login`)
    REFERENCES `pupilbooktests`.`users` (`Login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ParrentStudent_Users2`
    FOREIGN KEY (`Parent_Login`)
    REFERENCES `pupilbooktests`.`users` (`Login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 101
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pupilbook`.`studysubject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pupilbooktests`.`studysubject` (
  `idStudySubject` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `ShortName` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`idStudySubject`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pupilbook`.`results`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pupilbooktests`.`results` (
  `idResults` INT(11) NOT NULL AUTO_INCREMENT,
  `Description` VARCHAR(255) NULL DEFAULT NULL,
  `Score` TINYINT(4) NOT NULL,
  `Date` DATETIME NULL DEFAULT NULL,
  `Teacher_Login` VARCHAR(255) NOT NULL,
  `Student_Login` VARCHAR(255) NOT NULL,
  `StudySubject_idStudySubject` INT(11) NOT NULL,
  `SchoolYear_idSchoolYear` INT(11) NOT NULL,
  PRIMARY KEY (`idResults`),
  INDEX `fk_Results_Users1_idx` (`Teacher_Login` ASC),
  INDEX `fk_Results_Users2_idx` (`Student_Login` ASC),
  INDEX `fk_Results_StudySubject1_idx` (`StudySubject_idStudySubject` ASC),
  INDEX `fk_Results_SchoolYear1_idx` (`SchoolYear_idSchoolYear` ASC),
  CONSTRAINT `fk_Results_SchoolYear1`
    FOREIGN KEY (`SchoolYear_idSchoolYear`)
    REFERENCES `pupilbooktests`.`schoolyear` (`idSchoolYear`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Results_StudySubject1`
    FOREIGN KEY (`StudySubject_idStudySubject`)
    REFERENCES `pupilbooktests`.`studysubject` (`idStudySubject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Results_Users1`
    FOREIGN KEY (`Teacher_Login`)
    REFERENCES `pupilbooktests`.`users` (`Login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Results_Users2`
    FOREIGN KEY (`Student_Login`)
    REFERENCES `pupilbooktests`.`users` (`Login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 165
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pupilbook`.`sheduleitem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pupilbooktests`.`sheduleitem` (
  `idSheduleItem` INT(11) NOT NULL AUTO_INCREMENT,
  `day` TINYINT(3) NOT NULL,
  `hour` TINYINT(3) NOT NULL,
  `StudyGroup_idStudyGroup` INT(11) NOT NULL,
  `Users_Login` VARCHAR(255) NULL DEFAULT NULL,
  `StudySubject_idStudySubject` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idSheduleItem`),
  INDEX `fk_SheduleItem_StudyGroup1_idx` (`StudyGroup_idStudyGroup` ASC),
  INDEX `fk_SheduleItem_Users1_idx` (`Users_Login` ASC),
  INDEX `fk_SheduleItem_StudySubject1_idx` (`StudySubject_idStudySubject` ASC),
  CONSTRAINT `fk_SheduleItem_StudyGroup1`
    FOREIGN KEY (`StudyGroup_idStudyGroup`)
    REFERENCES `pupilbooktests`.`studygroup` (`idStudyGroup`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SheduleItem_StudySubject1`
    FOREIGN KEY (`StudySubject_idStudySubject`)
    REFERENCES `pupilbooktests`.`studysubject` (`idStudySubject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SheduleItem_Users1`
    FOREIGN KEY (`Users_Login`)
    REFERENCES `pupilbooktests`.`users` (`Login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 441
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pupilbook`.`teacher_subjects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pupilbooktests`.`teacher_subjects` (
  `idTeacher_Subjects` INT(11) NOT NULL,
  `Users_Login` VARCHAR(255) NOT NULL,
  `StudySubject_idStudySubject` INT(11) NOT NULL,
  PRIMARY KEY (`idTeacher_Subjects`),
  INDEX `fk_Teacher_Subjects_Users1_idx` (`Users_Login` ASC),
  INDEX `fk_Teacher_Subjects_StudySubject1_idx` (`StudySubject_idStudySubject` ASC),
  CONSTRAINT `fk_Teacher_Subjects_StudySubject1`
    FOREIGN KEY (`StudySubject_idStudySubject`)
    REFERENCES `pupilbooktests`.`studysubject` (`idStudySubject`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Teacher_Subjects_Users1`
    FOREIGN KEY (`Users_Login`)
    REFERENCES `pupilbooktests`.`users` (`Login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
