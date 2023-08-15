package entity;
import java.util.Objects;

/**
 * Classe para representar o condutor de um veiculo
 * @author João Gonçalo
 */
public class Driver {
    public String name;
    private String cpf;


    /**
     * Construtor com todos os atributos de condutor
     * @param name nome do condutor
     * @param cpf  documento do condutor
     */
    public Driver(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }


    // Getters & Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    //Equals & Hash
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Driver)) {
            return false;
        }
        Driver driver = (Driver) o;
        return Objects.equals(name, driver.name) && Objects.equals(cpf, driver.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cpf);
    }
    
}
