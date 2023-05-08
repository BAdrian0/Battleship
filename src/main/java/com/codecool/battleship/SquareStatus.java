package com.codecool.battleship;

public enum SquareStatus {

        EMPTY, SHIP, HIT, MISSED, SUNK, NEARBY;


        //Each SquareStatus has a unicode character that can be used for printing it out.
        public char GetCharacter(){
            if (this == SquareStatus.EMPTY){
                    return '.';
            } else if (this == SquareStatus.HIT){
                    return 'H';
            } else if (this == SquareStatus.SHIP) {
                    return 'S';
            }  else if (this == SquareStatus.NEARBY) {
                return '.';
            } else if (this == SquareStatus.MISSED) {
                return 'M';
            } else {
                    return 'X';
            }
        }


}
