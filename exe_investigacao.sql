 DROP DATABASE IF EXISTS bd;
 CREATE DATABASE bd;
 USE bd;
 
 SET SQL_SAFE_UPDATES = 0;
 
 CREATE TABLE investigacao(
	id INT PRIMARY KEY,
    nome VARCHAR(100),
    idade INT,
	profissao VARCHAR(100),
    cidade VARCHAR(100),
    estava_na_sala_do_cofre BOOLEAN,
	horario_visto TIME,
    possui_alibi BOOLEAN,
    quantidade_evidencias INT,
    nivel_suspeita INT
 );
 
 INSERT INTO  investigacao  VALUES (1,'Arthur Carvalho', 45, 'Empresário', 'São Paulo', TRUE, '23:00:00',FALSE, 3, 4);
 INSERT INTO  investigacao VALUES (2, 'Beatriz Moura', 34, 'Curadora de Arte', 'Campinas', FALSE, '22:45:00', TRUE, 1, 2);
 INSERT INTO  investigacao VALUES (3, 'Carlos Nogueira', 52, 'Colecionador', 'São Paulo', TRUE, '23:00:00',FALSE,4 , 5);
 INSERT INTO  investigacao VALUES (4, 'Daniela Rocha', 29, 'Jornalista', 'Santos ', FALSE, '23:10:00',FALSE, 2, 4);
 INSERT INTO  investigacao VALUES (5,'Eduardo Lima', 41, 'Advogado', 'Campinas', TRUE, '22:55:00',FALSE,2, 4);
 INSERT INTO  investigacao VALUES (6,'Fernanda Alves', 38, 'Arquiteta', 'Santos', FALSE, '22:40:00',TRUE,0, 1);
 INSERT INTO  investigacao VALUES (7, 'Gustavo Lima ', 47, 'Artista', 'São Paulo', TRUE, '23:00:00',FALSE, 3, 4);
 INSERT INTO  investigacao VALUES (8, 'Helena Duarte', 31 , 'Restauradora de Arte', 'Campinas', FALSE, '22:50:00',FALSE,1, 2);
 
 UPDATE investigacao SET nivel_suspeita = 5
 WHERE id = 2;
 
 UPDATE investigacao SET cidade = "Santa Rita do Sapucai"
 WHERE nome = "Helena Duarte";
 
 UPDATE investigacao
 SET nivel_suspeita = nivel_suspeita + 1
 WHERE  estava_na_sala_do_cofre = TRUE;
 
 #DELETE FROM investigacao WHERE possui_alibi = TRUE AND quantidade_evidencias = 0 AND nivel_suspeita < 4;
 
 #DELETE FROM investigacao WHERE nivel_suspeita = 1;
 
 SELECT * FROM investigacao;
 
 SELECT nome, profissao FROM investigacao;
 
 SELECT * FROM investigacao ORDER BY idade ASC; #ordena de forma crescente
 
 SELECT * FROM investigacao WHERE estava_na_sala_do_cofre = TRUE;
 
 SELECT * FROM investigacao WHERE nivel_suspeita > 3;
 
 SELECT * FROM investigacao WHERE idade BETWEEN 30 AND 50;
 
 SELECT * FROM investigacao WHERE nome LIKE 'A%';
 
 SELECT * FROM investigacao WHERE nome LIKE '%o';
 
 SELECT * FROM investigacao WHERE nome LIKE '%Art%';
 
 SELECT * FROM investigacao WHERE cidade IN ('São Paulo', 'Campinas', 'Santos');
 
 SELECT COUNT(*) FROM investigacao;
 
 SELECT MAX(nivel_suspeita) FROM investigacao;
 SELECT MIN(nivel_suspeita) FROM investigacao;
 
 SELECT SUM(quantidade_evidencias) FROM investigacao;
 
SELECT profissao, COUNT(*) FROM investigacao GROUP BY profissao;

SELECT cidade, AVG(nivel_suspeita) FROM investigacao GROUP BY cidade;