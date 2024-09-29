package hristo.com.controller;

import hristo.com.controller.console.ConsoleReader;
import hristo.com.model.School;
import hristo.com.model.Subject;
import hristo.com.model.Teacher;
import hristo.com.service.TeacherService;

import java.util.List;

public class TeacherController {

    private static final String TEACHER_HAS_A_CLASS_IS_EDITED = "Teacher has a class is edited.";
    private static final String TEACHER_HAS_A_CLASS_IS_NOT_EDITED = "Teacher has a class is not edited.";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SINGLE_SPACE = " ";
    private static final String THIS_IS_A_LIST_OF_ALL_TEACHERS = "This is a list of all teachers.";
    private static final String THIS_IS_A_LIST_OF_ALL_TEACHERS_WITHOUT_SCHOOL_CLASS =
            "This is a list of all teachers without a school class.";
    private static final String HAS_A_CLASS_MESSAGE_TO_CHOOSE_A_TEACHER = "If you choose a teacher without school class,"
            + NEW_LINE + "a class without teacher will be selected (if exist). "
            + NEW_LINE + "But if you choose a teacher with a class,"
            + NEW_LINE + "teacher will be removed from the class automatically.";
    private static final String TEACHER_IS_CREATED = "Teacher is created";
    private static final String INPUT_TEACHER_NAME = "Please, enter a name for the teacher.";
    private static final String CHOOSE_A_TEACHER = NEW_LINE + "Please, choose a teacher from the list above.";
    private static final String INCORRECT_TEACHER_NAME = "Incorrect teacher name.";
    private static final String TEACHER_IS_REMOVED = "Teacher is removed.";
    private static final String TEACHER_IS_NOT_REMOVED = "Teacher is not removed.";
    private static final String TEACHER_IS_NOT_CREATED = "Teacher is not created. " +
            "The name of the teacher already exist.";
    private static final String CHOOSE_A_CLASS_FOR_THE_TEACHER_IF_IT_NECESSARY = "Please, choose a class without teacher," +
            "if it necessary";
    private static final String INPUT_NEW_TEACHER_NAME = "Please, enter the new name of the teacher.";
    private static final String THE_NAME_IS_EDITED = "The name of the teacher is edited.";
    private static final String THE_NAME_IS_NOT_EDITED = "The name of the teacher is not edited.";
    private static final String SUBJECT_IS_EDITED = "The subject is edited";
    private static final String SUBJECT_IS_NOT_EDITED = "The subject is not edited";
    private static final String TEACHER_MENU_MESSAGE = NEW_LINE
            + "MAIN MENU" + NEW_LINE
            + "    TEACHER MANAGEMENT" + NEW_LINE
            + "        1. All Teachers information" + NEW_LINE
            + "        2. Concrete Teacher information" + NEW_LINE
            + "        3. Create Teacher" + NEW_LINE
            + "        4. Edit Teacher" + NEW_LINE
            + "        5. Remove Teacher" + NEW_LINE
            + "        6. HELP" + NEW_LINE
            + "        7. BACK" + NEW_LINE
            + NEW_LINE
            + "Enter your choice here: ";
    private static final String EDIT_MENU_MESSAGE = NEW_LINE
            + "MAIN MENU" + NEW_LINE
            + "    TEACHER MANAGEMENT" + NEW_LINE
            + "        EDIT TEACHER" + NEW_LINE
            + "            1. Edit Name of the teacher" + NEW_LINE
            + "            2. Edit Subject of the teacher" + NEW_LINE
            + "            3. Edit teacher 'Has a class'" + NEW_LINE
            + "            4. HELP" + NEW_LINE
            + "            5. BACK" + NEW_LINE
            + NEW_LINE
            + "Enter your choice here: ";
    private static final int MIN_VALUE_SUBMENU_CHOICE = 1;
    private static final int MAX_VALUE_SUBMENU_CHOICE = 7;
    private static final int MIN_VALUE_EDIT_TEACHER_MENU_CHOICE = 1;
    private static final int MAX_VALUE_EDIT_TEACHER_MENU_CHOICE = 5;

    private final TeacherService teacherService = new TeacherService();

    public void showTeacherMenu() {
        System.out.println(TEACHER_MENU_MESSAGE);
        int inputChoice = ConsoleReader.readIntegerInRange(MIN_VALUE_SUBMENU_CHOICE, MAX_VALUE_SUBMENU_CHOICE);

        switch (inputChoice) {
            case 1:
                showAllTeacherInformation();
                showTeacherMenu();
                break;
            case 2:
                showConcreteTeacherInformation();
                showTeacherMenu();
                break;
            case 3:
                createTeacher();
                showTeacherMenu();
                break;
            case 4:
                editTeacher();
                showTeacherMenu();
                break;
            case 5:
                removeTeacher();
                showTeacherMenu();
                break;
            case 6:
                System.out.println(AppController.WELCOME_MESSAGE);
                break;
            case 7:
        }
    }

    private void removeTeacher() {
        System.out.println();
        listAllNamesOfTeachers();
        String nameOfTheTeacherToRemove = ConsoleReader.getCorrectName(CHOOSE_A_TEACHER);
        boolean isTeacherRemoved = teacherService.removeTeacher(nameOfTheTeacherToRemove);

        if (isTeacherRemoved) {
            System.out.println(TEACHER_IS_REMOVED);
        } else {
            System.out.println(TEACHER_IS_NOT_REMOVED);
        }
    }

    private void editTeacher() {
        System.out.println(EDIT_MENU_MESSAGE);
        int inputChoice = ConsoleReader
                .readIntegerInRange(MIN_VALUE_EDIT_TEACHER_MENU_CHOICE, MAX_VALUE_EDIT_TEACHER_MENU_CHOICE);

        switch (inputChoice) {
            case 1:
                editNameOfTheTeacher();
                editTeacher();
                break;
            case 2:
                editSubjectOfTheTeacher();
                editTeacher();
                break;
            case 3:
                editTeacherHasAClass();
                editTeacher();
                break;
            case 4:
                System.out.println(AppController.WELCOME_MESSAGE);
                break;
            case 5:
        }
    }

    private void editTeacherHasAClass() {
        System.out.println(THIS_IS_A_LIST_OF_ALL_TEACHERS);
        listAllNamesOfTeachers();
        System.out.println(NEW_LINE + THIS_IS_A_LIST_OF_ALL_TEACHERS_WITHOUT_SCHOOL_CLASS);
        listAllNamesOfTeachersWithoutSchoolClass();
        System.out.println(NEW_LINE + HAS_A_CLASS_MESSAGE_TO_CHOOSE_A_TEACHER);
        String teacherName = ConsoleReader.getCorrectName(CHOOSE_A_TEACHER);
        SchoolClassController.listAllSchoolClassesWithoutTeacher();
        String className = ConsoleReader.getCorrectName(CHOOSE_A_CLASS_FOR_THE_TEACHER_IF_IT_NECESSARY);

        if (teacherService.editTeacherHasAClass(teacherName, className)) {
            System.out.println(TEACHER_HAS_A_CLASS_IS_EDITED);
        } else {
            System.out.println(TEACHER_HAS_A_CLASS_IS_NOT_EDITED);
        }
    }

    private void editSubjectOfTheTeacher() {
        listAllNamesOfTeachers();
        String teacherName = ConsoleReader.getCorrectName(CHOOSE_A_TEACHER);
        Subject newSubject = ConsoleReader.getSubject();

        if (teacherService.editSubjectOfTheTeacher(teacherName, newSubject)) {
            System.out.println(SUBJECT_IS_EDITED);
        } else {
            System.out.println(SUBJECT_IS_NOT_EDITED);
        }
    }

    private void editNameOfTheTeacher() {
        listAllNamesOfTeachers();
        String oldTeacherName = ConsoleReader.getCorrectName(CHOOSE_A_TEACHER);
        String newTeacherName = ConsoleReader.getCorrectName(INPUT_NEW_TEACHER_NAME);

        if (teacherService.editNameOfTheTeacher(oldTeacherName, newTeacherName)) {
            System.out.println(THE_NAME_IS_EDITED);
        } else {
            System.out.println(THE_NAME_IS_NOT_EDITED);
        }
    }

    private void createTeacher() {
        String name = ConsoleReader.getCorrectName(INPUT_TEACHER_NAME);
        Subject subject = ConsoleReader.getSubject();

        if (teacherService.createTeacher(name, subject)) {
            System.out.println(TEACHER_IS_CREATED);
        } else {
            System.out.println(TEACHER_IS_NOT_CREATED);
        }
    }

    private void showConcreteTeacherInformation() {
        listAllNamesOfTeachers();
        String teacherName = ConsoleReader.getCorrectName(CHOOSE_A_TEACHER);
        Teacher teacher = teacherService.getTeacher(teacherName);

        if (teacher == null) {
            System.out.println(INCORRECT_TEACHER_NAME);
        } else {
            System.out.println(teacher);
        }
    }

    public void showAllTeacherInformation() {
        List<Teacher> teachers = teacherService.getAllTeachers();

        for (Teacher teacher : teachers) {
            System.out.println(teacher.toString());
        }
    }

    public void listAllNamesOfTeachers() {
        for (Teacher teacher : School.getSchool().getTeachers()) {
            System.out.print(teacher.getName() + SINGLE_SPACE);
        }
    }

    public void listAllNamesOfTeachersWithoutSchoolClass() {
        for (Teacher teacher : School.getSchool().getTeachers()) {
            if (!teacher.doesTheTeacherHaveAClass()) {
                System.out.print(teacher.getName() + SINGLE_SPACE);
            }
        }
    }
}
