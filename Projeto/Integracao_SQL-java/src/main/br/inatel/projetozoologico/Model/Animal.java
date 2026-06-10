package main.br.inatel.projetozoologico.Model;

public class Animal {

    private int idAnimal;
    private String nome;
    private String sexo;
    private int idade;
    private String especie;
    private double peso;

    private Habitat habitat; //Relacionamento de 1:N com o Habitat

    public Animal(int idAnimal,String nome, String sexo, int idade, String especie, double peso) {
        this.idAnimal = idAnimal;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.especie = especie;
        this.peso = peso;
    }

    public Animal(String nome, String sexo, int idade, String especie, double peso) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.especie = especie;
        this.peso = peso;
    }

    public void mostraInfoAnimal() {
    System.out.println("\n=== DADOS DO ANIMAL ===");

    System.out.println("ID: " + idAnimal);
    System.out.println("Nome: " + nome);
    System.out.println("Espécie: " + especie);
    System.out.println("Sexo: " + sexo);
    System.out.println("Idade: " + idade + " anos");
    System.out.println("Peso: " + peso + " kg");

    // para verificar se o animal pertence a um habitat
        if (habitat == null) {
            System.out.println("Habitat não cadastrado!");
        } else {
            habitat.mostraInfoHabitat();
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

     public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

}
