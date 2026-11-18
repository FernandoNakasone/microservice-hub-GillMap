CREATE TABLE IF NOT EXISTS tb_regiao (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    situacao VARCHAR(10) NOT NULL,
    CONSTRAINT pk_tb_regiao PRIMARY KEY (id)
    );