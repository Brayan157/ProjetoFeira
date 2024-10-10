CREATE TABLE tb_itens(
    compra_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade FLOAT NOT NULL,
    PRIMARY KEY(compra_id, produto_id),
    CONSTRAINT fk_compra_itens FOREIGN KEY(compra_id) REFERENCES tb_compra(id),
    CONSTRAINT fk_produto_itens FOREIGN KEY(produto_id) REFERENCES tb_produto(id)
);