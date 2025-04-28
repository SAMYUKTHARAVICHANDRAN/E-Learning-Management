package elearning;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ELearningSystem system = new ELearningSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== E-LEARNING MANAGEMENT SYSTEM =====");
            System.out.println("1. Register as Student");
            System.out.println("2. Register as Instructor");
            System.out.println("3. Login as Student");
            System.out.println("4. Login as Instructor");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerStudent(system, scanner);
                    break;
                case 2:
                    registerInstructor(system, scanner);
                    break;
                case 3:
                    loginStudent(system, scanner);
                    break;
                case 4:
                    loginInstructor(system, scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using the E-Learning Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void registerStudent(ELearningSystem system, Scanner scanner) {
        System.out.println("\n===== STUDENT REGISTRATION =====");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Student student = new Student(system.getNextUserId(), name, email, password);
        system.addUser(student);
        
        System.out.println("Registration successful!");
        System.out.println("Your Student ID is: " + student.getId());
        System.out.println("Please remember your ID and password for login.");
    }

    private static void registerInstructor(ELearningSystem system, Scanner scanner) {
        System.out.println("\n===== INSTRUCTOR REGISTRATION =====");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Instructor instructor = new Instructor(system.getNextUserId(), name, email, password);
        system.addUser(instructor);
        
        System.out.println("Registration successful!");
        System.out.println("Your Instructor ID is: " + instructor.getId());
        System.out.println("Please remember your ID and password for login.");
    }

    private static void loginStudent(ELearningSystem system, Scanner scanner) {
        System.out.println("\n===== STUDENT LOGIN =====");
        System.out.print("Enter your ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Student student = system.findStudentById(id);
        if (student != null && student.getPassword().equals(password)) {
            System.out.println("Login successful!");
            studentMenu(system, scanner, student);
        } else {
            System.out.println("Invalid ID or password. Please try again.");
        }
    }

    private static void loginInstructor(ELearningSystem system, Scanner scanner) {
        System.out.println("\n===== INSTRUCTOR LOGIN =====");
        System.out.print("Enter your ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Instructor instructor = system.findInstructorById(id);
        if (instructor != null && instructor.getPassword().equals(password)) {
            System.out.println("Login successful!");
            instructorMenu(system, scanner, instructor);
        } else {
            System.out.println("Invalid ID or password. Please try again.");
        }
    }

    private static void studentMenu(ELearningSystem system, Scanner scanner, Student student) {
        boolean studentLoggedIn = true;
        while (studentLoggedIn) {
            System.out.println("\n===== STUDENT MENU =====");
            System.out.println("Logged in as: " + student.getName());
            System.out.println("1. View My Courses");
            System.out.println("2. View All Available Courses");
            System.out.println("3. Enroll in a Course");
            System.out.println("4. View Assignments");
            System.out.println("5. Submit Assignment");
            System.out.println("6. View Available Quizzes");
            System.out.println("7. Take a Quiz");
            System.out.println("8. View My Grades");
            System.out.println("9. View My Quiz Results");
            System.out.println("10. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    student.viewEnrolledCourses();
                    break;
                case 2:
                    system.displayAllCourses();
                    break;
                case 3:
                    system.displayAllCourses();
                    System.out.print("Enter course ID to enroll: ");
                    int courseId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Course course = system.findCourseById(courseId);
                    if (course != null) {
                        student.enrollInCourse(course);
                        System.out.println("Enrolled in " + course.getTitle() + " successfully!");
                    } else {
                        System.out.println("Course not found!");
                    }
                    break;
                case 4:
                    student.viewAssignments();
                    break;
                case 5:
                    student.viewAssignments();
                    System.out.print("Enter assignment ID to submit: ");
                    int assignmentId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
                    Assignment assignment = system.findAssignmentById(assignmentId);
                    if (assignment != null) {
                        System.out.print("Enter your submission text: ");
                        String content = scanner.nextLine();
                        Submission submission = new Submission(system.getNextSubmissionId(), student, assignment, content);
                        system.addSubmission(submission);
                        System.out.println("Assignment submitted successfully!");
                    } else {
                        System.out.println("Assignment not found!");
                    }
                    break;
                case 6:
                    student.viewQuizzes();
                    break;
                case 7:
                    takeQuiz(system, scanner, student);
                    break;
                case 8:
                    student.viewGrades(system.getSubmissions());
                    break;
                case 9:
                    student.viewQuizResults();
                    break;
                case 10:
                    studentLoggedIn = false;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void takeQuiz(ELearningSystem system, Scanner scanner, Student student) {
        student.viewQuizzes();
        System.out.print("Enter quiz ID to take: ");
        int quizId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Quiz quiz = system.findQuizById(quizId);
        if (quiz == null) {
            System.out.println("Quiz not found!");
            return;
        }
        
        // Check if student is enrolled in the course
        boolean enrolled = false;
        for (Course course : student.getEnrolledCourses()) {
            if (course.equals(quiz.getCourse())) {
                enrolled = true;
                break;
            }
        }
        
        if (!enrolled) {
            System.out.println("You must be enrolled in the course to take this quiz!");
            return;
        }
        
        // Create a new quiz attempt
        QuizAttempt attempt = new QuizAttempt(system.getNextQuizAttemptId(), student, quiz);
        student.addQuizAttempt(attempt);
        system.addQuizAttempt(attempt);
        
        System.out.println("\n===== TAKING QUIZ: " + quiz.getTitle() + " =====");
        System.out.println("Time Limit: " + quiz.getTimeLimit() + " minutes");
        System.out.println("Total Questions: " + quiz.getQuestions().size());
        System.out.println("Total Points: " + quiz.getTotalPoints());
        System.out.println("\nPress Enter to start the quiz...");
        scanner.nextLine();
        
        ArrayList<Question> questions = quiz.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + " of " + questions.size() + " (" + question.getPoints() + " points)");
            System.out.println(question.getText());
            
            ArrayList<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }
            
            System.out.print("Enter your answer (1-" + options.size() + "): ");
            int answer = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            // Convert from 1-based to 0-based indexing
            if (answer >= 1 && answer <= options.size()) {
                attempt.submitAnswer(i, answer - 1);
            } else {
                System.out.println("Invalid option. Skipping question.");
            }
        }
        
        // Complete the quiz
        attempt.complete();
        System.out.println("\n===== QUIZ COMPLETED =====");
        attempt.displayResult();
    }

    private static void instructorMenu(ELearningSystem system, Scanner scanner, Instructor instructor) {
        boolean instructorLoggedIn = true;
        while (instructorLoggedIn) {
            System.out.println("\n===== INSTRUCTOR MENU =====");
            System.out.println("Logged in as: " + instructor.getName());
            System.out.println("1. View My Courses");
            System.out.println("2. Create New Course");
            System.out.println("3. Create Assignment");
            System.out.println("4. Create Quiz");
            System.out.println("5. View Submissions");
            System.out.println("6. View Quiz Attempts");
            System.out.println("7. Grade Submission");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    instructor.viewTaughtCourses();
                    break;
                case 2:
                    System.out.print("Enter course title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter course description: ");
                    String description = scanner.nextLine();
                    Course newCourse = new Course(system.getNextCourseId(), title, description, instructor);
                    system.addCourse(newCourse);
                    instructor.addCourse(newCourse);
                    System.out.println("Course created successfully!");
                    break;
                case 3:
                    instructor.viewTaughtCourses();
                    System.out.print("Enter course ID for the assignment: ");
                    int courseId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
                    Course course = system.findCourseById(courseId);
                    if (course != null && course.getInstructor().equals(instructor)) {
                        System.out.print("Enter assignment title: ");
                        String assignmentTitle = scanner.nextLine();
                        System.out.print("Enter assignment description: ");
                        String assignmentDesc = scanner.nextLine();
                        System.out.print("Enter max points: ");
                        int maxPoints = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        
                        Assignment assignment = new Assignment(system.getNextAssignmentId(), 
                                                              assignmentTitle, 
                                                              assignmentDesc, 
                                                              course, 
                                                              maxPoints);
                        system.addAssignment(assignment);
                        course.addAssignment(assignment);
                        System.out.println("Assignment created successfully!");
                    } else {
                        System.out.println("Course not found or you don't have permission!");
                    }
                    break;
                case 4:
                    createQuiz(system, scanner, instructor);
                    break;
                case 5:
                    instructor.viewSubmissions(system.getSubmissions());
                    break;
                case 6:
                    instructor.viewQuizAttempts(system.getQuizAttempts());
                    break;
                case 7:
                    instructor.viewSubmissions(system.getSubmissions());
                    System.out.print("Enter submission ID to grade: ");
                    int submissionId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
                    Submission submission = system.findSubmissionById(submissionId);
                    if (submission != null && 
                        submission.getAssignment().getCourse().getInstructor().equals(instructor)) {
                        System.out.print("Enter grade (out of " + 
                                        submission.getAssignment().getMaxPoints() + "): ");
                        int grade = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        
                        System.out.print("Enter feedback: ");
                        String feedback = scanner.nextLine();
                        
                        instructor.gradeSubmission(submission, grade, feedback);
                        System.out.println("Submission graded successfully!");
                    } else {
                        System.out.println("Submission not found or you don't have permission!");
                    }
                    break;
                case 8:
                    instructorLoggedIn = false;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createQuiz(ELearningSystem system, Scanner scanner, Instructor instructor) {
        instructor.viewTaughtCourses();
        System.out.print("Enter course ID for the quiz: ");
        int courseId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Course course = system.findCourseById(courseId);
        if (course == null || !course.getInstructor().equals(instructor)) {
            System.out.println("Course not found or you don't have permission!");
            return;
        }
        
        System.out.print("Enter quiz title: ");
        String title = scanner.nextLine();
        System.out.print("Enter quiz description: ");
        String description = scanner.nextLine();
        System.out.print("Enter time limit (in minutes): ");
        int timeLimit = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        Quiz quiz = new Quiz(system.getNextQuizId(), title, description, course, timeLimit);
        system.addQuiz(quiz);
        course.addQuiz(quiz);
        
        System.out.print("Enter number of questions: ");
        int numQuestions = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        for (int i = 0; i < numQuestions; i++) {
            System.out.println("\nQuestion " + (i + 1) + ":");
            System.out.print("Enter question text: ");
            String questionText = scanner.nextLine();
            System.out.print("Enter points for this question: ");
            int points = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            Question question = new Question(system.getNextQuestionId(), questionText, points, quiz);
            
            System.out.print("Enter number of options: ");
            int numOptions = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            for (int j = 0; j < numOptions; j++) {
                System.out.print("Enter option " + (j + 1) + ": ");
                String option = scanner.nextLine();
                System.out.print("Is this the correct answer? (y/n): ");
                boolean isCorrect = scanner.nextLine().toLowerCase().startsWith("y");
                
                question.addOption(option, isCorrect);
            }
            
            system.addQuestion(question);
            quiz.addQuestion(question);
        }
        
        System.out.println("Quiz created successfully with " + numQuestions + " questions!");
    }
}