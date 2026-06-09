package main.br.inatel.projetozoologico.Model;

public class Alimento {

    private int idAlimento;
    private String tipoDeDieta;
    private int estoque;
    private String nome;

    public Alimento(int idAlimento, String tipoDeDieta, int estoque, String nome) {
        this.idAlimento = idAlimento;
        this.tipoDeDieta = tipoDeDieta;
        this.estoque = estoque;
        this.nome = nome;
    }

    public void mostraInfoAlimento() {

        System.out.println("\n=== DADOS DO ALIMENTO ===");

        System.out.println("ID: " + idAlimento);
        System.out.println("Nome: " + nome);
        System.out.println("Tipo de dieta: " + tipoDeDieta);

        if (estoque <= 0) {
            System.out.println("Produto sem estoque!");
        } else {
            System.out.println("Quantidade em estoque: " + estoque);
        }
    }

    public int getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }

    public String getTipoDeDieta() {
        return tipoDeDieta;
    }

    public void setTipoDeDieta(String tipoDeDieta) {
        this.tipoDeDieta = tipoDeDieta;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}