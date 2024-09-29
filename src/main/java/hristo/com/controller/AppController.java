package hristo.com.controller;

import hristo.com.controller.console.ConsoleReader;
import hristo.com.model.School;

import java.util.Scanner;

public class AppController {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NEW_LINE = System.lineSeparator();
    private static final int MIN_VALUE_MENU_CHOICE = 1;
    private static final int MAX_VALUE_MENU_CHOICE = 7;
    private static final String SHUT_DOWN_MESSAGE = "Program is shutting down.";
    private static final String MAIN_MENU_MESSAGE = NEW_LINE
            + "MAIN MENU" + NEW_LINE
            + "    1. Info for the school" + NEW_LINE
            + "    2. School Class Management" + NEW_LINE
            + "    3. Teacher Management" + NEW_LINE
            + "    4. Student Management" + NEW_LINE
            + "    5. Diary Management" + NEW_LINE
            + "    6. HELP" + NEW_LINE
            + "    7. EXIT" + NEW_LINE
            + NEW_LINE
            + "Enter your choice here: ";

    private final SchoolController schoolController = new SchoolController();
    private final SchoolClassController schoolClassController = new SchoolClassController();
    private final TeacherController teacherController = new TeacherController();
    private final StudentController studentController = new StudentController();
    private final DiaryController diaryController = new DiaryController();

    public static final String WELCOME_MESSAGE = NEW_LINE
            + "Welcome to School Management Application" + NEW_LINE
            + "Please, first enter a school name and then" + NEW_LINE
            + "choose a menu to create teachers, classes and students." + NEW_LINE
            + "To choose some action from menu, you must enter the number in front of the action." + NEW_LINE
            + "You can create classes with or without class teacher." + NEW_LINE
            + "If you create a class without a class teacher (if there is no created " + NEW_LINE
            + "teachers for example) it can be added later." + NEW_LINE
            + "When the class is created, the program creates also and" + NEW_LINE
            + "an empty diary for this class. In this diary, you can add students later." + NEW_LINE
            + "You can create student and add it to the class during creation " + NEW_LINE
            + "and this will add the student in the diary of the class."
            + "Also you can add a student later to some class." + NEW_LINE
            + "On each student you can add grades for different school subjects." + NEW_LINE
            + "There is an option to remove last grade from some school subject." + NEW_LINE
            + "If you need to read this message again, " + NEW_LINE
            + "it will be available in every manu as 'HELP'." + NEW_LINE;

    public void start() {
        try {
            while (!School.isSchoolCreated) {
                System.out.println(WELCOME_MESSAGE);
                schoolController.createSchool();
            }

            showMainMenu();
        } finally {
            SCANNER.close();
        }
    }

    private void showMainMenu() {
        while (true) {
            System.out.print(MAIN_MENU_MESSAGE);
            int input = ConsoleReader.readIntegerInRange(MIN_VALUE_MENU_CHOICE, MAX_VALUE_MENU_CHOICE);

            switch (input) {
                case 1:
                    schoolController.showSchoolInformation();
                    break;
                case 2:
                    schoolClassController.showSchoolClassMenu();
                    break;
                case 3:
                    teacherController.showTeacherMenu();
                    break;
                case 4:
                    studentController.showStudentMenu();
                    break;
                case 5:
                    diaryController.showDiaryMenu();
                    break;
                case 6:
                    System.out.println(WELCOME_MESSAGE);
                    break;
                case 7:
                    System.out.println(SHUT_DOWN_MESSAGE);
                    return;
            }
        }
    }
}
