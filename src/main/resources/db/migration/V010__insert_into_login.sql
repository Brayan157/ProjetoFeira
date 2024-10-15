-- Primeiro, insira os dados na tabela tb_pessoa
INSERT INTO tb_pessoa (nome, cpf, data_nascimento, funcao, telefone, creation_date, update_date)
VALUES (
    'Gustavo',
    '11111111111',
    '2001-06-04',
    'ADMINISTRADOR',
    '062984968910',
    NOW(),
    NOW()
);
--senha: 123456789
-- Em seguida, use o id gerado para inserir na tabela tb_login
INSERT INTO tb_login (email, senha, status, funcionario_id, creation_date, update_date)
VALUES (
    'feira1@email.com.br',
    '$2a$10$5GgIeK2h6DdGsH/Z6o7i2./8d9MP8vX7mNcbpeOuI7taZKG6IegLC',
    'ATIVO',
    1,
    NOW(),
    NOW()
);
