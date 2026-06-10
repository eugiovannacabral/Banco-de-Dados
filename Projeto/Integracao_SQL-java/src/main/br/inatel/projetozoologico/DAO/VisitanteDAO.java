package main.br.inatel.projetozoologico.DAO;

import main.br.inatel.projetozoologico.Model.Visitante;
import main.br.inatel.projetozoologico.Model.Ingresso;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class VisitanteDAO extends ConnectionDAO {


    // ===== Inserindo visitantes =====

    public boolean insertVisitante(Visitante visitante) {

        connectToDb(); // Abre conexão
        String sql = "INSERT INTO Visitante(nome, idade) VALUES (?, ?)";

        try {
            pst = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, visitante.getNome()); // Seta parâmetro 1
            pst.setInt(2, visitante.getIdade());

            pst.executeUpdate();
            ResultSet rsGerado = pst.getGeneratedKeys();

            if (rsGerado.next()) {
                visitante.setIdVisitante(rsGerado.getInt(1));
            }

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao inserir visitante: " + e.getMessage());
            return false;

        } finally {

            try {
                if (pst != null)
                    pst.close(); //fecha statement primeiro

                if (connection != null)
                    connection.close();

            } catch (SQLException e) {

                System.out.println("Erro ao fechar recursos: " + e.getMessage());

            }
        }
    }

    // ===== Buscar Visitante Geral =====

    public List<Visitante> selectVisitante(){

        List<Visitante> visitantes = new ArrayList<>();
        connectToDb();
        String sql = "SELECT * FROM Visitante";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()){
                Visitante visitante = new Visitante(
                        rs.getInt("id_Visitante"),
                        rs.getString("nome"),
                        rs.getInt("idade")
                );

                visitantes.add(visitante);
            }

        } catch (SQLException e){

            System.out.println("Erro ao buscar Visitantes: " + e.getMessage());

        } finally {

            try {
                if (rs != null)
                    rs.close();
                if(st != null)
                    st.close();
                if(connection != null)
                    connection.close();

            } catch (SQLException e){

                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return visitantes;
    }

    // ===== Buscar Visitante pelo Nome =====

    public Visitante selectVisitantePorNome(String nome){

        Visitante visitante = null;
        connectToDb();
        String sql =  "SELECT * FROM Visitante WHERE nome=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();

            if(rs.next()) {

                visitante = new Visitante(
                        rs.getInt("id_Visitante"),
                        rs.getString("nome"),
                        rs.getInt("idade")
                );
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar Visitante: " + e.getMessage());

        } finally {

            try {

                if(rs != null)
                    rs.close();

                if(pst != null)
                    pst.close();

                if(connection != null)
                    connection.close();

            } catch (SQLException e) {

                System.out.println("Erro ao fechar recursos: " + e.getMessage());

            }
        }
        return visitante;
    }

    // ===== JOIN com Ingresso =====

    public Visitante selectVisitanteComIngresso(int idVisitante){

        Visitante visitante = null;

        connectToDb();

        String sql = """
        SELECT
            v.id_Visitante,
            v.nome,
            v.idade,
            i.id_Ingresso,
            i.data,
            i.tipo,
            i.preco

        FROM Visitante v
        INNER JOIN Ingresso i
            ON v.id_Visitante = i.id_visitante

        WHERE v.id_Visitante = ?
        """;

        try{

            pst = connection.prepareStatement(sql);
            pst.setInt(1, idVisitante);

            rs = pst.executeQuery();

            if(rs.next()){

                visitante = new Visitante(
                        rs.getInt("id_Visitante"),
                        rs.getString("nome"),
                        rs.getInt("idade")
                );

                Ingresso ingresso = new Ingresso(
                        rs.getInt("id_Ingresso"),
                        rs.getString("data"),
                        rs.getString("tipo"),
                        rs.getDouble("preco")
                );

                visitante.setIngresso(ingresso);
            }

        } catch(SQLException e){

            System.out.println("Erro ao buscar visitante com ingresso: " + e.getMessage());

        } finally {
            try{
                if(rs != null) rs.close();
                if(pst != null) pst.close();
                if(connection != null) connection.close();

            } catch(SQLException e){
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return visitante;
    }

    // ===== Atualização dos Dados =====

    public boolean updateVisitante(Visitante visitante){
        connectToDb();
        String sql = "UPDATE Visitante SET nome=?, idade=? WHERE id_Visitante=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, visitante.getNome());
            pst.setInt(2, visitante.getIdade());
            pst.setInt(3, visitante.getIdVisitante());
            pst.executeUpdate();

            return true;

        } catch (SQLException e){

            System.out.println("Erro ao atualizar Visitante: " + e.getMessage());
            return false;

        } finally {

            try {
                if (pst != null)
                    pst.close();

                if (connection != null)
                    connection.close();

            } catch (SQLException e){

                System.out.println("Erro ao fechar recursos: " + e.getMessage());

            }
        }
    }

    // ===== Deletar Dados =====

    public boolean deleteVisitante(String nome){

        connectToDb();
        String sql = "DELETE FROM Visitante WHERE nome=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, nome);
            pst.execute();
            return true;

        } catch (SQLException e){

            System.out.println("Erro ao deletar Visitante " + e.getMessage());
            return false;

        } finally {

            try {

                if(pst != null)
                    pst.close();
                if(connection != null)
                    connection.close();

            } catch (SQLException e){

                System.out.println("Erro ao fechar recursos: " + e.getMessage());

            }
        }
    }

}