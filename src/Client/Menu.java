package src.Client;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

import src.Server.Protocol;
import src.Server.ProtocolHashTable;
import src.Server.Compression.Huffman.HuffmanCompress;
import src.Server.Compression.Huffman.Transport;
import src.entity.Driver;
import src.entity.Vehicle;


public class Menu {
    private Protocol server;
    //Inicializa o servidor de interação
    public Menu(Protocol server) {
        this.server = server;
    }
    
    //Função para limpar a tela
    public static void limpatela() {System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n "); }


    //Metodo para exibir as opções do menu
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
    
    //Metodo para executar instrução de acordo com a opção selecionada
    public void run(int option){
        Scanner scn = new Scanner(System.in);
        switch(option){
            //Exit
            case 0:{
                limpatela();
                System.out.println("Fechando...");
                break;
            }
            //Insert
            case 1:{                
                try {
                    limpatela();
                    
                    //Inicializa veículo e condutor para inserção
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
                    String yearString = scn.nextLine(); 
                    vehicle.setYearProduction(Integer.parseInt(yearString));
                    //Limpando o buffer
                    
                    System.out.print("\n.Nome do condutor: ");
                    driver.setName(scn.nextLine());
                    
                    System.out.print("\n.Cpf do condutor(apenas digitos): ");
                    driver.setCpf(scn.nextLine());
                    vehicle.setDriver(driver);
                    
                    //Inserindo de acordo com protocolo do servidor
                    HuffmanCompress compressor = new HuffmanCompress();
                    Character[] compressedVehicle = compressor.compress(vehicle.toString());
                    server.insert(compressedVehicle, compressor);
                } catch(NumberFormatException e){
                    e.printStackTrace();
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
            //Update
            case 2:{
                try {
                    limpatela();
                    Vehicle vehicle = new Vehicle();
                    Driver driver = new Driver();
                    HuffmanCompress compressor = new HuffmanCompress();

                    System.out.println("======Atualização de veiculo======");
                    System.out.print(".Renavam(Chave de busca): ");

                    String renavam = scn.nextLine();
                        //Garantindo que veiculo existe
                    if(this.server.search(compressor.compress(renavam), compressor).getMessage() != null){
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
                        vehicle.setRenavam(renavam);
                        
                        //Atualizando de acordo com protocolo do servidor
                        HuffmanCompress newCompressor = new HuffmanCompress();
                        server.update(newCompressor.compress(vehicle.toString()), newCompressor);
                        }
                        else{
                            throw new Exception("Nenhum veículo encontrado com esse Renavam");
                        }
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
                    Vehicle result = new Vehicle();
                    HuffmanCompress compressor = new HuffmanCompress();
                    Transport trasporteReceive = server.search(compressor.compress(scn.nextLine()), compressor);
                    String vehicleString = trasporteReceive.getCompressor().decompress(trasporteReceive.getMessage());
                    result = result.stringToVehicle(vehicleString);
                    if(vehicleString.isBlank())
                        throw new Exception("Nenhum dado encontrado");
                    else{
                        result = result.stringToVehicle(vehicleString);  
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
                    Vehicle result = new Vehicle();
                    HuffmanCompress compressor = new HuffmanCompress();
                    Transport trasporteReceive = server.searchByPlate(compressor.compress(scn.nextLine()), compressor);
                    String vehicleString = trasporteReceive.getCompressor().decompress(trasporteReceive.getMessage());
                    if(vehicleString.isBlank())
                        throw new Exception("Nenhum dado encontrado");
                    else{
                        result = result.stringToVehicle(vehicleString);  
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
                try {
                    limpatela();
                    
                    System.out.println("======Remoção de veiculo======");
                    System.out.print(".Renavam(Chave de busca): ");

                    HuffmanCompress compressor = new HuffmanCompress();

                    server.remove(compressor.compress(scn.nextLine()),  compressor);
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
                    List<Transport> transports = server.listAll();
                    for(Transport transport : transports){
                        String str = transport.getCompressor().decompress(transport.getMessage());
                        Vehicle vehicle = new Vehicle();
                        vehicle = vehicle.stringToVehicle(str);
                        System.out.println("\n------------------------------------");
                        System.out.println("Placa---> " + vehicle.getCarPlate());
                        System.out.println("Renavam-> " + vehicle.getRenavam());
                        System.out.println("Condutor->" + vehicle.getDriver().getName() + "     Cpf do condutor-> " + vehicle.getDriver().getCpf());
                        System.out.println("Modelo--> " + vehicle.getModel());
                        System.out.println("Ano de fabricação-> " + vehicle.getYearProduction());
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
                    Transport transport = server.size();
                    String cars = transport.getCompressor().decompress(transport.getMessage()).toString();
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
