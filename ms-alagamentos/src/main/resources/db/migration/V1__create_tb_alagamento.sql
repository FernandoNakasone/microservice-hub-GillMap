CREATE TABLE IF NOT EXISTS tb_alagamento (
    id BIGINT NOT NULL AUTO_INCREMENT,
    regiao_id BIGINT NOT NULL,
    endereco VARCHAR(150) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    altura_agua_cm DOUBLE NOT NULL,
    data_hora DATETIME(6) NOT NULL,
    CONSTRAINT pk_tb_alagamento PRIMARY KEY (id)
    );