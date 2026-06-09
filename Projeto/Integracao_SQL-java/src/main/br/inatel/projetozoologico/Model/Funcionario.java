package main.br.inatel.projetozoologico.Model;

public class Funcionario {

    private int idFuncionario;
    private String nome;
    private String funcao;
    private double salario;

    public Funcionario(int idFuncionario, String nome, String funcao, double salario) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.funcao = funcao;
        this.salario = salario;
    }

    public Funcionario(String nome, String funcao, double salario) {
        this.nome = nome;
        this.funcao = funcao;
        this.salario = salario;
    }

    public void mostraInfoFuncionario() {

        System.out.println("\n=== DADOS DO FUNCIONÁRIO ===");
        System.out.println("ID: " + idFuncionario);
        System.out.println("Nome: " + nome);
        System.out.println("Função: " + funcao);
        System.out.println("Salário: R$ " + salario);
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
