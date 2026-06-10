package main.br.inatel.projetozoologico.Model;

public class Visitante {

    private int id_Visitante;
    private String nome;
    private int idade;
    private Ingresso ingresso;

    public Visitante(int id_Visitante, String nome, int idade){
        this.id_Visitante = id_Visitante;
        this.nome = nome;
        this.idade = idade;
    }

    public Visitante(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }

    public void mostrarInfosVisitante(){
        System.out.println("\n=== DADOS DO VISITANTE ===");
        System.out.println("ID: " + id_Visitante);
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
    }

    // ======= Getters e Setters =======

    // ------- IdVisitante -------
    public int getIdVisitante() {
        return id_Visitante;
    }

    public void setIdVisitante(int id_Visitante) {

        this.id_Visitante = id_Visitante;
    }

    // ------- Nome -------
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // ------- idade -------
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    // ------- Ingresso -------
    public Ingresso getIngresso() { return ingresso; }

    public void setIngresso(Ingresso ingresso) { this.ingresso = ingresso; }
}