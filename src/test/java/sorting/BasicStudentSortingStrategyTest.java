package sorting;

import model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

public class BasicStudentSortingStrategyTest {
    @Test
    void testSort(){
        List<Student> listToSort = new ArrayList<>();
        Student.Builder builder = new Student.Builder("John McMilson",
                456,
                4.3,
                345697685);
        listToSort.add(builder.build());
        builder = new Student.Builder("Maya McMilson",
                345,
                4.7,
                445697685);
        listToSort.add(builder.build());
        builder = new Student.Builder("Billy Green",
                345,
                3.9,
                245697685);
        listToSort.add(builder.build());
        builder = new Student.Builder("Alice Hide",
                456,
                4.0,
                645697685);
        listToSort.add(builder.build());
        builder = new Student.Builder("George Smith",
                345,
                3.5,
                545697685);
        listToSort.add(builder.build());

        List<Student> expectedList = List.of(listToSort.get(4),
                listToSort.get(2),
                listToSort.get(3),
                listToSort.get(0),
                listToSort.get(1));

        StudentSorter.setStudentSortingStrategy(new BasicStudentSortingStrategy());
        StudentSorter.sort(listToSort, StudentField.AVERAGE_SCORE);

        Assertions.assertIterableEquals(listToSort, expectedList);
    }
}
