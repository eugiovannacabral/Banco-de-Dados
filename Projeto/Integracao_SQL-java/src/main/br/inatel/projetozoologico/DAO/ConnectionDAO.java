package main.br.inatel.projetozoologico.DAO;

import java.sql.*;

public class ConnectionDAO {
    Connection connection;// Conexão com o banco

    // Parâmetros utilizados nas subclasses:
    PreparedStatement pst;         // Comando SQL com parâmetros
    Statement st;                  // Comando SQL simples (sem parâmetros)
    ResultSet rs;                  // Resultado das consultas SQL

    // Informações de acesso ao banco de dados:
    String database = "Projeto_persona"; // Nome do BD
    String user = "root";
    String password = "root";
    String url = "jdbc:mysql://localhost:3306/" + database;

    // Estabelecer a conexão com o banco:
    public Connection connectToDb(){
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
        return null;
    
    }
    
}