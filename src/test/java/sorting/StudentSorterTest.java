package sorting;

import model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import collection.List;

public class StudentSorterTest {
    @Test
    void testSortingWithoutSettingStrategyThrowsException(){
        List<Student> students = new List<>();
        StudentSorter.setStudentSortingStrategy(null);
        Assertions.assertThrows(IllegalStateException.class, () ->
                StudentSorter.sort(students, StudentField.GROUP_NUMBER));
    }
    @Test
    void testSortingNullListThrowsException(){
        List<Student> students = null;
        Assertions.assertThrows(NullPointerException.class, () ->
                StudentSorter.sort(students, StudentField.GROUP_NUMBER));
    }
}
