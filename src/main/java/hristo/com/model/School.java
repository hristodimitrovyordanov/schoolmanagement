package hristo.com.model;

import java.util.ArrayList;
import java.util.List;

public class School {

    private String name;
    private final List<SchoolClass> classes;
    private final List<Teacher> teachers;
    private final List<Student> students;
    private final List<Diary> diaries;
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

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Diary> getDiaries() {
        return diaries;
    }

    @Override
    public String toString() {
        return "School " + name +
                " has " + classes.size() + " classes, " +
                teachers.size() + " teachers and " +
                students.size() + " students.";
    }
}
