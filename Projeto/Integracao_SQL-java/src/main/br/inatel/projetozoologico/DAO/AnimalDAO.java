package main.br.inatel.projetozoologico.DAO;

import main.br.inatel.projetozoologico.Model.Animal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO extends ConnectionDAO {

    // ------------INSERÇÃO------------//

    public boolean insertAnimal(Animal animal) {
        connectToDb();

        String sql = "INSERT INTO Animal(nome, sexo, idade, especie, peso, Habitat_idHabitat) VALUES (?, ?, ?, ?, ?, ?)";

        try {

            //cria um comando SQL preparado, esses numeros são as posicoes dos '?'
            //o get pega a informação do objeto Java e o set e para enviar as informacoes ao sql
            pst = connection.prepareStatement(sql);

            pst.setString(1, animal.getNome());
            pst.setString(2, animal.getSexo());
            pst.setInt(3, animal.getIdade());
            pst.setString(4, animal.getEspecie());
            pst.setDouble(5, animal.getPeso());
            pst.setInt(6, animal.getHabitat().getIdHabitat()); // pega o ID do habitat associado ao animal

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao inserir animal: " + e.getMessage());

            return false;

        //garante que conexões de banco de dados sempre sejam liberadas idenpedente de ocorrer sucesso ou erro na implementação
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

    //TEM QUE VER DE ACRESCENTAR AQUELES OUTROS ATRIBUTOS DO VIDEO
    // ------------SELECT COMPLETO DO ANIMAL------------//

    public List<Animal> selectAnimal() {

        List<Animal> animais = new ArrayList<>();

        connectToDb();

        String sql = "SELECT * FROM Animal";

        try {

            st = connection.createStatement();

            rs = st.executeQuery(sql);

            while(rs.next()) {

                Animal animal = new Animal(
                        rs.getInt("idAnimal"),
                        rs.getString("nome"),
                        rs.getString("sexo"),
                        rs.getInt("idade"),
                        rs.getString("especie"),
                        rs.getDouble("peso")
                );

                animais.add(animal);
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar animais: " + e.getMessage());

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

        return animais;
    }

    // ------------SELECT POR ATRIBUTO DE ANIMAL------------//

    public Animal selectAnimalByName(String nome) {

        Animal animal = null;

        connectToDb();

        String sql = "SELECT * FROM Animal WHERE nome=?";

        try {

            pst = connection.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();

            // Está retornando todas as informações do Animal, depois de fazer a busca pelo o seu nome
            //" Pegue os dados que vieram do banco e transforme em um objeto Animal"
            if(rs.next()) {

                animal = new Animal(
                        rs.getInt("idAnimal"),
                        rs.getString("nome"),
                        rs.getString("sexo"),
                        rs.getInt("idade"),
                        rs.getString("especie"),
                        rs.getDouble("peso")
                );
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar animal: " + e.getMessage());

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

        return animal;
    }

    // ------------UPDATE------------//
    public boolean updateAnimal(Animal animal) {

        connectToDb();

        String sql = "UPDATE Animal SET nome=?, sexo=?, idade=?, especie=?, peso=?, Habitat_idHabitat=? WHERE idAnimal=?";

        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, animal.getNome());
            pst.setString(2, animal.getSexo());
            pst.setInt(3, animal.getIdade());
            pst.setString(4, animal.getEspecie());
            pst.setDouble(5, animal.getPeso());
            pst.setInt(6, animal.getHabitat().getIdHabitat());

            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar animal: " + e.getMessage());

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
    public boolean deleteAnimal(String nome) {

        connectToDb();

        String sql = "DELETE FROM Animal WHERE nome=?";

        try {

            pst = connection.prepareStatement(sql);
            pst.setString(1, nome);
            pst.execute();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao deletar animal: " + e.getMessage());

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