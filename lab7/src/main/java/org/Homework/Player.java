package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Player implements Runnable {
    private String name;
    private Game game;
    private boolean running = true;
    private List<Token> tiles = new ArrayList<>();
    private List<Token> maximalSequence = new ArrayList<>();
    int points = 0;

    Player(String name) {
        this.name = name;
    }


    public void getLargestSequence(Token token) {
        if (maximalSequence.size() == 0) {
            maximalSequence.add(token);
            points++;
        } else {
            List<Token> candidateSequence = new ArrayList<>(maximalSequence);
            candidateSequence.add(token);

            // Check if the candidate sequence forms a closed loop
            if (isClosedLoop(candidateSequence)) {
                // If the candidate sequence is longer than the current maximal sequence, update it
                if (candidateSequence.size() > maximalSequence.size()) {
                    maximalSequence = candidateSequence;
                    points=candidateSequence.size();
                }
            } else {
                // If the candidate sequence is not a closed loop, try to extend it
                for (int i = 0; i < maximalSequence.size(); i++) {
                    Token lastToken = maximalSequence.get(i);
                    if (lastToken.getNumber2() == token.getNumber1()) {
                        List<Token> extendedSequence = new ArrayList<>(maximalSequence.subList(0, i + 1));
                        extendedSequence.add(token);
                        points++;
                        if (isClosedLoop(extendedSequence) && extendedSequence.size() > maximalSequence.size()) {
                            maximalSequence = extendedSequence;
                        }
                    }
                }
            }
        }
    }

    private boolean isClosedLoop(List<Token> sequence) {
        return sequence.get(sequence.size() - 1).getNumber2() == sequence.get(0).getNumber1();
    }


    public void run() {
        while (running && game.isGameInProgress()) {
            try {
                game.waitForTurn(this); // Wait for player's turn
//                Thread.sleep(1000);
                List<Token> list = game.getBag().extractTiles(1);
                if (!list.isEmpty()) {
                    tiles.addAll(list);
                    System.out.println(name + " " + tiles);

                    // Call getLargestSequence method with the newly received token
                    Token newToken = list.get(list.size()-1);
                    getLargestSequence(newToken);
                    game.nextTurn(); // Move to the next player's turn
                } else {
                    running = false;
                    game.setGameInProgress(false);
                    game.nextTurn(); // Notify all players that the game is over
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }


}