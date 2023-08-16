package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import AVL.AVLTree;
import entity.Vehicle;

public class ProtocolImpl extends UnicastRemoteObject implements Protocol {
    private AVLTree <Vehicle> tree;
    

    public ProtocolImpl() throws RemoteException {
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
            System.out.println("Veiculo de renavam-> " + vehicle.getRenavam() + " Removido");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void listAll() {
        List<Vehicle> list = this.tree.getAllData();
        for(int i = 0; i < list.size(); i++){
            System.out.println("------------------------------------");
            System.out.println("Carro n°: " + i);
            System.out.println("Placa---> " + list.get(i).getCarPlate());
            System.out.println("Renavam-> " + list.get(i).getRenavam());
            System.out.println("Condutor->" + list.get(i).getDriver().getName() + "     Cpf do condutor-> " + list.get(i).getDriver().getCpf());
            System.out.println("Modelo--> " + list.get(i).getModel());
            System.out.println("Ano de fabricação-> " + list.get(i).getYearProduction());
            System.out.println("------------------------------------");
        }
    }


    @Override
    public Vehicle search(String renavam) {
        try {
            Vehicle vehicle = new Vehicle(renavam);
            vehicle = this.tree.search(vehicle);
            if(vehicle == null)
                throw new Exception("Nenhum elemento encontrado"); 
            return vehicle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Vehicle searchByPlate(String plate) {
        try {
            List<Vehicle> list = this.tree.getAllData();
            Vehicle vehicle = null;
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getCarPlate().equals(plate))
                    vehicle = list.get(i);
                }
            return vehicle;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void update(Vehicle newVehicle) {
        try {
            if(this.tree.search(newVehicle) ==  null){
                throw new Exception("Nenhum veiculo encontrado com o renavam [" + newVehicle.getRenavam()+ "]");
            } else{
                remove(newVehicle);
                insert(newVehicle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int size() {
        return this.tree.countNodes();
    }    
}
