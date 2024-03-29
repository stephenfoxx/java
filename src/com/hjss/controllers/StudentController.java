package com.hjss.controllers;

import com.hjss.enums.Gender;
import com.hjss.enums.Grade;
import com.hjss.exception.InvalidAgeException;
import com.hjss.models.Student;
import com.hjss.views.GenderView;
import com.hjss.views.GradeView;
import com.hjss.views.LoginStudentView;

import java.util.List;
import java.util.Scanner;

/**
 * Controller class responsible for managing student-related operations.
 */
public class StudentController {

    private final GenderView genderView;

    private final GradeView gradeView;

    private final List<Student> students;

    /**
     * Constructs a new StudentController with the provided list of students.
     *
     * @param st The list of students to be managed by this controller.
     */
    public StudentController(List<Student> st) {
        genderView = new GenderView();
        gradeView = new GradeView();

        this.students = st;
    }

    /**
     * Creates a new student object with the provided details.
     *
     * @param id            The ID of the student.
     * @param name          The name of the student.
     * @param grade         The grade of the student.
     * @param gender        The gender of the student.
     * @param contactNumber The Emergency contact number of the student.
     * @param age           The age of the student.
     *
     * @return The newly created student object.
     * @throws InvalidAgeException If the age provided is invalid.
     */
    private Student create(int id, String name, Grade grade, Gender gender, String contactNumber, int age) throws InvalidAgeException {
        Student newStudent = new Student(id, name, grade, gender, contactNumber);
        newStudent.setAge(age);

        return newStudent;
    }

    /**
     * Retrieves the gender enum value based on the user's choice.
     *
     * @param choice The integer representing the user's choice.
     * @return The corresponding gender enum value, or null if the choice is invalid.
     */
    private Gender getGenderFromChoice(int choice) {
        if (choice == 1) {
            return Gender.Male;
        } else if (choice == 2) {
            return Gender.Female;
        } else {
            return null;
        }
    }

    /**
     * Retrieves the grade enum value based on the user's choice.
     *
     * @param choice The integer representing the user's choice.
     * @return The corresponding grade enum value, or null if the choice is invalid.
     */
    private Grade getGradeFromChoice(int choice) {
        return switch (choice) {
            case 1 -> Grade.ONE;
            case 2 -> Grade.TWO;
            case 3 -> Grade.THREE;
            case 4 -> Grade.FOUR;
            case 5 -> Grade.FIVE;
            default -> null;
        };
    }

    /**
     * Creates a new student based on user input.
     *
     * @return The newly created student.
     * @throws InvalidAgeException If the age provided is invalid.
     */
    public Student register() throws InvalidAgeException {
        Scanner console = new Scanner(System.in);

        String name, contactNumber;
        Gender gender;
        short age;
        Grade grade;

        int id = students.size() + 1;


        // Get Name
        System.out.print("Enter name: ");
        name = console.nextLine();

        int option;

        // Get Gender
        genderView.displayMenu();
        option = genderView.getMenuChoice();
        gender = getGenderFromChoice(option);

        // Get Age
        System.out.println(" ");
        System.out.print("Enter age: ");
        age = console.nextShort();
        console.nextLine();

        // Get contact Number
        System.out.println(" ");
        System.out.print("Enter Emergency Contact Number: ");
        contactNumber = console.nextLine();

        // Get grade
        gradeView.displayMenu();
        option = gradeView.getMenuChoice();
        grade = getGradeFromChoice(option);

        Student ns = create(id, name, grade, gender, contactNumber, age);

        students.add(ns);

        System.out.println();
        System.out.println("\u001B[32mSuccess: Your registration was completed successfully\u001B[0m");
        System.out.println();
        System.out.println("Student Information: ");
        System.out.println(ns);

        return ns;
    }

    /**
     * Logs in a student by displaying the login menu, retrieving the user's choice,
     * and returning the corresponding student object.
     *
     * @return The logged-in student or null if the user chooses to exit or register a new student.
     */
    public Student login() {
        var loginStudentView = new LoginStudentView(students);
        loginStudentView.displayMenu();
        int choice = loginStudentView.getMenuChoice();

        if (choice == 0) System.exit(0);

        if (choice == students.size() + 1) {
            return null;
        }

        return students.get(choice - 1);
    }

    /**
     * Creates a predefined set of students and adds them to the student list.
     */
    public void createStudents() {
        students.add(new Student(1, "Elizabeth", Grade.ONE, Gender.Female, "123456", 4));
        students.add(new Student(2, "Amaka", Grade.TWO, Gender.Female, "1234567", 5));
        students.add(new Student(3, "Chibuzor", Grade.THREE, Gender.Male, "1234566", 6));
        students.add(new Student(4, "Jake", Grade.FOUR, Gender.Male, "1234567", 7));
        students.add(new Student(5, "John", Grade.FIVE, Gender.Male, "1234567", 8));
        students.add(new Student(6, "Mary", Grade.ONE, Gender.Female, "1234568", 8));
        students.add(new Student(7, "Michael", Grade.TWO, Gender.Male, "1234569", 9));
        students.add(new Student(8, "Sarah", Grade.THREE, Gender.Female, "1234570", 10));
        students.add(new Student(9, "David", Grade.FOUR, Gender.Male, "1234571", 11));
        students.add(new Student(10, "Jennifer", Grade.FIVE, Gender.Female, "1234572", 7));
        students.add(new Student(11, "Daniel", Grade.ONE, Gender.Male, "1234573", 8));
        students.add(new Student(12, "Jessica", Grade.TWO, Gender.Female, "1234574", 9));
        students.add(new Student(13, "Joseph", Grade.THREE, Gender.Male, "1234575", 10));
        students.add(new Student(14, "Sophia", Grade.FOUR, Gender.Female, "1234576", 11));
        students.add(new Student(15, "Ethan", Grade.FIVE, Gender.Male, "1234577", 7));
    }


    /**
     * Retrieves the list of students managed by this controller.
     *
     * @return The list of students.
     */
    public List<Student> getStudents() {
        return students;
    }
}
