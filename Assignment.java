package elearning;

public class Assignment {
    private int id;
    private String title;
    private String description;
    private Course course;
    private int maxPoints;

    public Assignment(int id, String title, String description, Course course, int maxPoints) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.course = course;
        this.maxPoints = maxPoints;
    }

    public void displayInfo() {
        System.out.println("Assignment ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Course: " + course.getTitle());
        System.out.println("Max Points: " + maxPoints);
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

    public int getMaxPoints() {
        return maxPoints;
    }
}