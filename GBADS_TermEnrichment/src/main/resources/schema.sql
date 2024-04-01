-- -----------------------------------------------------
-- Schema termdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `termdb` ;

-- -----------------------------------------------------
-- Schema termdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `termdb` DEFAULT CHARACTER SET utf8 ;
USE `termdb` ;

-- -----------------------------------------------------
-- Table `termdb`.`term`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `termdb`.`term` ;

CREATE TABLE IF NOT EXISTS `termdb`.`term` (
  `term_id` BIGINT NOT NULL AUTO_INCREMENT,
  `species` VARCHAR(150) NOT NULL,
  `super_class`  VARCHAR(50) NOT NULL,
  `country` VARCHAR(300) NULL,
  `term_year` VARCHAR(10) NULL,
  `data_source` VARCHAR(500) NULL,
  `description` VARCHAR(1500) NULL,
  `ontology_version` VARCHAR(1500) NOT NULL,
  PRIMARY KEY (`term_id`))
ENGINE = InnoDB;



