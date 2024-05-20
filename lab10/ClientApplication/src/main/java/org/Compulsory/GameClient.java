package org.Compulsory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8100; // The server's port
        try (
                Scanner scanner = new Scanner(System.in);
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
            while (true) {

                // Prompt the user for input
                System.out.print("Enter something: ");

                // Read input from stdin
                String input = scanner.nextLine();

                if (input.equals("exit")) {
                    out.println("stop");
                }
                else{
                    out.println(input);
                }
                String raspuns = in.readLine();
                System.out.println(raspuns);
                if (raspuns.equals("Server stopped")) break;
            }

        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}
