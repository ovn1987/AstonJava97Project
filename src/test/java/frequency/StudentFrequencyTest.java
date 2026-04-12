package frequency;

import filler.FileFiller;
import model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StudentFrequencyTest {
    @Test
    void testGetStudentFrequencyConcurrently(){
        Student student = new Student.Builder("Козлова Ольга Владимировна",
                2, 3.6, 901234).build();
        FileFiller filler = new FileFiller("frequency/students.txt");

        List<Student> students = filler.fill(0);
        Assertions.assertEquals(StudentFrequency.getStudentFrequencyConcurrently(students, student), 4);
    }

    @Test
    void testGetStudentFrequencyConcurrentlyEmptyCollection(){
        Student student = new Student.Builder("Козлова Ольга Владимировна",
                2, 3.6, 901234).build();
        List<Student> students = new ArrayList<>();
        Assertions.assertEquals(StudentFrequency.getStudentFrequencyConcurrently(students, student), 0);
    }

    @Test
    void testNullCollectionThrowsException(){
        Student student = new Student.Builder("Козлова Ольга Владимировна",
                2, 3.6, 901234).build();
        List<Student> students = null;
        Assertions.assertThrows(NullPointerException.class, () ->
                StudentFrequency.getStudentFrequencyConcurrently(students, student));
    }
}
