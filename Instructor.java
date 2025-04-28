package elearning;

import java.util.ArrayList;

// Inheritance: Instructor extends User and implements Grader interface
public class Instructor extends User implements Grader {
    private ArrayList<Course> taughtCourses;

    public Instructor(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.taughtCourses = new ArrayList<>();
    }

    // Polymorphism: Overriding the abstract method from User
    @Override
    public void displayInfo() {
        System.out.println("Instructor ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Teaching " + taughtCourses.size() + " courses");
    }

    public void addCourse(Course course) {
        if (!taughtCourses.contains(course)) {
            taughtCourses.add(course);
        }
    }

    public void viewTaughtCourses() {
        if (taughtCourses.isEmpty()) {
            System.out.println("You are not teaching any courses.");
            return;
        }
        
        System.out.println("\n=== Your Courses ===");
        for (Course course : taughtCourses) {
            System.out.println("ID: " + course.getId() + " | Title: " + course.getTitle() + 
                              " | Students: " + course.getStudents().size());
        }
    }

    public void viewSubmissions(ArrayList<Submission> allSubmissions) {
        boolean hasSubmissions = false;
        System.out.println("\n=== Submissions for Your Courses ===");
        
        for (Submission submission : allSubmissions) {
            Course course = submission.getAssignment().getCourse();
            if (course.getInstructor().equals(this)) {
                System.out.println("Submission ID: " + submission.getId());
                System.out.println("Course: " + course.getTitle());
                System.out.println("Assignment: " + submission.getAssignment().getTitle());
                System.out.println("Student: " + submission.getStudent().getName());
                System.out.println("Content: " + submission.getContent());
                if (submission.isGraded()) {
                    System.out.println("Grade: " + submission.getGrade() + "/" + 
                                      submission.getAssignment().getMaxPoints());
                    System.out.println("Feedback: " + submission.getFeedback());
                } else {
                    System.out.println("Status: Not graded");
                }
                System.out.println("----------------------------");
                hasSubmissions = true;
            }
        }
        
        if (!hasSubmissions) {
            System.out.println("No submissions for your courses yet.");
        }
    }

    public void viewQuizAttempts(ArrayList<QuizAttempt> allAttempts) {
        boolean hasAttempts = false;
        System.out.println("\n=== Quiz Attempts for Your Courses ===");
        
        for (QuizAttempt attempt : allAttempts) {
            Course course = attempt.getQuiz().getCourse();
            if (course.getInstructor().equals(this)) {
                System.out.println("Quiz: " + attempt.getQuiz().getTitle());
                System.out.println("Course: " + course.getTitle());
                System.out.println("Student: " + attempt.getStudent().getName());
                if (attempt.isCompleted()) {
                    System.out.println("Score: " + attempt.getScore() + "/" + attempt.getQuiz().getTotalPoints());
                    double percentage = (double) attempt.getScore() / attempt.getQuiz().getTotalPoints() * 100;
                    System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
                } else {
                    System.out.println("Status: Incomplete");
                }
                System.out.println("----------------------------");
                hasAttempts = true;
            }
        }
        
        if (!hasAttempts) {
            System.out.println("No quiz attempts for your courses yet.");
        }
    }

    // Implementation of Grader interface method
    @Override
    public void gradeSubmission(Submission submission, int grade, String feedback) {
        if (grade > submission.getAssignment().getMaxPoints()) {
            grade = submission.getAssignment().getMaxPoints();
        }
        submission.setGrade(grade);
        submission.setFeedback(feedback);
        submission.setGraded(true);
    }

    public ArrayList<Course> getTaughtCourses() {
        return taughtCourses;
    }
}