package src.entity;
import java.io.Serializable;
import java.util.Objects;

/**
 * classe para representar um veiculo
 * @author João Gonçalo
 */
public class Vehicle implements Comparable<Vehicle>, Serializable{
    private String carPlate;    //  Placa do carro
    private String renavam;     //  Renavam do carro
    private Driver driver;      //  Motorista do carro
    public String model;        //  Modelo do carro
    public int yearProduction;  //  Ano de fabricação do carro

    /**
     * Construtor com todos os atributos de veiculo
     * @param carPlate Placa do carro
     * @param renavam  Renavam do carro
     * @param driver   Motorista do carro
     * @param model    Modelo do carro
     * @param yearProduction  Ano de fabricação do carro
     */
    public Vehicle(String carPlate, String renavam, Driver driver, String model, int yearProduction) {
        this.carPlate = carPlate;
        this.renavam = renavam;
        this.driver = driver;
        this.model = model;
        this.yearProduction = yearProduction;
    }

    public Vehicle(String renavam) {
        this.carPlate = "";
        this.renavam = renavam;
        this.driver = null;
        this.model = "";
        this.yearProduction = 0;
    }


    public Vehicle() {
    }


    //Getters & Setters
    public String getCarPlate() {
        return this.carPlate;
    }

    public void setCarPlate(String carPlate) {
        try {
            carPlate = carPlate.toUpperCase();
            if(carPlate.length() != 7)
                throw new Exception("Placa com quantidade inválida de caractéres");
            if(!carPlate.matches("[A-Z]{3}[0-9][A-Z][0-9]{2}"))
                throw new Exception("Placa não respeita o padrão Mercosul [ABC1D23]");
            this.carPlate = carPlate;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRenavam() {
        return this.renavam;
    }

    public void setRenavam(String renavam) {
        try {
            if(renavam.length() != 11)
                throw new Exception("Renavam não possui os 11 digitos necessários");
            if(!renavam.matches("\\d+"))
                throw new Exception("Renavam inválido, Renavam só pode conter números");
            this.renavam = renavam;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearProduction() {
        return this.yearProduction;
    }

    public void setYearProduction(int yearProduction) {
        this.yearProduction = yearProduction;
    }

    @Override
    public int compareTo(Vehicle vehicle){
        Double renavam_1 = Double.parseDouble(this.renavam);
        Double renavam_2 = Double.parseDouble(vehicle.getRenavam());

        return Double.compare(renavam_1, renavam_2);
    }


    // Equals & Hash
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Vehicle)) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(carPlate, vehicle.carPlate) && Objects.equals(renavam, vehicle.renavam) && Objects.equals(driver, vehicle.driver) && Objects.equals(model, vehicle.model) && yearProduction == vehicle.yearProduction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carPlate, renavam, driver, model, yearProduction);
    }
    


    @Override
    public String toString() {
        return "{" +
            " carPlate='" + getCarPlate() + "'" +
            ", renavam='" + getRenavam() + "'" +
            ", driver='" + getDriver().getName() + "'" +
            ", model='" + getModel() + "'" +
            ", yearProduction='" + getYearProduction() + "'" +
            "}";
    }


}