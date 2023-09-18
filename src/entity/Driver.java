package src.entity;
import java.io.Serializable;
import java.util.Objects;

/**
 * Classe para representar o condutor de um veiculo
 * @author João Gonçalo
 */
public class Driver implements Serializable{
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


    public Driver() {
    }


    // Getters & Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        try {
            if(name.isBlank() || name.isEmpty())
                throw new Exception("nome não pode ser vázio");
            this.name = name;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        try {
            if(!cpf.matches("[0-9]*"))
                throw new Exception("Cpf inválido, só pode conter digitos");
            if(cpf.length()!= 11)
                throw new Exception("Cpf inválido, só pode conter 11 digitos");
            this.cpf = cpf;
        } catch (Exception e) {
            e.printStackTrace();
        }
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
