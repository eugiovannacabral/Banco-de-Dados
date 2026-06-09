package main.br.inatel.projetozoologico.DAO;

import main.br.inatel.projetozoologico.Model.Habitat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitatDAO extends ConnectionDAO {

    // ------------ INSERT ------------ //

    public boolean insertHabitat(Habitat habitat) {

        connectToDb();

        String sql =
                "INSERT INTO Habitat(nome, clima, lotacao, tipo) VALUES (?, ?, ?, ?)";

        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, habitat.getNome());
            pst.setString(2, habitat.getClima());
            pst.setInt(3, habitat.getLotacao());
            pst.setString(4, habitat.getTipo());

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao inserir habitat: " + e.getMessage());
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

    // ------------ SELECT TODOS ------------ //

    public List<Habitat> selectHabitat() {

        List<Habitat> habitats = new ArrayList<>();

        connectToDb();

        String sql = "SELECT * FROM Habitat";

        try {

            st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {

                Habitat habitat = new Habitat(
                        rs.getInt("id_Habitat"),
                        rs.getString("nome"),
                        rs.getString("clima"),
                        rs.getInt("lotacao"),
                        rs.getString("tipo")
                );

                habitats.add(habitat);
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar habitats: " + e.getMessage());

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

        return habitats;
    }

    // ------------ SELECT POR TIPO ------------ //

    public String selectClimaByTipo(String tipo) {

        String clima = null;

        connectToDb();

        String sql = "SELECT clima FROM Habitat WHERE tipo = ?";

        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, tipo);

            rs = pst.executeQuery();

            if(rs.next()) {
                clima = rs.getString("clima");
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar clima: " + e.getMessage());

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
        return clima;
    }

    // ------------ UPDATE ------------ //

    public boolean updateHabitat(Habitat habitat) {

        connectToDb();

        String sql =
                "UPDATE Habitat SET nome=?, clima=?, lotacao=?, tipo=? WHERE id_Habitat=?";

        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, habitat.getNome());
            pst.setString(2, habitat.getClima());
            pst.setInt(3, habitat.getLotacao());
            pst.setString(4, habitat.getTipo());
            pst.setInt(5, habitat.getIdHabitat());

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar habitat: " + e.getMessage());

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

    public boolean deleteHabitat(String nome) {

        connectToDb();

        String sql = "DELETE FROM Habitat WHERE nome=?";

        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, nome);

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao deletar habitat: " + e.getMessage());

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
