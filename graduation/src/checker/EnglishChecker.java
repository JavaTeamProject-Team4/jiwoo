package checker;

import model.*;

public class EnglishChecker implements RequirementChecker {
    private static final int REQUIRED_TOEIC = 700;

    public boolean hasEnglishCourse(Student student) {
        for (int i = 0; i < student.getCourseCount(); i++) {
            TakenCourse tc = student.getTakenCourses()[i];
            Course c = tc.getCourse();
            String grade = tc.getGrade().toUpperCase();

            if (grade.equals("F") || grade.equals("NP")) {
                continue;
            }

            if (c instanceof GeneralCourse) {
                GeneralCourse gc = (GeneralCourse)c;

                if (gc.isEnglish()) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean check(Student student) {
        return student.getToeicScore() >= REQUIRED_TOEIC
                || hasEnglishCourse(student);
    }

    @Override
    public String getMessage(Student student) {
        if (check(student)) {
            if (student.getToeicScore() >= REQUIRED_TOEIC) {
                return "영어 요건 충족 - 토익 " + student.getToeicScore() + "/700";
            }

            return "영어 요건 충족 - 영어 교양 이수";
        }

        return "영어 요건 미충족 - 토익 "
                + (REQUIRED_TOEIC - student.getToeicScore())
                + "점 부족(" + student.getToeicScore()
                + "/700) 또는 영어 교양 이수 필요";
    }
}