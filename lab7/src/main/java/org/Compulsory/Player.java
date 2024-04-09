package org.Compulsory;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable
{
    private String name;
    private Game game;
    private boolean running;
    private List<Token> tokens=new ArrayList<>();
    private int n;
    private int maxTokenNumber;
    private int minTokenNumber;
    private int setLength=0;

    public void setGame(Game game)
    {
        this.game = game;
    }

    public Player(String name, int n)
    {
        this.name = name;
        this.n=n;
        maxTokenNumber=0;
        minTokenNumber=n;
    }

    public int getMaxTokenNumber() // contributes to determining winning condition
    {
        return maxTokenNumber;
    }

    public int getMinTokenNumber() // contributes to determining winning condition
    {
        return minTokenNumber;
    }

    public void setSetLength()
    {
        setLength=maxTokenNumber-minTokenNumber+1;
    }

    public void run()
    {
        running=true;
        while(running)
        {
            // add to the tokens list each extracted token
            Token token=game.extractToken();

            if(token==null)
            // stopping condition defined within the problem requirements
            {
                running=false; // the end of the game has been reached
            }
            else
            {
                tokens.add(token);
                System.out.println(name+" extracted token: "+token);
                System.out.println(game.getBag());

                int number1 = token.getNumber1();
                int number2 = token.getNumber2();
                if(number1==n)
                {
                    maxTokenNumber=n;
                    minTokenNumber=1;
                }
                else
                {
                    if(maxTokenNumber<number1)
                    {
                        maxTokenNumber=number1;
                    }
                    if(maxTokenNumber<number2)
                    {
                        maxTokenNumber=number2;
                    }
                    if(minTokenNumber>number1)
                    {
                        minTokenNumber=number1;
                    }
                    if(minTokenNumber>number2)
                    {
                        minTokenNumber=number2;
                    }
                }

                setSetLength();
                System.out.println(name+" has "+setLength+" tokens");

                if(setLength==n)
                // stopping condition defined within the problem requirements
                {
                    running=false;
                }
            }

            try
            {
                Thread.sleep(1000); // waits one second before extracting again
                // so that the other players have a chance to extract as well
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}