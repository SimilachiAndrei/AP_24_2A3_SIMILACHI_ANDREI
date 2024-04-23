package org.example;

import lombok.Getter;

import java.util.Objects;

@Getter

public class Token {
    private final int number1;
    private final int number2;

    public Token(int number1, int number2)
    {
        this.number1 = number1;
        this.number2 = number2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return number1 == token.number1 && number2 == token.number2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number1, number2);
    }

    @Override
    public String toString() {
        return "Token{" +
                "number1=" + number1 +
                ", number2=" + number2 +
                '}';
    }
}
