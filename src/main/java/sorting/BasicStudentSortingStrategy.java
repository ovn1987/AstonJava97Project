package sorting;

import jdk.jshell.spi.ExecutionControl;
import model.Student;

import java.util.Comparator;
import java.util.List;

public class BasicStudentSortingStrategy extends StudentSortingStrategy{
    @Override
    public void sort(List<Student> students, StudentField fieldToSortBy) {
        System.out.println("Заглушка метода сортировки.");
    }
}
