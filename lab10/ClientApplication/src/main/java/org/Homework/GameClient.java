package org.Homework;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port
        try (
                Scanner scanner = new Scanner(System.in);
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            while (true) {
                System.out.print("Enter a command: ");
                String input = scanner.nextLine();
                out.println(input);

                // Read the response from the server
                StringBuilder response = new StringBuilder();
                char[] cbuf = new char[1024];
                int charsRead;
                while ((charsRead = in.read(cbuf, 0, cbuf.length)) != -1) {
                    response.append(cbuf, 0, charsRead);
                    if (charsRead < cbuf.length) {
                        break;
                    }
                }

                System.out.println(response.toString());

                if (response.toString().contains("Game ended") || response.toString().contains("Server stopped")) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}
