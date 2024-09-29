package hristo.com.service;

import hristo.com.controller.console.ConsoleReader;
import hristo.com.model.School;

import java.util.Scanner;

public class SchoolService {

    private static final Scanner scanner = new Scanner(System.in);


    public School getSchool(String inputMessage) {
        String schoolName;

        if (School.getSchool().getName() == null) {
            schoolName = ConsoleReader.getCorrectName(inputMessage);
            School.getSchool().setName(schoolName);
        }

        return School.getSchool();
    }
}
