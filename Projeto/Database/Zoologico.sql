drop database if exists zoologico;
create database zoologico;
use zoologico;

-- Tabel Animal 
create table Animal (
	id_Animal int primary key auto_increment,
    nome varchar(45) not null,
    sexo varchar(10) not null, 
    idade int,
    especie varchar(45), 
    peso double (10, 2), 
    id_habitat int, 
    foreign key (id_habitat) references Habitat(id_Habitat)
);

-- Tabela Habitat
create table Habitat (
	id_Habitat int primary key auto_increment, 
    nome varchar(45) not null,
    clima varchar(45), 
    lotação int, 
    tipo varchar(45)
);

-- Tabela Ingresso
create table Ingresso ( 
	id_Ingresso int primary key auto_increment,
    data date,
    tipo varchar(45), 
    quantidade int, 
    preco double (10, 2),
    id_visitante int, 
    foreign key (id_visitante) references Visitante(id_Visitante)
);

-- Tabela Visitante 
create table Visitante ( 
	id_Visitante int primary key auto_increment,
    nome varchar(45) not null, 
    idade int not null, 
    numeroVisitante int
);

-- Tabela Funcionário
create table Funcionario (
	id_Funcionario int primary key auto_increment,
    nome varchar(45) not null, 
    funcao varchar(100) not null, 
    salario double (10, 2)
);

-- Tabela Consulta Veterinária
create table ConsultaVeterinaria (
    id_ConsultaVeterinaria int primary key auto_increment,
    data date,
    estado_de_saude varchar(45),
    id_animal int,
    id_funcionario int,
	FOREIGN KEY (id_animal) REFERENCES Animal(id_Animal),
    FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id_Funcionario)
);

-- Role para funcionários do Zoológico 
create role Privilegios;
grant all on * to Privilegios;
create user 'DiretorGeral'@'%' identified by 'senha';