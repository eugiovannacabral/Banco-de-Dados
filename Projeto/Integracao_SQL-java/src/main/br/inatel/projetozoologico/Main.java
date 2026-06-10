package main.br.inatel.projetozoologico;
import main.br.inatel.projetozoologico.DAO.ConnectionDAO;
import main.br.inatel.projetozoologico.DAO.AnimalDAO;
import main.br.inatel.projetozoologico.DAO.HabitatDAO;
import main.br.inatel.projetozoologico.DAO.AlimentoDAO;

import main.br.inatel.projetozoologico.Model.Animal;
import main.br.inatel.projetozoologico.Model.Habitat;
import main.br.inatel.projetozoologico.Model.Alimento;

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
                System.out.println("2- Habitat");
                System.out.println("3- Alimento");
                System.out.println("4- Funcionário");
                System.out.println("5- Consulta Veterinária");
                System.out.println("6- Ingresso");
                System.out.println("7- Visitante");
                System.out.println("8- Sair");
                System.out.println("-----------------------------------");

                opcao = teclado.nextInt();

                if(opcao == 1){
                    while(true){

                        int opcao_animal = 0;
                        System.out.println("--------MENU ANIMAL-------");
                        System.out.println("1- Cadastrar um Animal");
                        System.out.println("2- Listar todos os An1imal");
                        System.out.println("3- Buscar uma espécie por meio do nome do animal");
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
                            System.out.println("Digite o nome do animal:");
                            String nomeAnimal = teclado.nextLine();

                            AnimalDAO animaldao = new AnimalDAO();

                            String especie = animaldao.selectEspecieByNome(nomeAnimal);

                            if(especie != null){
                                System.out.println("Espécie: " + especie);
                            }else{
                                System.out.println("Animal não encontrado!");
                            }
                        }
                        else if (opcao_animal == 4){

                            System.out.println("Digite o nome do animal:");
                            String nomeAnimal = teclado.nextLine();

                            AnimalDAO animaldao = new AnimalDAO();

                            Animal animal = animaldao.selectAnimalComHabitat(nomeAnimal);

                            if(animal != null){
                                animal.mostraInfoAnimal();
                            }else{
                                System.out.println("Animal não encontrado!");
                            }
                        }
                        else if (opcao_animal == 5){

                            System.out.println("Digite o ID do animal que deseja atualizar:");
                            int idAnimal = teclado.nextInt();
                            teclado.nextLine();

                            System.out.println("Novo nome:");
                            String nomeAnimal = teclado.nextLine();

                            System.out.println("Novo sexo:");
                            String sexoAnimal = teclado.nextLine();

                            System.out.println("Nova idade:");
                            int idadeAnimal = teclado.nextInt();
                            teclado.nextLine();

                            System.out.println("Nova espécie:");
                            String especieAnimal = teclado.nextLine();

                            System.out.println("Novo peso:");
                            double pesoAnimal = teclado.nextDouble();
                            teclado.nextLine();

                            System.out.println("Novo ID do habitat:");
                            int idHabitat = teclado.nextInt();
                            teclado.nextLine();

                            Habitat habitat = new Habitat(idHabitat, null, null, 0, null);

                            Animal animal = new Animal(idAnimal, nomeAnimal, sexoAnimal, idadeAnimal, especieAnimal, pesoAnimal);

                            animal.setHabitat(habitat);

                            AnimalDAO animaldao = new AnimalDAO();

                            if(animaldao.updateAnimal(animal)){
                                System.out.println("Animal atualizado com sucesso!");
                            }else{
                                System.out.println("Erro ao atualizar animal!");
                            }
                        }
                        else if (opcao_animal == 6){

                            System.out.println("Digite o nome do animal que deseja deletar:");
                            String nomeAnimal = teclado.nextLine();

                            AnimalDAO animaldao = new AnimalDAO();

                            if(animaldao.deleteAnimal(nomeAnimal)){
                                System.out.println("Animal deletado com sucesso!");
                            }else{
                                System.out.println("Erro ao deletar animal!");
                            }
                        }
                        else if(opcao_animal == 7){
                            break;
                        }
                    }


                }

                if(opcao == 3) {
                    while (true) {

                        int opcao_alimento = 0;
                        System.out.println("--------MENU ALIMENTO-------");
                        System.out.println("1- Cadastrar um Alimento");
                        System.out.println("2- Listar todos os Alimentos");
                        System.out.println("3- Buscar um alimento por meio do nome");
                        System.out.println("4- Atualização dos dados de um Alimento");
                        System.out.println("5- Deletar de um Alimento ");
                        System.out.println("6- Voltar");

                        opcao_alimento = teclado.nextInt();
                        teclado.nextLine();

                        if (opcao_alimento == 1) {
                            //Primeiro registramos cada um dos parâmetros em relação aos atributos do SQL
                            String tipoDeDieta;
                            System.out.println("Nome da Dieta:");
                            tipoDeDieta = teclado.nextLine();

                            int estoque;
                            System.out.println("Quantidade do alimento no estoque:");
                            estoque = teclado.nextInt();
                            teclado.nextLine(); // limpa buffer

                            String nomeAlimento;
                            System.out.println("Nome do Alimento:");
                            nomeAlimento = teclado.nextLine();

                            Alimento alimento = new Alimento(tipoDeDieta, estoque, nomeAlimento);
                            AlimentoDAO alimentodao = new AlimentoDAO();
                            alimentodao.insertAlimento(alimento);
                        } else if (opcao_alimento == 2) {
                            // Irá ser mostrado todos os animais registrados anteriormente
                            // Como não há parâmetros a serem avaliados em selectAlimento, não é preciso fazer mais nada
                            AlimentoDAO alimentodao = new AlimentoDAO();

                            for (Alimento alimento : alimentodao.selectAlimento()) {
                                alimento.mostraInfoAlimento();
                            }
                        } else if (opcao_alimento == 3) {
                            System.out.println("Digite o nome do alimento:");
                            String nomeAlimento = teclado.nextLine();

                            AlimentoDAO alimentodao = new AlimentoDAO();

                            Alimento alimento = alimentodao.selectAlimentoByNome(nomeAlimento);

                            if (alimento != null) {
                                alimento.mostraInfoAlimento();
                            } else {
                                System.out.println("Alimento não encontrado!");
                            }
                        }
                    }
                }

                if(opcao == 7){
                    break;
                }
            }
        }
}
