import java.util.*;
import java.io.*;

public class StudentManager {
    private ArrayList<Student> studentList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n====== Student Record Management ======");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit and Save");
            System.out.println("6. Search Student");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> System.out.println("Exiting...");
                case 6 -> searchStudent();
                default -> System.out.println("Invalid choice, try again.");
            }
        } while (choice != 5);
    }

    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double marks = Double.parseDouble(parts[2]);
                studentList.add(new Student(id, name, marks));
            }
            System.out.println("Loaded student records from file.");
        } catch (IOException e) {
            System.out.println("No existing data found. Starting fresh.");
        }
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.csv"))) {
            for (Student s : studentList) {
                writer.write(s.getId() + "," + s.getName() + "," + s.getMarks());
                writer.newLine();
            }
            System.out.println("Student records saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving student records.");
        }
    }

    private void addStudent() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Marks: ");
        double marks = scanner.nextDouble();

        studentList.add(new Student(id, name, marks));
        System.out.println("Student added successfully!");
    }

    private void viewStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            for (Student s : studentList) {
                System.out.println(s);
            }
        }
    }

    private void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Student s : studentList) {
            if (s.getId() == id) {
                System.out.print("Enter new name: ");
                s.setName(scanner.nextLine());
                System.out.print("Enter new marks: ");
                s.setMarks(scanner.nextDouble());
                System.out.println("Student updated successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();

        boolean removed = studentList.removeIf(s -> s.getId() == id);
        if (removed) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private void searchStudent() {
        System.out.println("\nSearch by:");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter Student ID: ");
                int id = scanner.nextInt();
                boolean found = false;
                for (Student s : studentList) {
                    if (s.getId() == id) {
                        System.out.println("Student Found: " + s);
                        found = true;
                        break;
                    }
                }
                if (!found) System.out.println("No student found with ID: " + id);
            }
            case 2 -> {
                System.out.print("Enter Student Name: ");
                String name = scanner.nextLine().toLowerCase();
                boolean found = false;
                for (Student s : studentList) {
                    if (s.getName().toLowerCase().contains(name)) {
                        System.out.println("Student Found: " + s);
                        found = true;
                    }
                }
                if (!found) System.out.println("No student found with name containing: " + name);
            }
            default -> System.out.println("Invalid choice.");
        }
    }
}
