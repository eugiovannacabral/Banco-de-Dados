package main.br.inatel.projetozoologico.DAO;

import main.br.inatel.projetozoologico.Model.Animal;
import main.br.inatel.projetozoologico.Model.Habitat;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class AnimalDAO extends ConnectionDAO {

    // ------------INSERÇÃO------------//

    public boolean insertAnimal(Animal animal) {

        connectToDb();

        String sql =
                "INSERT INTO Animal(nome, sexo, idade, especie, peso, id_habitat) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            pst = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, animal.getNome());
            pst.setString(2, animal.getSexo());
            pst.setInt(3, animal.getIdade());
            pst.setString(4, animal.getEspecie());
            pst.setDouble(5, animal.getPeso());
            pst.setInt(6, animal.getHabitat().getIdHabitat());

            pst.executeUpdate();

            ResultSet rsGerado = pst.getGeneratedKeys();

            if (rsGerado.next()) {
                animal.setIdAnimal(rsGerado.getInt(1));
            }

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao inserir animal: " + e.getMessage());

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
                        rs.getInt("id_Animal"),
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
                        rs.getInt("id_Animal"),
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

     // ------------SELECT JOIN------------//

    public Animal selectAnimalComHabitat(String nome) {

    Animal animal = null;

    connectToDb();

    String sql = """
            SELECT
                a.id_Animal,
                a.nome AS nomeAnimal,
                a.sexo,
                a.idade,
                a.especie,
                a.peso,

                h.id_Habitat,
                h.nome AS nomeHabitat,
                h.clima,
                h.lotacao,
                h.tipo

            FROM Animal a
            INNER JOIN Habitat h
                ON a.id_habitat = h.id_Habitat

            WHERE a.nome = ?
            """;

    try {

        pst = connection.prepareStatement(sql);
        pst.setString(1, nome);

        rs = pst.executeQuery();

        if (rs.next()) {

            Habitat habitat = new Habitat(
                    rs.getInt("id_Habitat"),
                    rs.getString("nomeHabitat"),
                    rs.getString("clima"),
                    rs.getInt("lotacao"),
                    rs.getString("tipo")
            );

            animal = new Animal(
                    rs.getInt("id_Animal"),
                    rs.getString("nomeAnimal"),
                    rs.getString("sexo"),
                    rs.getInt("idade"),
                    rs.getString("especie"),
                    rs.getDouble("peso")
            );

            // Está associando o habitat ao animal
            animal.setHabitat(habitat);
        }

    } catch (SQLException e) {

        System.out.println("Erro ao buscar animal com habitat: " + e.getMessage());

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

    return animal;
}

    // ------------UPDATE------------//

    public boolean updateAnimal(Animal animal) {

        connectToDb();

        String sql =
                "UPDATE Animal SET nome=?, sexo=?, idade=?, especie=?, peso=?, id_habitat=? WHERE id_Animal=?";

        try {

            pst = connection.prepareStatement(sql);

            pst.setString(1, animal.getNome());
            pst.setString(2, animal.getSexo());
            pst.setInt(3, animal.getIdade());
            pst.setString(4, animal.getEspecie());
            pst.setDouble(5, animal.getPeso());
            pst.setInt(6, animal.getHabitat().getIdHabitat());
            pst.setInt(7, animal.getIdAnimal());

            pst.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar animal: " + e.getMessage());

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