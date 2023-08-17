package src.Client;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

import src.Server.Protocol;
import src.entity.Driver;
import src.entity.Vehicle;


public class Menu {
    private Protocol server;
    
    
    
    public Menu(Protocol server) {
        this.server = server;
    }
    
    public static void limpatela() {System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n "); }

    public void options(){
        System.out.println("---------------------------------------");
        System.out.println("1. Inserir novo veicúlo");
        System.out.println("2. Atualizar veículo existente");
        System.out.println("3. Buscar veículo por RENAVAM");
        System.out.println("4. Buscar veículo por placa");
        System.out.println("5. Remover veículo existente");
        System.out.println("6. Listar todos os veículo");
        System.out.println("7. Quantidade de veículos salvos");
        System.out.println("0. Sair");
        System.out.println("---------------------------------------");
    }
    
    public void run(int option){
        Scanner scn = new Scanner(System.in);
        switch(option){
            case 0:{
                limpatela();
                System.out.println("Fechando...");
                break;
            }
            //Insert
            case 1:{
                limpatela();
                Vehicle vehicle = new Vehicle();
                Driver driver = new Driver();
                
                System.out.println("======Cadastro de veiculo======");
                System.out.print(".Renavam: ");
                vehicle.setRenavam(scn.nextLine());
                System.out.print("\n.Placa do carro: ");
                vehicle.setCarPlate(scn.nextLine());
                System.out.print("\n.Modelo do carro: ");
                vehicle.setModel(scn.nextLine());
                System.out.print("\n.Ano de fabricação: ");
                vehicle.setYearProduction(scn.nextInt());
                scn.nextLine(); //Limpando o buffer
                System.out.print("\n.Nome do condutor: ");
                driver.setName(scn.nextLine());
                System.out.print("\n.Cpf do condutor(apenas digitos): ");
                driver.setCpf(scn.nextLine());
                vehicle.setDriver(driver);

                try {
                    server.insert(vehicle);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            }
            //Update
            case 2:{
                limpatela();
                Vehicle vehicle = new Vehicle();
                Driver driver = new Driver();
                
                System.out.println("======Atualização de veiculo======");
                System.out.print(".Renavam(Chave de busca): ");
                vehicle.setRenavam(scn.nextLine());
                System.out.println("\n.======Dados atualizados======");
                System.out.print("\n.Placa do carro: ");
                vehicle.setCarPlate(scn.nextLine());
                System.out.print("\n.Modelo do carro: ");
                vehicle.setModel(scn.nextLine());
                System.out.print("\n.Ano de fabricação: ");
                vehicle.setYearProduction(scn.nextInt());
                scn.nextLine(); //Limpando o buffer
                System.out.print("\n.Nome do condutor: ");
                driver.setName(scn.nextLine());
                System.out.print("\n.Cpf do condutor(apenas digitos): ");
                driver.setCpf(scn.nextLine());
                vehicle.setDriver(driver);
                try {
                    server.update(vehicle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            //Search
            case 3:{
                limpatela();
                System.out.println("======Busca por renavam======");
                System.out.print(".Renavam(Chave de busca): ");

                try {
                    Vehicle result = server.search(scn.nextLine());
                    if(result == null)
                        throw new Exception("Nenhum dado encontrado");
                    else{
                        System.out.println("\n------------------------------------");
                        System.out.println("Placa---> " + result.getCarPlate());
                        System.out.println("Renavam-> " + result.getRenavam());
                        System.out.println("Condutor->" + result.getDriver().getName() + "     Cpf do condutor-> " + result.getDriver().getCpf());
                        System.out.println("Modelo--> " + result.getModel());
                        System.out.println("Ano de fabricação-> " + result.getYearProduction());
                        System.out.println("------------------------------------");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
            //SearchByPlate(mais lento)
            case 4:{
                limpatela();
                System.out.println("======Busca pela placa======");
                System.out.print(".Placa do carro(Chave de busca): ");

                try {
                    Vehicle result = server.searchByPlate(scn.nextLine());
                    if(result == null)
                        throw new Exception("Nenhum dado encontrado");
                    else{
                        System.out.println("\n------------------------------------");
                        System.out.println("Placa---> " + result.getCarPlate());
                        System.out.println("Renavam-> " + result.getRenavam());
                        System.out.println("Condutor->" + result.getDriver().getName() + "     Cpf do condutor-> " + result.getDriver().getCpf());
                        System.out.println("Modelo--> " + result.getModel());
                        System.out.println("Ano de fabricação-> " + result.getYearProduction());
                        System.out.println("------------------------------------");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
            //Remove
            case 5:{
                limpatela();
                System.out.println("======Remoção de veiculo======");
                System.out.print(".Renavam(Chave de busca): ");
                Vehicle vehicle = new Vehicle(scn.nextLine());

                try {
                    server.remove(vehicle);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
            //ListAll
            case 6:{
                limpatela();
                System.out.println("======Listagem de todos os veiculos======");
                
                try {
                    List<Vehicle> list = server.listAll();
                    for(int i = 0; i < list.size(); i++){
                        System.out.println("------------------------------------");
                        System.out.println("Carro n°: " + (i + 1));
                        System.out.println("Placa---> " + list.get(i).getCarPlate());
                        System.out.println("Renavam-> " + list.get(i).getRenavam());
                        System.out.println("Condutor->" + list.get(i).getDriver().getName() + "     Cpf do condutor-> " + list.get(i).getDriver().getCpf());
                        System.out.println("Modelo--> " + list.get(i).getModel());
                        System.out.println("Ano de fabricação-> " + list.get(i).getYearProduction());
                        System.out.println("------------------------------------");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
            //countVahicle
            case 7:{
                limpatela();
                try {
                    int cars = server.size();
                    System.out.println("======Total de "+ cars +" salvos======");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
            default:{
                limpatela();
                System.out.println("OPÇÃO INVALIDA!!!!!!!!!!");
                break;
            }
        }
    }

    public Protocol getServer() {
        return this.server;
    }

    public void setServer(Protocol server) {
        this.server = server;
    }

}
