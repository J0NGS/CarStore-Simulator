package src.Server;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) throws IOException {
        try {
            Protocol protocol = new ProtocolImpl();
            String name = "rmi://localhost/BDVehicle";
            
            System.out.println("started...");
			LocateRegistry.createRegistry(1099);
            Naming.rebind(name, protocol);
            System.out.println("Waiting for requests...");
			
            // Criando um arquivo para redirecionar a saída
            File logFile = new File("E:/Documentos/GitHub/joao-goncalo-pratica-off-1-main/src/Server/serverLog.txt");
            PrintStream printStream = new PrintStream(new FileOutputStream(logFile));
            System.setOut(printStream); // Redirecionando a saída padrão
            System.out.println("started...");
            System.out.println("Waiting for requests...");

            protocol.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}