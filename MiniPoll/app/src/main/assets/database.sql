--
-- File generated with SQLiteStudio v3.1.1 on jeu. mars 8 16:42:25 2018
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: AGREEMENT
DROP TABLE IF EXISTS AGREEMENT;
CREATE TABLE AGREEMENT ( IDAGREEMENT not null primary key,TITLE not null,AUTHOR not null references USER,CLOSED not null default false);
INSERT INTO AGREEMENT (IDAGREEMENT, TITLE, AUTHOR, CLOSED) VALUES (1, 'Aidez moi a choisir un Pokemon :-)', 'Jolie Fille', 'false');

-- Table: ANSWER_AGREEMENT
DROP TABLE IF EXISTS ANSWER_AGREEMENT;
CREATE TABLE ANSWER_AGREEMENT ( LOGIN not null references USER,IDAGREEMENT not null references AGREEMENT,CHOICE not null references CHOICE_AGREEMENT,WEIGHT not null,unique(LOGIN,IDAGREEMENT,WEIGHT),unique(LOGIN,IDAGREEMENT,CHOICE) );
INSERT INTO ANSWER_AGREEMENT (LOGIN, IDAGREEMENT, CHOICE, WEIGHT) VALUES ('Sacha LOL', 1, 3, 1);
INSERT INTO ANSWER_AGREEMENT (LOGIN, IDAGREEMENT, CHOICE, WEIGHT) VALUES ('Sacha LOL', 1, 2, 3);
INSERT INTO ANSWER_AGREEMENT (LOGIN, IDAGREEMENT, CHOICE, WEIGHT) VALUES ('Sacha LOL', 1, 1, 2);
INSERT INTO ANSWER_AGREEMENT (LOGIN, IDAGREEMENT, CHOICE, WEIGHT) VALUES ('Le Doc', 1, 3, 2);
INSERT INTO ANSWER_AGREEMENT (LOGIN, IDAGREEMENT, CHOICE, WEIGHT) VALUES ('Le Doc', 1, 2, 3);
INSERT INTO ANSWER_AGREEMENT (LOGIN, IDAGREEMENT, CHOICE, WEIGHT) VALUES ('Le Doc', 1, 1, 1);
INSERT INTO ANSWER_AGREEMENT (LOGIN, IDAGREEMENT, CHOICE, WEIGHT) VALUES ('Pika Pika', 1, 3, 1);
INSERT INTO ANSWER_AGREEMENT (LOGIN, IDAGREEMENT, CHOICE, WEIGHT) VALUES ('Pika Pika', 1, 2, 2);
INSERT INTO ANSWER_AGREEMENT (LOGIN, IDAGREEMENT, CHOICE, WEIGHT) VALUES ('Pika Pika', 1, 1, 3);

-- Table: ANSWER_BIPOLL
DROP TABLE IF EXISTS ANSWER_BIPOLL;
CREATE TABLE ANSWER_BIPOLL (LOGIN NOT NULL REFERENCES USER, CHOICE NOT NULL REFERENCES CHOICE_BIPOLL, IDBIPOLL NOT NULL REFERENCES BIPOLL, UNIQUE (LOGIN, IDBIPOLL));
INSERT INTO ANSWER_BIPOLL (LOGIN, CHOICE, IDBIPOLL) VALUES ('Sacha LOL', 2, 1);
INSERT INTO ANSWER_BIPOLL (LOGIN, CHOICE, IDBIPOLL) VALUES ('Pika Pika', 1, 1);

-- Table: ANSWER_QUIZ
DROP TABLE IF EXISTS ANSWER_QUIZ;
CREATE TABLE ANSWER_QUIZ (LOGIN not null references USER,CHOICE not null references CHOICE_QUIZ,IDQUESTION not null references QUESTION_QUIZ,unique(LOGIN,IDQUESTION));
INSERT INTO ANSWER_QUIZ (LOGIN, CHOICE, IDQUESTION) VALUES ('Pika Pika', 14, 1);
INSERT INTO ANSWER_QUIZ (LOGIN, CHOICE, IDQUESTION) VALUES ('Pika Pika', 22, 2);
INSERT INTO ANSWER_QUIZ (LOGIN, CHOICE, IDQUESTION) VALUES ('Pika Pika', 33, 3);
INSERT INTO ANSWER_QUIZ (LOGIN, CHOICE, IDQUESTION) VALUES ('Jolie Fille', 12, 1);
INSERT INTO ANSWER_QUIZ (LOGIN, CHOICE, IDQUESTION) VALUES ('Jolie Fille', 24, 2);
INSERT INTO ANSWER_QUIZ (LOGIN, CHOICE, IDQUESTION) VALUES ('Jolie Fille', 31, 3);
INSERT INTO ANSWER_QUIZ (LOGIN, CHOICE, IDQUESTION) VALUES ('Le Doc', 12, 1);
INSERT INTO ANSWER_QUIZ (LOGIN, CHOICE, IDQUESTION) VALUES ('Le Doc', 23, 2);
INSERT INTO ANSWER_QUIZ (LOGIN, CHOICE, IDQUESTION) VALUES ('Le Doc', 31, 3);

-- Table: BIPOLL
DROP TABLE IF EXISTS BIPOLL;
CREATE TABLE BIPOLL ( IDBIPOLL not null primary key,TITLE not null,AUTHOR not null references USER,CHOICE1 not null references CHOICE_BIPOLL,CHOICE2 not null references CHOICE_BIPOLL,CLOSED not null default false );
INSERT INTO BIPOLL (IDBIPOLL, TITLE, AUTHOR, CHOICE1, CHOICE2, CLOSED) VALUES (1, 'Quelle est ton pokemon prefere?', 'Le Doc', 1, 2, 'false');

-- Table: CHOICE_AGREEMENT
DROP TABLE IF EXISTS CHOICE_AGREEMENT;
CREATE TABLE CHOICE_AGREEMENT ( IDCHOICE not null primary key,IDAGREEMENT not null references AGREEMENT,TITLE not null);
INSERT INTO CHOICE_AGREEMENT (IDCHOICE, IDAGREEMENT, TITLE) VALUES (1, 1, 'Salameche');
INSERT INTO CHOICE_AGREEMENT (IDCHOICE, IDAGREEMENT, TITLE) VALUES (2, 1, 'Bulbizard');
INSERT INTO CHOICE_AGREEMENT (IDCHOICE, IDAGREEMENT, TITLE) VALUES (3, 1, 'Carapuce');

-- Table: CHOICE_BIPOLL
DROP TABLE IF EXISTS CHOICE_BIPOLL;
CREATE TABLE CHOICE_BIPOLL( IDCHOICE not null primary key,CONTENT not null);
INSERT INTO CHOICE_BIPOLL (IDCHOICE, CONTENT) VALUES (1, 'Pikachu');
INSERT INTO CHOICE_BIPOLL (IDCHOICE, CONTENT) VALUES (2, 'Florisard');

-- Table: CHOICE_QUIZ
DROP TABLE IF EXISTS CHOICE_QUIZ;
CREATE TABLE CHOICE_QUIZ (IDCHOICE NOT NULL PRIMARY KEY, IDQUESTION NOT NULL REFERENCES QUESTION_QUIZ, TITLE NOT NULL, ISGOOD_ANSWER NOT NULL, UNIQUE (IDCHOICE, IDQUESTION));
INSERT INTO CHOICE_QUIZ (IDCHOICE, IDQUESTION, TITLE, ISGOOD_ANSWER) VALUES (11, 1, 'Salameche', 0);
INSERT INTO CHOICE_QUIZ (IDCHOICE, IDQUESTION, TITLE, ISGOOD_ANSWER) VALUES (12, 1, 'Magicarpe', 1);
INSERT INTO CHOICE_QUIZ (IDCHOICE, IDQUESTION, TITLE, ISGOOD_ANSWER) VALUES (13, 1, 'Ratata', 0);
INSERT INTO CHOICE_QUIZ (IDCHOICE, IDQUESTION, TITLE, ISGOOD_ANSWER) VALUES (14, 1, 'Roucoups', 0);
INSERT INTO CHOICE_QUIZ (IDCHOICE, IDQUESTION, TITLE, ISGOOD_ANSWER) VALUES (21, 2, 'Zarbi', 0);
INSERT INTO CHOICE_QUIZ (IDCHOICE, IDQUESTION, TITLE, ISGOOD_ANSWER) VALUES (22, 2, 'Kadabra', 0);
INSERT INTO CHOICE_QUIZ (IDCHOICE, IDQUESTION, TITLE, ISGOOD_ANSWER) VALUES (23, 2, 'Barbouille', 1);
INSERT INTO CHOICE_QUIZ (IDCHOICE, IDQUESTION, TITLE, ISGOOD_ANSWER) VALUES (24, 2, 'ColoSinge', 0);
INSERT INTO CHOICE_QUIZ (IDCHOICE, IDQUESTION, TITLE, ISGOOD_ANSWER) VALUES (31, 3, 'Mer', 1);
INSERT INTO CHOICE_QUIZ (IDCHOICE, IDQUESTION, TITLE, ISGOOD_ANSWER) VALUES (32, 3, 'Volcan', 0);
INSERT INTO CHOICE_QUIZ (IDCHOICE, IDQUESTION, TITLE, ISGOOD_ANSWER) VALUES (33, 3, 'Prairie', 0);
INSERT INTO CHOICE_QUIZ (IDCHOICE, IDQUESTION, TITLE, ISGOOD_ANSWER) VALUES (34, 3, 'Champs', 0);

-- Table: FRIENDS
DROP TABLE IF EXISTS FRIENDS;
CREATE TABLE FRIENDS (LOGIN1 NOT NULL REFERENCES USER, LOGIN2 NOT NULL REFERENCES USER, STATE DEFAULT 0, UNIQUE (LOGIN1, LOGIN2));
INSERT INTO FRIENDS (LOGIN1, LOGIN2, STATE) VALUES ('Sacha LOL', 'Pika Pika', 1);
INSERT INTO FRIENDS (LOGIN1, LOGIN2, STATE) VALUES ('Sacha LOL', 'Jolie Fille', 0);
INSERT INTO FRIENDS (LOGIN1, LOGIN2, STATE) VALUES ('Le Doc', 'Sacha LOL', 1);

-- Table: QUESTION_QUIZ
DROP TABLE IF EXISTS QUESTION_QUIZ;
CREATE TABLE QUESTION_QUIZ ( IDQUESTION not null primary key,IDQUIZ not null references QUIZ,POSITION not null,TITLE not null,unique(IDQUIZ,POSITION));
INSERT INTO QUESTION_QUIZ (IDQUESTION, IDQUIZ, POSITION, TITLE) VALUES (1, 1, 1, 'lequel de ces pokemon est de type eau?');
INSERT INTO QUESTION_QUIZ (IDQUESTION, IDQUIZ, POSITION, TITLE) VALUES (2, 1, 2, 'lequel n est pas un pokemon?');
INSERT INTO QUESTION_QUIZ (IDQUESTION, IDQUIZ, POSITION, TITLE) VALUES (3, 1, 3, 'Ou peut-on trouver des Staris?');

-- Table: QUIZ
DROP TABLE IF EXISTS QUIZ;
CREATE TABLE QUIZ( IDQUIZ not null primary key,TITLE not null,AUTHOR not null references USER,CLOSED not null default false);
INSERT INTO QUIZ (IDQUIZ, TITLE, AUTHOR, CLOSED) VALUES (1, 'Mon premier petit quiz :-)', 'Sacha LOL', 'false');

-- Table: USER
DROP TABLE IF EXISTS USER;
CREATE TABLE USER ( LOGIN not null primary key,PASSWORD not null,LASTNAME not null, FIRSTNAME not null,PICTURE, EMAIL not null,BESTFRIEND references USER,unique(EMAIL) );
INSERT INTO USER (LOGIN, PASSWORD, LASTNAME, FIRSTNAME, PICTURE, EMAIL, BESTFRIEND) VALUES ('Sacha LOL', 'Catchemall', 'Ketchum', 'Sacha', -1, 'Sacha@pokemon.lol', NULL);
INSERT INTO USER (LOGIN, PASSWORD, LASTNAME, FIRSTNAME, PICTURE, EMAIL, BESTFRIEND) VALUES ('Pika Pika', 'Sacha', 'Chu', 'Pika', -1, 'Pika@pokemon.lol', 'Sacha LOL');
INSERT INTO USER (LOGIN, PASSWORD, LASTNAME, FIRSTNAME, PICTURE, EMAIL, BESTFRIEND) VALUES ('Jolie Fille', 'Coeur', 'unknown', 'Ondine', -1, 'Ondine@pokemon.lol', NULL);
INSERT INTO USER (LOGIN, PASSWORD, LASTNAME, FIRSTNAME, PICTURE, EMAIL, BESTFRIEND) VALUES ('Le Doc', 'Pokesciences', 'Le Professeur', 'Chen', -1, 'Chen@pokemon.lol', NULL);

-- Table: VIEW_AGREEMENT
DROP TABLE IF EXISTS VIEW_AGREEMENT;
CREATE TABLE VIEW_AGREEMENT (LOGIN NOT NULL REFERENCES USER, IDAGREEMENT NOT NULL REFERENCES AGREEMENT, UNIQUE (LOGIN, IDAGREEMENT));
INSERT INTO VIEW_AGREEMENT (LOGIN, IDAGREEMENT) VALUES ('Sacha LOL', 1);
INSERT INTO VIEW_AGREEMENT (LOGIN, IDAGREEMENT) VALUES ('Pika Pika', 1);
INSERT INTO VIEW_AGREEMENT (LOGIN, IDAGREEMENT) VALUES ('Le Doc', 1);

-- Table: VIEW_BIPOLL
DROP TABLE IF EXISTS VIEW_BIPOLL;
CREATE TABLE VIEW_BIPOLL (LOGIN NOT NULL REFERENCES USER, IDBIPOLL NOT NULL REFERENCES BIPOLL, UNIQUE (LOGIN, IDBIPOLL));
INSERT INTO VIEW_BIPOLL (LOGIN, IDBIPOLL) VALUES ('Sacha LOL', 1);
INSERT INTO VIEW_BIPOLL (LOGIN, IDBIPOLL) VALUES ('Jolie Fille', 1);
INSERT INTO VIEW_BIPOLL (LOGIN, IDBIPOLL) VALUES ('Pika Pika', 1);

-- Table: VIEW_QUIZ
DROP TABLE IF EXISTS VIEW_QUIZ;
CREATE TABLE VIEW_QUIZ (LOGIN NOT NULL REFERENCES USER, IDQUIZ NOT NULL REFERENCES QUIZ, UNIQUE (LOGIN, IDQUIZ));
INSERT INTO VIEW_QUIZ (LOGIN, IDQUIZ) VALUES ('Le Doc', 1);
INSERT INTO VIEW_QUIZ (LOGIN, IDQUIZ) VALUES ('Jolie Fille', 1);
INSERT INTO VIEW_QUIZ (LOGIN, IDQUIZ) VALUES ('Pika Pika', 1);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
