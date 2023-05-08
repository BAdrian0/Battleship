package com.codecool.battleship;


import java.util.concurrent.TimeUnit;


public class Game {
    public Display display = new Display();
    public Input input = new Input();
    public Square square;

    public Ship ship;


    public void play() throws InterruptedException {
        BoardFactory boardPlayer1 = new BoardFactory();
        String namePlayer1 = input.getNameForPlayer();
        Player player1 = new Player(namePlayer1, boardPlayer1);
        BoardFactory boardPlayer2 = new BoardFactory();
        Player player2 = new Player(input.getNameForPlayer(), boardPlayer2);
        Player player = player1;
        BoardFactory board = boardPlayer1;
        int boardSize = input.askForBoardSize();
        boardPlayer1.setOcean(boardSize);
        boardPlayer2.setOcean(boardSize);
        ShipType[] shipTypes = ShipType.values();
//        ShipType[] shipTypes = {ShipType.DESTROYER};

        for (ShipType shipType : shipTypes) {
            Ship shipPlayer1 = new Ship(shipType);
            Ship shipPlayer2 = new Ship(shipType);
            player1.getShipList().add(shipPlayer1);
            player2.getShipList().add(shipPlayer2);
        }
        int repeat = 0;
        do {
            repeat += 1;
            display.displayPlayerTurn(player.getPlayerName());
            display.displayBoard(boardSize, board, "place", player.getPlayerName());
            int placementMethod = input.askForPlacementMethod();
            if (placementMethod == 1) {
                for (Ship ship : ((player == player1) ? player1.getShipList() : player2.getShipList())) {
                    int[] coordinates;
                    int x;
                    int y;
                    boolean placement;
                    do {
                        coordinates = input.getValidCoordinates(boardSize, "place", ship.getShipType());
                        x = coordinates[0];
                        y = coordinates[1];
                        placement = board.manualPlacement(ship, x, y, input.getDirection());
                        display.displayBoard(boardSize, board, "place", player.getPlayerName());
                    } while (!placement);

                }
            } else if (placementMethod == 2) {
                for (Ship ship : ((player == player1) ? player1.getShipList() : player2.getShipList())) {
                    board.randomPlacement(ship, boardSize);
                }
                display.displayBoard(boardSize, board, "place", player.getPlayerName());
            }
            player = (player == player1) ? player2 : player1; // switch player
            board = (player == player2) ? boardPlayer2 : boardPlayer1; // switch player
        } while (!(repeat == 2));


        do {
            board = (player == player1) ? boardPlayer2 : boardPlayer1; // switch player
            display.displayPlayerTurn(player.getPlayerName());
            display.displayBoard(boardSize, board, "shoot", (player == player1) ? player2.getPlayerName() : player1.getPlayerName());
            int[] coordinates = input.getValidCoordinates(boardSize, "shoot", ShipType.CARRIER);
            int x = coordinates[0];
            int y = coordinates[1];
            if (board.ocean[y][x].squareStatus.GetCharacter() == 'S') {
                square = board.ocean[y][x];
                board.ocean[y][x].squareStatus = SquareStatus.HIT;

                for (Ship ship : (player == player1) ? player2.getShipList() : player1.getShipList()) {
                    for (int i = 0; i < ship.getSquareList().size(); i++) {
                        if (ship.getSquareList().get(i).getX() == x && ship.getSquareList().get(i).getY() == y) {
                            ship.getSquareList().get(i).squareStatus = SquareStatus.HIT;
                            ship.checkAndSetSunk(board);
                        }
                    }
                }
                display.displayBoard(boardSize, board, "shoot",
                        (player == player1) ? player2.getPlayerName() : player1.getPlayerName());
                display.hitMessage();
                TimeUnit.SECONDS.sleep(1);
            } else {
                board.ocean[y][x].squareStatus = SquareStatus.MISSED;
                display.displayBoard(boardSize, board, "shoot",
                        (player == player1) ? player2.getPlayerName() : player1.getPlayerName());
                display.missedMessage();
//                TimeUnit.SECONDS.sleep(1);
            }
            player = (player == player1) ? player2 : player1; // switch player
        } while (player.isAlive((player == player1) ? player1.getShipList() : player2.getShipList()));
        if (!player.isAlive((player == player1) ? player1.getShipList() : player2.getShipList())){
            display.displayWinningMessage((player == player1) ? player2 : player1);
        }
    }
}
