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