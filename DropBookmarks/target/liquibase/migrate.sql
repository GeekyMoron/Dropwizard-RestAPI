--  Create Database Lock Table
CREATE TABLE DATABASECHANGELOGLOCK (ID INT NOT NULL, `LOCKED` BIT(1) NOT NULL, LOCKGRANTED datetime NULL, LOCKEDBY VARCHAR(255) NULL, CONSTRAINT PK_DATABASECHANGELOGLOCK PRIMARY KEY (ID));

--  Initialize Database Lock Table
DELETE FROM DATABASECHANGELOGLOCK;

INSERT INTO DATABASECHANGELOGLOCK (ID, `LOCKED`) VALUES (1, 0);

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET `LOCKED` = 1, LOCKEDBY = 'C02FV9AFMD6R (192.168.2.104)', LOCKGRANTED = NOW() WHERE ID = 1 AND `LOCKED` = 0;

--  Create Database Change Log Table
CREATE TABLE DATABASECHANGELOG (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED datetime NOT NULL, ORDEREXECUTED INT NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35) NULL, `DESCRIPTION` VARCHAR(255) NULL, COMMENTS VARCHAR(255) NULL, TAG VARCHAR(255) NULL, LIQUIBASE VARCHAR(20) NULL, CONTEXTS VARCHAR(255) NULL, LABELS VARCHAR(255) NULL, DEPLOYMENT_ID VARCHAR(10) NULL);

--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/dataBaseMigrations.xml
--  Ran at: 13/07/23, 1:08 AM
--  Against: root@localhost@jdbc:mysql://localhost:3306/DropBookmarks
--  Liquibase version: 4.22.0
--  *********************************************************************

--  Changeset src/main/resources/dataBaseMigrations.xml::1::nikhil
--  it is a script to create users table
CREATE TABLE users (id BIGINT AUTO_INCREMENT NOT NULL, username VARCHAR(155) NOT NULL, password VARCHAR(155) NOT NULL, CONSTRAINT PK_USERS PRIMARY KEY (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, `DESCRIPTION`, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1', 'nikhil', 'src/main/resources/dataBaseMigrations.xml', NOW(), 1, '9:176e3825e070a86afc8a36050494b42c', 'createTable tableName=users', 'it is a script to create users table', 'EXECUTED', NULL, NULL, '4.22.0', '9190713222');

--  Changeset src/main/resources/dataBaseMigrations.xml::2::nikhil
--  it is a script to create bookmarks table
CREATE TABLE bookmarks (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(255) NOT NULL, url VARCHAR(1955) NOT NULL, `description` VARCHAR(1955) NULL, user_id BIGINT NOT NULL, CONSTRAINT PK_BOOKMARKS PRIMARY KEY (id), CONSTRAINT fk_users_id FOREIGN KEY (user_id) REFERENCES user(id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, `DESCRIPTION`, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('2', 'nikhil', 'src/main/resources/dataBaseMigrations.xml', NOW(), 2, '9:7b2df930338b0f3610fd9a24bcf8a6fd', 'createTable tableName=bookmarks', 'it is a script to create bookmarks table', 'EXECUTED', NULL, NULL, '4.22.0', '9190713222');

--  Changeset src/main/resources/dataBaseMigrations.xml::3::nikhil
--  it is a script to inert data in users table
INSERT INTO users (id, username, password) VALUES ('1', 'udemy', 'password');

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, `DESCRIPTION`, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('3', 'nikhil', 'src/main/resources/dataBaseMigrations.xml', NOW(), 3, '9:b086351b6c1c6d9293df16628d3f6771', 'insert tableName=users', 'it is a script to inert data in users table', 'EXECUTED', NULL, NULL, '4.22.0', '9190713222');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET `LOCKED` = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

