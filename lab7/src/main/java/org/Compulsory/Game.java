package org.Compulsory;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    private final int n;
    private final Bag bag;
    private final List<Player> players = new ArrayList<>();

    public Game(int n)
    {
        this.n = n;
        this.bag=new Bag(n);
    }

    public void addPlayer(Player player)
    {
        players.add(player);
        player.setGame(this);
    }

    public void play()
    {
        for(Player player:players)
        {
            Thread playerThread=new Thread(player);
            playerThread.start();
        }
    }

    public synchronized Token extractToken()
    {
        return bag.extractToken();
    }

    public Bag getBag()
    {
        return bag;
    }
}
