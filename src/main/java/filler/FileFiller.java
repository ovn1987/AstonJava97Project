package filler;

import model.Student;
import validator.StudentValidator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Заполнитель коллекции из текстового файла
// Ожидает формат строки: ФИО,номер группы,средний балл,номер зачётки
public class FileFiller implements DataFiller {

    private final String FILE_NAME;

    public FileFiller(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    @Override
    public List<Student> fill(int size) {
        List<Student> students = new ArrayList<>();
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
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
        return students;
    }

    private Student parseStudent(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            System.err.println("Неверный формат строки (нужно 4 поля): " + line);
            return null;
        }
        try {
            String fullName = parts[0].trim();
            int group = Integer.parseInt(parts[1].trim());
            double score = Double.parseDouble(parts[2].trim());
            int record = Integer.parseInt(parts[3].trim());
            return new Student.Builder(fullName, group, score, record).build();
        } catch (NumberFormatException e) {
            System.err.println("Ошибка чисел в строке: " + line);
            return null;
        }
    }
}