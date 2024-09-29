package hristo.com.model;

public class Student {

    private String name;
    private String schoolClassName;

    public Student(String name, String schoolClassName) {
        this.name = name;
        this.schoolClassName = schoolClassName;
    }

    public Student(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolClassName() {
        return schoolClassName;
    }

    public void setSchoolClassName(String schoolClassName) {
        this.schoolClassName = schoolClassName;
    }

    @Override
    public String toString() {
        return "Student " + name +
                " is in school class " + schoolClassName;
    }
}
