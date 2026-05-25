drop database if exists zoologico;
create database zoologico;
use zoologico;

-- Tabel Animal 
create table Animal (
	idAnimal int primary key auto_increment,
    nome varchar(45) not null,
    sexo varchar(10) not null, 
    idade int,
    especie varchar(45), 
    peso double (10, 2), 
    id_habitat int, 
    foreign key (id_habitat) references Habitat(idHabitat)
);

-- Tabela Habitat
create table Habitat (
	idHabitat int primary key auto_increment, 
    nome varchar(45) not null,
    clima varchar(45), 
    lotação int, 
    tipo varchar(45)
);

-- Tabela Ingresso
create table Ingresso ( 
	idIngresso int primary key auto_increment,
    data date,
    tipo varchar(45), 
    quantidade int, 
    preco double (10, 2),
    id_visitante int, 
    foreign key (id_visitante) references Visitante(idVisitante)
);

-- Tabela Visitante 
create table Visitante ( 
	idVisitante int primary key auto_increment,
    nome varchar(45) not null, 
    idade int not null, 
    numeroVisitante int
);

-- Tabela Funcionário
create table Funcionario (
	idFuncionario int primary key auto_increment,
    nome varchar(45) not null, 
    funcao varchar(100) not null, 
    salario double (10, 2)
);

-- Role para funcionários do Zoológico 
create role Privilegios;
grant all on * to Privilegios;
create user 'DiretorGeral'@'%' identified by 'senha';
