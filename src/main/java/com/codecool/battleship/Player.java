package com.codecool.battleship;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String playerName;
    private final Board playerBoard;

    private final List<Ship> shipList;

    private Input input = new Input();

    public List<Ship> getShipList() {
        return shipList;
    }

    public Player(String playerName, Board playerBoard) {
        this.playerName = playerName;
        this.playerBoard = playerBoard;
        this.shipList = new ArrayList<>();
    }

    public String getPlayerName() {
        return this.playerName;
    }


    public boolean isAlive(List<Ship> shipList) {
        boolean result = false;
      for (Ship ship : shipList) {
          if(!ship.isSunk()){
              result = true;
          }
       }
      return result;
    }

}
