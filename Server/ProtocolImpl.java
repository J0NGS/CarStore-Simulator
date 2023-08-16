package Server;

import java.rmi.server.UnicastRemoteObject;

import AVL.AVLTree;
import entity.Vehicle;

public class ProtocolImpl extends UnicastRemoteObject implements Protocol {
    private AVLTree <Vehicle> tree;
    

    public ProtocolImpl() {
        this.tree = new AVLTree<>();
    }


    @Override
    public void insert(Vehicle vehicle) {
        try {
            this.tree.insert(vehicle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean remove(Vehicle vehicle) {
        try {
            this.tree.remove(vehicle);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public String listAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listAll'");
    }


    @Override
    public Vehicle search(String renavam) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }


    @Override
    public Vehicle searchByPlate(String plate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByPlate'");
    }


    @Override
    public void update(Vehicle newVehicle, String renavam) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }


    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }
    
    
    
    
}
