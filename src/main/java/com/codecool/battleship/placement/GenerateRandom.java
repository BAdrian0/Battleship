package com.codecool.battleship.placement;

import java.util.Random;

public class GenerateRandom {

    public static int randomCoordinates(int oceanSize){
        return (int) Math.floor(Math.random()*(oceanSize));
    }


    private static final Random rand = new Random();
    public static Direction randomDirection(){
        Direction[] directions = Direction.values();
        return directions[rand.nextInt(directions.length)];
    }

}

