INSERT INTO tb_pessoa (nome, cpf, data_nascimento, funcao, telefone, creation_date, update_date)
VALUES (
    'ADMIN',
    '99999999999',
    '2001-06-04',
    'ADMINISTRADOR',
    '62984968910',
    '2024-10-10T14:47:18.036882',
    '2024-10-10T14:47:18.036882'
);

-- Em seguida, insira na tabela tb_login.
-- Substitua NEW_ID pela consulta para buscar o último ID inserido na tabela tb_pessoa
-- senha: 12345678
INSERT INTO tb_login (email, senha, status, funcionario_id, creation_date, update_date)
VALUES (
    'feiraProjeto2024@email.com',
    '$2a$10$FOnU8jpDSjeZgio2TqVXK.bIwBuXABrByf8a3hj9VPawJQKzItJvW',
    'ATIVO',
    (SELECT id FROM tb_pessoa WHERE cpf = '99999999999'),  -- Capturando o ID da pessoa inserida
    '2024-10-10T14:47:18.133237',
    '2024-10-10T14:47:18.133237'
);