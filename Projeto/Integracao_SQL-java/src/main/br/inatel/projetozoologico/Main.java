package main.br.inatel.projetozoologico;
import main.br.inatel.projetozoologico.DAO.ConnectionDAO;
import main.br.inatel.projetozoologico.DAO.AnimalDAO;
import main.br.inatel.projetozoologico.Model.Animal;

import java.util.Scanner;

public class Main extends ConnectionDAO {
    public static void main(String[] args) {
        //connectToDb(); escreveu aqui só para ver se a conexão está funcionando

        Scanner teclado = new Scanner(System.in);

            while(true){
                int opcao = 0;
                System.out.println("-----BEM-VINDO AO NOSSO ZOOLÓGICO-------");
                System.out.print("1- Animais");
                System.out.print("2- Funcionários");
                System.out.print("3- Habitat");
                System.out.print("4- Consulta Veterinária");
                System.out.print("5- Ingresso");
                System.out.print("6- Visitante");
                System.out.print("7- Sair");

                if(opcao == 1){
                    while(true){
                        int opcao_animal = 0;
                        System.out.print("1- Cadastrar um Animal");
                        System.out.print("2- Listar as informações de um Animal");
                        System.out.print("3- Buscar um Animal por meio do nome");
                        System.out.print("4- Listar as informações de um Animal e de seu habitat");
                        System.out.print("5- Atualização dos dados de um Animal ");
                        System.out.print("6- Deletar de um Animal ");
                        System.out.print("7- Voltar ");


                        if(opcao_animal == 1){
                            String nomeAnimal;
                            nomeAnimal = teclado.nextLine();
                            Animal animal = new Animal(nomeAnimal,);
                            AnimalDAO animaldao = new AnimalDAO();
                            animaldao.insertAnimal(animal);
                        }
                    }
                }
            }
        }
}
