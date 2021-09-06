package cinema;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cinema {

    private static final double[] stats = new double[4];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] cinemaRoom = initializeTheRoom(scanner);
        menu(scanner, cinemaRoom);
    }

    private static String[][] initializeTheRoom(Scanner scanner) {
        System.out.println("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int seats = scanner.nextInt();
        String[][] cinemaRoom = new String[rows][seats];
        for (String[] insideArray : cinemaRoom) {
            Arrays.fill(insideArray, "S");
        }
        totalIncome(rows, seats);
        return cinemaRoom;
    }

    private static void menu(Scanner scanner, String[][] cinemaRoom) {
        boolean exit = false;
        int menuItem = 3;

        while (!exit) {
            System.out.println();
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            menuItem = scanner.nextInt();
            switch (menuItem) {
                case 1:
                    showTheSeats(cinemaRoom);
                    break;
                case 2:
                    reserveSeats(cinemaRoom, scanner);
                    break;
                case 3:
                    showStatistics();
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }
    }

    private static void showStatistics() {
        System.out.printf("Number of purchased tickets: %d\n", (int) stats[0]);
        System.out.printf("Percentage:: %.2f%%\n", stats[1]);
        System.out.printf("Current income: $%d\n", (int) stats[2]);
        System.out.printf("Total income: $%d\n", (int) stats[3]);
    }

    private static void totalIncome(int rows, int seats) {
        int totalSeats = rows * seats;
        if (totalSeats <= 60) {
            stats[3] = totalSeats * 10;
        } else {
            if (rows % 10 == 0) {
                stats[3] = rows / 2 * seats * 10 + rows / 2 * seats * 8;
            } else {
                stats[3] = rows / 2 * seats * 10 + ((rows / 2) + 1) * seats * 8;
            }
        }
    }

    public static void showTheSeats(String[][] cinemaRoom) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= cinemaRoom[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < cinemaRoom.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < cinemaRoom[i].length; j++) {
                System.out.print(cinemaRoom[i][j] + " ");
                if (j + 1 == cinemaRoom[i].length) {
                    System.out.println();
                }
            }
        }
    }

    public static String[][] reserveSeats(String[][] cinemaRoom, Scanner scanner) {
        boolean askAgain = true;
        int row = 0;
        int column = 0;
        System.out.println();
        while (askAgain) {
            System.out.println("Enter a row number: ");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            column = scanner.nextInt();
            System.out.println();
            if (row > cinemaRoom.length || column > cinemaRoom[0].length) {
                System.out.println("Wrong input!");
                System.out.println();
            } else if (cinemaRoom[row - 1][column - 1].equalsIgnoreCase("B")) {
                System.out.println("That ticket has already been purchased!");
                System.out.println();

            } else {
                askAgain = false;
            }
        }
        stats[2] += calculateSeatPrice(cinemaRoom.length, cinemaRoom[0].length, row, column);

        for (int i = 0; i < cinemaRoom.length; i++) {
            for (int j = 0; j < cinemaRoom[i].length; j++) {
                if (i + 1 == row && j + 1 == column) {
                    cinemaRoom[i][j] = "B";
                    stats[0] += 1;
                    stats[1] = (stats[0] * 100) / (cinemaRoom.length * cinemaRoom[0].length);
                }
            }
        }
        return cinemaRoom;
    }

    public static int calculateSeatPrice(int rows, int seats, int selectedRow, int selectedSeat) throws InputMismatchException {
        if (rows * seats <= 60) {
            System.out.printf("Ticket price: $%d", 10);
            System.out.println();
            return 10;
        } else {
            if (rows % 2 == 0) {
                if (selectedRow <= rows / 2) {
                    System.out.printf("Ticket price: $%d", 10);
                    System.out.println();
                    return 10;
                } else {
                    System.out.printf("Ticket price: $%d", 8);
                    System.out.println();
                    return 8;
                }
            } else {
                if (selectedRow <= rows / 2) {
                    System.out.printf("Ticket price: $%d", 10);
                    System.out.println();
                    return 10;
                } else {
                    System.out.printf("Ticket price: $%d", 8);
                    System.out.println();
                    return 8;
                }
            }
        }
    }
}