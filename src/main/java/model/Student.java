package model;

import java.util.Objects;

public class Student {
    private final String fullName;
    private final int groupNumber;
    private final double averageScore;
    private final int recordStudentBookNumber;

    private Student(Builder builder) {
        this.fullName = builder.fullName;
        this.groupNumber = builder.groupNumber;
        this.averageScore = builder.averageScore;
        this.recordStudentBookNumber = builder.recordStudentBookNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public int getRecordStudentBookNumber() {
        return recordStudentBookNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return groupNumber == student.groupNumber && Double.compare(averageScore, student.averageScore) == 0 && recordStudentBookNumber == student.recordStudentBookNumber && Objects.equals(fullName, student.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, groupNumber, averageScore, recordStudentBookNumber);
    }

    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", groupNumber=" + groupNumber +
                ", averageScore=" + averageScore +
                ", recordStudentBookNumber=" + recordStudentBookNumber +
                '}';
    }

    public static class Builder {
        private String fullName;
        private int groupNumber;
        private double averageScore;
        private int recordStudentBookNumber;

        public Builder(String fullName, int groupNumber, double averageScore, int recordStudentBookNumber) {
            this.fullName = fullName;
            this.groupNumber = groupNumber;
            this.averageScore = averageScore;
            this.recordStudentBookNumber = recordStudentBookNumber;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setGroupNumber(int groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder setAverageScore(double averageScore) {
            this.averageScore = averageScore;
            return this;
        }

        public Builder setRecordStudentBookNumber(int recordStudentBookNumber) {
            this.recordStudentBookNumber = recordStudentBookNumber;
            return this;
        }

        public Student build() {
            return new Student(this);
        }

    }
}
