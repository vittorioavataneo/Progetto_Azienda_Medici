DROP TABLE IF EXISTS persona, visita_specialistica, indirizzo, medico, paziente;
DROP TYPE IF EXISTS sesso, tipo_di_contatto, pacchetto, pagamento, stato;


--TYPE ENUM
CREATE TYPE sesso					AS ENUM ('UOMO', 'DONNA', 'NON_BINARIO');
CREATE TYPE tipo_di_contatto		AS ENUM ('INTERVISTA');
CREATE TYPE pacchetto               AS ENUM ('CORPO_MENTE', 'CORPO', 'MENTE');
CREATE TYPE pagamento               AS ENUM ('BONIFICO', 'SATISPAY', 'CARTA_DI_CREDITO_O_DEBITO');
CREATE TYPE stato                   AS ENUM('DA_PROGRAMMARE', 'PROGRAMMATO', 'FATTO', 'ANNULATO');


--TABLE
--Tabella persona
CREATE TABLE persona (
      id_persona        BIGINT          NOT NULL,
      nome              VARCHAR(50)     NOT NULL,
      cognome           VARCHAR(50)     NOT NULL,
      data_di_nascita   DATE            NOT NULL,
      telefono          VARCHAR(20),
      email             VARCHAR(100)    NOT NULL,
      sesso             sesso           NOT NULL,

      CONSTRAINT PK_persona PRIMARY KEY(id_persona)
);
CREATE SEQUENCE persona_sequence
  start 1
  increment 1
  OWNED BY persona.id_persona;


--Tabella visita_specialistica
CREATE TABLE visita_specialistica(
    id_visita_specialistica     BIGINT      NOT NULL,
    specialistica               VARCHAR(30) NOT NULL,

    CONSTRAINT PK_visita_specialistica PRIMARY KEY(id_visita_specialistica)

);
CREATE SEQUENCE visita_specialistica_sequence
  start 1
  increment 1
  OWNED BY visita_specialistica.id_visita_specialistica;


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

--Tabella medico
CREATE TABLE medico
(
	id_medico                   BIGINT          NOT NULL,
    id_indirizzo                BIGINT          NOT NULL,
    username                    VARCHAR(30)     NOT NULL,
    password                    VARCHAR(500)    NOT NULL,
    id_visita_specialistica     BIGINT          NOT NULL,
    fatturazione                BOOLEAN         NOT NULL,

	CONSTRAINT PK_medico PRIMARY KEY(id_medico),

    CONSTRAINT FK_medico_persona FOREIGN KEY(id_medico)
		REFERENCES persona(id_persona),
    CONSTRAINT FK_medico_indirizzo FOREIGN KEY(id_indirizzo)
		REFERENCES indirizzo(id_indirizzo),
    CONSTRAINT FK_medico_visita_specialistica FOREIGN KEY(id_visita_specialistica)
        REFERENCES visita_specialistica(id_visita_specialistica)

);

CREATE SEQUENCE medico_sequence
  start 1
  increment 1
  OWNED BY medico.id_medico;


--Tabella paziente
CREATE TABLE paziente(
    id_paziente             BIGINT              NOT NULL,
    fatturazione            BOOLEAN             NOT NULL,
    id_medico               BIGINT              NOT NULL,
    data_prenotazione       DATE                NOT NULL,
    tipo_di_contatto        tipo_di_contatto    NOT NULL,
    id_visita_specialistica BIGINT              NOT NULL,
    pagamento               pagamento           NOT NULL,
    note_pagamento          VARCHAR(500),
    pacchetto               pacchetto           NOT NULL,
    note                    VARCHAR(500),
    stato                   stato               NOT NULL,

    CONSTRAINT PK_paziente PRIMARY KEY(id_paziente),

    CONSTRAINT FK_paziente_persona FOREIGN KEY(id_paziente)
		REFERENCES persona(id_persona),
    CONSTRAINT FK_paziente_medico  FOREIGN KEY(id_medico)
		REFERENCES medico(id_medico),
    CONSTRAINT FK_paziente_visita_specialistica FOREIGN KEY(id_visita_specialistica)
                REFERENCES visita_specialistica(id_visita_specialistica)

);

CREATE SEQUENCE id_paziente_sequence
    start 1
    increment 1
    OWNED BY paziente.id_paziente;