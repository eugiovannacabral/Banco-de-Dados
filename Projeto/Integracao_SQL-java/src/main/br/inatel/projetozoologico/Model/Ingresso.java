package main.br.inatel.projetozoologico.Model;

public class Ingresso {

    private int id_Ingresso;
    private String data;
    private String tipo;
    private double preco;
    private Visitante visitante;

    public Ingresso(int id_Ingresso, String data, String tipo, double preco){
        this.id_Ingresso = id_Ingresso;
        this.data = data;
        this.tipo = tipo;
        this.preco = preco;
    }

    public Ingresso(String data, String tipo, double preco){
        this.data = data;
        this.tipo = tipo;
        this.preco = preco;
    }

    public void mostrarInfosIngresso(){
        System.out.println("\n=== DADOS DO INGRESSO ===");
        System.out.println("ID: " + id_Ingresso);
        System.out.println("Data: " + data);
        System.out.println("Tipo: " + tipo);
        System.out.println("Preço: " + preco);
    }

    // ======= Getters e Setters =======

    // ------- IdIngresso -------
    public int getIdIngresso() {
        return id_Ingresso;
    }

    public void setIdIngresso(int id_Ingresso) {

        this.id_Ingresso = id_Ingresso;
    }

    // ------- Data -------
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    // ------- tipo -------
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // ------- Preco -------
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // ------- Visitante -------
    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }
}