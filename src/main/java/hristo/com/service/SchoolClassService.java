package hristo.com.service;

import hristo.com.model.*;

import java.util.List;

public class SchoolClassService {

    private final TeacherService teacherService = new TeacherService();

    public List<SchoolClass> getAllSchoolClasses() {
        return School.getSchool().getSchoolClasses();
    }

    public boolean createSchoolClass(String schoolClassName, String nameOfTheTeacher) {
        SchoolClass schoolClass = getSchoolClass(schoolClassName);

        if (schoolClass != null) {
            return false;
        }

        Diary diary = new Diary(schoolClassName);

        School.getSchool().getDiaries().add(diary);
        Teacher teacher = teacherService.getTeacher(nameOfTheTeacher);

        if (teacher != null) {
            teacher.setHasASchoolClass(true);
        }

        SchoolClass newSchoolClass = new SchoolClass(schoolClassName,teacher, diary);
        School.getSchool().getSchoolClasses().add(newSchoolClass);
        return true;
    }

    public boolean removeSchoolClass(String schoolClassName) {
        SchoolClass schoolClass = getSchoolClass(schoolClassName);

        if (schoolClass != null) {
            if (schoolClass.getTeacherOfTheSchoolClass() != null) {
                schoolClass.getTeacherOfTheSchoolClass().setHasASchoolClass(false);
            }

            for (Student student : schoolClass.getStudentsInClass()) {
                student.setSchoolClassName(null);
            }

            School.getSchool().getSchoolClasses().remove(schoolClass);
            return true;
        }

        return false;
    }

    public boolean editNameOfTheSchoolClass(String oldSchoolClassName, String newSchoolClassName) {
        SchoolClass schoolClass = getSchoolClass(oldSchoolClassName);

        if (schoolClass != null) {
            schoolClass.setName(newSchoolClassName);
            return true;
        }

        return false;
    }

    public static SchoolClass getSchoolClass(String schoolClassName) {
        for (SchoolClass schoolClass : School.getSchool().getSchoolClasses()) {
            if (schoolClass.getName().equals(schoolClassName)) {
                return schoolClass;
            }
        }

        return null;
    }

    public boolean editTeacherOfTheClass(String schoolClassName, String teacherName) {
        SchoolClass schoolClass = getSchoolClass(schoolClassName);

        if (schoolClass == null) {
            return false;
        }

        Teacher newTeacher = teacherService.getTeacher(teacherName);

        if (newTeacher == null || newTeacher.doesTheTeacherHaveAClass()) {
            return false;
        }

        if (schoolClass.getTeacherOfTheSchoolClass() == null) {
            schoolClass.setTeacherOfTheSchoolClass(newTeacher);
        } else {
            Teacher oldTeacher = schoolClass.getTeacherOfTheSchoolClass();
            oldTeacher.setHasASchoolClass(false);
            schoolClass.setTeacherOfTheSchoolClass(newTeacher);
            newTeacher.setHasASchoolClass(true);
        }

        return true;
    }

    public static boolean removeStudentFromAClass(String schoolClassName, String student) {
        SchoolClass schoolClass = getSchoolClass(schoolClassName);
        Student studentToRemove = StudentService.getStudent(student);

        if (schoolClass != null && studentToRemove != null) {
            for (Student studentOfAClass : schoolClass.getStudentsInClass()) {
                if (studentOfAClass.getName().equals(studentToRemove.getName())) {
                    schoolClass.getStudentsInClass().remove(studentToRemove);
                    schoolClass.getDiary().getDiaryOfClass().remove(studentToRemove);
                    studentToRemove.setSchoolClassName(null);
                    return true;
                }
            }
        }

        return false;
    }
}
