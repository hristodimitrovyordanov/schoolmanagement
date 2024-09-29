package hristo.com.service;

import hristo.com.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {

    public List<Student> getAllStudents() {
        return School.getSchool().getStudents();
    }

    public static Student getStudent(String nameStudent) {
        for (Student student : School.getSchool().getStudents()) {
            if (student.getName().equals(nameStudent)) {
                return student;
            }
        }

        return null;
    }

    public boolean createStudent(String studentName, String schoolClassName) {
        Student student = getStudent(studentName);

        if (student != null) {
            return false;
        }

        SchoolClass schoolClass = SchoolClassService.getSchoolClass(schoolClassName);

        if (schoolClass != null) {
            Student newStudent = new Student(studentName, schoolClassName);
            School.getSchool().getStudents().add(newStudent);
//            schoolClass.getStudentsInClass().add(newStudent);
            addStudentToSchoolClass(studentName, schoolClassName);
        } else {
            Student newStudent = new Student(studentName);
            School.getSchool().getStudents().add(newStudent);
        }

        return true;
    }

    public static boolean addStudentToSchoolClass (String studentName, String schoolClassName) {
        SchoolClass schoolClass = SchoolClassService.getSchoolClass(schoolClassName);
        Student student = StudentService.getStudent(studentName);

        if (schoolClass != null && student != null) {
            schoolClass.getStudentsInClass().add(student);
            student.setSchoolClassName(schoolClassName);
            Map<Subject, List<Integer>> studentNotebook = new HashMap<>();

            for (Subject subject : Subject.values()) {
                studentNotebook.put(subject, new ArrayList<>());
            }

            schoolClass.getDiary().getDiaryOfClass().put(student, studentNotebook);
            return true;
        }

        return false;
    }

    public boolean removeStudent(String studentName) {
        Student studentToRemove = getStudent(studentName);

        if (studentToRemove == null) {
            return false;
        }

        if (studentToRemove.getSchoolClassName() != null) {
            String schoolClassName = studentToRemove.getSchoolClassName();
            SchoolClassService.removeStudentFromAClass(schoolClassName, studentName);
        }

        School.getSchool().getStudents().remove(studentToRemove);
        return true;
    }

    public boolean editStudent(String studentName, String newStudentName) {
        Student student = getStudent(studentName);

        if (student == null) {
            return false;
        }

        student.setName(newStudentName);
        return true;
    }
}
