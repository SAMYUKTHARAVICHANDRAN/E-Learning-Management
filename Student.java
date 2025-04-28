package elearning;

import java.util.ArrayList;

// Inheritance: Student extends User
public class Student extends User {
    private ArrayList<Course> enrolledCourses;
    private ArrayList<QuizAttempt> quizAttempts;

    public Student(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.enrolledCourses = new ArrayList<>();
        this.quizAttempts = new ArrayList<>();
    }

    // Polymorphism: Overriding the abstract method from User
    @Override
    public void displayInfo() {
        System.out.println("Student ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Enrolled in " + enrolledCourses.size() + " courses");
    }

    public void enrollInCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
            course.addStudent(this);
        }
    }

    public void viewEnrolledCourses() {
        if (enrolledCourses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
            return;
        }
        
        System.out.println("\n=== Your Enrolled Courses ===");
        for (Course course : enrolledCourses) {
            System.out.println("ID: " + course.getId() + " | Title: " + course.getTitle() + 
                              " | Instructor: " + course.getInstructor().getName());
        }
    }

    public void viewAssignments() {
        if (enrolledCourses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
            return;
        }
        
        System.out.println("\n=== Your Assignments ===");
        boolean hasAssignments = false;
        
        for (Course course : enrolledCourses) {
            ArrayList<Assignment> assignments = course.getAssignments();
            if (!assignments.isEmpty()) {
                System.out.println("\nCourse: " + course.getTitle());
                for (Assignment assignment : assignments) {
                    System.out.println("Assignment ID: " + assignment.getId() + 
                                      " | Title: " + assignment.getTitle() + 
                                      " | Max Points: " + assignment.getMaxPoints());
                }
                hasAssignments = true;
            }
        }
        
        if (!hasAssignments) {
            System.out.println("No assignments available in your courses.");
        }
    }

    public void viewQuizzes() {
        if (enrolledCourses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
            return;
        }
        
        System.out.println("\n=== Available Quizzes ===");
        boolean hasQuizzes = false;
        
        for (Course course : enrolledCourses) {
            ArrayList<Quiz> quizzes = course.getQuizzes();
            if (!quizzes.isEmpty()) {
                System.out.println("\nCourse: " + course.getTitle());
                for (Quiz quiz : quizzes) {
                    System.out.println("Quiz ID: " + quiz.getId() + 
                                      " | Title: " + quiz.getTitle() + 
                                      " | Questions: " + quiz.getQuestions().size() +
                                      " | Total Points: " + quiz.getTotalPoints() +
                                      " | Time Limit: " + quiz.getTimeLimit() + " minutes");
                }
                hasQuizzes = true;
            }
        }
        
        if (!hasQuizzes) {
            System.out.println("No quizzes available in your courses.");
        }
    }

    public void viewGrades(ArrayList<Submission> allSubmissions) {
        boolean hasSubmissions = false;
        System.out.println("\n=== Your Assignment Grades ===");
        
        for (Submission submission : allSubmissions) {
            if (submission.getStudent().equals(this)) {
                System.out.println("Course: " + submission.getAssignment().getCourse().getTitle());
                System.out.println("Assignment: " + submission.getAssignment().getTitle());
                if (submission.isGraded()) {
                    System.out.println("Grade: " + submission.getGrade() + "/" + 
                                      submission.getAssignment().getMaxPoints());
                    System.out.println("Feedback: " + submission.getFeedback());
                } else {
                    System.out.println("Status: Not graded yet");
                }
                System.out.println("----------------------------");
                hasSubmissions = true;
            }
        }
        
        if (!hasSubmissions) {
            System.out.println("You haven't submitted any assignments yet.");
        }
    }

    public void viewQuizResults() {
        if (quizAttempts.isEmpty()) {
            System.out.println("You haven't attempted any quizzes yet.");
            return;
        }
        
        System.out.println("\n=== Your Quiz Results ===");
        for (QuizAttempt attempt : quizAttempts) {
            System.out.println("Quiz: " + attempt.getQuiz().getTitle());
            System.out.println("Course: " + attempt.getQuiz().getCourse().getTitle());
            if (attempt.isCompleted()) {
                System.out.println("Score: " + attempt.getScore() + "/" + attempt.getQuiz().getTotalPoints());
                double percentage = (double) attempt.getScore() / attempt.getQuiz().getTotalPoints() * 100;
                System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
            } else {
                System.out.println("Status: Incomplete");
            }
            System.out.println("----------------------------");
        }
    }

    public void addQuizAttempt(QuizAttempt attempt) {
        quizAttempts.add(attempt);
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public ArrayList<QuizAttempt> getQuizAttempts() {
        return quizAttempts;
    }
}