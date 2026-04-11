package sorting;

import filler.FileFiller;
import model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ExcludeOddStudentSortingStrategyTest{
    @Test
    void testSort(){
        FileFiller filler = new FileFiller("sorting/students.txt");
        List<Student> students = filler.fill(0);
        filler = new FileFiller("sorting/exclude_odd_strategy_expected_students.txt");
        List<Student> expectedStudents = filler.fill(0);
        StudentSorter.setStudentSortingStrategy(new ExcludeOddStudentSortingStrategy());
        StudentSorter.sort(students, StudentField.GROUP_NUMBER);
        Assertions.assertIterableEquals(students, expectedStudents);
    }
    @Test
    void testSortAllOdd(){
        FileFiller filler = new FileFiller("sorting/only_odd_students.txt");
        List<Student> students = filler.fill(0);
        List<Student> expectedStudents = List.copyOf(students);
        StudentSorter.setStudentSortingStrategy(new ExcludeOddStudentSortingStrategy());
        StudentSorter.sort(students, StudentField.GROUP_NUMBER);
        Assertions.assertIterableEquals(students, expectedStudents);
    }
    @Test
    void testSortAllEven(){
        FileFiller filler = new FileFiller("sorting/only_even_students.txt");
        List<Student> students = filler.fill(0);
        filler = new FileFiller("sorting/exclude_odd_strategy_expected_only_even_students.txt");
        List<Student> expectedStudents = filler.fill(0);
        StudentSorter.setStudentSortingStrategy(new ExcludeOddStudentSortingStrategy());
        StudentSorter.sort(students, StudentField.GROUP_NUMBER);
        Assertions.assertIterableEquals(students, expectedStudents);
    }
}
