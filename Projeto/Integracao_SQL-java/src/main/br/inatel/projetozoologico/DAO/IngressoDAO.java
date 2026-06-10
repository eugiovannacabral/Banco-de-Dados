package main.br.inatel.projetozoologico.DAO;

import main.br.inatel.projetozoologico.Model.Visitante;
import main.br.inatel.projetozoologico.Model.Ingresso;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class IngressoDAO extends ConnectionDAO {

    // ===== Inserindo ingressos =====

    public boolean insertIngresso(Ingresso ingresso) {

        connectToDb(); // Abre conexão
        String sql = "INSERT INTO Ingresso(data, tipo, preco, id_visitante) VALUES (?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, ingresso.getData()); // Seta parâmetro 1
            pst.setString(2, ingresso.getTipo());
            pst.setDouble(3, ingresso.getPreco());
            pst.setInt(4, ingresso.getVisitante().getIdVisitante());

            pst.executeUpdate();
            ResultSet rsGerado = pst.getGeneratedKeys();

            if (rsGerado.next()) {
                ingresso.setIdIngresso(rsGerado.getInt(1));
            }

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao inserir ingresso: " + e.getMessage());
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

    // ===== Buscar Ingresso Geral =====

    public List<Ingresso> selectIngresso(){

        List<Ingresso> ingressos = new ArrayList<>();

        connectToDb();

        String sql = "SELECT * FROM Ingresso";

        try {

            st = connection.createStatement();

            rs = st.executeQuery(sql);

            while(rs.next()){

                Ingresso ingresso = new Ingresso(
                        rs.getInt("id_Ingresso"),
                        rs.getString("data"),
                        rs.getString("tipo"),
                        rs.getDouble("preco")
                );

                // Cria um visitante apenas com o ID
                Visitante visitante = new Visitante(
                        rs.getInt("id_visitante"),
                        null,
                        0
                );

                // Associa o visitante ao ingresso
                ingresso.setVisitante(visitante);

                ingressos.add(ingresso);
            }

        } catch (SQLException e){

            System.out.println("Erro ao buscar Ingressos: " + e.getMessage());

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

        return ingressos;
    }

    // ===== Buscar Ingresso por data =====

    public Ingresso selectIngressoPorData(String data){

        Ingresso ingresso = null;
        connectToDb();
        String sql =  "SELECT * FROM Ingresso WHERE data=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, data);
            rs = pst.executeQuery();

            if(rs.next()) {

                ingresso = new Ingresso(
                        rs.getInt("id_Ingresso"),
                        rs.getString("data"),
                        rs.getString("tipo"),
                        rs.getDouble("preco")
                );
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar Ingresso: " + e.getMessage());

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

        return ingresso;
    }

    // ===== Join com o Visitante =====

    public Ingresso selectIngressoComVisitante(int idIngresso) {

        Ingresso ingresso = null;

        connectToDb();

        String sql = """
            SELECT
                i.id_Ingresso,
                i.data,
                i.tipo,
                i.preco,

                v.id_Visitante,
                v.nome AS nomeVisitante,
                v.idade

            FROM Ingresso i
            INNER JOIN Visitante v
                ON i.id_visitante = v.id_Visitante

            WHERE i.id_Ingresso = ?
            """;

        try {

            pst = connection.prepareStatement(sql);
            pst.setInt(1, idIngresso);

            rs = pst.executeQuery();

            if (rs.next()) {

                Visitante visitante = new Visitante(
                        rs.getInt("id_Visitante"),
                        rs.getString("nomeVisitante"),
                        rs.getInt("idade")
                );

                ingresso = new Ingresso(
                        rs.getInt("id_Ingresso"),
                        rs.getString("data"),
                        rs.getString("tipo"),
                        rs.getDouble("preco")
                );

                // associa o visitante ao ingresso
                ingresso.setVisitante(visitante);
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar ingresso com visitante: " + e.getMessage());

        } finally {

            try {

                if (rs != null)
                    rs.close();

                if (pst != null)
                    pst.close();

                if (connection != null)
                    connection.close();

            } catch (SQLException e) {

                System.out.println("Erro ao fechar recursos: " + e.getMessage());

            }
        }

        return ingresso;
    }

    // ===== Atualização dos Dados =====

    public boolean updateIngresso(Ingresso ingresso){
        connectToDb();
        String sql = "UPDATE Ingresso SET data=?, tipo=?, preco=?, id_visitante=? WHERE id_Ingresso=?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, ingresso.getData());
            pst.setString(2, ingresso.getTipo());
            pst.setDouble(3, ingresso.getPreco());
            pst.setInt(4, ingresso.getVisitante().getIdVisitante());
            pst.setInt(5, ingresso.getIdIngresso());
            pst.executeUpdate();

            return true;

        } catch (SQLException e){

            System.out.println("Erro ao atualizar Ingresso: " + e.getMessage());
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

    // ===== Deletar Dados por data =====

    public boolean deleteIngresso(String data){

        connectToDb();
        String sql = "DELETE FROM Ingresso WHERE data=?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, data);
            pst.execute();
            return true;

        } catch (SQLException e){

            System.out.println("Erro ao deletar Ingresso " + e.getMessage());
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