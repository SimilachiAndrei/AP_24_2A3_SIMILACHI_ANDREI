package org.Homework;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

@Setter
@Getter
class ClientThread extends Thread {
    private Socket socket = null;
    private Game room;
    private List<Game> rooms;
    private BufferedReader in;
    private PrintWriter out;
    private Player boatPlacer = new Player(this);

    public ClientThread(Socket socket, List<Game> rooms) {
        this.socket = socket;
        this.rooms = rooms;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Error creating input/output streams: " + e);
        }
    }

    public void run() {
        try {
            while (true) {
                if (room != null) {
                    // Forward message to the game
                    synchronized (room.getTurnLock()) {
                        room.getTurnLock().notifyAll(); // Notify the game that a message has been received
                    }
                } else {
                    String received = in.readLine();
                    if (received.equals("stop")) {
                        out.println("Server stopped");
                        break;
                    } else if (received.startsWith("create")) {
                        String roomName = received.split(" ")[1];
                        room = new Game(roomName);
                        room.setPlayer1(this);
                        rooms.add(room);
                        out.println("Room created");
                    } else if (received.startsWith("join")) {
                        String roomName = received.split(" ")[1];
                        for (Game game : rooms) {
                            if (game.getName().equals(roomName) && game.getPlayer2() == null) {
                                game.setPlayer2(this);
                                room = game;
                                out.println("Joined room");
                                if (room.getPlayer1() != null && room.getPlayer2() != null) {
                                    // Start the game in a separate thread
                                    Thread gameThread = new Thread(room);
                                    gameThread.start();
                                }
                                break;
                            }
                        }
                    } else if (received.equals("view")) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (Game game : rooms) {
                            stringBuilder.append(room.getName()).append(" ");
                        }
                        out.println(stringBuilder.toString());
                    }
                    else if (received.startsWith("place")) {
                        String[] splitted = received.split(" ");
                        int posX = Integer.parseInt(splitted[1]);
                        int posY = Integer.parseInt(splitted[2]);
                        char dir = splitted[3].charAt(0);
                        int boatSize = Integer.parseInt(splitted[4]);

                        String result = boatPlacer.placeBoat(posX, posY, dir, boatSize);
                        out.println(result);
                    }
                    else {
                        out.println("Server has received command " + received);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}