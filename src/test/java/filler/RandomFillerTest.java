package filler;

import collection.List;
import model.Student;
import org.junit.jupiter.api.Test;
import validator.StudentValidator;

import static org.junit.jupiter.api.Assertions.*;

class RandomFillerTest {

    @Test
    void testFillReturnsExactSize() {
        RandomFiller filler = new RandomFiller();
        int size = 10;
        List<Student> students = filler.fill(size);
        assertEquals(size, students.size(), "Должно быть ровно " + size + " студентов");
    }

    @Test
    void testAllGeneratedStudentsAreValid() {
        RandomFiller filler = new RandomFiller();
        int size = 20;
        List<Student> students = filler.fill(size);
        for (Student s : students) {
            assertTrue(StudentValidator.validate(s),
                    "Сгенерирован невалидный студент: " + s);
        }
    }

    @Test
    void testFillWithZeroOrNegativeSizeReturnsEmptyList() {
        RandomFiller filler = new RandomFiller();
        assertTrue(filler.fill(0).isEmpty(), "При size=0 должен быть пустой список");
        assertTrue(filler.fill(-5).isEmpty(), "При отрицательном size должен быть пустой список");
    }

    @Test
    void testGeneratedDataWithinBounds() {
        RandomFiller filler = new RandomFiller();
        int size = 50;
        List<Student> students = filler.fill(size);
        for (Student s : students) {
            assertTrue(s.getGroupNumber() >= 1 && s.getGroupNumber() <= 4,
                    "Номер группы вне диапазона: " + s.getGroupNumber());
            assertTrue(s.getAverageScore() >= 2.0 && s.getAverageScore() <= 5.0,
                    "Средний балл вне диапазона: " + s.getAverageScore());
            assertTrue(s.getRecordStudentBookNumber() >= 100_000 && s.getRecordStudentBookNumber() <= 999_999,
                    "Номер зачётки вне диапазона: " + s.getRecordStudentBookNumber());
        }
    }
}