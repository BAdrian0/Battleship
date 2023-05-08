package com.codecool.battleship;

public class Square {

    private int X;

    private int Y;

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    SquareStatus squareStatus;

    public SquareStatus getSquareStatus(){
        return squareStatus;
    }

    public void setSquareStatus(SquareStatus squareStatus) {
        this.squareStatus = squareStatus;
    }

    public Square(int y, int x, SquareStatus status){
        this.X = x;
        this.Y = y;
        this.squareStatus = status;
    }

    public char graphicalSquareStatus() {
        return switch (this.squareStatus) {
            case EMPTY, HIT, SHIP, MISSED, SUNK, NEARBY -> this.squareStatus.GetCharacter();
        };
    }

}
