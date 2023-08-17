package src.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import src.entity.Vehicle;


public interface Protocol extends Remote {
    public void init()throws RemoteException;
    public void insert(Vehicle vehicle) throws RemoteException;
    public boolean remove(Vehicle vehicle) throws RemoteException;
    public List<Vehicle> listAll() throws RemoteException;
    public Vehicle search(String renavam) throws RemoteException;
    public Vehicle searchByPlate(String plate) throws RemoteException;
    public void update(Vehicle newVehicle) throws RemoteException;
    public int size() throws RemoteException;
}
