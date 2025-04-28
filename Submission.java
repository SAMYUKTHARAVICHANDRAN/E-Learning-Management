package elearning;

public class Submission {
    private int id;
    private Student student;
    private Assignment assignment;
    private String content;
    private int grade;
    private String feedback;
    private boolean graded;

    public Submission(int id, Student student, Assignment assignment, String content) {
        this.id = id;
        this.student = student;
        this.assignment = assignment;
        this.content = content;
        this.graded = false;
        this.feedback = "";
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public String getContent() {
        return content;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public boolean isGraded() {
        return graded;
    }

    public void setGraded(boolean graded) {
        this.graded = graded;
    }
}