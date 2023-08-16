package Server;

import java.io.*;
import java.net.*;
import AVL.AVLTree; // Importe a sua classe AVLTree aqui
import entity.Vehicle;

public class Server {
    private AVLTree<Vehicle> database; // Instância da base de dados AVLTree
    private ServerSocket serverSocket;
    private boolean isRunning = true;

    public Server(int port) {
        // Inicialize a base de dados AVLTree
        database = new AVLTree<>();

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is running on port " + port);
        } catch (IOException e) {
            System.err.println("Error creating server socket: " + e.getMessage());
            System.exit(1);
        }
    }

    public void start() {
        while (isRunning) {
            try {
                Socket clientSocket = serverSocket.accept();
                // Crie uma nova thread para lidar com cada cliente
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            } catch (IOException e) {
                System.err.println("Error accepting client connection: " + e.getMessage());
            }
        }
    }

    // Classe interna para lidar com os clientes em threads separadas
    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                System.err.println("Error creating client handler: " + e.getMessage());
            }
        }

        @Override
        public void run() {
            // Lógica para lidar com as requisições do cliente
            try {
                // Leia a mensagem do cliente
                String clientMessage = in.readLine();
                
                // Implemente a lógica de processamento da mensagem
                // Você pode usar estruturas de controle para identificar a operação solicitada
                // e executar as ações apropriadas na base de dados AVLTree
                
                // Envie uma resposta de volta ao cliente
                String response = "Response from server";
                out.println(response);
                
                // Feche os fluxos e o socket
                out.close();
                in.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error handling client request: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        int port = 12345; // Porta do servidor
        Server server = new Server(port);
        server.start();
    }
}
