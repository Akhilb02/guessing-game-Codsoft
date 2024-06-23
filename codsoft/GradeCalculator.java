import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the number of subjects: ");
            int numSubjects = scanner.nextInt();
            int[] marks = new int[numSubjects];

            for (int i = 0; i < numSubjects; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
                marks[i] = scanner.nextInt();
            }

            int totalMarks = calculateTotalMarks(marks);
            double averagePercentage = calculateAveragePercentage(totalMarks, numSubjects);
            char grade = calculateGrade(averagePercentage);

            System.out.println("\nTotal Marks: " + totalMarks);
            System.out.println("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
            System.out.println("Grade: " + grade);

            System.out.print("Do you want to enter marks for another student? (yes/no): ");
            scanner.nextLine();  // Consume newline
            String continueInput = scanner.nextLine().trim().toLowerCase();
            if (!continueInput.equals("yes")) {
                break;
            }
        }

        System.out.println("Thank you for using the Grade Calculator!");
        scanner.close();
    }

    public static int calculateTotalMarks(int[] marks) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    public static double calculateAveragePercentage(int totalMarks, int numSubjects) {
        return (double) totalMarks / numSubjects;
    }

    public static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}
