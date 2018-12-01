# DROP TABLE IF EXISTS LOGIN;
DROP TABLE IF EXISTS COORDENACAO;
DROP TABLE IF EXISTS HISTORICO;
DROP TABLE IF EXISTS REQUISITO;
DROP TABLE IF EXISTS TURMA;
DROP TABLE IF EXISTS ALUNO;
DROP TABLE IF EXISTS DISCIPLINA;

CREATE TABLE DISCIPLINA(
  CODCRED varchar(10) NOT NULL PRIMARY KEY,
  DISCIPLINA varchar(100) NOT NULL,
  SEMESTRE int(2) NOT NULL
);

CREATE TABLE ALUNO(
  MATRICULA varchar(10) NOT NULL PRIMARY KEY,
  EMAIL varchar(100) NOT NULL
);

CREATE TABLE TURMA(
  CODCRED varchar(10) NOT NULL,
  TURMA int(4) NOT NULL,
  DISCIPLINA varchar(100) NOT NULL,
  QTD int(3) NOT NULL,
  HORARIO varchar(20) NOT NULL,
  PRIMARY KEY (CODCRED, TURMA),
  FOREIGN KEY (CODCRED) REFERENCES DISCIPLINA(CODCRED)
);

CREATE TABLE REQUISITO(
  CODCRED varchar(10) NOT NULL PRIMARY KEY,
  PREREQUISITO varchar(100) NOT NULL,
  FOREIGN KEY (CODCRED) REFERENCES DISCIPLINA(CODCRED)
);

CREATE TABLE HISTORICO(
  MATRICULA varchar(10) NOT NULL,
  CODCRED varchar(10) NOT NULL,
  STATUS varchar(3) NOT NULL,
  TURMA int(4) NOT NULL,
  PRIMARY KEY (MATRICULA, CODCRED),
  FOREIGN KEY (MATRICULA) REFERENCES ALUNO(MATRICULA),
  FOREIGN KEY (CODCRED) REFERENCES DISCIPLINA(CODCRED)
);

CREATE TABLE COORDENACAO(
  MATRICULA varchar(10) NOT NULL PRIMARY KEY,
  EMAIL varchar(100) NOT NULL
);

# CREATE TABLE LOGIN(
#   USUARIO varchar(10) NOT NULL PRIMARY KEY,
#   PERMISSAO varchar (10) NOT NULL,
#   SENHA varchar(100) NOT NULL,
# );