package main;

import java.util.Scanner;
import model.Student;
import service.StudentService;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentService service = new StudentService();

        while (true) {
            try {
                System.out.println("-------------------------------------------------------------------------------------------------------------------");
                System.out.println("Instructions: Press\n1. Add Student | 2. View All Students | 3. Search Student | 4. Update Student | 5. Delete Student | 6. Exit System");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println("-------------------------------------------------------------------------------------------------------------------");

                switch (choice) {

                    case 1:
                        System.out.println("Enter student details");
                        System.out.print("ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Name: ");
                        String name = scanner.nextLine();

                        if (name.isEmpty()) {
                            System.out.println("Name cannot be empty!");
                            break;
                        }

                        System.out.print("Age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();

                        if (age <= 0) {
                            System.out.println("Invalid age!");
                            break;
                        }

                        System.out.print("Course: ");
                        String course = scanner.nextLine();

                        boolean added = service.addStudent(new Student(id, name, age, course));

                        System.out.println(added ? "Student Added!" : "ID already exists!");
                        break;

                    case 2:
                        System.out.println("Id |  Name |  Age |  Course");
                        service.viewStudents();
                        break;

                    case 3:
                        System.out.print("Enter ID: ");
                        System.out.println(service.searchStudent(scanner.nextInt()));
                        break;

                    case 4:
                        System.out.print("ID: ");
                        int uid = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("New Name: ");
                        String uname = scanner.nextLine();

                        System.out.print("New Age: ");
                        int uage = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("New Course: ");
                        String ucourse = scanner.nextLine();

                        System.out.println(service.updateStudent(uid, uname, uage, ucourse)? "Updated!": "Student not found");
                        break;

                    case 5:
                        System.out.print("ID: ");
                        System.out.println(service.deleteStudent(scanner.nextInt())? "Deleted!": "Student not found");
                        break;

                    case 6:
                        scanner.close();
                        System.out.println("System exited.\nAll student data saved.");
                        System.exit(0);
                }

            }
            catch (Exception e) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
    }
}
