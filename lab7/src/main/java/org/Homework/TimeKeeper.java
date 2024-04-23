package org.example;

import lombok.Getter;

public class TimeKeeper extends Thread {
    private final Game game;
    private final long timeLimitMillis;
    @Getter
    private boolean timeUp = false;

    public TimeKeeper(Game game, long timeLimitMillis) {
        this.game = game;
        this.timeLimitMillis = timeLimitMillis;
        setDaemon(true); // Set the thread as a daemon to automatically terminate when other non-daemon threads finish
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeLimitMillis) {
            // Display running time of the game
            System.out.println("Game running for: " + (System.currentTimeMillis() - startTime) + " ms");
            try {
                Thread.sleep(1000); // Check every second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // If time limit is reached, stop the game
        timeUp = true;
        game.setGameInProgress(false);
        synchronized (game.getTurnLock()) {
            game.getTurnLock().notifyAll(); // Notify all players that the game is over
        }
    }

}
