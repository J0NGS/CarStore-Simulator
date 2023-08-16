import java.io.*;
import java.net.*;

public class TestClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Endereço IP do servidor
        int serverPort = 12345; // Porta do servidor

        try (Socket socket = new Socket(serverAddress, serverPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Envia uma requisição para inserção de veículo
            String request = "1 | ABC123 | 123456789 | John Doe | 12345678901 | Sedan | 2022";
            out.println(request);
            String response = in.readLine();
            System.out.println("Response from server: " + response);

            // Implemente a lógica para testar as outras operações do seu protocolo
            // Exemplo: enviar requisições para listar veículos, alterar veículo, remover veículo, etc.

        } catch (IOException e) {
            System.err.println("Error in client: " + e.getMessage());
        }
    }
}

