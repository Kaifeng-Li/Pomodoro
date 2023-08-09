package main;

import java.util.Scanner;

public class TimerModeSelector {
    private Scanner scanner;

    public TimerModeSelector() {
        scanner = new Scanner(System.in);
    }

    public void runTimerModeSelection() {
        while (true) {
            System.out.println("Choose Mode by Number / Exit by type 3");
            System.out.println("1 / Regular Timer");
            System.out.println("2 / Tomato Timer");

            try {
                int choice = scanner.nextInt();

                if (choice == 1) {
                    int focusTime = getFocusTimeFromUser();
                    TimerMode regularTimer = new RegularTimer(focusTime);
                    regularTimer.startTimer();
                } else if (choice == 2) {
                    int focusTime = getFocusTimeFromUser();
                    int breakTime = getBreakTimeFromUser();
                    TimerMode tomatoTimer = new TomatoTimer(focusTime, breakTime);
                    tomatoTimer.startTimer();
                } else if (choice == 3) {
                    System.out.println("Exiting the program. Goodbye!");
                    break; // Exit the loop and terminate the program
                } else {
                    System.out.println("Not a choice. Please try again.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input from the scanner buffer
            }
        }

        scanner.close();
    }

    private int getFocusTimeFromUser() {
        return collector("focus");
    }

    private int getBreakTimeFromUser() {
        return collector("break");
    }

    private int collector(String type){
        System.out.print("Enter " + type + " time in minutes: ");
        String input = scanner.next();
        while(inputDetector(input)){
            System.out.print("Please enter an positive integer: ");
            input = scanner.next();
        }
        return Integer.parseInt(input);
    }

    private boolean inputDetector(String input){
        try {
            Integer.parseInt(String.valueOf(input));
        } catch (NumberFormatException e){
            return true;
        }
        return Integer.parseInt(input) <= 0;
    }
}