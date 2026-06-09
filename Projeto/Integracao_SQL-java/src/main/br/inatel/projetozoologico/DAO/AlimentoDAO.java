package main.br.inatel.projetozoologico.DAO;

import main.br.inatel.projetozoologico.Model.Alimento;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlimentoDAO extends ConnectionDAO {

    // ------------ INSERÇÃO ------------ //

    public boolean insertAlimento(Alimento alimento) {

        connectToDb();

        String sql = "INSERT INTO Alimento(tipo_de_dieta, estoque, nome) VALUES (?, ?, ?)";

        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, alimento.getTipoDeDieta());
            pst.setInt(2, alimento.getEstoque());
            pst.setString(3, alimento.getNome());

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao inserir alimento: " + e.getMessage());

            return false;

        } finally {

            try {

                if (pst != null)
                    pst.close();

                if (connection != null)
                    connection.close();

            } catch (SQLException e) {

                System.out.println("Erro ao fechar recursos: " + e.getMessage());

            }
        }
    }

    // ------------ SELECT COMPLETO ------------ //

    public List<Alimento> selectAlimento() {

        List<Alimento> alimentos = new ArrayList<>();

        connectToDb();

        String sql = "SELECT * FROM Alimento";

        try {

            st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {

                Alimento alimento = new Alimento(
                        rs.getInt("id_Alimento"),
                        rs.getString("tipo_de_dieta"),
                        rs.getInt("estoque"),
                        rs.getString("nome")
                );

                alimentos.add(alimento);
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar alimentos: " + e.getMessage());

        } finally {

            try {

                if (rs != null)
                    rs.close();

                if (st != null)
                    st.close();

                if (connection != null)
                    connection.close();

            } catch (SQLException e) {

                System.out.println("Erro ao fechar recursos: " + e.getMessage());

            }
        }

        return alimentos;
    }

    // ------------ SELECT POR NOME ------------ //

    public Alimento selectAlimentoByNome(String nome) {

        Alimento alimento = null;

        connectToDb();

        String sql = "SELECT * FROM Alimento WHERE nome = ?";

        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, nome);

            rs = pst.executeQuery();

            if (rs.next()) {

                alimento = new Alimento(
                        rs.getInt("id_Alimento"),
                        rs.getString("tipo_de_dieta"),
                        rs.getInt("estoque"),
                        rs.getString("nome")
                );
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar alimento: " + e.getMessage());

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

        return alimento;
    }

    // ------------ UPDATE ------------ //

    public boolean updateAlimento(Alimento alimento) {

        connectToDb();

        String sql = """
                UPDATE Alimento
                SET tipo_de_dieta = ?,
                    estoque = ?,
                    nome = ?
                WHERE id_Alimento = ?
                """;

        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, alimento.getTipoDeDieta());
            pst.setInt(2, alimento.getEstoque());
            pst.setString(3, alimento.getNome());
            pst.setInt(4, alimento.getIdAlimento());

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar alimento: " + e.getMessage());

            return false;

        } finally {

            try {

                if (pst != null)
                    pst.close();

                if (connection != null)
                    connection.close();

            } catch (SQLException e) {

                System.out.println("Erro ao fechar recursos: " + e.getMessage());

            }
        }
    }

    // ------------ DELETE ------------ //

    public boolean deleteAlimento(String nome) {

        connectToDb();

        String sql = "DELETE FROM Alimento WHERE nome = ?";

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

                if (pst != null)
                    pst.close();

                if (connection != null)
                    connection.close();

            } catch (SQLException e) {

                System.out.println("Erro ao fechar recursos: " + e.getMessage());

            }
        }
    }

    // ------------ ASSOCIAÇÃO N:M COM ANIMAL ------------ //

    public boolean associarAlimentoAnimal(int idAlimento, int idAnimal) {

        connectToDb();

        String sql = """
                INSERT INTO Alimento_has_Animal
                (id_alimento, id_animal)
                VALUES (?, ?)
                """;

        try {

            pst = connection.prepareStatement(sql);

            pst.setInt(1, idAlimento);
            pst.setInt(2, idAnimal);

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao associar alimento e animal: " + e.getMessage());

            return false;

        } finally {

            try {

                if (pst != null)
                    pst.close();

                if (connection != null)
                    connection.close();

            } catch (SQLException e) {

                System.out.println("Erro ao fechar recursos: " + e.getMessage());

            }
        }
    }
}