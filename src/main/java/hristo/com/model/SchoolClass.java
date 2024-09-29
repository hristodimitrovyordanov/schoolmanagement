package hristo.com.model;

import java.util.ArrayList;
import java.util.List;

public class SchoolClass {

    private String name;
    private Teacher teacherOfTheSchoolClass;
    private final List<Student> studentsInClass;
    private final Diary diary;



    public SchoolClass(String name, Teacher teacher, Diary diary) {
        this.name = name;
        this.teacherOfTheSchoolClass = teacher;
        this.diary = diary;
        this.studentsInClass = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacherOfTheSchoolClass() {
        return teacherOfTheSchoolClass;
    }

    public void setTeacherOfTheSchoolClass(Teacher teacherOfTheSchoolClass) {
        this.teacherOfTheSchoolClass = teacherOfTheSchoolClass;
    }

    public List<Student> getStudentsInClass() {
        return studentsInClass;
    }

    public Diary getDiary() {
        return diary;
    }

    @Override
    public String toString() {
        return "SchoolClass " + name +
                " has teacher of the class " + teacherOfTheSchoolClass +
                " with these students " + studentsInClass.toString() +
                " and created automatically diary of " + diary.getSchoolClassName() + " class.";
    }
}
