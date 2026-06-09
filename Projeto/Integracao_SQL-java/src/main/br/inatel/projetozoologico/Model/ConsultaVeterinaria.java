package main.br.inatel.projetozoologico.Model;

import java.sql.Date;

public class ConsultaVeterinaria {

    private int idConsultaVeterinaria;
    private Date data;
    private String estadoDeSaude;

    //Relacionamento 1:N com as classes animal e funcionario
    private Animal animal;
    private Funcionario funcionario;

    public ConsultaVeterinaria(int idConsultaVeterinaria, Date data, String estadoDeSaude) {
        this.idConsultaVeterinaria = idConsultaVeterinaria;
        this.data = data;
        this.estadoDeSaude = estadoDeSaude;
    }

    public ConsultaVeterinaria(Date data, String estadoDeSaude) {
        this.data = data;
        this.estadoDeSaude = estadoDeSaude;
    }

    public void mostraInfoConsulta() {

        System.out.println("\n=== CONSULTA VETERINÁRIA ===");

        System.out.println("ID: " + idConsultaVeterinaria);
        System.out.println("Data: " + data);
        System.out.println("Estado de saúde: " + estadoDeSaude);

        if(animal != null)
            System.out.println("Animal a ser atendido: " + animal.getNome());

        if(funcionario != null)
            System.out.println("Veterinário: " + funcionario.getNome());
    }

    public int getIdConsultaVeterinaria() {
        return idConsultaVeterinaria;
    }

    public void setIdConsultaVeterinaria(int idConsultaVeterinaria) {
        this.idConsultaVeterinaria = idConsultaVeterinaria;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEstadoDeSaude() {
        return estadoDeSaude;
    }

    public void setEstadoDeSaude(String estadoDeSaude) {
        this.estadoDeSaude = estadoDeSaude;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
