DROP TABLE IF EXISTS app_user, token, persona, visita_specialistica, indirizzo, medico, paziente, visita_medica, admin;
DROP TYPE IF EXISTS sesso, tipo_di_contatto, pacchetto, pagamento, stato, role, token_type;


--TYPE ENUM
CREATE TYPE sesso					AS ENUM ('UOMO', 'DONNA', 'NON_BINARIO');
CREATE TYPE tipo_di_contatto		AS ENUM ('INTERVISTA');
CREATE TYPE pacchetto               AS ENUM ('CORPO_MENTE', 'CORPO', 'MENTE');
CREATE TYPE pagamento               AS ENUM ('BONIFICO', 'SATISPAY', 'CARTA_DI_CREDITO_O_DEBITO', 'PAYPAL', 'CONTANTI');
CREATE TYPE stato                   AS ENUM ('DA_PROGRAMMARE', 'PROGRAMMATO', 'FATTO', 'ANNULLATO');
CREATE TYPE role                    AS ENUM ('USER', 'PROFESSIONAL', 'ADMIN');
CREATE TYPE token_type              AS ENUM ('BEARER');


--TABLE
--Tabella user_p
CREATE TABLE app_user (
    id_app_user     INT             NOT NULL,
    email           VARCHAR(50)     NOT NULL,
    password        VARCHAR(500)    NOT NULL,
    role            role            NOT NULL,

    CONSTRAINT PK_app_user PRIMARY KEY(id_app_user)

);
CREATE SEQUENCE app_user_sequence
     start 1
     increment 1
     OWNED BY app_user.id_app_user;


--Table token
CREATE TABLE token(

    id_token        INT             NOT NULL,
    token_code      VARCHAR(500)    NOT NULL,
    token_type      token_type      NOT NULL,
    revoked         BOOLEAN         NOT NULL,
    expired         BOOLEAN         NOT NULL,
    id_app_user     INT             NOT NULL,

    CONSTRAINT PK_token PRIMARY KEY(id_token),

    CONSTRAINT FK_token_app_user FOREIGN KEY(id_app_user )
            REFERENCES app_user(id_app_user) ON DELETE CASCADE

);
CREATE SEQUENCE token_sequence
     start 1
     increment 1
     OWNED BY token.id_token;



--Tabella persona
CREATE TABLE persona (

      id_persona        BIGINT          NOT NULL,
      nome              VARCHAR(50)     NOT NULL,
      cognome           VARCHAR(50)     NOT NULL,
      data_di_nascita   DATE            NOT NULL,
      telefono          VARCHAR(20),
      sesso             sesso           NOT NULL,
      id_app_user       INT,

      CONSTRAINT PK_persona PRIMARY KEY(id_persona),

      CONSTRAINT FK_persona_app_user FOREIGN KEY(id_app_user )
            REFERENCES app_user(id_app_user) ON DELETE CASCADE
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
CREATE TABLE medico(
	id_medico                   BIGINT          NOT NULL,
    id_indirizzo                BIGINT,
    codice_dottore              VARCHAR(500)    NOT NULL,
    id_visita_specialistica     BIGINT          NOT NULL,

	CONSTRAINT PK_medico PRIMARY KEY(id_medico),

    CONSTRAINT FK_medico_persona FOREIGN KEY(id_medico)
		REFERENCES persona(id_persona) ON DELETE CASCADE,
    CONSTRAINT FK_medico_indirizzo FOREIGN KEY(id_indirizzo)
		REFERENCES indirizzo(id_indirizzo) ON DELETE CASCADE,
    CONSTRAINT FK_medico_visita_specialistica FOREIGN KEY(id_visita_specialistica)
        REFERENCES visita_specialistica(id_visita_specialistica) ON DELETE CASCADE

);

CREATE SEQUENCE medico_sequence
  start 1
  increment 1
  OWNED BY medico.id_medico;


--Tabella paziente
CREATE TABLE paziente(

    id_paziente             BIGINT              NOT NULL,
    codice_fiscale          CHAR(16)            NOT NULL,

    CONSTRAINT PK_paziente PRIMARY KEY(id_paziente),

    CONSTRAINT FK_paziente_persona FOREIGN KEY(id_paziente)
		REFERENCES persona(id_persona) ON DELETE CASCADE

);

CREATE SEQUENCE paziente_sequence
    start 1
    increment 1
    OWNED BY paziente.id_paziente;


--Tabella visita_medica
CREATE TABLE visita_medica(

    id_visita_medica        BIGINT              NOT NULL,
    fatturazione            BOOLEAN             NOT NULL,
    id_medico               BIGINT              NOT NULL,
    id_paziente             BIGINT              NOT NULL,
    data_prenotazione       DATE                NOT NULL,
    tipo_di_contatto        tipo_di_contatto    NOT NULL,
    id_visita_specialistica BIGINT              NOT NULL,
    pagamento               pagamento           NOT NULL,
    note_pagamento          VARCHAR(500),
    pacchetto               pacchetto           NOT NULL,
    note                    VARCHAR(500),
    stato                   stato               NOT NULL,

    CONSTRAINT PK_visita_medica PRIMARY KEY(id_visita_medica),

    CONSTRAINT FK_visita_medica_medico  FOREIGN KEY(id_medico)
        REFERENCES medico(id_medico) ON DELETE CASCADE,
    CONSTRAINT FK_visita_medica_paziente FOREIGN KEY(id_paziente)
        REFERENCES paziente(id_paziente) ON DELETE CASCADE,
    CONSTRAINT FK_visita_medica_visita_specialistica FOREIGN KEY(id_visita_specialistica)
        REFERENCES visita_specialistica(id_visita_specialistica) ON DELETE CASCADE
);

CREATE SEQUENCE visita_medica_sequence
    start 1
    increment 1
    OWNED BY visita_medica.id_visita_medica;


--Tabella admin
CREATE TABLE admin(

    id_admin                BIGINT              NOT NULL,
    codice_admin            VARCHAR(500)        NOT NULL,

    CONSTRAINT PK_admin PRIMARY KEY(id_admin),

    CONSTRAINT FK_admin_persona FOREIGN KEY(id_admin)
        REFERENCES persona(id_persona) ON DELETE CASCADE
);

CREATE SEQUENCE admin_sequence
    start 1
    increment 1
    OWNED BY admin.id_admin;
