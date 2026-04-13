package filler;

import model.Student;
import validator.StudentValidator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import collection.List;

/**
 * Заполнитель коллекции из текстового файла. Заполняет с ипользованием Stream
 */
public class FileFiller extends ParseFiller {

    // Имя файла
    private final String FILE_NAME;

    public FileFiller(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    /**
     * Метод получения студентов из файла определенного формата
     * @param size желаемое количество студентов
     * @return кастомный список студентов
     */
    @Override
    public List<Student> fill(int size) {
        List<Student> students = new List<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(FILE_NAME)) {
            if (is == null) {
                System.err.println("Файл " + FILE_NAME + " не найден в ресурсах.");
                return students;
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                students = reader.lines()
                        .limit(size > 0 ? size : Long.MAX_VALUE)
                        .map(this::parseStudent)
                        .filter(StudentValidator::validate)
                        .collect(List::new, List::add, List::addAll);
            }
        } catch (Exception e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
        return students;
    }

}