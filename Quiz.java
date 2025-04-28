package elearning;


import java.util.ArrayList;

public class Quiz {
    private int id;
    private String title;
    private String description;
    private Course course;
    private ArrayList<Question> questions;
    private int totalPoints;
    private int timeLimit; // in minutes

    public Quiz(int id, String title, String description, Course course, int timeLimit) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.course = course;
        this.timeLimit = timeLimit;
        this.questions = new ArrayList<>();
        this.totalPoints = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
        totalPoints += question.getPoints();
    }

    public void displayInfo() {
        System.out.println("Quiz ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Course: " + course.getTitle());
        System.out.println("Time Limit: " + timeLimit + " minutes");
        System.out.println("Total Questions: " + questions.size());
        System.out.println("Total Points: " + totalPoints);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Course getCourse() {
        return course;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getTimeLimit() {
        return timeLimit;
    }
}
