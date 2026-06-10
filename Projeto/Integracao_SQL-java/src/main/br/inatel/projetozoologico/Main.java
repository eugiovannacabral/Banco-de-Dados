package main.br.inatel.projetozoologico;
import main.br.inatel.projetozoologico.DAO.ConnectionDAO;
import main.br.inatel.projetozoologico.DAO.AnimalDAO;
import main.br.inatel.projetozoologico.DAO.HabitatDAO;
import main.br.inatel.projetozoologico.DAO.AlimentoDAO;
import main.br.inatel.projetozoologico.DAO.FuncionarioDAO;
import main.br.inatel.projetozoologico.DAO.ConsultaVeterinariaDAO;
import main.br.inatel.projetozoologico.DAO.VisitanteDAO;
import main.br.inatel.projetozoologico.DAO.IngressoDAO;

import main.br.inatel.projetozoologico.Model.Animal;
import main.br.inatel.projetozoologico.Model.Habitat;
import main.br.inatel.projetozoologico.Model.Alimento;
import main.br.inatel.projetozoologico.Model.Funcionario;
import main.br.inatel.projetozoologico.Model.ConsultaVeterinaria;
import main.br.inatel.projetozoologico.Model.Ingresso;
import main.br.inatel.projetozoologico.Model.Visitante;

import java.util.Scanner;
import java.util.Locale;
import java.util.List;

public class Main extends ConnectionDAO {
    public static void main(String[] args) {
        //connectToDb(); escreveu aqui só para ver se a conexão está funcionando

        Scanner teclado = new Scanner(System.in);
        teclado.useLocale(java.util.Locale.US);

        while (true) {
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

            if (opcao == 1) {
                while (true) {

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

                    if (opcao_animal == 1) {
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
                    } else if (opcao_animal == 2) {
                        // Irá ser mostrado todos os animais registrados anteriormente
                        // Como não há parâmetros a serem avaliados em selectAnimal, não é preciso fazer mais nada
                        AnimalDAO animaldao = new AnimalDAO();

                        for (Animal animal : animaldao.selectAnimal()) {
                            animal.mostraInfoAnimal();
                        }
                    } else if (opcao_animal == 3) {
                        System.out.println("Digite o nome do animal:");
                        String nomeAnimal = teclado.nextLine();

                        AnimalDAO animaldao = new AnimalDAO();

                        String especie = animaldao.selectEspecieByNome(nomeAnimal);

                        if (especie != null) {
                            System.out.println("Espécie: " + especie);
                        } else {
                            System.out.println("Animal não encontrado!");
                        }
                    } else if (opcao_animal == 4) {

                        System.out.println("Digite o nome do animal:");
                        String nomeAnimal = teclado.nextLine();

                        AnimalDAO animaldao = new AnimalDAO();

                        Animal animal = animaldao.selectAnimalComHabitat(nomeAnimal);

                        if (animal != null) {
                            animal.mostraInfoAnimal();
                        } else {
                            System.out.println("Animal não encontrado!");
                        }
                    } else if (opcao_animal == 5) {

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

                        if (animaldao.updateAnimal(animal)) {
                            System.out.println("Animal atualizado com sucesso!");
                        } else {
                            System.out.println("Erro ao atualizar animal!");
                        }
                    } else if (opcao_animal == 6) {

                        System.out.println("Digite o nome do animal que deseja deletar:");
                        String nomeAnimal = teclado.nextLine();

                        AnimalDAO animaldao = new AnimalDAO();

                        if (animaldao.deleteAnimal(nomeAnimal)) {
                            System.out.println("Animal deletado com sucesso!");
                        } else {
                            System.out.println("Erro ao deletar animal!");
                        }
                    } else if (opcao_animal == 7) {
                        break;
                    }
                }


            }

            if (opcao == 3) {
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

                    else if (opcao_alimento == 4) {
                        System.out.println("Digite o ID do alimento que deseja atualizar:");
                        int idAlimento = teclado.nextInt();
                        teclado.nextLine();

                        System.out.println("Nova Dieta:");
                        String tipoDeDieta = teclado.nextLine();

                        System.out.println("Nova quantidade do alimento no estoque:");
                        int estoqueAlimento = teclado.nextInt();
                        teclado.nextLine();

                        System.out.println("Novo nome:");
                        String nomeAlimento = teclado.nextLine();

                        Alimento alimento = new Alimento(idAlimento, tipoDeDieta, estoqueAlimento, nomeAlimento);

                        AlimentoDAO alimentodao = new AlimentoDAO();

                        if (alimentodao.updateAlimento(alimento)) {
                            System.out.println("Alimento atualizado com sucesso!");
                        } else {
                            System.out.println("Erro ao atualizar alimento!");
                        }
                    }
                    else if (opcao_alimento == 6) {

                        System.out.println("Digite o ID do alimento:");
                        int idAlimento = teclado.nextInt();

                        System.out.println("Digite o ID do animal:");
                        int idAnimal = teclado.nextInt();
                        teclado.nextLine();

                        AlimentoDAO alimentodao = new AlimentoDAO();

                        if(alimentodao.associarAlimentoAnimal(idAlimento, idAnimal)) {
                            System.out.println("Associação realizada com sucesso!");
                        } else {
                            System.out.println("Erro ao realizar associação!");
                        }
                    }
                    else if (opcao_alimento == 7) {
                        break;
                    }
                }
            }

            if(opcao == 4){
                while(true){

                    int opcao_funcionario = 0;

                    System.out.println("--------MENU FUNCIONÁRIO-------");
                    System.out.println("1- Cadastrar Funcionário");
                    System.out.println("2- Listar Funcionários");
                    System.out.println("3- Buscar Funcionário por nome");
                    System.out.println("4- Atualizar Funcionário");
                    System.out.println("5- Deletar Funcionário");
                    System.out.println("6- Listar funcionários de um animal");
                    System.out.println("7- Listar funcionários de um habitat");
                    System.out.println("8- Voltar");

                    opcao_funcionario = teclado.nextInt();
                    teclado.nextLine();

                    if(opcao_funcionario == 1){

                        System.out.println("Nome:");
                        String nome = teclado.nextLine();

                        System.out.println("Função:");
                        String funcao = teclado.nextLine();

                        System.out.println("Salário:");
                        double salario = teclado.nextDouble();
                        teclado.nextLine();

                        Funcionario funcionario =
                                new Funcionario(nome, funcao, salario);

                        FuncionarioDAO funcionarioDAO =
                                new FuncionarioDAO();

                        funcionarioDAO.insertFuncionario(funcionario);
                    }

                    else if(opcao_funcionario == 2){

                        FuncionarioDAO funcionarioDAO =
                                new FuncionarioDAO();

                        for(Funcionario funcionario :
                                funcionarioDAO.selectFuncionario()){

                            funcionario.mostraInfoFuncionario();
                        }
                    }

                    else if(opcao_funcionario == 3){

                        System.out.println("Nome do funcionário:");
                        String nome = teclado.nextLine();

                        FuncionarioDAO funcionarioDAO =
                                new FuncionarioDAO();

                        Funcionario funcionario =
                                funcionarioDAO.selectFuncionarioByNome(nome);

                        if(funcionario != null)
                            funcionario.mostraInfoFuncionario();
                        else
                            System.out.println("Funcionário não encontrado!");
                    }

                    else if(opcao_funcionario == 4){

                        System.out.println("ID do funcionário:");
                        int id = teclado.nextInt();
                        teclado.nextLine();

                        System.out.println("Novo nome:");
                        String nome = teclado.nextLine();

                        System.out.println("Nova função:");
                        String funcao = teclado.nextLine();

                        System.out.println("Novo salário:");
                        double salario = teclado.nextDouble();
                        teclado.nextLine();

                        Funcionario funcionario =
                                new Funcionario(id, nome, funcao, salario);

                        FuncionarioDAO funcionarioDAO =
                                new FuncionarioDAO();

                        funcionarioDAO.updateFuncionario(funcionario);
                    }

                    else if(opcao_funcionario == 5){

                        System.out.println("Nome do funcionário:");
                        String nome = teclado.nextLine();

                        FuncionarioDAO funcionarioDAO =
                                new FuncionarioDAO();

                        funcionarioDAO.deleteFuncionario(nome);
                    }

                    else if(opcao_funcionario == 6){

                            System.out.println("ID do animal:");
                            int idAnimal = teclado.nextInt();
                            teclado.nextLine();

                            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

                            funcionarioDAO.listarFuncionariosPorAnimal(idAnimal);
                    }

                    else if(opcao_funcionario == 7){

                            System.out.println("ID do habitat:");
                            int idHabitat = teclado.nextInt();
                            teclado.nextLine();

                            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

                            funcionarioDAO.listarFuncionariosPorHabitat(idHabitat);
                    }

                    else if(opcao_funcionario == 8){
                        break;
                    }
                }
            }

            if(opcao == 5){
                while(true){

                    int opcao_consulta = 0;

                    System.out.println("------MENU CONSULTA VETERINÁRIA------");
                    System.out.println("1- Cadastrar Consulta");
                    System.out.println("2- Listar Consultas");
                    System.out.println("3- Buscar Consulta por Estado de Saúde");
                    System.out.println("4- Mostrar Consulta Completa");
                    System.out.println("5- Atualizar Consulta");
                    System.out.println("6- Deletar Consulta");
                    System.out.println("7- Voltar");

                    opcao_consulta = teclado.nextInt();
                    teclado.nextLine();

                    if(opcao_consulta == 1){

                        System.out.println("Data (AAAA-MM-DD):");
                        java.sql.Date data =
                                java.sql.Date.valueOf(teclado.nextLine());

                        System.out.println("Estado de saúde:");
                        String estado = teclado.nextLine();

                        System.out.println("ID do Animal:");
                        int idAnimal = teclado.nextInt();

                        System.out.println("ID do Funcionário:");
                        int idFuncionario = teclado.nextInt();
                        teclado.nextLine();

                        Animal animal =
                                new Animal(idAnimal,null,null,0,null,0);

                        Funcionario funcionario =
                                new Funcionario(idFuncionario,null,null,0);

                        ConsultaVeterinaria consulta =
                                new ConsultaVeterinaria(data, estado);

                        consulta.setAnimal(animal);
                        consulta.setFuncionario(funcionario);

                        ConsultaVeterinariaDAO consultaDAO =
                                new ConsultaVeterinariaDAO();

                        consultaDAO.insertConsulta(consulta);
                    }

                    else if(opcao_consulta == 2){

                        ConsultaVeterinariaDAO consultaDAO =
                                new ConsultaVeterinariaDAO();

                        for(ConsultaVeterinaria consulta :
                                consultaDAO.selectConsultas()){

                            consulta.mostraInfoConsulta();
                        }
                    }

                    else if(opcao_consulta == 3){

                        System.out.println("Estado de saúde:");
                        String estado = teclado.nextLine();

                        ConsultaVeterinariaDAO consultaDAO =
                                new ConsultaVeterinariaDAO();

                        ConsultaVeterinaria consulta =
                                consultaDAO.selectConsultaByEstado(estado);

                        if(consulta != null)
                            consulta.mostraInfoConsulta();
                        else
                            System.out.println("Consulta não encontrada!");
                    }

                    else if(opcao_consulta == 4){

                        System.out.println("ID da consulta:");
                        int idConsulta = teclado.nextInt();
                        teclado.nextLine();

                        ConsultaVeterinariaDAO consultaDAO =
                                new ConsultaVeterinariaDAO();

                        ConsultaVeterinaria consulta =
                                consultaDAO.selectConsultaComDetalhes(idConsulta);

                        if(consulta != null){

                            consulta.mostraInfoConsulta();

                            System.out.println("\n=== ANIMAL ===");
                            consulta.getAnimal().mostraInfoAnimal();

                            System.out.println("\n=== FUNCIONÁRIO ===");
                            consulta.getFuncionario().mostraInfoFuncionario();

                        }else{
                            System.out.println("Consulta não encontrada!");
                        }
                    }

                    else if(opcao_consulta == 5){

                        System.out.println("ID da consulta:");
                        int idConsulta = teclado.nextInt();
                        teclado.nextLine();

                        System.out.println("Nova data (AAAA-MM-DD):");
                        java.sql.Date data =
                                java.sql.Date.valueOf(teclado.nextLine());

                        System.out.println("Novo estado:");
                        String estado = teclado.nextLine();

                        System.out.println("Novo ID Animal:");
                        int idAnimal = teclado.nextInt();

                        System.out.println("Novo ID Funcionário:");
                        int idFuncionario = teclado.nextInt();
                        teclado.nextLine();

                        Animal animal =
                                new Animal(idAnimal,null,null,0,null,0);

                        Funcionario funcionario =
                                new Funcionario(idFuncionario,null,null,0);

                        ConsultaVeterinaria consulta =
                                new ConsultaVeterinaria(
                                        idConsulta,
                                        data,
                                        estado
                                );

                        consulta.setAnimal(animal);
                        consulta.setFuncionario(funcionario);

                        ConsultaVeterinariaDAO consultaDAO =
                                new ConsultaVeterinariaDAO();

                        consultaDAO.updateConsulta(consulta);
                    }

                    else if(opcao_consulta == 6){

                        System.out.println("ID da consulta:");
                        int idConsulta = teclado.nextInt();
                        teclado.nextLine();

                        ConsultaVeterinariaDAO consultaDAO =
                                new ConsultaVeterinariaDAO();

                        consultaDAO.deleteConsulta(idConsulta);
                    }

                    else if(opcao_consulta == 7){
                        break;
                    }
                }
            }

            if (opcao == 6) {
                while (true) {

                    int opcao_ingresso = 0;
                    System.out.println("--------MENU INGRESSO-------");
                    System.out.println("1- Cadastrar um Ingresso");
                    System.out.println("2- Listar todos os Ingressos");
                    System.out.println("3- Buscar um Ingresso por meio da data");
                    System.out.println("4- Listar as informações de um Ingresso");
                    System.out.println("5- Atualização dos dados de um Ingresso ");
                    System.out.println("6- Deletar um Ingresso");
                    System.out.println("7- Voltar");

                    opcao_ingresso = teclado.nextInt();
                    teclado.nextLine();

                    if (opcao_ingresso == 1) {

                        System.out.println("Data do ingresso (AAAA-MM-DD):");
                        String data = teclado.nextLine();

                        System.out.println("Tipo do ingresso:");
                        String tipo = teclado.nextLine();

                        System.out.println("Preço:");
                        double preco = teclado.nextDouble();
                        teclado.nextLine();

                        System.out.println("ID do visitante:");
                        int idVisitante = teclado.nextInt();
                        teclado.nextLine();

                        Visitante visitante = new Visitante(idVisitante, null, 0);

                        Ingresso ingresso = new Ingresso(data, tipo, preco);
                        ingresso.setVisitante(visitante);

                        IngressoDAO ingressodao = new IngressoDAO();

                        if (ingressodao.insertIngresso(ingresso)) {
                            System.out.println("Ingresso cadastrado com sucesso!");
                        } else {
                            System.out.println("Erro ao cadastrar ingresso!");
                        }
                    } else if (opcao_ingresso == 2) {

                        IngressoDAO ingressodao = new IngressoDAO();

                        for (Ingresso ingresso : ingressodao.selectIngresso()) {
                            ingresso.mostrarInfosIngresso();
                        }
                    } else if (opcao_ingresso == 3) {

                        System.out.println("Digite a data do ingresso (AAAA-MM-DD):");
                        String data = teclado.nextLine();

                        IngressoDAO ingressodao = new IngressoDAO();

                        Ingresso ingresso = ingressodao.selectIngressoPorData(data);

                        if (ingresso != null) {
                            ingresso.mostrarInfosIngresso();
                        } else {
                            System.out.println("Ingresso não encontrado!");
                        }
                    } else if (opcao_ingresso == 4) {

                        System.out.println("Digite o ID do ingresso:");
                        int idIngresso = teclado.nextInt();
                        teclado.nextLine();

                        IngressoDAO ingressodao = new IngressoDAO();

                        Ingresso ingresso = ingressodao.selectIngressoComVisitante(idIngresso);

                        if (ingresso != null) {
                            ingresso.mostrarInfosIngresso();

                            if (ingresso.getVisitante() != null) {
                                ingresso.getVisitante().mostrarInfosVisitante();
                            }

                        } else {
                            System.out.println("Ingresso não encontrado!");
                        }
                    } else if (opcao_ingresso == 5) {

                        System.out.println("Digite o ID do ingresso que deseja atualizar:");
                        int idIngresso = teclado.nextInt();
                        teclado.nextLine();

                        System.out.println("Nova data (AAAA-MM-DD):");
                        String data = teclado.nextLine();

                        System.out.println("Novo tipo:");
                        String tipo = teclado.nextLine();

                        System.out.println("Novo preço:");
                        double preco = teclado.nextDouble();
                        teclado.nextLine();

                        System.out.println("Novo ID do visitante:");
                        int idVisitante = teclado.nextInt();
                        teclado.nextLine();

                        Visitante visitante = new Visitante(idVisitante, null, 0);

                        Ingresso ingresso = new Ingresso(idIngresso, data, tipo, preco);
                        ingresso.setVisitante(visitante);

                        IngressoDAO ingressodao = new IngressoDAO();

                        if (ingressodao.updateIngresso(ingresso)) {
                            System.out.println("Ingresso atualizado com sucesso!");
                        } else {
                            System.out.println("Erro ao atualizar ingresso!");
                        }
                    } else if (opcao_ingresso == 6) {

                        System.out.println("Digite a data do ingresso que deseja deletar:");
                        String data = teclado.nextLine();

                        IngressoDAO ingressodao = new IngressoDAO();

                        if (ingressodao.deleteIngresso(data)) {
                            System.out.println("Ingresso deletado com sucesso!");
                        } else {
                            System.out.println("Erro ao deletar ingresso!");
                        }
                    } else if (opcao_ingresso == 7) {

                        break;
                    }
                }
            }

            if (opcao == 7) {
                while (true) {

                    int opcao_visitante = 0;
                    System.out.println("--------MENU VISITANTE-------");
                    System.out.println("1- Cadastrar um Visitante");
                    System.out.println("2- Listar todos os Visitantes");
                    System.out.println("3- Buscar um visitante por meio do nome");
                    System.out.println("4- Listar as informações de um Visitante e de seu ingresso");
                    System.out.println("5- Atualização dos dados de um Visitante ");
                    System.out.println("6- Deletar um Visitante");
                    System.out.println("7- Voltar");

                    opcao_visitante = teclado.nextInt();
                    teclado.nextLine();

                    if (opcao_visitante == 1) {
                        //Primeiro registramos cada um dos parâmetros em relação aos atributos do SQL
                        String nomeVisitante;
                        System.out.println("Nome do Visitante:");
                        nomeVisitante = teclado.nextLine();

                        int idadeVisitante;
                        System.out.println("Idade do Visitante:");
                        idadeVisitante = teclado.nextInt();
                        teclado.nextLine(); // limpa buffer

                        Visitante visitante = new Visitante(nomeVisitante, idadeVisitante);
                        VisitanteDAO visitantedao = new VisitanteDAO();
                        visitantedao.insertVisitante(visitante);

                    } else if (opcao_visitante == 2) {
                        // Mostra todos os visitantes cadastrados

                        VisitanteDAO visitantedao = new VisitanteDAO();

                        for (Visitante visitante : visitantedao.selectVisitante()) {
                            visitante.mostrarInfosVisitante();
                        }
                    } else if (opcao_visitante == 3) {
                        System.out.println("Digite o nome do Visitante:");
                        String nomeVisitante = teclado.nextLine();

                        VisitanteDAO visitantedao = new VisitanteDAO();

                        Visitante visitante = visitantedao.selectVisitantePorNome(nomeVisitante);

                        if (visitante != null) {

                            visitante.mostrarInfosVisitante();

                        } else {

                            System.out.println("Visitante não encontrado!");

                        }
                    } else if (opcao_visitante == 4) {

                        System.out.println("Digite o ID do visitante:");
                        int idVisitante = teclado.nextInt();
                        teclado.nextLine();

                        VisitanteDAO visitantedao = new VisitanteDAO();

                        Visitante visitante = visitantedao.selectVisitanteComIngresso(idVisitante);

                        if (visitante != null) {
                            visitante.mostrarInfosVisitante();

                            if (visitante.getIngresso() != null) {
                                visitante.getIngresso().mostrarInfosIngresso();
                            }

                        } else {
                            System.out.println("Visitante não encontrado!");
                        }

                    } else if (opcao_visitante == 5) {

                        System.out.println("Digite o ID do visitante que deseja atualizar:");
                        int idVisitante = teclado.nextInt();
                        teclado.nextLine();

                        System.out.println("Novo nome:");
                        String nomeVisitante = teclado.nextLine();

                        System.out.println("Nova idade:");
                        int idadeVisitante = teclado.nextInt();
                        teclado.nextLine();

                        Visitante visitante = new Visitante(idVisitante, nomeVisitante, idadeVisitante);

                        VisitanteDAO visitantedao = new VisitanteDAO();

                        if (visitantedao.updateVisitante(visitante)) {
                            System.out.println("Visitante atualizado com sucesso!");
                        } else {
                            System.out.println("Erro ao atualizar visitante!");
                        }
                    } else if (opcao_visitante == 6) {

                        System.out.println("Digite o nome do visitante que deseja deletar:");
                        String nomeVisitante = teclado.nextLine();

                        VisitanteDAO visitantedao = new VisitanteDAO();

                        if (visitantedao.deleteVisitante(nomeVisitante)) {
                            System.out.println("Visitante deletado com sucesso!");
                        } else {
                            System.out.println("Erro ao deletar visitante!");
                        }
                    } else if (opcao_visitante == 7) {
                        break;
                    }

                }
            }
            if (opcao == 8) {
                System.out.println("OBRIGADA POR NOS VISITAR");
                break;
            }
        }
    }
}