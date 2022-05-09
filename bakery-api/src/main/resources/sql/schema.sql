DROP DATABASE IF EXISTS localbakery;
CREATE DATABASE localbakery;


DROP TABLE IF EXISTS localbakery.stores;
CREATE TABLE IF NOT EXISTS localbakery.stores
(
    storeId           INT AUTO_INCREMENT PRIMARY KEY,
    type              VARCHAR(255),
    name              VARCHAR(255) NOT NULL,
    address           VARCHAR(255),
    ratingAverage     FLOAT        NOT NULL DEFAULT 0,
    totalCount        INT,
    totalRating       FLOAT        NOT NULL DEFAULT 0,
    thumbnailImageUrl VARCHAR(1024),
    phoneNumber       VARCHAR(255),
    homePageUrl       VARCHAR(255),
    startTime         TIME,
    endTime           TIME,
    location          POINT,
    modifiedAt        TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    modifiedBy        VARCHAR(255) NOT NULL,
    createdAt         TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    createdBy         VARCHAR(255) NOT NULL
) ENGINE = INNODB;



DROP TABLE IF EXISTS localbakery.accounts;
CREATE TABLE iF NOT EXISTS localbakery.accounts
(
    accountId BIGINT AUTO_INCREMENT PRIMARY KEY,
    email     VARCHAR(255),
    userName  VARCHAR(255),
    roles     VARCHAR(255),
    imageUrl  VARCHAR(255)
) ENGINE = INNODB;

DROP TABLE IF EXISTS localbakery.hometown;
CREATE TABLE iF NOT EXISTS localbakery.hometown
(
    hometownId INT AUTO_INCREMENT PRIMARY KEY ,
    address          POINT NOT NULL
) ENGINE = INNODB;

DROP TABLE IF EXISTS localbakery.store_menus;
CREATE TABLE IF NOT EXISTS localbakery.store_menus
(
    storeMenuId INT AUTO_INCREMENT PRIMARY KEY,
    storeId     INT          NOT NULL,
    name        VARCHAR(255),
    price       INT,
    signature   TINYINT      NOT NULL,
    modifiedAt  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifiedBy  VARCHAR(255) NOT NULL,
    createdAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    createdBy   VARCHAR(255) NOT NULL
) ENGINE = INNODB;

DROP TABLE IF EXISTS localbakery.reviews;
CREATE TABLE IF NOT EXISTS localbakery.reviews
(
    reviewId      INT AUTO_INCREMENT PRIMARY KEY,
    storeId       INT   NOT NULL,
    reviewerId    INT   NOT NULL,
    reviewerEmail VARCHAR(255),
    contents      VARCHAR(1024),
    ratings       FLOAT NOT NULL DEFAULT 0,
    specials      VARCHAR(255),
    recommends    VARCHAR(255),
    modifiedAt    TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
    createdAt     TIMESTAMP      DEFAULT CURRENT_TIMESTAMP
) ENGINE = INNODB;

# DROP TABLE IF EXISTS localbakery.accounts;
# CREATE TABLE IF NOT EXISTS localbakery.accounts
# (
#     id           INT AUTO_INCREMENT PRIMARY KEY,
#     email VARCHAR(255),
#     user_name VARCHAR(255)
#
# ) ENGINE = INNODB;

DROP TABLE IF EXISTS localbakery.images;
CREATE TABLE IF NOT EXISTS localbakery.images
(
    imageId    INT AUTO_INCREMENT PRIMARY KEY,
    imageUrl   VARCHAR(1024),
    reviewId   INT NOT NULL,
    modifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    createdAt  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_reviews_images FOREIGN KEY (reviewId) REFERENCES reviews (reviewId) ON DELETE CASCADE
) ENGINE = INNODB;