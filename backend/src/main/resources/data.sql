INSERT INTO sala (id_sala, descricao, andar, capacidade, status)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'Sala de Aula 1', '1 Andar', 20, 0),
       ('002b8715-1a44-4990-86ab-f19fa45868b9', 'Sala de Aula 2', '1 Andar', 25, 0),
       ('d5e7417e-5131-4b65-bdad-3e3f553e0f9a', 'Sala de Aula 2', '2 Andar', 10, 2);

INSERT INTO agendamento (id_agendamento, id_sala, data, descricao, turno, horario)
VALUES ('4af53896-7e7b-499d-bea7-9b67fd9065cf', '550e8400-e29b-41d4-a716-446655440000', '2025-06-08', 'Início da cadeira', 'MANHA', 1);
