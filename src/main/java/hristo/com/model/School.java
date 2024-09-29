package hristo.com.model;

import java.util.ArrayList;
import java.util.List;

public class School {

    private String name;
    private List<SchoolClass> classes;
    private List<Teacher> teachers;
    private List<Student> students;
    private List<Diary> diaries;
    public static boolean isSchoolCreated = false;

    private static School school = null;

    private School() {
        this.classes = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
        this.diaries = new ArrayList<>();
    }

    public static School getSchool() {
        if (school == null) {
            school = new School();
            isSchoolCreated = true;
        }

        return school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SchoolClass> getSchoolClasses() {
        return classes;
    }

    public void setClasses(List<SchoolClass> classes) {
        this.classes = classes;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Diary> getDiaries() {
        return diaries;
    }

    public void setDiaries(List<Diary> diaries) {
        this.diaries = diaries;
    }

    @Override
    public String toString() {
        return "School " + name +
                " has " + classes.size() + " classes, " +
                teachers.size() + " teachers and " +
                students.size() + " students.";
    }
}
