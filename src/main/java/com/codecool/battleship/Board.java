package com.codecool.battleship;

import com.codecool.battleship.placement.ShipPlacement;

import java.util.Objects;

public class Board {


    //The Board class has a Square[][] ocean field. This contains the squares that the board consists of.
    protected Square[][] ocean;

    int oceanSize;

    //    public void setOcean(Square[][] ocean) {
    public void setOcean(int oceanSize) {
        this.ocean = new Square[oceanSize][oceanSize];
        for (int row = 0; row < oceanSize; row++) {
            for (int col = 0; col < oceanSize; col++) {
                this.ocean[row][col] = new Square(row, col, SquareStatus.EMPTY);
            }
        }
        this.oceanSize = oceanSize;
    }

    public Square[][] getOcean() {
        return ocean;
    }


    //The Board class has an isPlacementOk() method that verifies if placement of ship is possible
    public boolean isPlacementOK(ShipType shipType, ShipPlacement shipPlacement) {

        boolean responseMargins = false;//false = ship extends over margins
        //check for margins of board
        switch (shipPlacement.shipDirection) {
            case EAST -> {
                if ((shipPlacement.shipPosition.x + shipType.getLength()) < oceanSize) {
                    responseMargins = true;//ship ok, inside margins
                }
            }
            case WEST -> {
                if ((shipPlacement.shipPosition.x - shipType.getLength()) >= 0) {
                    responseMargins = true;
                }
            }
            case NORTH -> {
                if ((shipPlacement.shipPosition.y - shipType.getLength()) >= 0) {
                    responseMargins = true;
                }
            }
            case SOUTH -> {
                if ((shipPlacement.shipPosition.y + shipType.getLength()) < oceanSize) {
                    responseMargins = true;
                }
            }
        }


        boolean responseOverlapping = false;//false = ships are ok, not overlapping

        if (!responseMargins) {
            return false;
        } else {
            //check for ship overlapping
            switch (shipPlacement.shipDirection) {
                case EAST -> {
                    for (int i = 0; i < shipType.getLength(); i++) {
                        if (this.ocean[shipPlacement.shipPosition.y][shipPlacement.shipPosition.x + i].squareStatus
                                != SquareStatus.EMPTY) {
                            responseOverlapping = true;//true = ships are overlapping
                            break;
                        }
                    }
                }
                case WEST -> {
                    for (int i = 0; i < shipType.getLength(); i++) {
                        if (this.ocean[shipPlacement.shipPosition.y][shipPlacement.shipPosition.x - i].squareStatus
                                != SquareStatus.EMPTY) {
                            responseOverlapping = true;
                            break;
                        }
                    }
                }
                case NORTH -> {
                    for (int i = 0; i < shipType.getLength(); i++) {
                        if (this.ocean[shipPlacement.shipPosition.y - i][shipPlacement.shipPosition.x].squareStatus
                                != SquareStatus.EMPTY) {
                            responseOverlapping = true;
                            break;
                        }
                    }
                }
                case SOUTH -> {
                    for (int i = 0; i < shipType.getLength(); i++) {
                        if (this.ocean[shipPlacement.shipPosition.y + i][shipPlacement.shipPosition.x].squareStatus
                                != SquareStatus.EMPTY) {
                            responseOverlapping = true;
                            break;
                        }
                    }
                }
            }
        }
        return !responseOverlapping;
    }

    public String boardToString(int boardSize, String action, String playerName){
        StringBuilder sb = new StringBuilder();
        sb.append("   ").append(playerName).append("'board");
        sb.append("\n");
        sb.append("   ");
        for (int i = 0; i < boardSize; i++) {
            sb.append("  ").append((char) ('A' + i));
        }
        sb.append("\n");
        for (int y = 0; y < boardSize; y++) {
            sb.append(String.format("%3s", y + 1));;
            for (int x = 0; x < ocean[0].length; x++) {
                if (Objects.equals(action, "place")) {
                    sb.append("  ").append(ocean[y][x].squareStatus.GetCharacter());
                } else {
                    if (ocean[y][x].squareStatus.GetCharacter() == 'H' || ocean[y][x].squareStatus.GetCharacter() == 'M' ||
                            ocean[y][x].squareStatus.GetCharacter() == 'X'){
                        sb.append("  ").append(ocean[y][x].squareStatus.GetCharacter());
                    } else {
                        sb.append("  ").append(".");
                    }
                }
            }
            sb.append("\n");
        }
        return String.valueOf(sb);
    }
}
