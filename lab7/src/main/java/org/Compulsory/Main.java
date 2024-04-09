package org.Compulsory;

public class Main
{
    public static void main(String[] args)
    {
        int n=10;
        Game game = new Game(n);
        game.addPlayer(new Player("Player 1", n));
        game.addPlayer(new Player("Player 2", n));
        game.addPlayer(new Player("Player 3", n));
        game.play();
    }
}