package util;

import java.io.*;
import java.util.ArrayList;
import model.Student;

public class FileHandler {

    private static final String FILE_NAME = "students.csv";

    // SAVE DATA
    public static void save(ArrayList<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                bw.write(s.toString());
                bw.newLine();
            }
        }
        catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // LOAD DATA
    public static ArrayList<Student> load() {
        ArrayList<Student> students = new ArrayList<>();

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return students;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" | ");
                students.add( new Student(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), data[3]));
            }
        }
        catch (IOException e) {
            System.out.println("Error loading data.");
        }

        return students;
    }
}
