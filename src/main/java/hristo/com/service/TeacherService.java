package hristo.com.service;

import hristo.com.model.School;
import hristo.com.model.SchoolClass;
import hristo.com.model.Subject;
import hristo.com.model.Teacher;

import java.util.List;

public class TeacherService {

    public boolean createTeacher(String name, Subject subject) {
        Teacher teacher = getTeacher(name);

        if (teacher != null) {
            return false;
        }

        Teacher newTeacher = new Teacher(name, subject);
        School.getSchool().getTeachers().add(newTeacher);
        return true;
    }

    public List<Teacher> getAllTeachers() {
        return School.getSchool().getTeachers();
    }

    public Teacher getTeacher(String nameTeacher) {
        for (Teacher teacher : School.getSchool().getTeachers()) {
            if (teacher.getName().equals(nameTeacher)) {
                return teacher;
            }
        }

        return null;
    }

    public boolean removeTeacher(String nameOfTheTeacherToRemove) {
        Teacher teacher = getTeacher(nameOfTheTeacherToRemove);

        if (teacher != null) {

            if (teacher.doesTheTeacherHaveAClass()) {
                for (SchoolClass schoolClass : School.getSchool().getSchoolClasses()) {
                    if (schoolClass.getTeacherOfTheSchoolClass().equals(teacher)) {
                        schoolClass.setTeacherOfTheSchoolClass(null);
                        break;
                    }
                }
            }

            School.getSchool().getTeachers().remove(teacher);
            return true;
        }

        return false;
    }

    public boolean editNameOfTheTeacher(String oldTeacherName, String newTeacherName) {
        Teacher teacher = getTeacher(oldTeacherName);

        if (teacher != null) {

            if (teacher.doesTheTeacherHaveAClass()) {
                for (SchoolClass schoolClass : School.getSchool().getSchoolClasses()) {
                    if (schoolClass.getTeacherOfTheSchoolClass().getName().equals(oldTeacherName)) {
                        schoolClass.getTeacherOfTheSchoolClass().setName(newTeacherName);
                        break;
                    }
                }
            }

            teacher.setName(newTeacherName);
            return true;
        }

        return false;
    }

    public boolean editSubjectOfTheTeacher(String teacherName, Subject newSubject) {
        Teacher teacher = getTeacher(teacherName);

        if (teacher == null) {
            return false;
        }

        teacher.setSubjectOfTeaching(newSubject);
        return true;
    }

    public boolean editTeacherHasAClass(String teacherName, String className) {
        Teacher teacher = getTeacher(teacherName);

        if (teacher != null) {
            if (teacher.doesTheTeacherHaveAClass()) {
                for (SchoolClass schoolClass : School.getSchool().getSchoolClasses()) {
                    if (schoolClass.getTeacherOfTheSchoolClass().getName().equals(teacher.getName())) {
                        schoolClass.setTeacherOfTheSchoolClass(null);
                        teacher.setHasASchoolClass(false);
                    }
                }
            } else {
                SchoolClass schoolClass = SchoolClassService.getSchoolClass(className);

                if (schoolClass == null) {
                    return false;
                } else {
                    schoolClass.setTeacherOfTheSchoolClass(teacher);
                    teacher.setHasASchoolClass(true);
                }
            }

            return true;
        }

        return false;
    }
}
