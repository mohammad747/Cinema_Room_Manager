package cinema;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] cinemaRoom = initializeTheRoom(scanner);
        menu(scanner, cinemaRoom);
//        showTheSeats(cinemaRoom);
//        reserveSeats(cinemaRoom, scanner);
//        showTheSeats(cinemaRoom);

//        try {
//            totalIncome(scanner);
//        } catch (InputMismatchException ime) {
//            System.out.println("Incorrect input!");
//        }
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
        return cinemaRoom;
    }

    private static void menu(Scanner scanner, String[][] cinemaRoom) {
        boolean exit = false;
        int menuItem = 3;

        while (!exit) {
            System.out.println();
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "0. Exit");
            menuItem = scanner.nextInt();
            switch (menuItem) {
                case 1:
                    showTheSeats(cinemaRoom);
                    break;
                case 2:
                    reserveSeats(cinemaRoom, scanner);
                    break;
                case 0:
                    exit = true;
                    break;
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
        System.out.println();
        System.out.println("Enter a row number: ");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int column = scanner.nextInt();
        System.out.println();
        calculateSeatPrice(cinemaRoom.length, cinemaRoom[0].length, row, column);
        for (int i = 0; i < cinemaRoom.length; i++) {
            for (int j = 0; j < cinemaRoom[i].length; j++) {
                if (i + 1 == row && j + 1 == column) {
                    cinemaRoom[i][j] = "B";
                }
            }
        }
        return cinemaRoom;
    }

    public static void calculateSeatPrice(int rows, int seats, int selectedRow, int selectedSeat) throws InputMismatchException {
        if (rows * seats <= 60) {
            System.out.printf("Ticket price: $%d", 10);
            System.out.println();
        } else {
            if (rows % 2 == 0) {
                if (selectedRow <= rows / 2) {
                    System.out.printf("Ticket price: $%d", 10);
                    System.out.println();
                } else {
                    System.out.printf("Ticket price: $%d", 8);
                    System.out.println();
                }
//                System.out.printf("Total income:%n $%d", (rows / 2 * seats * 10) + (rows / 2 * seats * 8));
            } else {
                if (selectedRow <= rows / 2) {
                    System.out.printf("Ticket price: $%d", 10);
                    System.out.println();
                } else {
                    System.out.printf("Ticket price: $%d", 8);
                    System.out.println();
                }
//                System.out.printf("Ticket price: $%d", 8);
//                System.out.println();
//                System.out.printf("Total income:%n $%d", (rows / 2 * seats * 10) + ((rows / 2 + 1) * seats * 8));
            }
        }
    }
}