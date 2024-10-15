import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MiniProject {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<FacultyMember> facultyMembers = new ArrayList<>();

    public void addStudent(String name, int year) {
        Student student = new Student(name, year);
        students.add(student);
    }

    public void addFaculty(String name, int employeeId) {
        FacultyMember faculty = new FacultyMember(name, employeeId);
        facultyMembers.add(faculty);
    }

    public void displayStudentsByYear() {
        HashMap<Integer, Integer> yearCountMap = new HashMap<>();

        for (Student student : students) {
            int year = student.getYear();
            yearCountMap.put(year, yearCountMap.getOrDefault(year, 0) + 1);
        }

        System.out.println("Students by Year:");
        for (Map.Entry<Integer, Integer> entry : yearCountMap.entrySet()) {
            System.out.println("Year " + entry.getKey() + ": " + entry.getValue() + " students");
        }
    }

    public void displayFacultyInDescendingOrder() {
        Collections.sort(facultyMembers, new Comparator<FacultyMember>() {
            @Override
            public int compare(FacultyMember faculty1, FacultyMember faculty2) {
                return Integer.compare(faculty2.getEmployeeId(), faculty1.getEmployeeId());
            }
        });

        System.out.println("Faculty Members in Descending Order of Employee IDs:");
        for (FacultyMember faculty : facultyMembers) {
            System.out.println(faculty.toString());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MiniProject edicSystem = new MiniProject();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Add Faculty");
            System.out.println("3. Display Students by Year");
            System.out.println("4. Display Faculty in Descending Order");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter year of participation: ");
                    int studentYear = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    edicSystem.addStudent(studentName, studentYear);
                    break;
                case 2:
                    System.out.print("Enter faculty name: ");
                    String facultyName = scanner.nextLine();
                    System.out.print("Enter employee ID: ");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine();
                    edicSystem.addFaculty(facultyName, employeeId);
                    break;
                case 3:
                    edicSystem.displayStudentsByYear();
                    break;
                case 4:
                    edicSystem.displayFacultyInDescendingOrder();
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

class Student {
    private String name;
    private int year;
    private boolean[] surveyResponses;

    public Student(String name, int year) {
        this.name = name;
        this.year = year;
        this.surveyResponses = new boolean[5]; // Assuming 5 Yes/No questions in the survey
    }

    public void setSurveyResponse(int questionNumber, boolean response) {
        if (questionNumber >= 0 && questionNumber < surveyResponses.length) {
            surveyResponses[questionNumber] = response;
        }
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Year: " + year;
    }
}

class FacultyMember {
    private String name;
    private int employeeId;

    public FacultyMember(String name, int employeeId) {
        this.name = name;
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Employee ID: " + employeeId;
    }
}