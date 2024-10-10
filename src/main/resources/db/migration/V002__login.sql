CREATE TABLE tb_login(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email VARCHAR(40) NOT NULL UNIQUE,
    senha VARCHAR(250) NOT NULL,
    status VARCHAR(10) NOT NULL,
    funcionario_id INT NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    update_date TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT fk_login_pessoa FOREIGN KEY(funcionario_id) REFERENCES tb_pessoa(id)
);
