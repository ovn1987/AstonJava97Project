package filler;

import collection.List;
import model.Student;
import org.junit.jupiter.api.Test;
import validator.StudentValidator;

import static org.junit.jupiter.api.Assertions.*;

class FileFillerTest {

    @Test
    void testFillValidFileAllStudentsValid() {
        FileFiller filler = new FileFiller("valid_students.txt");
        List<Student> students = filler.fill(0);

        for (Student s : students) {
            assertTrue(StudentValidator.validate(s),
                    "Студент из валидного файла не прошёл валидацию: " + s);
        }
        assertFalse(students.isEmpty(), "Файл с валидными данными не должен возвращать пустой список");
    }

    @Test
    void testFillWithSizeLimit() {
        FileFiller filler = new FileFiller("valid_students.txt");
        int limit = 2;
        List<Student> students = filler.fill(limit);

        assertTrue(students.size() <= limit, "Лимит превышен");
        for (Student s : students) {
            assertTrue(StudentValidator.validate(s));
        }
    }

    @Test
    void testFillInvalidFileOnlyValidStudentsLoaded() {
        FileFiller filler = new FileFiller("invalid_students.txt");
        List<Student> students = filler.fill(0);

        for (Student s : students) {
            assertTrue(StudentValidator.validate(s),
                    "Из файла с ошибками загружен невалидный студент: " + s);
        }
        assertNotNull(students);
    }

    @Test
    void testFileNotFoundReturnsEmptyList() {
        FileFiller filler = new FileFiller("non_existent_file.txt");
        List<Student> students = filler.fill(0);
        assertTrue(students.isEmpty(), "При отсутствии файла должен быть пустой список");
    }

    @Test
    void testEmptyFileReturnsEmptyList() {
        FileFiller filler = new FileFiller("empty_file.txt");
        List<Student> students = filler.fill(0);
        assertTrue(students.isEmpty(), "Пустой файл должен давать пустой список");
    }
}