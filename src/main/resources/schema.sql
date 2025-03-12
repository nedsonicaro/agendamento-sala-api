CREATE TABLE IF NOT EXISTS sala
(
    id_sala    UUID        NOT NULL PRIMARY KEY,
    descricao  VARCHAR(50) NOT NULL,
    andar      VARCHAR(50) NOT NULL,
    capacidade INT         NOT NULL,
    status     INT         NOT NULL
);

CREATE TABLE IF NOT EXISTS agendamento
(
    id_agendamento UUID        NOT NULL PRIMARY KEY,
    id_sala        UUID        NOT NULL REFERENCES sala (id_sala),
    data           DATE        NOT NULL,
    descricao      VARCHAR(50) NOT NULL,
    turno          VARCHAR(50) NOT NULL,
    horario        INT         NOT NULL
);

