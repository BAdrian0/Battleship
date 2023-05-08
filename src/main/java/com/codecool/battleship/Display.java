package com.codecool.battleship;

import java.util.Arrays;

public class Display{


    public void displayWelcomeMessage (){
        System.out.println("Battleship");
        System.out.println();
    }

    public void displayGameModeOptions (){
        System.out.println("1. New game\n2. High scores\n3. Exit");
        System.out.println();
    }

    public void displayInvalidChoiceMessage() {
        System.out.println("Invalid choice!\n" );
    }

    public void askForInput() {
        System.out.println("Enter your choice: ");
    }

    public void askForPlayerName() {
        System.out.println("Player name: ");
    }

    public void askForPlaceCoordinates(ShipType shipType) {
        System.out.println("Place ship " + shipType.name() + " (length " + shipType.getLength() + ") to: ");
    }

    public void getBoatDirection() {
        System.out.println("Choose direction (N, S, E, W): ");
    }

    public void askForShootCoordinates() {
        System.out.println("Shoot to: ");
    }

    public void displayPlayerTurn(String name) {
        System.out.println();
        System.out.println(name + "'s turn");
        System.out.println();
    }

    public void displayIncorrectName() {
        System.out.println("Doesn't look like a name! Type your name here: ");
    }

    public void askForBoardSize() {
        System.out.println();
        System.out.println("Choose the board size: ");
    }

    public void displayBoard(int boardSize, BoardFactory board, String action, String name) {
        System.out.println(board.boardToString(boardSize, action, name));
    }

    public void askForPlacement() {
        System.out.println("Choose placement ship method:\n1. Manual\n2. Random");
    }


    public void hitMessage() {
        System.out.println();
        System.out.println("You hit!");
    }

    public void missedMessage() {
        System.out.println();
        System.out.println("You missed!");
    }

    public void sunkMessage() {
        System.out.println();
        System.out.println("You sunk a boat!");
    }

    public void displayWinningMessage(Player player) {
        System.out.println();
        System.out.println("Congratulation " + player.getPlayerName() +" you won!");
        System.out.println();
    }
}
