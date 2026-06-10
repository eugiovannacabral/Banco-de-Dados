package main.br.inatel.projetozoologico;
import main.br.inatel.projetozoologico.DAO.ConnectionDAO;
import main.br.inatel.projetozoologico.DAO.AnimalDAO;
import main.br.inatel.projetozoologico.DAO.HabitatDAO;

import main.br.inatel.projetozoologico.Model.Animal;
import main.br.inatel.projetozoologico.Model.Habitat;

import java.util.Scanner;
import java.util.Locale;
import java.util.List;

public class Main extends ConnectionDAO {
    public static void main(String[] args) {
        //connectToDb(); escreveu aqui só para ver se a conexão está funcionando

        Scanner teclado = new Scanner(System.in);
        teclado.useLocale(java.util.Locale.US);

            while(true){
                int opcao = 0;
                System.out.println("-----BEM-VINDO AO NOSSO ZOOLÓGICO-------");
                System.out.println("1- Animais");
                System.out.println("2- Funcionários");
                System.out.println("3- Habitat");
                System.out.println("4- Consulta Veterinária");
                System.out.println("5- Ingresso");
                System.out.println("6- Visitante");
                System.out.println("7- Sair");
                System.out.println("-----------------------------------");

                opcao = teclado.nextInt();

                if(opcao == 1){
                    while(true){

                        int opcao_animal = 0;
                        System.out.println("1- Cadastrar um Animal");
                        System.out.println("2- Listar as informações de um Animal");
                        System.out.println("3- Buscar um Animal por meio do nome");
                        System.out.println("4- Listar as informações de um Animal e de seu habitat");
                        System.out.println("5- Atualização dos dados de um Animal ");
                        System.out.println("6- Deletar de um Animal ");
                        System.out.println("7- Voltar");

                        opcao_animal = teclado.nextInt();
                        teclado.nextLine(); // limpa buffer

                        if(opcao_animal == 1){
                            //Primeiro registramos cada um dos parâmetros em relação aos atributos do SQL
                            String nomeAnimal;
                            System.out.println("Nome do Animal:");
                            nomeAnimal = teclado.nextLine();

                            String sexoAnimal;
                            System.out.println("Sexo do Animal:");
                            sexoAnimal = teclado.nextLine();

                            int idadeAnimal;
                            System.out.println("Idade do Animal:");
                            idadeAnimal = teclado.nextInt();
                            teclado.nextLine(); // limpa buffer

                            String especieAnimal;
                            System.out.println("Especie que o Animal pertence:");
                            especieAnimal = teclado.nextLine();

                            double pesoAnimal;
                            System.out.println("Peso do animal (kg):");
                            pesoAnimal = teclado.nextDouble();
                            teclado.nextLine(); // limpa buffer

                            int idHabitat;
                            System.out.println("ID do habitat:");
                            idHabitat = teclado.nextInt();
                            Habitat habitat = new Habitat(idHabitat, null, null, 0, null);

                            Animal animal = new Animal(nomeAnimal, sexoAnimal, idadeAnimal, especieAnimal, pesoAnimal);
                            animal.setHabitat(habitat);
                            AnimalDAO animaldao = new AnimalDAO();
                            animaldao.insertAnimal(animal);
                        }
                        else if (opcao_animal == 2){
                            // Irá ser mostrado todos os animais registrados anteriormente
                            // Como não há parâmetros a serem avaliados em selectAnimal, não é preciso fazer mais nada
                            AnimalDAO animaldao = new AnimalDAO();

                            for(Animal animal : animaldao.selectAnimal()){
                                animal.mostraInfoAnimal();
                            }
                        }
                        else if (opcao_animal == 3){
                            String nomeAnimal;

                            System.out.println("Digite o nome do animal que deseja buscar:");
                            nomeAnimal = teclado.nextLine();

                            AnimalDAO animaldao = new AnimalDAO();

                            Animal animal = animaldao.selectAnimalByName(nomeAnimal);

                            if(animal != null){
                                animal.mostraInfoAnimal();
                            }else{
                                System.out.println("Animal não encontrado!");
                            }
                        }
                        else if(opcao_animal == 7){
                            break;
                        }
                    }


                }
                if(opcao == 7){
                    break;
                }
            }
        }
}
