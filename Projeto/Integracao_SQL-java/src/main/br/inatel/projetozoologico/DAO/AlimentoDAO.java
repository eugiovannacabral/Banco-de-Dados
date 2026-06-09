package main.br.inatel.projetozoologico.DAO;

import main.br.inatel.projetozoologico.Model.Animal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlimentoDAO extends ConnectionDAO {

    // ------------INSERÇÃO------------//

    public boolean insertAlimento(Alimento alimento){
        connectToDb();

        String sql = "INSERT INTO Alimento(idAlimento, tipoDeDieta, estoque, nome) VALUES (?, ?, ?, ?)";

        try {
            //cria um comando SQL preparado, esses numeros são as posicoes dos '?'
            //o get pega a informação do objeto Java e o set e para enviar as informacoes ao sql
            pst = connection.prepareStatement(sql);

            pst.setString(1, alimento.getIdAlimento());
            pst.setString(2, alimento.getTipoDeDieta());
            pst.setInt(3, alimento.getEstoque());
            pst.setString(4, alimento.getNome());// pega o ID do habitat associado ao animal

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao inserir a: " +alimento e.getMessage());

            return false;

            //garante que conexões de banco de dados sempre sejam liberadas idenpedente de ocorrer sucesso ou erro na implementação
        } finally {
            try{
                if(pst != null)
                    pst.close();

                if(connection != null)
                    connection.close();

            } catch (SQLException e) {

                System.out.println("Erro ao fechar recursos: " + e.getMessage());

            }
        }
    }

    //TEM QUE VER DE ACRESCENTAR AQUELES OUTROS ATRIBUTOS DO VIDEO
    // ------------SELECT COMPLETO DO ANIMAL------------//

    public List<Alimento> selectAlimento() {

        List<Alimento> alimentos = new ArrayList<>();

        connectToDb();

        String sql = "SELECT * FROM Alimento";

        try {

            st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {

                Alimento alimento = new Alimento(
                        rs.getInt("IdAlimento"),
                        rs.getString("tipoDeDieta"),
                        rs.getInt("estoque"),
                        rs.getString("nome")
                );

                alimentos.add(alimento)

            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar alimentos: " + e.getMessage());

        } finally {
            try {

                if(rs != null)
                    rs.close();

                if(st != null)
                    st.close();

                if(connection != null)
                    connection.close();

            } catch (SQLException e) {

                System.out.println("Erro ao fechar recursos: " + e.getMessage());

            }
        }

        return alimentos;
    }

    // ------------SELECT POR ATRIBUTO DE ANIMAL------------//

    public Alimento selectAlimentoByNome(String nome) {

        Alimento alimento = null;

        connectToDb();

        String sql = "SELECT * FROM Alimento WHERE nome=?";

        try {

            pst = connection.prepareStatement(sql);
            pst.setString(1, n);
            rs = pst.executeQuery();

            // Está retornando todas as informações do Alimento, depois de fazer a busca pelo o seu nome
            //" Pegue os dados que vieram do banco e transforme em um objeto Alimento"
            if(rs.next()) {

                alimento = new Alimento(
                        rs.getInt("idAlimento"),
                        rs.getString("tipoDeDieta"),
                        rs.getInt"estoque"),
                        rs.getString("nome")
                );
        }

    } catch (SQLException e) {

            System.out.println("Erro ao buscar alimento: " + e.getMessage());

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

        return alimento;

    }

    // ------------UPDATE------------//
    public boolean updateAlimento (Alimento alimento) {

        connectToDb();

        String sql = "UPDATE Alimento SET tipoDeDieta=?, estoque=?, nome=? WHERE idAlimento=?";

        try {
            pst = connection.prepareStatement(sql);

            pst.setString(1, animal.getTipoDeDieta());
            pst.setInt(2, animal.getEstoque());
            pst.setString(3, animal.getNome());

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar alimento: " + e.getMessage());

            return false;

        } finally {

            try {

                if(pst != null)
                    pst.close();

                if(connection != null)
                    connection.close();

            } catch (SQLException e) {

                System.out.println("Erro ao fechar recursos: " + e.getMessage());

            }
        }
    }

    // ------------DELETE------------//
    public boolean deleteAlimento(String nome) {

        connectToDb();

        String sql = "DELETE FROM Alimento WHERE nome=?";

        try {

            pst = connection.prepareStatement(sql);
            pst.setString(1, nome);
            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao deletar alimento: " + e.getMessage());

            return false;

        } finally {
            try {

                if(pst != null)
                    pst.close();

                if(connection != null)
                    connection.close();

            } catch (SQLException e) {

                System.out.println("Erro ao fechar recursos: " + e.getMessage());

            }
        }
    }
}

