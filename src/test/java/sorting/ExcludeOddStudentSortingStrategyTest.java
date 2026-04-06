package sorting;

import model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ExcludeOddStudentSortingStrategyTest {
    @Test
    void testSort(){
        List<Student> listToSort = new ArrayList<>();
        Student.Builder builder = new Student.Builder("John McMilson",
                356,
                4.3,
                545697686);
        listToSort.add(builder.build());
        builder = new Student.Builder("Maya McMilson",
                145,
                4.7,
                445697685);
        listToSort.add(builder.build());
        builder = new Student.Builder("Billy Green",
                845,
                3.9,
                245697686);
        listToSort.add(builder.build());
        builder = new Student.Builder("Alice Hide",
                256,
                4.0,
                645697685);
        listToSort.add(builder.build());
        builder = new Student.Builder("George Smith",
                146,
                3.5,
                545697686);
        listToSort.add(builder.build());

        List<Student> expectedList = List.of(listToSort.get(4),
                listToSort.get(1),
                listToSort.get(2),
                listToSort.get(3),
                listToSort.get(0));

        StudentSorter.setStudentSortingStrategy(new ExcludeOddStudentSortingStrategy());
        StudentSorter.sort(listToSort, StudentField.GROUP_NUMBER);

        Assertions.assertIterableEquals(listToSort, expectedList);
    }
}
