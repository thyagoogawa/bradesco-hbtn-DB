BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS ALUNO (
       id  integer,
        email varchar(255),
        matricula varchar(255),
        nascimento date,
        nomeCompleto varchar(255),
        primary key (id)
    );
CREATE TABLE IF NOT EXISTS ALUNO_HAS_CURSO (
       curso_id bigint not null,
        aluno_id bigint not null
    );
CREATE TABLE IF NOT EXISTS CURSO (
       id  integer,
        nome varchar(255),
        sigla varchar(255),
        professor_id bigint,
        primary key (id)
    );
CREATE TABLE IF NOT EXISTS ENDERECO (
       id  integer,
        bairro varchar(255),
        cep varchar(255),
        cidade varchar(255),
        endereco varchar(255),
        estado varchar(255),
        logradouro varchar(255),
        numero varchar(255),
        aluno_id bigint not null,
        primary key (id)
    );
CREATE TABLE IF NOT EXISTS MATERIAL_CURSO (
       id  integer,
        url varchar(255), curso_id bigint not null,
        primary key (id)
    );
CREATE TABLE IF NOT EXISTS PROFESSOR (
       id  integer,
        email varchar(255),
        matricula varchar(255),
        nomeCompleto varchar(255),
        primary key (id)
    );
CREATE TABLE IF NOT EXISTS TELEFONE (
       id  integer,
        DDD varchar(255),
        numero varchar(255),
        aluno_id bigint not null,
        primary key (id)
    );
INSERT INTO "ALUNO" ("id","email","matricula","nascimento","nomeCompleto") VALUES (1,'vladmir.silva@email.com','8698',275540400000,'Vladmir da Silva'),
 (2,'vladmir.silva@email.com','8698',275540400000,'Vladmir da Silva'),
 (3,'junior.pereira@email.com','864',275540400000,'Junior Pereira Guedes'),
 (4,'junior.pereira@email.com','864',275540400000,'Junior Pereira Guedes');
INSERT INTO "ALUNO_HAS_CURSO" ("curso_id","aluno_id") VALUES (1,1),
 (2,2),
 (3,3),
 (4,3),
 (5,4),
 (6,4);
INSERT INTO "CURSO" ("id","nome","sigla","professor_id") VALUES (1,'Java Spring Boot','JSB',1),
 (2,'Java Spring Boot','JSB',2),
 (3,'Java EE','JEE',3),
 (4,'Java Spring Boot','JSB',4),
 (5,'Java EE','JEE',5),
 (6,'Java Spring Boot','JSB',6);
INSERT INTO "ENDERECO" ("id","bairro","cep","cidade","endereco","estado","logradouro","numero","aluno_id") VALUES (1,'Jardim das Acacias','12345-678','São Paulo','345','SP','Rua das Flores','Apto 45',1),
 (2,'Jardim das Acacias','12345-678','São Paulo','345','SP','Rua das Flores','Apto 45',2);
INSERT INTO "MATERIAL_CURSO" ("id","url","curso_id") VALUES (1,'http://www.cursojava.com.br',1),
 (2,'http://www.cursojava.com.br',2),
 (3,'http://www.cursojee.com.br',5),
 (4,'http://www.cursojsb.com.br',6);
INSERT INTO "PROFESSOR" ("id","email","matricula","nomeCompleto") VALUES (1,'carlos.silva@email.com.br','246','Carlos da Silva'),
 (2,'carlos.silva@email.com.br','246','Carlos da Silva'),
 (3,'carlos.silva@email.com.br','246','Carlos da Silva'),
 (4,'wellington.carneiro@email.com.br','187','Wellington Carneiro'),
 (5,'carlos.silva@email.com.br','246','Carlos da Silva'),
 (6,'wellington.carneiro@email.com.br','187','Wellington Carneiro');
INSERT INTO "TELEFONE" ("id","DDD","numero","aluno_id") VALUES (1,'11','98765-4321',1),
 (2,'11','98765-4321',2);
COMMIT;
