CREATE TABLE tb_pessoa(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    cpf VARCHAR(15) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    funcao VARCHAR(40) NOT NULL,
    telefone VARCHAR(40) NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    update_date TIMESTAMP WITH TIME ZONE NOT NULL
);

