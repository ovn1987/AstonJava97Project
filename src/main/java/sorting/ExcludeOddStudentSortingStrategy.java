package sorting;

import model.Student;

import java.util.ArrayList;
import java.util.List;

public class ExcludeOddStudentSortingStrategy extends BasicStudentSortingStrategy {
    private double getNumericFieldValue(Student instance, StudentField field){
        switch (field){
            case StudentField.GROUP_NUMBER -> {return instance.getGroupNumber();}
            case StudentField.AVERAGE_SCORE -> {return instance.getAverageScore();}
            case StudentField.RECORD_STUDENT_BOOK_NUMBER -> {return instance.getRecordStudentBookNumber();}
            default -> {throw new RuntimeException("Unsupported argument value");}
        }
    }
    @Override
    public void sort(List<Student> students, StudentField fieldToSortBy) {
        if(!fieldToSortBy.isNumeric()){
            throw new IllegalArgumentException("Student field to sort by should be numeric.");
        }
        List<Student> studentsToSort = new ArrayList<>();
        for(Student student: students){
            if (getNumericFieldValue(student, fieldToSortBy) % 2 == 0){
                studentsToSort.add(student);
            }
        }
        super.sort(studentsToSort, fieldToSortBy);
        int j = 0;
        for(int i = 0; i < students.size(); i++){
            if (getNumericFieldValue(students.get(i), fieldToSortBy) % 2 == 0){
                students.set(i, studentsToSort.get(j));
                j++;
            }
        }
    }
}
