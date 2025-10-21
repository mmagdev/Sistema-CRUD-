-- Script SQL para criação da tabela de funcionários

CREATE TABLE funcionario(
	id UUID PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	matricula VARCHAR(15) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	salario DECIMAL(10,2) NOT NULL
);