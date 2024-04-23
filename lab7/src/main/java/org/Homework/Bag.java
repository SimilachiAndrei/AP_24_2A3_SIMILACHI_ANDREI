package org.example;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class Bag {
    private List<Token> tokens = new ArrayList<>();

    Bag() {
        Random rand = new Random();
        for (int i = 0; i < 9; i++) {
            Token token = new Token((Math.abs(rand.nextInt() % 100)), Math.abs(rand.nextInt() % 100));
            addToken(token);
        }
    }

    public boolean addToken(Token token) {
        if (tokens.contains(token)) return false;
        tokens.add(token);
        return true;
    }

    public synchronized List<Token> extractTiles(int howMany) {
        List<Token> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (tokens.isEmpty()) {
                break;
            }
            extracted.add(tokens.get(0));
            tokens.remove(tokens.get(0));
        }
        return extracted;
    }


    public boolean isEmpty() {
        return tokens.isEmpty();
    }
}
