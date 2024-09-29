package hristo.com.controller;

import hristo.com.controller.console.ConsoleReader;
import hristo.com.model.Diary;
import hristo.com.model.School;
import hristo.com.model.Student;
import hristo.com.model.Subject;
import hristo.com.service.DiaryService;
import hristo.com.service.StudentService;

import java.util.List;
import java.util.Map;

public class DiaryController {

    private static final String NEW_LINE = System.lineSeparator();
    private static final int MIN_VALUE_SUBMENU_CHOICE = 1;
    private static final int MAX_VALUE_SUBMENU_CHOICE = 6;
    private static final int GRADE_MIN_VALUE = 1;
    private static final int GRADE_MAX_VALUE = 6;
    private static final String CHOOSE_SCHOOL_CLASS_MESSAGE = NEW_LINE + "Please, choose a class from above.";
    private static final String CHOOSE_STUDENT_MESSAGE = NEW_LINE + "Please, choose a student from above.";
    private static final String THE_CLASS_DOES_NOT_EXIST = "The class does not exist.";
    private static final String THE_STUDENT_DOES_NOT_EXIST = "The student does not exist.";
    private static final String GRADE_IS_ADDED = "The grade of the student is added.";
    private static final String GRADE_IS_NOT_ADDED = "The grade of the student is not added.";
    private static final String SINGLE_SPACE = " ";
    private static final String TWO_TABS_SPACE = "        ";
    private static final String CHOSEN_SUBJECT_IS_CORRECT = "Chosen subject is correct.";
    private static final String CHOSEN_SUBJECT_IS_NOT_CORRECT = "Chosen subject is not correct.";
    private static final String REMOVE_LAST_GRADE_MESSAGE = "When you select a subject, this action " + NEW_LINE +
            "will remove the last grade from this subject.";
    private static final String THE_GRADE_IS_EDITED = "The grade is edited.";
    private static final String THE_GRADE_IS_NOT_EDITED = "The grade is not edited.";
    private static final String PLEASE_ENTER_A_GRADE = "Please enter a grade between " + GRADE_MIN_VALUE
            + " and " + GRADE_MAX_VALUE + ": ";
    private static final String STUDENT_MENU_MESSAGE = NEW_LINE
            + "MAIN MENU" + NEW_LINE
            + "    DIARY MANAGEMENT" + NEW_LINE
            + "        1. All Diary information" + NEW_LINE
            + "        2. Concrete Diary information" + NEW_LINE
            + "        3. Add grade to student" + NEW_LINE
            + "        4. Remove grade from a student" + NEW_LINE
            + "        5. HELP" + NEW_LINE
            + "        6. BACK" + NEW_LINE
            + NEW_LINE
            + "Enter your choice here: ";

    private final DiaryService diaryService = new DiaryService();

    public void showDiaryMenu() {
        System.out.println(STUDENT_MENU_MESSAGE);
        int inputChoice = ConsoleReader.readIntegerInRange(MIN_VALUE_SUBMENU_CHOICE, MAX_VALUE_SUBMENU_CHOICE);

        switch (inputChoice) {
            case 1:
                showAllDiaries();
                showDiaryMenu();
                break;
            case 2:
                showSchoolClassDiary();
                showDiaryMenu();
                break;
            case 3:
                addGradeToStudent();
                showDiaryMenu();
                break;
            case 4:
                removeGrade();
                showDiaryMenu();
                break;
            case 5:
                System.out.println(AppController.WELCOME_MESSAGE);
                break;
            case 6:
        }
    }

    private void removeGrade() {
        SchoolClassController.listAllSchoolClassNames();
        String schoolClassName = ConsoleReader.getCorrectName(CHOOSE_SCHOOL_CLASS_MESSAGE);
        editConcreteSchoolClassDiary(schoolClassName);
    }

    private void editConcreteSchoolClassDiary(String schoolClassName) {
        SchoolClassController.listAllStudentsInTheClass(schoolClassName);
        String studentName = ConsoleReader.getCorrectName(CHOOSE_STUDENT_MESSAGE);

        if (StudentService.getStudent(studentName) != null) {
            System.out.println(REMOVE_LAST_GRADE_MESSAGE);
            Subject subject = ConsoleReader.getSubject();

            if (subject != null) {
                System.out.println(CHOSEN_SUBJECT_IS_CORRECT);
            } else {
                System.out.println(CHOSEN_SUBJECT_IS_NOT_CORRECT);
            }

            if (diaryService.removeLastGradeOfSubject(schoolClassName, studentName, subject)) {
                System.out.println(THE_GRADE_IS_EDITED);
            } else {
                System.out.println(THE_GRADE_IS_NOT_EDITED);
            }
        }
    }

    private void showAllDiaries() {
        for (Diary diary : School.getSchool().getDiaries()) {
            System.out.println(diary.getSchoolClassName());
            listConcreteSchoolClass(diary);
        }
    }

    private void addGradeToStudent() {
        SchoolClassController.listAllSchoolClassNames();
        String schoolClassName = ConsoleReader.getCorrectName(CHOOSE_SCHOOL_CLASS_MESSAGE);
        Diary diary = diaryService.getDiary(schoolClassName);

        if (diary != null) {
            SchoolClassController.listAllStudentsInTheClass(schoolClassName);
            String studentName = ConsoleReader.getCorrectName(CHOOSE_STUDENT_MESSAGE);

            if (StudentService.getStudent(studentName) != null) {
                Subject subject = ConsoleReader.getSubject();

                if (subject != null) {
                    System.out.println(CHOSEN_SUBJECT_IS_CORRECT);
                } else {
                    System.out.println(CHOSEN_SUBJECT_IS_NOT_CORRECT);
                }

                System.out.println(PLEASE_ENTER_A_GRADE);
                int grade = ConsoleReader.readIntegerInRange(GRADE_MIN_VALUE, GRADE_MAX_VALUE);

                if (diaryService.addGradeToStudent(schoolClassName, studentName, subject, grade)) {
                    System.out.println(GRADE_IS_ADDED);
                } else {
                    System.out.println(GRADE_IS_NOT_ADDED);
                }
            } else {
                System.out.println(THE_STUDENT_DOES_NOT_EXIST);
            }
        } else {
            System.out.println(THE_CLASS_DOES_NOT_EXIST);
        }
    }

    private void showSchoolClassDiary() {
        SchoolClassController.listAllSchoolClassNames();
        String schoolClassName = ConsoleReader.getCorrectName(CHOOSE_SCHOOL_CLASS_MESSAGE);
        Diary diary = diaryService.getDiary(schoolClassName);

        if (diary != null) {
            listConcreteSchoolClass(diary);
        } else {
            System.out.println(THE_CLASS_DOES_NOT_EXIST);
        }
    }

    private void listConcreteSchoolClass(Diary diary) {
        for (Map.Entry<Student, Map<Subject, List<Integer>>> entry : diary.getDiaryOfClass().entrySet()) {
            System.out.println(entry.getKey().getName());
            Map<Subject, List<Integer>> gradesInSubjects = entry.getValue();

            for (Map.Entry<Subject, List<Integer>> subjectEntry : gradesInSubjects.entrySet()) {
                System.out.print(TWO_TABS_SPACE + subjectEntry.getKey() + SINGLE_SPACE + SINGLE_SPACE);

                for (Integer grade : subjectEntry.getValue()) {
                    System.out.print(grade + SINGLE_SPACE);
                }

                System.out.println();
            }
        }
    }

}
