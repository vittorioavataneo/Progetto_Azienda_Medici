DROP TABLE IF EXISTS persona, indirizzo, fatturazione, medico, paziente;
DROP TYPE IF EXISTS sesso, visita, contatto, pacchetto, pagamento;

--TYPE ENUM
CREATE TYPE sesso					AS ENUM ('UOMO', 'DONNA', 'NON_BINARIO');
CREATE TYPE visita                  AS ENUM ('PSICOLOGO');
CREATE TYPE contatto				AS ENUM ('INTERVISTA');
CREATE TYPE pacchetto               AS ENUM ('MULTI_VISITA');
CREATE TYPE pagamento               AS ENUM ('CONTANTI', 'CARTA_DI_CREDITO');

--TABLE
--Tabella persona
CREATE TABLE persona (
  id_persona        BIGINT          NOT NULL,
  nome              VARCHAR(50)     NOT NULL,
  cognome           VARCHAR(50)     NOT NULL,
  telefono          VARCHAR(20),
  email             VARCHAR(100)    NOT NULL,
  sesso             sesso           NOT NULL,

  CONSTRAINT PK_persona PRIMARY KEY(id_persona)
);
CREATE SEQUENCE persona_sequence
  start 1
  increment 1
  OWNED BY persona.id_persona;


--Tabella indirizzo
CREATE TABLE indirizzo(
    id_indirizzo    BIGINT          NOT NULL,
    via             VARCHAR(100)    NOT NULL,
    cap             CHAR(5)         NOT NULL,
    città           VARCHAR(30)     NOT NULL,
    provincia       CHAR(2)         NOT NULL,
    nazione         VARCHAR(30)     NOT NULL,

    CONSTRAINT PK_indirizzo PRIMARY KEY(id_indirizzo)
);

CREATE SEQUENCE indirizzo_sequence
    start 1
    increment 1
    OWNED BY indirizzo.id_indirizzo;


--Tabella fatturazione
CREATE TABLE fatturazione(
    id_fatturazione     BIGINT      NOT NULL,
    id_indirizzo        BIGINT      NOT NULL,
    codice_fiscale      CHAR(16)    NOT NULL,
    partita_iva         CHAR(11),

    CONSTRAINT PK_fatturazione PRIMARY KEY(id_fatturazione),
    CONSTRAINT PK_fatturazione_indirizzo FOREIGN KEY(id_indirizzo)
        REFERENCES indirizzo(id_indirizzo)
);

CREATE SEQUENCE fatturazione_sequence
    start 1
    increment 1
    OWNED BY fatturazione.id_fatturazione;


--Tabella medico
CREATE TABLE medico
(
	id_medico           BIGINT          NOT NULL,
    id_indirizzo        BIGINT          NOT NULL,
    username            VARCHAR(30)     NOT NULL,
    password            VARCHAR(30)     NOT NULL,
    visita              visita          NOT NULL,
    id_fatturazione     BIGINT,

	CONSTRAINT PK_medico PRIMARY KEY(id_medico),

    CONSTRAINT FK_medico_persona FOREIGN KEY(id_medico)
		REFERENCES persona(id_persona),
    CONSTRAINT FK_medico_indirizzo FOREIGN KEY(id_indirizzo)
		REFERENCES indirizzo(id_indirizzo),
    CONSTRAINT FK_medico_fatturazione FOREIGN KEY(id_fatturazione)
		REFERENCES fatturazione(id_fatturazione)

);

CREATE SEQUENCE medico_sequence
  start 1
  increment 1
  OWNED BY medico.id_medico;


--Tabella paziente
CREATE TABLE paziente(
    id_paziente             BIGINT      NOT NULL,
    id_fatturazione         BIGINT,
    id_medico               BIGINT      NOT NULL,
    data_prenotazione       DATE        NOT NULL,
    contatto                contatto    NOT NULL,
    visita                  visita      NOT NULL,
    pagamento               pagamento   NOT NULL,
    note_pagamento          VARCHAR(500),
    pacchetto               pacchetto   NOT NULL,
    note                    VARCHAR(500),

    CONSTRAINT PK_paziente PRIMARY KEY(id_paziente),

    CONSTRAINT FK_paziente_persona FOREIGN KEY(id_paziente)
		REFERENCES persona(id_persona),
    CONSTRAINT FK_paziente_medico  FOREIGN KEY(id_medico)
		REFERENCES medico(id_medico),
    CONSTRAINT FK_paziente_fatturazione FOREIGN KEY(id_fatturazione)
		REFERENCES fatturazione(id_fatturazione)

);

CREATE SEQUENCE id_paziente_sequence
    start 1
    increment 1
    OWNED BY paziente.id_paziente;