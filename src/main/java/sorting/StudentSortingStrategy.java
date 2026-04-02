package sorting;

import model.Student;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public abstract class StudentSortingStrategy {
    private HashMap<StudentField, Comparator<Student>> comparators;
    public StudentSortingStrategy(){
        comparators = new HashMap<>();
        comparators.put(StudentField.FULL_NAME, Comparator.comparing(Student::getFullName));
        comparators.put(StudentField.GROUP_NUMBER, Comparator.comparing(Student::getGroupNumber));
        comparators.put(StudentField.AVERAGE_SCORE, Comparator.comparing(Student::getAverageScore));
        comparators.put(StudentField.RECORD_STUDENT_BOOK_NUMBER,
                Comparator.comparing(Student::getRecordStudentBookNumber));

    }
    protected Comparator<Student> getComparator(StudentField field){
        return comparators.get(field);
    }
    public abstract void sort(List<Student> students, StudentField fieldToSortBy);
}
