package Server;

import java.rmi.Remote;

import entity.Vehicle;

public interface Protocol extends Remote {
    public void insert(Vehicle vehicle);
    public boolean remove(Vehicle vehicle);
    public String listAll();
    public Vehicle search(String renavam);
    public Vehicle searchByPlate(String plate);
    public void update(Vehicle newVehicle, String renavam);
    public int size();
}
