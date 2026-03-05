package service;

import java.util.ArrayList;
import model.Student;
import util.FileHandler;

public class StudentService {

    private final ArrayList<Student> students;

    public StudentService() {
        students = FileHandler.load();
    }

    // UNIQUE ID CHECK
    private boolean idExists(int id) {
        return students.stream().anyMatch(s -> s.getId() == id);
    }

    // ADD
    public boolean addStudent(Student s) {
        if (idExists(s.getId()))
            return false;

        students.add(s);
        FileHandler.save(students);
        return true;
    }

    // VIEW
    public void viewStudents() {
        if (students.isEmpty())
            System.out.println("No records found.");

        students.forEach(System.out::println);
    }

    // SEARCH
    public Student searchStudent(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    // UPDATE
    public boolean updateStudent(int id, String name, int age, String course) {
        Student s = searchStudent(id);

        if (s == null) {
            return false;
        }

        s.setName(name);
        s.setAge(age);
        s.setCourse(course);

        FileHandler.save(students);
        return true;
    }

    // DELETE
    public boolean deleteStudent(int id) {
        Student s = searchStudent(id);

        if (s == null) {
            return false;
        }

        students.remove(s);
        FileHandler.save(students);
        return true;
    }
}
