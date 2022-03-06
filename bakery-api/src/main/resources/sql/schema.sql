DROP DATABASE IF EXISTS localbakery;
CREATE DATABASE localbakery;


DROP TABLE IF EXISTS localbakery.stores;
CREATE TABLE IF NOT EXISTS localbakery.stores
(
    storeId           INT AUTO_INCREMENT PRIMARY KEY,
    type              VARCHAR(255),
    name              VARCHAR(255) NOT NULL,
    address           VARCHAR(255),
    thumbnailImageUrl VARCHAR(1024),
    phoneNumber       VARCHAR(255),
    homePageUrl       VARCHAR(255),
    startTime         TIME,
    endTime           TIME,
    location          POINT,
    modifiedAt        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifiedBy        VARCHAR(255) NOT NULL,
    createdAt         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    createdBy         VARCHAR(255) NOT NULL
) ENGINE = INNODB;

DROP TABLE IF EXISTS localbakery.store_menus;
CREATE TABLE IF NOT EXISTS localbakery.store_menus
(
    storeMenuId           INT AUTO_INCREMENT PRIMARY KEY,
    storeId INT NOT NULL,
    name VARCHAR(255),
    price INT,
    signature TINYINT NOT NULL,
    modifiedAt        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifiedBy        VARCHAR(255) NOT NULL,
    createdAt         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    createdBy         VARCHAR(255) NOT NULL
) ENGINE = INNODB;