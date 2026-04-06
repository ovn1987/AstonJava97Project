package sorting;

import model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ExcludeOddStudentSortingStrategyTest extends StudentSortingStrategyTest{
    @Override
    protected StudentSortingStrategy getStrategy() {
        return new ExcludeOddStudentSortingStrategy();
    }

    @Override
    protected List<Student> getExpectedList() {
        List<Student> listToSort = getListToSort();
        return List.of(listToSort.get(3),
                listToSort.get(1),
                listToSort.get(2),
                listToSort.get(4),
                listToSort.get(0));
    }

    @Test
    void testSortingByNonNumericFieldThrowsException(){
        StudentSorter.setStudentSortingStrategy(getStrategy());
        List<Student> list = new ArrayList<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            StudentSorter.sort(list, StudentField.FULL_NAME);
        });
    }
}
