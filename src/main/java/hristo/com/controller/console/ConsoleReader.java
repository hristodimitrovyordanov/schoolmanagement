package hristo.com.controller.console;

import hristo.com.model.Subject;

import java.util.Scanner;

public class ConsoleReader {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String INVALID_INT_MESSAGE = "%s is not an integer. Please try again." + NEW_LINE;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INVALID_RANGE_MESSAGE = "Min value cannot exceed max value." + NEW_LINE;
    private static final String NOT_WITHIN_RANGE_MESSAGE = "Input must be between %d and %d" + NEW_LINE;
    private static final String EMPTY_STRING = "";
    private static final String WHITE_SPACE = "\\s+";
    private static final String SELECT_A_SUBJECT_MESSAGE = NEW_LINE + "Please select a subject from the list, " +
            "or write 'back' to set 'subject' to 'null' and return to previous menu.";
    private static final String BACK = "back";

    public static int readInt() {
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.printf(INVALID_INT_MESSAGE, input);
        }

        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public static int readIntegerInRange(int minValue, int maxValue) {

        if (minValue > maxValue) {
            throw new IllegalArgumentException(INVALID_RANGE_MESSAGE);
        }

        int input;
        boolean isWithinRange;

        do {
            input = ConsoleReader.readInt();
            isWithinRange = (input >= minValue) && (input <= maxValue);

            if (!isWithinRange) {
                System.out.printf(NOT_WITHIN_RANGE_MESSAGE, minValue, maxValue);
            }
        } while (!isWithinRange);

        return input;
    }

    public static String getCorrectName(String message) {
        String name = EMPTY_STRING;

        while (isWhiteSpace(name)) {
            System.out.println(message);
            name = ConsoleReader.readString();
        }

        return name;
    }

    public static boolean isWhiteSpace(String string) {
        string = string.replaceAll(WHITE_SPACE, EMPTY_STRING);
        return string.equals(EMPTY_STRING);
    }

    public static String readString() {
        return scanner.nextLine();
    }

    public static Subject getSubject() {
        Subject subjectToReturn = null;
        boolean isNotSubjectSelected = true;

        while (isNotSubjectSelected) {
            for (Subject subject : Subject.values()) {
                System.out.print(subject + " ");
            }

            String subjectName = getCorrectName(SELECT_A_SUBJECT_MESSAGE);

            if (subjectName.equalsIgnoreCase(BACK)) {
                break;
            }

            for (Subject subject : Subject.values()) {
                if (subjectName.equals(subject.toString())) {
                    subjectToReturn = subject;
                    isNotSubjectSelected = false;
                }
            }
        }

        return subjectToReturn;
    }
}
