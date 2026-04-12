package sorting;

import filler.FileFiller;
import model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import collection.List;

public class BasicStudentSortingStrategyTest{
    @Test
    void testSort(){
        FileFiller filler = new FileFiller("sorting/students.txt");
        List<Student> students = filler.fill(0);
        filler = new FileFiller("sorting/basic_strategy_expected_students.txt");
        List<Student> expectedStudents = filler.fill(0);
        StudentSorter.setStudentSortingStrategy(new BasicStudentSortingStrategy());
        StudentSorter.sort(students, StudentField.GROUP_NUMBER);
        Assertions.assertIterableEquals(students, expectedStudents);
    }
    @Test
    void testSortOneElement(){
        Student student = new Student.Builder("Новиков Игорь Петрович",
                3,
                4.3,
                112233)
                .build();
        List<Student> list = new List<>();
        list.add(student);
        List<Student> expectedList = new List<>();
        expectedList.add(student);
        StudentSorter.setStudentSortingStrategy(new BasicStudentSortingStrategy());
        StudentSorter.sort(list, StudentField.GROUP_NUMBER);
        Assertions.assertIterableEquals(list, expectedList);
    }
    @Test
    void testSortEmptyCollection(){
        List<Student> list = new List<>();
        List<Student> expectedList = new List<>();
        StudentSorter.setStudentSortingStrategy(new BasicStudentSortingStrategy());
        StudentSorter.sort(list, StudentField.GROUP_NUMBER);
        Assertions.assertIterableEquals(list, expectedList);
    }
}
