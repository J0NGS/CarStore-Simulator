package src.Server;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import src.Server.ProtocolHashTable.HashTableType;

public class Server {
    public static void main(String[] args) throws IOException {
        try {
            //Protocolo implementado
            Protocol protocol = new ProtocolHashTable(HashTableType.EXTERNAL_CHAINING);
            //Endereço
            String name = "rmi://localhost/BDVehicle";
            
			LocateRegistry.createRegistry(1099);
            //Registrando e associando o protocolo
            Naming.rebind(name, protocol);
            
            System.out.println("started...");
            System.out.println("Waiting for requests...");
			
            // Criando um arquivo para redirecionar a saída
            File logFile = new File("E:/Documentos/Projetos/joao-goncalo-pratica-off-1-main/arquivos/serverLog.txt");
            PrintStream printStream = new PrintStream(new FileOutputStream(logFile));
            
            // Redirecionando a saída padrão
            System.setOut(printStream); 
            
            System.out.println("started...");
            System.out.println("Waiting for requests...");

            //Inicio do protocolo do servidor adicionando 50 carros
            protocol.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}