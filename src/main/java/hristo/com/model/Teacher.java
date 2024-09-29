package hristo.com.model;

public class Teacher {

    private String name;
    private Subject subjectOfTeaching;
    private boolean hasASchoolClass;

    public Teacher(String name, Subject subjectOfTeaching) {
        this.name = name;
        this.subjectOfTeaching = subjectOfTeaching;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubjectOfTeaching(Subject subjectOfTeaching) {
        this.subjectOfTeaching = subjectOfTeaching;
    }

    public boolean doesTheTeacherHaveAClass() {
        return hasASchoolClass;
    }

    public void setHasASchoolClass(boolean hasASchoolClass) {
        this.hasASchoolClass = hasASchoolClass;
    }

    @Override
    public String toString() {
        return "Teacher " +  name +
                ", subjectOfTeaching = " + subjectOfTeaching +
                ", hasASchoolClass = " + hasASchoolClass;
    }
}
