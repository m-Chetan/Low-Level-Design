package SnakeAndLadder;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    int numberOfDice;
    int min = 1;
    int max = 6;

    Dice(int numberOfDice){
        this.numberOfDice = numberOfDice;
    }

    public int rollDice(){
        int diceCount = 0;
        int totalCount = 0;

        while(diceCount < numberOfDice){
            totalCount += ThreadLocalRandom.current().nextInt(min,max+1);
            diceCount++;
        }
        return totalCount;
    }
}
