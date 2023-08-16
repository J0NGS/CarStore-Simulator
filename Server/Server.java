package Server;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) throws IOException {
        try {
            Protocol protocol = new ProtocolImpl();
            String objName = "rmi://localhost/BDVehicle";
			
            System.out.println("started...");
			LocateRegistry.createRegistry(1099);
            Naming.rebind(objName, protocol);
            System.out.println("Waiting for requests...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}