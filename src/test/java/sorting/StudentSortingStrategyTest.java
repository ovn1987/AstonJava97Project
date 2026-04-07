package sorting;

import model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public abstract class StudentSortingStrategyTest {
    protected abstract StudentSortingStrategy getStrategy();
    protected abstract List<Student> getExpectedList();

    protected List<Student> getListToSort(){
        List<Student> listToSort = new ArrayList<>();
        Student.Builder builder = new Student.Builder("John McMilson",
                556,
                4.3,
                345697685);
        listToSort.add(builder.build());
        builder = new Student.Builder("Maya McMilson",
                245,
                4.7,
                445697685);
        listToSort.add(builder.build());
        builder = new Student.Builder("Billy Green",
                145,
                3.9,
                245697685);
        listToSort.add(builder.build());
        builder = new Student.Builder("Alice Hide",
                156,
                4.0,
                645697685);
        listToSort.add(builder.build());
        builder = new Student.Builder("George Smith",
                246,
                3.5,
                545697685);
        listToSort.add(builder.build());
        return listToSort;
    }

    @Test
    void testSort(){
        List<Student> listToSort = getListToSort();

        List<Student> expectedList = getExpectedList();

        StudentSorter.setStudentSortingStrategy(getStrategy());
        StudentSorter.sort(listToSort, StudentField.GROUP_NUMBER);

        Assertions.assertIterableEquals(listToSort, expectedList);
    }

    @Test
    void testSortOneElement(){
        StudentSortingStrategy[] strategies = new StudentSortingStrategy[]{
                new BasicStudentSortingStrategy(),
                new ExcludeOddStudentSortingStrategy()};
        Student student = new Student.Builder("John Crow",
                123,
                5.0,
                345678)
                .build();
        List<Student> list = new ArrayList<>();
        list.add(student);
        List<Student> expectedList = new ArrayList<>();
        expectedList.add(student);
        StudentSorter.setStudentSortingStrategy(getStrategy());
        StudentSorter.sort(list, StudentField.GROUP_NUMBER);
        Assertions.assertIterableEquals(list, expectedList);
    }
}
