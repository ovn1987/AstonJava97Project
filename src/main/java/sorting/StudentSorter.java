package sorting;

import model.Student;

import java.util.List;

public class StudentSorter {
    private static StudentSortingStrategy studentSortingStrategy;

    public static void setStudentSortingStrategy(StudentSortingStrategy studentSortingStrategy) {
        StudentSorter.studentSortingStrategy = studentSortingStrategy;
    }

    public static void sort(List<Student> students, StudentField fieldToSortBy){
        if (studentSortingStrategy == null){
            throw new IllegalStateException("Не выбрана стратегия сортировки.");
        }
        studentSortingStrategy.sort(students, fieldToSortBy);
    }
}
