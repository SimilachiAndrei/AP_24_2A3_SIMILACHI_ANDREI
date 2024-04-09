package org.Compulsory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bag
{
    private final List<Token> tokens;

    public Bag(int n)
    {
        this.tokens=generateTokens(n);
    }

    private List<Token> generateTokens(int n) // generate the n tokens
    {
        List<Token> generatedTokens = new ArrayList<>();

        for(int i=1; i<=n; i++)
        {
            int number1=i;
            int number2;
            if(i==n)
            {
                number2=1; // creates the last pair, (n, 1)
            }
            else
            {
                number2=i+1; // creates the pair (i, i+1)
            }
            Token token=new Token(number1, number2);
            generatedTokens.add(token);
        }
        Collections.shuffle(generatedTokens); // sorts the list randomly

        return generatedTokens;
    }

    public synchronized Token extractToken()
    {
        if(!tokens.isEmpty()) // if there are still tokens left, then extracting is possible
        {
            return tokens.remove(0); // removes the first element (List is a FIFO structure)
        }
        return null; // the bag is empty
    }

    @Override
    public String toString()
    {
        return "Bag{" +
                "tokens=" + tokens +
                '}';
    }
}
