package Number_Game;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Number_game {
    private static final int MAX_ATTEMPTS = 10;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the number guessing game!");
            System.out.println("You have 10 chances to guess the number.");
            System.out.println("Let's start the game!");

            int low = readInt(scanner, "Enter the lower limit: ");
            int high = readInt(scanner, "Enter the upper limit: ");

            if (low > high) {
                System.out.println("Please input a valid range where the lower limit is less than or equal to the upper limit.");
                return;
            }

            System.out.printf("You have %d chances to guess the number from %d to %d.%n", MAX_ATTEMPTS, low, high);
            GameResult result = playGame(low, high, MAX_ATTEMPTS, scanner);

            if (result.won) {
                System.out.printf("Congratulations! You guessed the number %d in %d chances.%n",
                        result.targetNumber, result.attemptsUsed);
            } else {
                System.out.printf("You lost. The number was %d.%n", result.targetNumber);
            }
        }
    }

    static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                scanner.next();
            }
        }
    }

    static int generateRandomNumber(int low, int high) {
        if (low > high) {
            throw new IllegalArgumentException("Lower limit cannot be greater than upper limit.");
        }
        return ThreadLocalRandom.current().nextInt(low, high + 1);
    }

    static String evaluateGuess(int guess, int targetNumber) {
        if (guess == targetNumber) {
            return "correct";
        }
        if (guess < targetNumber) {
            return "too_low";
        }
        return "too_high";
    }

    static GameResult playGame(int low, int high, int maxAttempts, Scanner scanner) {
        int targetNumber = generateRandomNumber(low, high);
        int attemptsUsed = 0;

        while (attemptsUsed < maxAttempts) {
            int guess = readInt(scanner, "Guess the number: ");
            attemptsUsed++;

            String result = evaluateGuess(guess, targetNumber);
            if ("correct".equals(result)) {
                return new GameResult(true, targetNumber, attemptsUsed);
            }

            if ("too_high".equals(result)) {
                System.out.println("Your guess is too high! Try lower.");
            } else {
                System.out.println("Your guess is too low! Try higher.");
            }
        }

        return new GameResult(false, targetNumber, attemptsUsed);
    }

    static class GameResult {
        final boolean won;
        final int targetNumber;
        final int attemptsUsed;

        GameResult(boolean won, int targetNumber, int attemptsUsed) {
            this.won = won;
            this.targetNumber = targetNumber;
            this.attemptsUsed = attemptsUsed;
        }
    }
}
