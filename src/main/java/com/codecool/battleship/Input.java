package com.codecool.battleship;

import com.codecool.battleship.placement.Direction;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Input {

    private Scanner scanner;
    Display display = new Display();

    public int getGameMode() {
        int[] validAnswers = {1, 2};
        int gameMode;
        while (true) {
            scanner = new Scanner(System.in);
            display.askForInput();
            int userInput = scanner.nextInt();
            try {
                if (checkForValidAnswer(userInput, validAnswers)) {
                    gameMode = userInput;
                    break;
                } else {
                    display.displayInvalidChoiceMessage();
                }
            } catch (InputMismatchException ignored) {
            }
        }
        return gameMode;
    }

    private boolean checkForValidAnswer(int input, int[] validAnswers) {
        for (int elem : validAnswers) {
            if (elem == input) {
                return true;
            }
        }
        return false;
    }

    public String getNameForPlayer() {
        String name;
        while (true) {
            scanner = new Scanner(System.in);
            display.askForPlayerName();
            String userChoice = scanner.nextLine();
            try {
                if (userChoice.matches("[a-zA-Z]+")) {
                    name = userChoice;
                    break;
                } else {
                    display.displayIncorrectName();
                }
            } catch (InputMismatchException ignored) {
            }
        }
        return name;
    }

    public int[] getValidCoordinates(int boardSize, String action, ShipType shipType) {
        int[] coordinates = new int[2];
        int row;
        int col;
        while (true) {
            scanner = new Scanner(System.in);
            if ((checkCoordinatesForAction(action))) {
                display.askForPlaceCoordinates(shipType);
            } else {
                display.askForShootCoordinates();
            }
            String inputs = scanner.nextLine();
            if (Objects.equals(inputs, "quit")) {
                System.exit(0);
            }
            try {
                if (!Objects.equals(inputs, "")) {
                    col = inputs.substring(0, 1).toUpperCase().charAt(0) - 65;
                    row = Integer.parseInt(inputs.substring(1)) - 1;
                    if (row >= 0 && boardSize - 1 >= row && col >= 0 && boardSize - 1 >= col) {
                        coordinates[0] = col;
                        coordinates[1] = row;
                        break;
                    } else {
                        display.displayInvalidChoiceMessage();
                    }
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                display.displayInvalidChoiceMessage();
            }
        }
        return coordinates;
    }


    public Direction getDirection() {
        Direction result;
            scanner = new Scanner(System.in);
            display.getBoatDirection();
            String input = "";
            int count = 0;
            do {
                if (count>0) {System.out.println("Invalid entry!");}
                count++;
                String inputs = scanner.nextLine();
                input = inputs.trim().toUpperCase();
            } while (!input.equals("N") && !input.equals("NORTH") && !input.equals("S") &&
                    !input.equals("SOUTH") && !input.equals("E") && !input.equals("EAST") &&
                    !input.equals("W") && !input.equals("WEST"));
            result = switch (input) {
                case "N", "NORTH" -> Direction.NORTH;
                case "S", "SOUTH" -> Direction.SOUTH;
                case "E", "EAST" -> Direction.EAST;
                case "W", "WEST" -> Direction.WEST;
                default -> throw new IllegalStateException("Unexpected value");
            };
        return result;
    }

    private boolean checkCoordinatesForAction(String action) {
        return Objects.equals(action, "place");
    }

    public int askForBoardSize() {
        int boardSize;
        while (true) {
            scanner = new Scanner(System.in);
            display.askForBoardSize();
            boardSize = scanner.nextInt();
            try {
                if (boardSize >= 10 && boardSize <= 20) {
                    break;
                } else {
                    display.displayInvalidChoiceMessage();
                }
            } catch (InputMismatchException ignored) {

            }
        }
        return boardSize;
    }

    public int askForPlacementMethod() {
        int[] validAnswers = new int[]{1, 2};
        int placementMethod;
        while (true) {
            scanner = new Scanner(System.in);
            display.askForPlacement();

            try {
                placementMethod = scanner.nextInt();
                if (checkForValidAnswer(placementMethod, validAnswers)) {
                    break;
                } else {
                    display.displayInvalidChoiceMessage();
                }
            } catch (InputMismatchException ignored) {

            }
        }
        return placementMethod;
    }
}
