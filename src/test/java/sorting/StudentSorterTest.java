package sorting;

import model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class StudentSorterTest {
    @Test
    void testSortingWithoutSettingStrategyThrowsException(){
        List<Student> students = new ArrayList<>();
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
