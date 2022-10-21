CREATE  TABLE users (
                        id int(10) auto_increment primary key,
                        username VARCHAR(45) NOT NULL ,
                        password VARCHAR(45) NOT NULL ,
                        enabled TINYINT NOT NULL DEFAULT 1
);

CREATE TABLE questions (
                           id int(10) auto_increment primary key,
                           question varchar(800) NOT NULL,
                           right_option int(10) NOT NULL references options(id)
);

CREATE TABLE options (
                         id int(10) auto_increment primary key,
                         question_id int(10) NOT NULL references questions(id),
                         `option` varchar(150) NOT NULL
);