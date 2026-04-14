DROP TABLE IF EXISTS cats;
DROP TABLE IF EXISTS members;

CREATE TABLE members
(
    memberId    INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    password    VARCHAR(50)  NOT NULL,
    email       VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(50)  NOT NULL
);

CREATE TABLE cats
(
    catId INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(100) NOT NULL,
    mom   VARCHAR(100),
    dad   VARCHAR(100),
    color VARCHAR(100) NOT NULL,
    race  ENUM('BRITISH_SHORTHAIR', 'MAINE_COON', 'NORSK_SKOVKAT') NOT NULL,
    memberId INT,
    FOREIGN KEY (memberId)
    REFERENCES members(memberId)
    ON DELETE CASCADE
);

