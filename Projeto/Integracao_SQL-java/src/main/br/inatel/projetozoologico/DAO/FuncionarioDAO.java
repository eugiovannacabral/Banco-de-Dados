package main.br.inatel.projetozoologico.DAO;

import main.br.inatel.projetozoologico.Model.Funcionario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO extends ConnectionDAO {

    // ------------ INSERÇÃO ------------ //

    public boolean insertFuncionario(Funcionario funcionario) {

        connectToDb();

        String sql = """
                INSERT INTO Funcionario
                (nome, funcao, salario)
                VALUES (?, ?, ?)
                """;

        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, funcionario.getNome());
            pst.setString(2, funcionario.getFuncao());
            pst.setDouble(3, funcionario.getSalario());

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao inserir funcionário: " + e.getMessage());

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

    public List<Funcionario> selectFuncionario() {

        List<Funcionario> funcionarios = new ArrayList<>();

        connectToDb();

        String sql = "SELECT * FROM Funcionario";

        try {

            st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {

                Funcionario funcionario = new Funcionario(
                        rs.getInt("id_Funcionario"),
                        rs.getString("nome"),
                        rs.getString("funcao"),
                        rs.getDouble("salario")
                );

                funcionarios.add(funcionario);
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar funcionários: " + e.getMessage());

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

        return funcionarios;
    }

    // ------------ SELECT POR NOME ------------ //

    public Funcionario selectFuncionarioByNome(String nome) {

        Funcionario funcionario = null;

        connectToDb();

        String sql = "SELECT * FROM Funcionario WHERE nome = ?";

        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, nome);

            rs = pst.executeQuery();

            if (rs.next()) {

                funcionario = new Funcionario(
                        rs.getInt("id_Funcionario"),
                        rs.getString("nome"),
                        rs.getString("funcao"),
                        rs.getDouble("salario")
                );
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar funcionário: " + e.getMessage());

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

        return funcionario;
    }

    // ------------ UPDATE ------------ //

    public boolean updateFuncionario(Funcionario funcionario) {

        connectToDb();

        String sql = """
                UPDATE Funcionario
                SET nome = ?,
                    funcao = ?,
                    salario = ?
                WHERE id_Funcionario = ?
                """;

        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, funcionario.getNome());
            pst.setString(2, funcionario.getFuncao());
            pst.setDouble(3, funcionario.getSalario());
            pst.setInt(4, funcionario.getIdFuncionario());

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());

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

    public boolean deleteFuncionario(String nome) {

        connectToDb();

        String sql = "DELETE FROM Funcionario WHERE nome = ?";

        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, nome);

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao deletar funcionário: " + e.getMessage());

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

    // ------------ N:M FUNCIONÁRIO - ANIMAL ------------ //

    public void listarFuncionariosPorAnimal(int idAnimal){

        connectToDb();

        String sql =
                "SELECT f.nome, f.funcao " +
                        "FROM Funcionario f " +
                        "INNER JOIN Funcionario_has_Animal fa " +
                        "ON f.id_Funcionario = fa.id_funcionario " +
                        "WHERE fa.id_animal = ?";

        try{

            pst = connection.prepareStatement(sql);
            pst.setInt(1, idAnimal);

            rs = pst.executeQuery();

            System.out.println("\nFuncionários responsáveis:");

            while(rs.next()){

                System.out.println(
                        rs.getString("nome")
                                + " - "
                                + rs.getString("funcao")
                );
            }

        }catch(SQLException e){

            System.out.println(e.getMessage());

        }
    }

    // ------------ N:M FUNCIONÁRIO - HABITAT ------------ //

    public void listarFuncionariosPorHabitat(int idHabitat){

        connectToDb();

        String sql =
                "SELECT f.nome, f.funcao " +
                        "FROM Funcionario f " +
                        "INNER JOIN Funcionario_has_Habitat fh " +
                        "ON f.id_Funcionario = fh.id_funcionario " +
                        "WHERE fh.id_habitat = ?";

        try{

            pst = connection.prepareStatement(sql);
            pst.setInt(1, idHabitat);

            rs = pst.executeQuery();

            System.out.println("\nFuncionários responsáveis:");

            while(rs.next()){

                System.out.println(
                        rs.getString("nome")
                                + " - "
                                + rs.getString("funcao")
                );
            }

        }catch(SQLException e){

            System.out.println(e.getMessage());

        }
    }
}
