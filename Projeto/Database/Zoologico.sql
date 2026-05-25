drop database if exists zoologico;
create database zoologico;
use zoologico;

-- ================================== --
--     TABELAS DO BANCO DE DADOS      --
-- ================================== --

-- Tabel Animal 
create table Animal (
	id_Animal int primary key auto_increment,
    nome varchar(45),
    sexo varchar(10), 
    idade int,
    especie varchar(100), 
    peso double (10, 2), 
    id_habitat int, 
    foreign key (id_habitat) references Habitat(id_Habitat)
);

-- Tabela Habitat
create table Habitat (
	id_Habitat int primary key auto_increment, 
    nome varchar(45),
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
    nome varchar(45), 
    idade int, 
    numeroVisitante int
);

-- Tabela Funcionário
create table Funcionario (
	id_Funcionario int primary key auto_increment,
    nome varchar(45) , 
    funcao varchar(100), 
    salario double (10, 2)
);

-- Tabela intermediário entre Funcionário e Animal
create table Funcionario_has_Animal (
	id_alimento INT,
	id_animal INT,
    PRIMARY KEY (id_alimento, id_animal),
	FOREIGN KEY (id_alimento) REFERENCES Alimento(id_Alimento),
    FOREIGN KEY (id_animal) REFERENCES Animal(id_Animal)
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

-- Tabela Alimento
create table Alimento (
	id_Alimento int primary key auto_increment,
    tipo_de_dieta varchar(45), 
    estoque int , 
    nome varchar(45)
);

-- Tabela intermediário entre Alimento e Animal
create table Alimento_has_Animal (
	id_alimento INT,
	id_animal INT,
    PRIMARY KEY (id_alimento, id_animal),
	FOREIGN KEY (id_alimento) REFERENCES Alimento(id_Alimento),
    FOREIGN KEY (id_animal) REFERENCES Animal(id_Animal)
);

-- ================================== --
--          INSERÇÃO DE DADOS         --
-- ================================== --

-- Inserindo dados na tabela Animal

Insert into Animal(nome, sexo, idade, especie, peso, id_habitat) values
('Ornitorinco', 'macho', 5, 'Ornithorhynchus Anatinus', 2.4, 1),
('Tambaqui', 'macho', 40 ,'Colossoma Macropomum', 27.5 , 2),
('Esquilo-Cinzento-Oriental', 'fêmea', 3 ,'Sciurus Carolinensis', 0.600 , 3),
('Arara-Azul-Grande', 'fêmea', 35 ,'Anodorhynchus Hyacinthinus', 1.34, 4),
('Hipopótamo-comum', 'macho', 40 ,'Hippopotamus amphibius', 4500, 5);

-- Inserindo dados na tabela Habitat

Insert into Habitat(nome, sexo, idade, especie, peso, id_habitat) values
('Ornitorinco', 'macho', 5, 'Ornithorhynchus Anatinus', 2.4, 1),


-- Role para funcionários do Zoológico 
create role Privilegios;
grant all on * to Privilegios;
create user 'DiretorGeral'@'%' identified by 'senha';