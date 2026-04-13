package validator;

import model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentValidatorTest {

    @Test
    void testValidFullNames() {
        assertTrue(StudentValidator.validateFullName("Иванов Иван Иванович"));
        assertTrue(StudentValidator.validateFullName("Петров Пётр"));
        assertTrue(StudentValidator.validateFullName("Сидорова Анна Сергеевна"));
        assertTrue(StudentValidator.validateFullName("А Б"));
        assertTrue(StudentValidator.validateFullName("Сидоров-Петров Александр"));
    }

    @Test
    void testInvalidFullNames() {
        assertFalse(StudentValidator.validateFullName(""));
        assertFalse(StudentValidator.validateFullName("   "));
        assertFalse(StudentValidator.validateFullName("Иванов"));
        assertFalse(StudentValidator.validateFullName("ivanov ivan"));
        assertFalse(StudentValidator.validateFullName("Иванов Иван 123"));
        assertFalse(StudentValidator.validateFullName("---"));
        assertFalse(StudentValidator.validateFullName("-Иванов"));
        assertFalse(StudentValidator.validateFullName(null));
    }

    @Test
    void testValidateGroupNumber() {
        assertTrue(StudentValidator.validateGroupNumber(1));
        assertTrue(StudentValidator.validateGroupNumber(2));
        assertTrue(StudentValidator.validateGroupNumber(4));
        assertFalse(StudentValidator.validateGroupNumber(0));
        assertFalse(StudentValidator.validateGroupNumber(5));
    }

    @Test
    void testValidateAverageScore() {
        assertTrue(StudentValidator.validateAverageScore(2.0));
        assertTrue(StudentValidator.validateAverageScore(3.5));
        assertTrue(StudentValidator.validateAverageScore(5.0));
        assertTrue(StudentValidator.validateAverageScore(2.00));
        assertTrue(StudentValidator.validateAverageScore(4.75));
        assertFalse(StudentValidator.validateAverageScore(4.555));
        assertFalse(StudentValidator.validateAverageScore(5.1));
        assertFalse(StudentValidator.validateAverageScore(1.9));
    }

    @Test
    void testValidateRecordNumber() {
        assertTrue(StudentValidator.validateRecordNumber(100000));
        assertTrue(StudentValidator.validateRecordNumber(500000));
        assertTrue(StudentValidator.validateRecordNumber(999999));
        assertFalse(StudentValidator.validateRecordNumber(99999));
        assertFalse(StudentValidator.validateRecordNumber(1000000));
    }

    @Test
    void testValidateValidStudent() {
        Student student = new Student.Builder("Иванов Иван Иванович", 2, 4.5, 123456).build();
        assertTrue(StudentValidator.validate(student));
    }

    @Test
    void testValidateWithMessageValidStudent() {
        Student validStudent = new Student.Builder("Иванов Иван Иванович", 2, 4.5, 123456).build();
        StringBuilder sb = new StringBuilder();
        assertTrue(StudentValidator.validateWithMessage(validStudent, sb));
        assertEquals(0, sb.length());
    }
}