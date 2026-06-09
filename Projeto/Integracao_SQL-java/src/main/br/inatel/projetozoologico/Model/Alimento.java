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
        System.out.println("\n=== DADOS DO Alimento ===");

        System.out.println("ID: " + idAlimento);
        System.out.println("Nome: " + nome);
        System.out.println("Dieta na qual possui este alimento: " + tipoDeDieta);

        // para verificar se tem deste alimento nno estoque
        if (estoque == null) {
            System.out.println("Alimento não cadastrado!");
        } else {
            System.out.println("Estoque deste alimento: " + estoque.getNome());
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
