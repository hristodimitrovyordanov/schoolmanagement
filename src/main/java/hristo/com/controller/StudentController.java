package hristo.com.controller;

import hristo.com.controller.console.ConsoleReader;
import hristo.com.model.School;
import hristo.com.model.Student;
import hristo.com.service.StudentService;

import java.util.List;

public class StudentController {

    private final StudentService studentService = new StudentService();

    private static final String NEW_LINE = System.lineSeparator();
    private static final String SINGLE_SPACE = " ";
    private static final String CHOOSE_A_STUDENT = NEW_LINE + "Please, choose a student from the list above.";
    private static final String INCORRECT_STUDENT_NAME = "Incorrect student name.";
    private static final String INPUT_STUDENT_NAME = NEW_LINE + "Please, enter a name for the student.";
    private static final String INPUT_NEW_STUDENT_NAME = NEW_LINE + "Please, enter a new name for the student.";
    private static final String CHOOSE_A_CLASS = NEW_LINE + "Please, choose a class for the student from above." + NEW_LINE +
            "If school class is not selected, then it can be added later.";
    private static final String STUDENT_IS_CREATED = "The student is created.";
    private static final String STUDENT_IS_NOT_CREATED = "The student is not created.";
    private static final String STUDENT_IS_REMOVED = "Student is removed.";
    private static final String STUDENT_IS_NOT_REMOVED = "Student is not removed.";
    private static final String STUDENT_IS_EDITED = "Student is edited.";
    private static final String STUDENT_IS_NOT_EDITED = "Student is not edited.";
    private static final String STUDENT_MENU_MESSAGE = NEW_LINE
            + "MAIN MENU" + NEW_LINE
            + "    STUDENT MANAGEMENT" + NEW_LINE
            + "        1. All Students information" + NEW_LINE
            + "        2. Concrete Student information" + NEW_LINE
            + "        3. Create Student" + NEW_LINE
            + "        4. Edit Student" + NEW_LINE
            + "        5. Remove Student" + NEW_LINE
            + "        6. HELP" + NEW_LINE
            + "        7. BACK" + NEW_LINE
            + NEW_LINE
            + "Enter your choice here: ";

    private static final int MIN_VALUE_SUBMENU_CHOICE = 1;
    private static final int MAX_VALUE_SUBMENU_CHOICE = 7;

    public void showStudentMenu() {
        System.out.println(STUDENT_MENU_MESSAGE);
        int inputChoice = ConsoleReader.readIntegerInRange(MIN_VALUE_SUBMENU_CHOICE, MAX_VALUE_SUBMENU_CHOICE);

        switch (inputChoice) {
            case 1:
                showAllStudentInformation();
                showStudentMenu();
                break;
            case 2:
                showConcreteStudentInformation();
                showStudentMenu();
                break;
            case 3:
                createStudent();
                showStudentMenu();
                break;
            case 4:
                editStudent();
                showStudentMenu();
                break;
            case 5:
                removeStudent();
                showStudentMenu();
                break;
            case 6:
                System.out.println(AppController.WELCOME_MESSAGE);
                break;
            case 7:
        }
    }

    private void editStudent() {
        listAllNamesOfStudents();
        String studentName = ConsoleReader.getCorrectName(INPUT_STUDENT_NAME);
        String newStudentName = ConsoleReader.getCorrectName(INPUT_NEW_STUDENT_NAME);

        if (studentService.editStudent(studentName, newStudentName)) {
            System.out.println(STUDENT_IS_EDITED);
        } else {
            System.out.println(STUDENT_IS_NOT_EDITED);
        }
    }

    private void removeStudent() {
        listAllNamesOfStudents();
        String studentName = ConsoleReader.getCorrectName(INPUT_STUDENT_NAME);

        if (studentService.removeStudent(studentName)) {
            System.out.println(STUDENT_IS_REMOVED);
        } else {
            System.out.println(STUDENT_IS_NOT_REMOVED);
        }
    }

    private void createStudent() {
        String studentName = ConsoleReader.getCorrectName(INPUT_STUDENT_NAME);
        SchoolClassController.listAllSchoolClassNames();
        String schoolClassName = ConsoleReader.getCorrectName(CHOOSE_A_CLASS);

        if (studentService.createStudent(studentName, schoolClassName)) {
            System.out.println(STUDENT_IS_CREATED);
        } else {
            System.out.println(STUDENT_IS_NOT_CREATED);
        }
    }

    private void showConcreteStudentInformation() {
        listAllNamesOfStudents();
        String studentName = ConsoleReader.getCorrectName(CHOOSE_A_STUDENT);
        Student student = StudentService.getStudent(studentName);

        if (student == null) {
            System.out.println(INCORRECT_STUDENT_NAME);
        } else {
            System.out.println(student);
        }
    }

    private void listAllNamesOfStudents() {
        for (Student student : School.getSchool().getStudents()) {
            System.out.print(student.getName() + SINGLE_SPACE);
        }
    }

    public static void listStudentsWithoutSchoolClass() {
        for (Student student : School.getSchool().getStudents()) {
            if (student.getSchoolClassName() == null) {
                System.out.print(student.getName() + SINGLE_SPACE);
            }
        }
    }

    public void showAllStudentInformation() {
        List<Student> students = studentService.getAllStudents();

        for (Student student : students) {
            System.out.println(student.toString());
        }
    }
}
