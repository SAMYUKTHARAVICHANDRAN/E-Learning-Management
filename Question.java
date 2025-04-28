package elearning;


import java.util.ArrayList;

public class Question {
    private int id;
    private String text;
    private ArrayList<String> options;
    private int correctOption; // 0-based index of correct option
    private int points;
    private Quiz quiz;

    public Question(int id, String text, int points, Quiz quiz) {
        this.id = id;
        this.text = text;
        this.points = points;
        this.quiz = quiz;
        this.options = new ArrayList<>();
    }

    public void addOption(String option, boolean isCorrect) {
        options.add(option);
        if (isCorrect) {
            correctOption = options.size() - 1;
        }
    }

    public boolean checkAnswer(int selectedOption) {
        return selectedOption == correctOption;
    }

    public void display() {
        System.out.println(text + " (" + points + " points)");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public int getPoints() {
        return points;
    }

    public Quiz getQuiz() {
        return quiz;
    }
}
