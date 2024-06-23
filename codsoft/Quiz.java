import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {
    private List<QuizQuestion> questions;
    private int score;
    private int currentQuestionIndex;
    private boolean answerSubmitted;
    private int[] userAnswers;
    private Scanner scanner;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.userAnswers = new int[questions.size()];
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int totalQuestions = questions.size();
        int questionsPerRound = 5;
        int totalRounds = totalQuestions / questionsPerRound;

        for (int round = 0; round < totalRounds; round++) {
            List<QuizQuestion> roundQuestions = getRandomQuestions(questions, questionsPerRound);

            System.out.println("Round " + (round + 1) + ": Answer the following " + questionsPerRound + " questions:");
            score += conductRound(roundQuestions);
        }

        displayResults();

        // Ask user if they want to take another quiz
        System.out.print("Do you want to take another quiz? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("yes")) {
            resetQuiz();
            start(); // Restart quiz
        } else {
            System.out.println("Thank you for participating in the quiz!");
            scanner.close();
        }
    }

    private List<QuizQuestion> getRandomQuestions(List<QuizQuestion> allQuestions, int count) {
        Collections.shuffle(allQuestions);
        return allQuestions.subList(0, count);
    }

    private int conductRound(List<QuizQuestion> roundQuestions) {
        int roundScore = 0;

        for (int i = 0; i < roundQuestions.size(); i++) {
            QuizQuestion question = roundQuestions.get(i);

            System.out.println("Question " + (i + 1) + ": " + question.getQuestion());

            String[] options = question.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ". " + options[j]);
            }

            System.out.print("Enter your answer (1-" + options.length + "): ");
            int answer = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline character

            if (answer >= 0 && answer < options.length) {
                userAnswers[currentQuestionIndex] = answer;
                if (answer == question.getCorrectAnswerIndex()) {
                    roundScore++;
                }
            } else {
                System.out.println("Invalid answer. Skipping question.");
            }
        }

        return roundScore;
    }

    private void displayResults() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your Total Score: " + score + "/" + (questions.size() / 5 * 5)); // Total possible score

        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.getQuestion());
            System.out.println("Correct Answer: " + question.getOptions()[question.getCorrectAnswerIndex()]);
            if (userAnswers[i] != -1) {
                System.out.println("Your Answer: " + question.getOptions()[userAnswers[i]]);
            } else {
                System.out.println("Your Answer: No answer");
            }
        }
    }

    private void resetQuiz() {
        this.score = 0;
        this.currentQuestionIndex = 0;
        for (int i = 0; i < userAnswers.length; i++) {
            userAnswers[i] = -1;
        }
    }

    public static void main(String[] args) {
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new QuizQuestion("What is the capital of France?", new String[]{"Berlin", "London", "Paris", "Madrid"}, 2));
        questions.add(new QuizQuestion("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1));
        questions.add(new QuizQuestion("What is the color of the sky?", new String[]{"Green", "Blue", "Red", "Yellow"}, 1));
        questions.add(new QuizQuestion("Which animal is known as the King of the Jungle?", new String[]{"Elephant", "Lion", "Tiger", "Bear"}, 1));
        questions.add(new QuizQuestion("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 1));
        questions.add(new QuizQuestion("What is the largest ocean on Earth?", new String[]{"Atlantic Ocean", "Indian Ocean", "Pacific Ocean", "Arctic Ocean"}, 2));
        questions.add(new QuizQuestion("What is the boiling point of water?", new String[]{"90°C", "100°C", "110°C", "120°C"}, 1));

        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}
