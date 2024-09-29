package hristo.com.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Diary {

    private String schoolClassName;
    private Map<Student, Map<Subject, List<Integer>>> diaryOfClass;

    public Diary(String schoolClassName) {
        this.schoolClassName = schoolClassName;
        this.diaryOfClass = new HashMap<>();
    }

    public String getSchoolClassName() {
        return schoolClassName;
    }

    public void setSchoolClassName(String schoolClassName) {
        this.schoolClassName = schoolClassName;
    }

    public Map<Student, Map<Subject, List<Integer>>> getDiaryOfClass() {
        return diaryOfClass;
    }

    public void setDiaryOfClass(Map<Student, Map<Subject, List<Integer>>> diaryOfClass) {
        this.diaryOfClass = diaryOfClass;
    }

    @Override
    public String toString() {
        return "Diary of " + schoolClassName + ", diaryOfClass = " + diaryOfClass;
    }
}
