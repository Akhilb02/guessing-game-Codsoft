import java.util.Scanner;
import java.util.Random;

public class GuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int start = 1;
        int end = 100;
        int maxAttempts = 10;
        int totalRounds = 0;
        int totalAttempts = 0;

        while (true) {
            totalRounds++;
            System.out.println("\nRound " + totalRounds);
            int target = random.nextInt(end - start + 1) + start;
            int attempts = 0;
            boolean guessedCorrectly = false;

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;
                if (guess < target) {
                    System.out.println("Too low!");
                } else if (guess > target) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Correct!");
                    System.out.println("Congratulations! You've guessed the number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    break;
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The number was " + target + ".");
            }

            totalAttempts += attempts;

            System.out.print("Do you want to play another round? (yes/no): ");
            scanner.nextLine(); // consume newline
            String playAgain = scanner.nextLine().trim().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;
            }
        }

        if (totalRounds > 0) {
            double averageAttempts = (double) totalAttempts / totalRounds;
            System.out.println("\nYou played " + totalRounds + " rounds with an average of " + String.format("%.2f", averageAttempts) + " attempts per round.");
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
