package model;

public class Student {
    private int studentId;       // 학번 
    private String studentName;  // 이름 
    private int studentMajor;    // 전공 (1:심컴, 2:플숲, 3:인컴, 4:글숲) 
    private int counselingCount; // 지도교수 상담 횟수 
    private int toeicScore;      // 토익 성적 
    private double gpa;          // 평점 (요구사항에 맞춰 추가됨)
    
    // 수강한 과목 배열
    private TakenCourse[] takenCourses = new TakenCourse[100];
    private int courseCount = 0;

    public Student(int studentId, String studentName, int studentMajor, int counselingCount, int toeicScore) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentMajor = studentMajor;
        this.counselingCount = counselingCount;
        this.toeicScore = toeicScore;
        this.gpa = 0.0; 
    }

    public void addTakenCourse(TakenCourse tc) {
        if (courseCount < takenCourses.length) {
            takenCourses[courseCount++] = tc;
        }
    }

    // Getters & Setters
    public TakenCourse[] getTakenCourses() { return takenCourses; }
    public int getCourseCount() { return courseCount; }
    public int getCounselingCount() { return counselingCount; }
    public int getToeicScore() { return toeicScore; }
    public String getStudentName() { return studentName; }
    public int getStudentId() { return studentId; }
    public int getStudentMajor() { return studentMajor; }
    
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }
}