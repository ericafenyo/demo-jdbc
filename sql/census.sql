CREATE TABLE `cities`(
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    `code` INT NOT NULL,
    `population` BIGINT NOT NULL,
    `departmentId` BIGINT NOT NULL,
    `regionId` BIGINT NOT NULL
);


ALTER TABLE `cities` ADD UNIQUE `cities_name_unique`(`name`);

CREATE TABLE `departments`(
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `code` VARCHAR(10) NOT NULL
);

ALTER TABLE `departments` ADD UNIQUE `departments_code_unique`(`code`);

CREATE TABLE `regions`(
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    `code` INT NOT NULL
);

ALTER TABLE `regions` ADD UNIQUE `regions_name_unique`(`name`);

ALTER TABLE `cities` ADD CONSTRAINT `fk_city_region` FOREIGN KEY(`regionId`) REFERENCES `regions`(`id`);
    
ALTER TABLE `cities` ADD CONSTRAINT `fk_city_department` FOREIGN KEY(`departmentId`) REFERENCES `departments`(`id`);