package com.codecool.battleship;


import com.codecool.battleship.placement.Direction;
import com.codecool.battleship.placement.ShipPlacement;

public class BoardFactory extends Board {


    public void randomPlacement(Ship ship, int oceanSize) {
        ShipPlacement shipPlacement;
        do {
            shipPlacement = new ShipPlacement(oceanSize);
        } while (!isPlacementOK(ship.getShipType(), shipPlacement));
        putShipOnBoard(ship, oceanSize, shipPlacement);
    }

    private void putShipOnBoard(Ship ship, int oceanSize, ShipPlacement shipPlacement) {
        if (isPlacementOK(ship.getShipType(), shipPlacement)) {
            switch (shipPlacement.shipDirection) {
                case EAST -> {
                    for (int i = 0; i < ship.getShipType().getLength(); i++) {
                        this.ocean[shipPlacement.shipPosition.y][shipPlacement.shipPosition.x + i].squareStatus =
                                SquareStatus.SHIP;
                        ship.getSquareList().add(ocean[shipPlacement.shipPosition.y][shipPlacement.shipPosition.x + i]);
                        //N on top
                        if (shipPlacement.shipPosition.y>0) {
                            this.ocean[shipPlacement.shipPosition.y-1][shipPlacement.shipPosition.x+i].squareStatus =
                                    SquareStatus.NEARBY;
                        }
                        //N under
                        if (shipPlacement.shipPosition.y<(oceanSize -1)) {
                            this.ocean[shipPlacement.shipPosition.y+1][shipPlacement.shipPosition.x+i].squareStatus =
                                    SquareStatus.NEARBY;
                        }
                    }
                    //N at left
                    if (shipPlacement.shipPosition.x>0) {
                        this.ocean[shipPlacement.shipPosition.y][shipPlacement.shipPosition.x-1].squareStatus =
                                SquareStatus.NEARBY;
                    }
                    //N at right
                    if ((shipPlacement.shipPosition.x+ ship.getShipType().getLength())<=(oceanSize -1)) {
                        this.ocean[shipPlacement.shipPosition.y][shipPlacement.shipPosition.x +
                                ship.getShipType().getLength()].squareStatus = SquareStatus.NEARBY;
                    }
                }
                case WEST -> {
                    for (int i = 0; i < ship.getShipType().getLength(); i++) {
                        this.ocean[shipPlacement.shipPosition.y][shipPlacement.shipPosition.x - i].squareStatus =
                                SquareStatus.SHIP;
                        ship.getSquareList().add(ocean[shipPlacement.shipPosition.y][shipPlacement.shipPosition.x - i]);
                        //N on top
                        if (shipPlacement.shipPosition.y>0) {
                            this.ocean[shipPlacement.shipPosition.y-1][shipPlacement.shipPosition.x-i].squareStatus =
                                    SquareStatus.NEARBY;
                        }
                        //N under
                        if (shipPlacement.shipPosition.y<(oceanSize -1)) {
                            this.ocean[shipPlacement.shipPosition.y+1][shipPlacement.shipPosition.x-i].squareStatus =
                                    SquareStatus.NEARBY;
                        }
                    }
                    //N at left
                    if ((shipPlacement.shipPosition.x- ship.getShipType().getLength())>=0) {
                        this.ocean[shipPlacement.shipPosition.y][shipPlacement.shipPosition.x -
                                ship.getShipType().getLength()].squareStatus =
                                SquareStatus.NEARBY;
                    }
                    //N at right
                    if (shipPlacement.shipPosition.x<(oceanSize -1)) {
                        this.ocean[shipPlacement.shipPosition.y][shipPlacement.shipPosition.x+1].squareStatus =
                                SquareStatus.NEARBY;
                    }
                }
                case NORTH -> {
                    for (int i = 0; i < ship.getShipType().getLength(); i++) {
                        this.ocean[shipPlacement.shipPosition.y - i][shipPlacement.shipPosition.x].squareStatus =
                                SquareStatus.SHIP;
                        ship.getSquareList().add(ocean[shipPlacement.shipPosition.y - i][shipPlacement.shipPosition.x]);
                        //N at left
                        if (shipPlacement.shipPosition.x>0) {
                            this.ocean[shipPlacement.shipPosition.y-i][shipPlacement.shipPosition.x-1].squareStatus =
                                    SquareStatus.NEARBY;
                        }
                        //N at right
                        if (shipPlacement.shipPosition.x<(oceanSize -1)) {
                            this.ocean[shipPlacement.shipPosition.y-i][shipPlacement.shipPosition.x+1].squareStatus =
                                    SquareStatus.NEARBY;
                        }
                    }
                    //N on top
                    if (shipPlacement.shipPosition.y- ship.getShipType().getLength()>=0) {
                        this.ocean[shipPlacement.shipPosition.y-
                                ship.getShipType().getLength()][shipPlacement.shipPosition.x].squareStatus =
                                SquareStatus.NEARBY;
                    }
                    //N under
                    if (shipPlacement.shipPosition.y<(oceanSize -1)) {
                        this.ocean[shipPlacement.shipPosition.y+1][shipPlacement.shipPosition.x].squareStatus =
                                SquareStatus.NEARBY;
                    }
                }
                case SOUTH -> {
                    for (int i = 0; i < ship.getShipType().getLength(); i++) {
                        this.ocean[shipPlacement.shipPosition.y + i][shipPlacement.shipPosition.x].squareStatus =
                                SquareStatus.SHIP;
                        ship.getSquareList().add(ocean[shipPlacement.shipPosition.y + i][shipPlacement.shipPosition.x]);
                        //N at left
                        if (shipPlacement.shipPosition.x>0) {
                            this.ocean[shipPlacement.shipPosition.y+i][shipPlacement.shipPosition.x-1].squareStatus =
                                    SquareStatus.NEARBY;
                        }
                        //N at right
                        if (shipPlacement.shipPosition.x<(oceanSize -1)) {
                            this.ocean[shipPlacement.shipPosition.y+i][shipPlacement.shipPosition.x+1].squareStatus =
                                    SquareStatus.NEARBY;
                        }
                    }
                    //N on top
                    if (shipPlacement.shipPosition.y>0) {
                        this.ocean[shipPlacement.shipPosition.y-1][shipPlacement.shipPosition.x].squareStatus =
                                SquareStatus.NEARBY;
                    }
                    //N under
                    if ((shipPlacement.shipPosition.y+ ship.getShipType().getLength())<=(oceanSize -1)) {
                        this.ocean[shipPlacement.shipPosition.y+
                                ship.getShipType().getLength()][shipPlacement.shipPosition.x].squareStatus =
                                SquareStatus.NEARBY;
                    }
                }
            }
        }
    }

    public boolean manualPlacement(Ship ship, int x, int y, Direction direction) {
        ShipPlacement shipPlacement;
        shipPlacement = new ShipPlacement(x, y, direction);

        if (!isPlacementOK(ship.getShipType(), shipPlacement)) {
            System.out.println("Ship outside of board or overlapping");
            return false;
        } else {
            putShipOnBoard(ship, oceanSize, shipPlacement);
            return true;
        }
    }
}
