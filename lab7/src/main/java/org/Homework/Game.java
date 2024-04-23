package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
public class Game {
    private final Bag bag = new Bag();
    private final List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0; // Index of the current player taking turn
    private boolean gameInProgress = false; // Indicates whether the game is in progress
    private final Object turnLock = new Object(); // Lock object for turn synchronization
    private final TimeKeeper timeKeeper;

    public Game(long time) {
        this.timeKeeper = new TimeKeeper(this,time);
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void play() {
        gameInProgress = true;
        timeKeeper.start(); // Start the timekeeper thread

        List<Thread> threads = new ArrayList<>();
        // Start player threads
        for (Player player : players) {
            Thread thread = new Thread(player);
            threads.add(thread);
            thread.start();
        }

        // Wait for all player threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (turnLock) {
            gameInProgress = false;
            turnLock.notifyAll();
        }

        if (timeKeeper.isTimeUp()) {
            System.out.println("Game time limit exceeded. Game stopped.");
        }

        determineWinner();

    }


    // Method for player threads to wait for their turn
    public void waitForTurn(Player player) throws InterruptedException {
        synchronized (turnLock) {
            if (!gameInProgress) {
                return; // Exit the method if the game is no longer in progress
            }
            while (players.indexOf(player) != currentPlayerIndex || !gameInProgress) {
                if (!gameInProgress) {
                    return; // Exit the method if the game is no longer in progress
                }
                turnLock.wait();
            }
        }
    }

    // Method to advance to the next player's turn
    public void nextTurn() {
        synchronized (turnLock) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            turnLock.notifyAll();
        }
    }

    // Main method to determine the winner after the game ends
    public void determineWinner() {
        Player winner = null;
        int maxPoints = Integer.MIN_VALUE;
        for (Player player : players) {
            if (player.getPoints() > maxPoints) {
                maxPoints = player.getPoints();
                winner = player;
            }
        }
        System.out.println("The winner is: " + winner.getName() + " with " + winner.getPoints() + " points!");
    }


    public static void main(String args[]) {
        Game game = new Game(1);
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));
        game.addPlayer(new Player("Player 3"));
        game.play();
    }
}
