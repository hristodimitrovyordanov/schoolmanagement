package hristo.com.service;

import hristo.com.model.*;

import java.util.List;
import java.util.Map;

public class DiaryService {

    public Diary getDiary(String schoolClassName) {
        for (Diary diary : School.getSchool().getDiaries()) {
            if (diary.getSchoolClassName().equals(schoolClassName)) {
                return diary;
            }
        }

        return null;
    }

    public boolean addGradeToStudent(String schoolClassName, String studentName, Subject subject, int grade) {
        Student studentToAddGrade = StudentService.getStudent(studentName);
        Diary diary = getDiary(schoolClassName);

        for (Map.Entry<Student, Map<Subject, List<Integer>>> entry : diary.getDiaryOfClass().entrySet()) {

            assert studentToAddGrade != null;

            if (studentToAddGrade.equals(entry.getKey())) {
                Map<Subject, List<Integer>> gradesInSubjects = entry.getValue();

                for (Map.Entry<Subject, List<Integer>> subjectListEntry : gradesInSubjects.entrySet()) {
                    if (subjectListEntry.getKey().equals(subject)) {
                        subjectListEntry.getValue().add(grade);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean removeLastGradeOfSubject(String schoolClassName, String studentName, Subject subject) {
        Student student = StudentService.getStudent(studentName);
        Diary diary = getDiary(schoolClassName);

        for (Map.Entry<Student, Map<Subject, List<Integer>>> entry : diary.getDiaryOfClass().entrySet()) {
            assert student != null;

            if (student.equals(entry.getKey())) {
                Map<Subject, List<Integer>> studentNotes = entry.getValue();
                List<Integer> subjectNotes = studentNotes.get(subject);

                if (subjectNotes.isEmpty()) {
                    return false;
                }

                subjectNotes.remove(subjectNotes.size() - 1);
                return true;
            }
        }

        return false;
    }
}
