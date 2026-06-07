package main.br.inatel.projetozoologico.Model;

public class Animal {

    private int idAnimal;

    private String nome;
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    private String sexo;
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    private int idade;
    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    private String especie;
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    private double peso;

    private Habitat habitat; //Relacionamento de 1:N com o Hbitat

    public Animal(int idAnimal,String nome, String sexo, int idade, String especie, double peso) {
        this.idAnimal = idAnimal;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.especie = especie;
        this.peso = peso;
    }

    public void mostraInfoPersonagem() {
        System.out.println(ANSI_CYAN  + "\nNome do NPC: " + nome + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Idade: " + idade + " anos");
        System.out.println("Gênero: " + genero);
        System.out.println("Ocupacao: " + ocupacao + ANSI_RESET);
        if(arcana == null){
            System.out.println(ANSI_BLUE + "Não tem arcana!" + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_BLUE + "Arcana: " + arcana + "\n" + ANSI_RESET);
        }
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
