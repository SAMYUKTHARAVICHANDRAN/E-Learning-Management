package elearning;

//Abstract class demonstrating abstraction
public abstract class User {
 private int id;
 private String name;
 private String email;
 private String password;

 public User(int id, String name, String email, String password) {
     this.id = id;
     this.name = name;
     this.email = email;
     this.password = password;
 }

 // Abstract method to be implemented by subclasses
 public abstract void displayInfo();

 // Getters and setters
 public int getId() {
     return id;
 }

 public String getName() {
     return name;
 }

 public String getEmail() {
     return email;
 }

 public String getPassword() {
     return password;
 }

 public void setName(String name) {
     this.name = name;
 }

 public void setEmail(String email) {
     this.email = email;
 }

 public void setPassword(String password) {
     this.password = password;
 }
}