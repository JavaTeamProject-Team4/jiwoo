// ========================================
// GraduationChecker.java
// 전체 졸업 요건 검사 클래스
// ========================================

package checker;

import model.Student;

public class GraduationChecker {

    // 모든 checker 저장
    private RequirementChecker[] checkers;

    public GraduationChecker() {

        checkers =
                new RequirementChecker[] {

                new CreditChecker(),
                new RequiredMajorChecker(),
                new CheomseongChecker(),
                new SDGChecker(),
                new EnglishChecker(),
                new CounselingChecker(),
                new GpaChecker()
        };
    }

    // 전체 결과 출력
    public void printGraduationResult(
            Student student) {

        boolean canGraduate = true;

        System.out.println(
                "===== 졸업 요건 검사 ====="
        );
        
        String majorName = "";
        switch (student.getStudentMajor()) {
            case 1: majorName = "심화컴퓨팅"; break;
            case 2: majorName = "플랫폼SW"; break;
            case 3: majorName = "인공지능컴퓨팅"; break;
            case 4: majorName = "글로벌SW"; break;
            default: majorName = "알수없음"; break;
        }
        
        
        // 학생 이름 및 학번 출력 추가
        System.out.println(
        		" " +majorName + " " + student.getStudentName() + " (" + student.getStudentId() + ")"
        		);
        System.out.println("");

        // 모든 checker 실행
        for(int i = 0;
            i < checkers.length;
            i++) {

            // 결과 출력
            System.out.println(
                    checkers[i]
                    .getMessage(student)
            );

            // 하나라도 실패 시
            if(!checkers[i]
                    .check(student)) {

                canGraduate = false;
            }
        }

        System.out.println();

        // 최종 결과
        if(canGraduate) {

            System.out.println(
                    "졸업 가능합니다."
            );
        }
        else {

            System.out.println(
                    "졸업 불가능합니다."
            );
        }
    }
}