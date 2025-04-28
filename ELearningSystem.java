package elearning;

import java.util.ArrayList;

public class ELearningSystem {
    private ArrayList<User> users;
    private ArrayList<Course> courses;
    private ArrayList<Assignment> assignments;
    private ArrayList<Submission> submissions;
    private ArrayList<Quiz> quizzes;
    private ArrayList<Question> questions;
    private ArrayList<QuizAttempt> quizAttempts;
   
    private int nextUserId = 1;
    private int nextCourseId = 1;
    private int nextAssignmentId = 1;
    private int nextSubmissionId = 1;
    private int nextQuizId = 1;
    private int nextQuestionId = 1;
    private int nextQuizAttemptId = 1;

    public ELearningSystem() {
        users = new ArrayList<>();
        courses = new ArrayList<>();
        assignments = new ArrayList<>();
        submissions = new ArrayList<>();
        quizzes = new ArrayList<>();
        questions = new ArrayList<>();
        quizAttempts = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public void addSubmission(Submission submission) {
        submissions.add(submission);
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void addQuizAttempt(QuizAttempt attempt) {
        quizAttempts.add(attempt);
    }

    public void displayAllUsers() {
        System.out.println("\n===== ALL USERS =====");
        for (User user : users) {
            if (user instanceof Student) {
                System.out.println("Student ID: " + user.getId() + " | Name: " + user.getName());
            } else if (user instanceof Instructor) {
                System.out.println("Instructor ID: " + user.getId() + " | Name: " + user.getName());
            }
        }
    }

    public void displayAllCourses() {
        System.out.println("\n===== ALL COURSES =====");
        if (courses.isEmpty()) {
            System.out.println("No courses available yet.");
            return;
        }
        
        for (Course course : courses) {
            System.out.println("ID: " + course.getId() + 
                              " | Title: " + course.getTitle() + 
                              " | Instructor: " + course.getInstructor().getName() + 
                              " | Students: " + course.getStudents().size());
        }
    }

    public Student findStudentById(int id) {
        for (User user : users) {
            if (user instanceof Student && user.getId() == id) {
                return (Student) user;
            }
        }
        return null;
    }

    public Instructor findInstructorById(int id) {
        for (User user : users) {
            if (user instanceof Instructor && user.getId() == id) {
                return (Instructor) user;
            }
        }
        return null;
    }

    public Course findCourseById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    public Assignment findAssignmentById(int id) {
        for (Assignment assignment : assignments) {
            if (assignment.getId() == id) {
                return assignment;
            }
        }
        return null;
    }

    public Submission findSubmissionById(int id) {
        for (Submission submission : submissions) {
            if (submission.getId() == id) {
                return submission;
            }
        }
        return null;
    }

    public Quiz findQuizById(int id) {
        for (Quiz quiz : quizzes) {
            if (quiz.getId() == id) {
                return quiz;
            }
        }
        return null;
    }

    public Question findQuestionById(int id) {
        for (Question question : questions) {
            if (question.getId() == id) {
                return question;
            }
        }
        return null;
    }

    public QuizAttempt findQuizAttemptById(int id) {
        for (QuizAttempt attempt : quizAttempts) {
            if (attempt.getId() == id) {
                return attempt;
            }
        }
        return null;
    }

    // Getters for next IDs
    public int getNextUserId() {
        return nextUserId++;
    }

    public int getNextCourseId() {
        return nextCourseId++;
    }

    public int getNextAssignmentId() {
        return nextAssignmentId++;
    }

    public int getNextSubmissionId() {
        return nextSubmissionId++;
    }

    public int getNextQuizId() {
        return nextQuizId++;
    }

    public int getNextQuestionId() {
        return nextQuestionId++;
    }

    public int getNextQuizAttemptId() {
        return nextQuizAttemptId++;
    }

    // Getters for lists
    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public ArrayList<Submission> getSubmissions() {
        return submissions;
    }

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public ArrayList<QuizAttempt> getQuizAttempts() {
        return quizAttempts;
    }
}