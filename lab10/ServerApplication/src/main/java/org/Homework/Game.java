package org.Homework;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@Getter
@Setter
public class Game implements Runnable {
    private String name;
    private ClientThread player1;
    private ClientThread player2;
    private int turn = 1; // 1 for player1's turn, 2 for player2's turn
    private final Object turnLock = new Object(); // Lock object for synchronization
    private Integer isOn = 0; // Tracks the winner

    public Game(String name) {
        this.name = name;
    }

    public void run() {
        try {
            while (isOn == 0) {
                synchronized (turnLock) {
                    ClientThread currentPlayer = turn == 1 ? player1 : player2;
                    ClientThread opponent = turn == 1 ? player2 : player1;

                    String received = currentPlayer.getIn().readLine();

                    if (received.startsWith("shoot")) {
                        String[] splitted = received.split(" ");
                        int posX = Integer.parseInt(splitted[1]);
                        int posY = Integer.parseInt(splitted[2]);

                        String result = opponent.getBoatPlacer().shoot(posX, posY);
                        currentPlayer.getOut().println(result);

                        if (result.equals("Hit!")) {
                            // Player gets another turn
                            continue;
                        }

                        // Check if all boats are sunk
                        if (opponent.getBoatPlacer().checkAllSunk()) {
                            isOn = turn; // Set the winner
                            currentPlayer.getOut().println("You win!");
                            opponent.getOut().println("You lose!");
                            break;
                        }

                        // Switch turns
                        turn = turn == 1 ? 2 : 1;
                    }
                }
            }
            System.out.println("Player" + isOn + " wins!");
        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }
}