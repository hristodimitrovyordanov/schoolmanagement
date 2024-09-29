package hristo.com.controller;

import hristo.com.service.SchoolService;

public class SchoolController {

    private static final String INPUT_SCHOOL_NAME = "Please, enter the name of the school: ";

    private final SchoolService schoolService = new SchoolService();
    private final SchoolClassController schoolClassController = new SchoolClassController();
    private final TeacherController teacherController = new TeacherController();
    private final StudentController studentController = new StudentController();

    public void showSchoolInformation() {
        System.out.println(schoolService.getSchool(INPUT_SCHOOL_NAME));
        schoolClassController.showAllSchoolClassInformation();
        teacherController.showAllTeacherInformation();
        studentController.showAllStudentInformation();
    }

    public void createSchool() {
        System.out.println(schoolService.getSchool(INPUT_SCHOOL_NAME));
    }
}
