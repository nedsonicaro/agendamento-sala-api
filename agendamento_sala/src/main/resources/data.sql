INSERT INTO sala (id_sala, descricao, andar, capacidade, status)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'Sala de Aula 1', '1º Andar', 20, 1);

INSERT INTO agendamento (id_agendamento, id_sala, data, descricao, turno, horario)
VALUES ('4af53896-7e7b-499d-bea7-9b67fd9065cf', '550e8400-e29b-41d4-a716-446655440000', '2025-06-08', 'Início da cadeira', 'MANHA', 1);
