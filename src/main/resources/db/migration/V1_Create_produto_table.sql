CREATE TABLE produto(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    preco DECIMAL(255) NOT NULL,
    quantidade BIGINT NOT NULL,
)

ALTER TABLE produto
add tamanho varchar(55);

ALTER TABLE pessoa
add dataVenda date;


