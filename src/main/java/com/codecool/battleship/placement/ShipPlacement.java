package com.codecool.battleship.placement;

public class ShipPlacement {
    public Direction shipDirection;

    public Position shipPosition;

    //random ship placement
    public ShipPlacement(int oceanSize){
        int x = GenerateRandom.randomCoordinates(oceanSize);
        int y = GenerateRandom.randomCoordinates(oceanSize);
        this.shipPosition = new Position(x,y);
        this.shipDirection = GenerateRandom.randomDirection();
    }

    //manual ship placement
    public  ShipPlacement(int x, int y, Direction direction){
        this.shipDirection = direction;
        this.shipPosition = new Position(x,y);
    };

}
