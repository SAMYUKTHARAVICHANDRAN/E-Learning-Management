package elearning;

import java.util.ArrayList;

public class Course {
    private int id;
    private String title;
    private String description;
    private Instructor instructor;
    private ArrayList<Student> students;
    private ArrayList<Assignment> assignments;
    private ArrayList<Quiz> quizzes;

    public Course(int id, String title, String description, Instructor instructor) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
        this.quizzes = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }

    public void displayInfo() {
        System.out.println("Course ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Instructor: " + instructor.getName());
        System.out.println("Enrolled Students: " + students.size());
        System.out.println("Assignments: " + assignments.size());
        System.out.println("Quizzes: " + quizzes.size());
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

    public Instructor getInstructor() {
        return instructor;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }
}