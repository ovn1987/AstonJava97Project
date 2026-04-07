package sorting;

import model.Student;
import java.util.List;

public class BasicStudentSortingStrategyTest extends StudentSortingStrategyTest{
    @Override
    protected StudentSortingStrategy getStrategy() {
        return new BasicStudentSortingStrategy();
    }

    @Override
    protected List<Student> getExpectedList() {
        List<Student> listToSort = getListToSort();
        return List.of(listToSort.get(2),
                listToSort.get(3),
                listToSort.get(1),
                listToSort.get(4),
                listToSort.get(0));
    }
}
