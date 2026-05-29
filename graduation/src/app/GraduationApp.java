package app;

import java.util.Scanner;

import checker.GraduationChecker;
import db.CourseDB;
import model.Course;
import model.Student;
import model.TakenCourse;

public class GraduationApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);//1231231

        CourseDB.loadGeneralCourses();

        Student student = null;
        GraduationChecker graduationChecker = new GraduationChecker();

        while (true) {
            System.out.println("========== 졸업요건 충족 여부 판단 프로그램 ==========");
            System.out.println("1. 학생 정보 입력");
            System.out.println("2. 수강 과목 입력");
            System.out.println("3. 졸업요건 확인");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택: ");

            int menu = sc.nextInt();

            if (menu == 1) {
                System.out.print("학번: ");
                int id = sc.nextInt();

                System.out.print("이름: ");
                String name = sc.next();

                System.out.print("전공 선택(1.심컴 2.플숲 3.인컴 4.글숲): ");
                int major = sc.nextInt();

                CourseDB.loadMajorCoursesByMajor(major);

                System.out.print("지도교수 상담 횟수: ");
                int counseling = sc.nextInt();

                System.out.print("토익 점수: ");
                int toeic = sc.nextInt();

                student = new Student(id, name, major, counseling, toeic);
                System.out.print("평점 평균: ");
                student.setGpa(sc.nextDouble());

                System.out.println("학생 정보 입력 완료");
                System.out.println("");
            }

            else if (menu == 2) {
                if (student == null) {
                    System.out.println("학생 정보를 먼저 입력하세요.");
                    continue;
                }

                // [수정된 부분] 이전 nextInt() 등에 의해 남은 엔터키(개행문자) 버퍼 비우기
                sc.nextLine();

                while (true) {
                    // [수정된 부분] 띄어쓰기 입력을 받기 위해 nextLine() 사용 및 안내 문구 수정
                    System.out.print("과목코드 또는 과목명 입력(exit 입력 시 종료): ");
                    String code = sc.nextLine().trim();

                    if (code.equalsIgnoreCase("exit")) {
                        break;
                    }

                    Course course = CourseDB.findCourse(code);

                    if (course == null) {
                        System.out.println("해당 과목을 찾을 수 없습니다.");
                        continue;
                    }

                    System.out.print("성적 입력(A+, A0, B+, P, F, NP 등): ");
                    // [수정된 부분] 성적도 nextLine()으로 통일하여 안전하게 입력받음
                    String grade = sc.nextLine().trim();

                    student.addTakenCourse(new TakenCourse(course, grade));

                    System.out.println(course.getLectureName() + " 추가 완료");
                }
            }

            else if (menu == 3) {
                if (student == null) {
                    System.out.println("학생 정보를 먼저 입력하세요.");
                    continue;
                }

                graduationChecker.printGraduationResult(student);
            }

            else if (menu == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            else {
                System.out.println("잘못된 메뉴입니다.");
            }
        }

        sc.close();
    }
}