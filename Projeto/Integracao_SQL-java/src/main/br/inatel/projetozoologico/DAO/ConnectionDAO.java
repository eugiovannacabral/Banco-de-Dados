package main.br.inatel.projetozoologico.DAO;

import java.sql.*;

public abstract class ConnectionDAO {
    static Connection connection;// Conexão com o banco

    // Parâmetros utilizados nas subclasses:
    PreparedStatement pst;         // Comando SQL com parâmetros
    Statement st;                  // Comando SQL simples (sem parâmetros)
    ResultSet rs;                  // Resultado das consultas SQL

    // Informações de acesso ao banco de dados:
    static String database = "zoologico"; // Nome do BD
    static String user = "root";
    static String password = "root";
    static String url = "jdbc:mysql://localhost:3306/" + database;

    // Estabelecer a conexão com o banco:
    public static Connection connectToDb(){
        try {
            System.out.println("Deu certo !");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
        return null;
    
    }
    
}