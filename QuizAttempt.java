package elearning;


import java.util.ArrayList;
import java.util.Date;

public class QuizAttempt {
    private int id;
    private Student student;
    private Quiz quiz;
    private Date startTime;
    private Date endTime;
    private ArrayList<Integer> answers; // Stores the selected option for each question
    private int score;
    private boolean completed;

    public QuizAttempt(int id, Student student, Quiz quiz) {
        this.id = id;
        this.student = student;
        this.quiz = quiz;
        this.startTime = new Date(); // Current time
        this.answers = new ArrayList<>();
        this.completed = false;
        
        // Initialize answers with -1 (unanswered)
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            answers.add(-1);
        }
    }

    public void submitAnswer(int questionIndex, int selectedOption) {
        if (questionIndex >= 0 && questionIndex < answers.size()) {
            answers.set(questionIndex, selectedOption);
        }
    }

    public void complete() {
        this.endTime = new Date();
        this.completed = true;
        calculateScore();
    }

    private void calculateScore() {
        score = 0;
        ArrayList<Question> questions = quiz.getQuestions();
        
        for (int i = 0; i < questions.size(); i++) {
            if (i < answers.size() && answers.get(i) != -1) {
                Question question = questions.get(i);
                if (question.checkAnswer(answers.get(i))) {
                    score += question.getPoints();
                }
            }
        }
    }

    public void displayResult() {
        System.out.println("\n===== QUIZ RESULT =====");
        System.out.println("Quiz: " + quiz.getTitle());
        System.out.println("Student: " + student.getName());
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Score: " + score + "/" + quiz.getTotalPoints());
        
        double percentage = (double) score / quiz.getTotalPoints() * 100;
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        
        System.out.println("\n===== QUESTION REVIEW =====");
        ArrayList<Question> questions = quiz.getQuestions();
        
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            int selectedOption = i < answers.size() ? answers.get(i) : -1;
            
            System.out.println("\nQuestion " + (i + 1) + ": " + question.getText());
            ArrayList<String> options = question.getOptions();
            
            for (int j = 0; j < options.size(); j++) {
                String marker = "";
                if (j == question.getCorrectOption()) {
                    marker = " ✓";
                }
                if (j == selectedOption && j != question.getCorrectOption()) {
                    marker = " ✗";
                }
                System.out.println((j + 1) + ". " + options.get(j) + marker);
            }
            
            if (selectedOption == -1) {
                System.out.println("You did not answer this question.");
            } else if (question.checkAnswer(selectedOption)) {
                System.out.println("Correct! You earned " + question.getPoints() + " points.");
            } else {
                System.out.println("Incorrect. The correct answer was: " + 
                                  (question.getCorrectOption() + 1) + ". " + 
                                  options.get(question.getCorrectOption()));
            }
        }
    }

    // Getters
    public int getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public ArrayList<Integer> getAnswers() {
        return answers;
    }

    public int getScore() {
        return score;
    }

    public boolean isCompleted() {
        return completed;
    }
}
