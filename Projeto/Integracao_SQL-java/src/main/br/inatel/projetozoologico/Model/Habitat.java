package main.br.inatel.projetozoologico.Model;

public class Habitat {

    private int idHabitat;
    private String nome;
    private String clima;
    private int lotacao;
    private String tipo;

    public Habitat(int idHabitat, String nome, String clima, int lotacao, String tipo) {
        this.idHabitat = idHabitat;
        this.nome = nome;
        this.clima = clima;
        this.lotacao = lotacao;
        this.tipo = tipo;
    }

    public void mostraInfoHabitat() {

        System.out.println("\n=== DADOS DO HABITAT ===");

        System.out.println("ID: " + idHabitat);
        System.out.println("Nome: " + nome);
        System.out.println("Clima: " + clima);
        System.out.println("Lotação: " + lotacao);
        System.out.println("Tipo: " + tipo);
    }

    public int getIdHabitat() {
        return idHabitat;
    }

    public void setIdHabitat(int idHabitat) {
        this.idHabitat = idHabitat;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}