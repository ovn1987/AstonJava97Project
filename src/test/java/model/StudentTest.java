package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testValidStudentCreation() {
        Student student = new Student.Builder("Иванов Иван Иванович", 2, 4.5, 123456).build();
        assertNotNull(student);
        assertEquals("Иванов Иван Иванович", student.getFullName());
        assertEquals(2, student.getGroupNumber());
        assertEquals(4.5, student.getAverageScore());
        assertEquals(123456, student.getRecordStudentBookNumber());
    }

    @Test
    void testInvalidStudentThrowsException() {
        // Неверное ФИО (пустая строка)
        assertThrows(IllegalArgumentException.class,
                () -> new Student.Builder("", 2, 4.5, 123456).build());

        // Неверный номер группы (5)
        assertThrows(IllegalArgumentException.class,
                () -> new Student.Builder("Иванов Иван Иванович", 5, 4.5, 123456).build());

        // Неверный средний балл (6.0)
        assertThrows(IllegalArgumentException.class,
                () -> new Student.Builder("Иванов Иван Иванович", 2, 6.0, 123456).build());

        // Неверный номер зачётки (12345)
        assertThrows(IllegalArgumentException.class,
                () -> new Student.Builder("Иванов Иван Иванович", 2, 4.5, 12345).build());
    }

    @Test
    void testEqualsAndHashCode() {
        Student s1 = new Student.Builder("Иванов Иван Иванович", 2, 4.5, 123456).build();
        Student s2 = new Student.Builder("Иванов Иван Иванович", 2, 4.5, 123456).build();
        Student s3 = new Student.Builder("Петров Пётр Петрович", 1, 3.8, 234567).build();

        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
        assertNotEquals(s1, s3);
    }

    @Test
    void testToString() {
        Student student = new Student.Builder("Иванов Иван Иванович", 2, 4.5, 123456).build();
        String expected = "Student{fullName='Иванов Иван Иванович', groupNumber=2, averageScore=4.5, recordStudentBookNumber=123456}";
        assertEquals(expected, student.toString());
    }
}