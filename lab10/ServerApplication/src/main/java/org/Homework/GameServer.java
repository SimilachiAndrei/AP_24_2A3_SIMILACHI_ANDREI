package org.Homework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {
    public static final int PORT = 8100;
    private ServerSocket serverSocket;
    private List<Game> rooms = new ArrayList<>();
    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public GameServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
        while (true) {
            System.out.println("Waiting for a client...");
            Socket socket = serverSocket.accept();
            ClientThread client = new ClientThread(socket, rooms);
            threadPool.execute(client);
        }
    }

    public static void main(String[] args) throws IOException {
        new GameServer();
    }
}