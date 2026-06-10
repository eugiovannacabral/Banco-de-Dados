package main.br.inatel.projetozoologico.DAO;

import main.br.inatel.projetozoologico.Model.ConsultaVeterinaria;
import main.br.inatel.projetozoologico.Model.Animal;
import main.br.inatel.projetozoologico.Model.Funcionario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaVeterinariaDAO extends ConnectionDAO {

    // ------------ INSERT ------------ //

    public boolean insertConsulta(ConsultaVeterinaria consulta) {
        connectToDb();

        String sql =
                "INSERT INTO ConsultaVeterinaria(data, estado_de_saude, id_animal, id_funcionario) " +
                        "VALUES (?, ?, ?, ?)";

        try {

            pst = connection.prepareStatement(sql);

            pst.setDate(1, consulta.getData());
            pst.setString(2, consulta.getEstadoDeSaude());
            pst.setInt(3, consulta.getAnimal().getIdAnimal());
            pst.setInt(4, consulta.getFuncionario().getIdFuncionario());

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao inserir consulta: " + e.getMessage());
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

    public List<ConsultaVeterinaria> selectConsultas() {

        List<ConsultaVeterinaria> consultas = new ArrayList<>();

        connectToDb();

        String sql = "SELECT * FROM ConsultaVeterinaria";

        try {

            st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {

                ConsultaVeterinaria consulta =
                        new ConsultaVeterinaria(
                                rs.getInt("id_ConsultaVeterinaria"),
                                rs.getDate("data"),
                                rs.getString("estado_de_saude")
                        );

                consultas.add(consulta);
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar consultas: " + e.getMessage());

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

        return consultas;
    }

    // ------------ SELECT POR ESTADO ------------ //

    public ConsultaVeterinaria selectConsultaByEstado(String estado) {

        ConsultaVeterinaria consulta = null;

        connectToDb();

        String sql =
                "SELECT * FROM ConsultaVeterinaria WHERE estado_de_saude = ?";

        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, estado);

            rs = pst.executeQuery();

            if (rs.next()) {

                consulta = new ConsultaVeterinaria(
                        rs.getInt("id_ConsultaVeterinaria"),
                        rs.getDate("data"),
                        rs.getString("estado_de_saude")
                );
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar consulta: " + e.getMessage());

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

        return consulta;
    }

    // ------------ SELECT JOIN ------------ //

    public ConsultaVeterinaria selectConsultaComDetalhes(int idConsulta) {

        ConsultaVeterinaria consulta = null;

        connectToDb();

        String sql =
                "SELECT " +
                        "c.id_ConsultaVeterinaria, " +
                        "c.data, " +
                        "c.estado_de_saude, " +

                        "a.id_Animal, " +
                        "a.nome AS nomeAnimal, " +
                        "a.sexo, " +
                        "a.idade, " +
                        "a.especie, " +
                        "a.peso, " +

                        "f.id_Funcionario, " +
                        "f.nome AS nomeFuncionario, " +
                        "f.funcao, " +
                        "f.salario " +

                        "FROM ConsultaVeterinaria c " +

                        "INNER JOIN Animal a " +
                        "ON c.id_animal = a.id_Animal " +

                        "INNER JOIN Funcionario f " +
                        "ON c.id_funcionario = f.id_Funcionario " +

                        "WHERE c.id_ConsultaVeterinaria = ?";

        try {

            pst = connection.prepareStatement(sql);

            pst.setInt(1, idConsulta);

            rs = pst.executeQuery();

            if (rs.next()) {

                Animal animal = new Animal(
                        rs.getInt("id_Animal"),
                        rs.getString("nomeAnimal"),
                        rs.getString("sexo"),
                        rs.getInt("idade"),
                        rs.getString("especie"),
                        rs.getDouble("peso")
                );

                Funcionario funcionario = new Funcionario(
                        rs.getInt("id_Funcionario"),
                        rs.getString("nomeFuncionario"),
                        rs.getString("funcao"),
                        rs.getDouble("salario")
                );

                consulta = new ConsultaVeterinaria(
                        rs.getInt("id_ConsultaVeterinaria"),
                        rs.getDate("data"),
                        rs.getString("estado_de_saude")
                );

                consulta.setAnimal(animal);
                consulta.setFuncionario(funcionario);
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar consulta: " + e.getMessage());

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

        return consulta;
    }

    // ------------ UPDATE ------------ //

    public boolean updateConsulta(ConsultaVeterinaria consulta) {

        connectToDb();

        String sql =
                "UPDATE ConsultaVeterinaria " +
                        "SET data=?, estado_de_saude=?, id_animal=?, id_funcionario=? " +
                        "WHERE id_ConsultaVeterinaria=?";

        try {

            pst = connection.prepareStatement(sql);

            pst.setDate(1, consulta.getData());
            pst.setString(2, consulta.getEstadoDeSaude());
            pst.setInt(3, consulta.getAnimal().getIdAnimal());
            pst.setInt(4, consulta.getFuncionario().getIdFuncionario());
            pst.setInt(5, consulta.getIdConsultaVeterinaria());

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar consulta: " + e.getMessage());

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

    public boolean deleteConsulta(int idConsulta) {

        connectToDb();

        String sql =
                "DELETE FROM ConsultaVeterinaria WHERE id_ConsultaVeterinaria=?";

        try {

            pst = connection.prepareStatement(sql);

            pst.setInt(1, idConsulta);

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao deletar consulta: " + e.getMessage());

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
