package hristo.com.controller;

import hristo.com.controller.console.ConsoleReader;
import hristo.com.model.School;
import hristo.com.model.SchoolClass;
import hristo.com.model.Student;
import hristo.com.service.SchoolClassService;
import hristo.com.service.StudentService;

import java.util.List;

public class SchoolClassController {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String SINGLE_SPACE = " ";
    private static final int MIN_VALUE_CLASS_MENU_CHOICE = 1;
    private static final int MAX_VALUE_CLASS_MENU_CHOICE = 7;
    private static final int MIN_VALUE_EDIT_CLASS_MENU_CHOICE = 1;
    private static final int MAX_VALUE_EDIT_CLASS_MENU_CHOICE = 6;
    private static final String INCORRECT_SCHOOL_CLASS_NAME = "You are entered incorrect school class name.";
    private static final String SCHOOL_CLASS_IS_REMOVED = "School class is removed.";
    private static final String INCORRECT_SCHOOL_CLASS = "Incorrect School Class.";
    private static final String CHOOSE_SCHOOL_CLASS_MESSAGE = NEW_LINE + "Please, choose a class from above.";
    private static final String CHOOSE_STUDENT_MESSAGE = NEW_LINE + "Please, choose a student from above.";
    private static final String SCHOOL_CLASS_IS_CREATED = "School class is created";
    private static final String SCHOOL_CLASS_IS_NOT_CREATED = "School class is not created. " +
            "The name of the class already exist.";
    private static final String INPUT_CLASS_NAME = "Please, enter the name of the class.";
    private static final String THE_NAME_IS_EDITED = "The name is edited.";
    private static final String THE_NAME_IS_NOT_EDITED = "The name is not edited.";
    private static final String ENTER_NEW_CLASS_NAME = "Please, enter the new school class name.";
    private static final String CHOOSE_A_TEACHER = NEW_LINE + "Please, choose a teacher from the list above.";
    private static final String TEACHER_OF_THE_CLASS_IS_EDITED = "Teacher of the class is edited.";
    private static final String TEACHER_OF_THE_CLASS_IS_NOT_EDITED = "Teacher of the class is not edited. Wrong input.";
    private static final String STUDENT_IS_SUCCESSFULLY_REMOVED_FROM_THE_CLASS = "Student is successfully" +
            " removed from the class.";
    private static final String STUDENT_IS_NOT_REMOVED_FROM_THE_CLASS = "Student is not removed from the class.";
    private static final String STUDENT_IS_SUCCESSFULLY_ADDED_TO_THE_CLASS = "Student is successfully added to the class.";
    private static final String STUDENT_IS_NOT_ADDED_TO_THE_CLASS = "Student is not added to the class.";
    private static final String CLASS_MENU_MESSAGE = NEW_LINE
            + "MAIN MENU" + NEW_LINE
            + "    SCHOOL CLASS MANAGEMENT" + NEW_LINE
            + "        1. All Class information" + NEW_LINE
            + "        2. Concrete Class information" + NEW_LINE
            + "        3. Create Class" + NEW_LINE
            + "        4. Edit Class" + NEW_LINE
            + "        5. Remove Class" + NEW_LINE
            + "        6. HELP" + NEW_LINE
            + "        7. BACK" + NEW_LINE
            + NEW_LINE
            + "Enter your choice here: ";
    private static final String EDIT_MENU_MESSAGE = NEW_LINE
            + "MAIN MENU" + NEW_LINE
            + "    SCHOOL CLASS MANAGEMENT" + NEW_LINE
            + "        EDIT SCHOOL CLASS" + NEW_LINE
            + "            1. Edit Name of the class" + NEW_LINE
            + "            2. Edit Teacher of the class" + NEW_LINE
            + "            3. Add Student to the class" + NEW_LINE
            + "            4. Remove Student from the class" + NEW_LINE
            + "            5. HELP" + NEW_LINE
            + "            6. BACK" + NEW_LINE
            + NEW_LINE
            + "Enter your choice here: ";

    private final SchoolClassService schoolClassService = new SchoolClassService();
    private final TeacherController teacherController = new TeacherController();

    public void showSchoolClassMenu() {
        System.out.println(CLASS_MENU_MESSAGE);
        int inputChoice = ConsoleReader.readIntegerInRange(MIN_VALUE_CLASS_MENU_CHOICE, MAX_VALUE_CLASS_MENU_CHOICE);

        switch (inputChoice) {
            case 1:
                showAllSchoolClassInformation();
                showSchoolClassMenu();
                break;
            case 2:
                showConcreteSchoolClassInformation();
                showSchoolClassMenu();
                break;
            case 3:
                createSchoolClass();
                showSchoolClassMenu();
                break;
            case 4:
                editSchoolClass();
                showSchoolClassMenu();
                break;
            case 5:
                removeSchoolClass();
                showSchoolClassMenu();
                break;
            case 6:
                System.out.println(AppController.WELCOME_MESSAGE);
                break;
            case 7:
        }
    }

    private void removeSchoolClass() {
        listAllSchoolClassNames();
        String schoolClassNameToRemove = ConsoleReader.getCorrectName(CHOOSE_SCHOOL_CLASS_MESSAGE);
        boolean isSchoolClassRemoved = schoolClassService.removeSchoolClass(schoolClassNameToRemove);

        if (isSchoolClassRemoved) {
            System.out.println(SCHOOL_CLASS_IS_REMOVED);
        } else {
            System.out.println(INCORRECT_SCHOOL_CLASS);
        }
    }

    private void showConcreteSchoolClassInformation() {
        listAllSchoolClassNames();
        String schoolClassName = ConsoleReader.getCorrectName(CHOOSE_SCHOOL_CLASS_MESSAGE);
        SchoolClass schoolClass = SchoolClassService.getSchoolClass(schoolClassName);

        if (schoolClass == null) {
            System.out.println(INCORRECT_SCHOOL_CLASS_NAME);
        } else {
            System.out.println(schoolClass);
        }
    }

    private void createSchoolClass() {
        String schoolClassName = ConsoleReader.getCorrectName(INPUT_CLASS_NAME);
        teacherController.listAllNamesOfTeachersWithoutSchoolClass();
        String nameOfTheTeacher = ConsoleReader.getCorrectName(CHOOSE_A_TEACHER);

        if (schoolClassService.createSchoolClass(schoolClassName, nameOfTheTeacher)) {
            System.out.println(SCHOOL_CLASS_IS_CREATED);
        } else {
            System.out.println(SCHOOL_CLASS_IS_NOT_CREATED);
        }

    }

    public void showAllSchoolClassInformation() {
        List<SchoolClass> schoolClasses = schoolClassService.getAllSchoolClasses();

        for (SchoolClass schoolClass : schoolClasses) {
            System.out.println(schoolClass.toString());
        }
    }

    public static void listAllSchoolClassNames() {
        for (SchoolClass schoolClass : School.getSchool().getSchoolClasses()) {
            System.out.print(schoolClass.getName() + SINGLE_SPACE);
        }
    }

    public static void listAllSchoolClassesWithoutTeacher() {
        for (SchoolClass schoolClass : School.getSchool().getSchoolClasses()) {
            if (schoolClass.getTeacherOfTheSchoolClass() == null) {
                System.out.print(schoolClass.getName() + SINGLE_SPACE);
            }
        }
    }

    private void editSchoolClass() {
        System.out.println(EDIT_MENU_MESSAGE);
        int inputChoice = ConsoleReader
                .readIntegerInRange(MIN_VALUE_EDIT_CLASS_MENU_CHOICE, MAX_VALUE_EDIT_CLASS_MENU_CHOICE);

        switch (inputChoice) {
            case 1:
                editNameOfTheSchoolClass();
                editSchoolClass();
                break;
            case 2:
                editTeacherOfTheClass();
                editSchoolClass();
                break;
            case 3:
                addStudentToTheClass();
                editSchoolClass();
                break;
            case 4:
                removeStudentFromAClass();
                editSchoolClass();
                break;
            case 5:
                System.out.println(AppController.WELCOME_MESSAGE);
                break;
            case 6:
        }
    }

    private void removeStudentFromAClass() {
        listAllSchoolClassNames();
        String schoolClassName = ConsoleReader.getCorrectName(CHOOSE_SCHOOL_CLASS_MESSAGE);
        listAllStudentsInTheClass(schoolClassName);
        String studentName = ConsoleReader.getCorrectName(CHOOSE_STUDENT_MESSAGE);

        if (SchoolClassService.removeStudentFromAClass(schoolClassName, studentName)) {
            System.out.println(STUDENT_IS_SUCCESSFULLY_REMOVED_FROM_THE_CLASS);
        } else {
            System.out.println(STUDENT_IS_NOT_REMOVED_FROM_THE_CLASS);
        }
    }

    private void addStudentToTheClass() {
        listAllSchoolClassNames();
        String schoolClassName = ConsoleReader.getCorrectName(CHOOSE_SCHOOL_CLASS_MESSAGE);
        StudentController.listStudentsWithoutSchoolClass();
        String studentName = ConsoleReader.getCorrectName(CHOOSE_STUDENT_MESSAGE);

        if (StudentService.addStudentToSchoolClass(studentName, schoolClassName)) {
            System.out.println(STUDENT_IS_SUCCESSFULLY_ADDED_TO_THE_CLASS);
        } else {
            System.out.println(STUDENT_IS_NOT_ADDED_TO_THE_CLASS);
        }
    }

    public static void listAllStudentsInTheClass(String className) {
        SchoolClass schoolClass = SchoolClassService.getSchoolClass(className);

        if (schoolClass != null) {
            for (Student student : schoolClass.getStudentsInClass()) {
                System.out.println(student.getName() + SINGLE_SPACE);
            }
        }
    }

    private void editTeacherOfTheClass() {
        listAllSchoolClassNames();
        String schoolClassName = ConsoleReader.getCorrectName(CHOOSE_SCHOOL_CLASS_MESSAGE);
        teacherController.listAllNamesOfTeachersWithoutSchoolClass();
        String teacherName = ConsoleReader.getCorrectName(CHOOSE_A_TEACHER);
        boolean isTeacherEdited = schoolClassService.editTeacherOfTheClass(schoolClassName, teacherName);

        if (isTeacherEdited) {
            System.out.println(TEACHER_OF_THE_CLASS_IS_EDITED);
        } else {
            System.out.println(TEACHER_OF_THE_CLASS_IS_NOT_EDITED);
        }
    }

    private void editNameOfTheSchoolClass() {
        listAllSchoolClassNames();
        String oldSchoolClassName = ConsoleReader.getCorrectName(INPUT_CLASS_NAME);
        String newSchoolClassName = ConsoleReader.getCorrectName(ENTER_NEW_CLASS_NAME);

        if (schoolClassService.editNameOfTheSchoolClass(oldSchoolClassName, newSchoolClassName)) {
            System.out.println(THE_NAME_IS_EDITED);
        } else {
            System.out.println(THE_NAME_IS_NOT_EDITED);
        }
    }
}
