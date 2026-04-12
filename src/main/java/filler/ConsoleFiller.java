package filler;

import collection.List;
import model.Student;
import validator.StudentValidator;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Класс, заполняющий список объектами, которые вводятся с консоли
 */
public class ConsoleFiller extends ParseFiller {

    private Scanner scanner;

    public ConsoleFiller(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public List<Student> fill(int size) {
        if (size <= 0) {
            return new List<>();
        }

        scanner.nextLine();
        return IntStream.iterate(0, i -> i + 1)
                .mapToObj(i -> readStudent())
                .filter(StudentValidator::validate)
                .limit(size)
                .collect(List::new, List::add, List::addAll);
    }

    private Student readStudent() {
        System.out.println("Введите студента в формате ФИО,Номер_группы,Средний_балл,Номер_зачетной_книжки");
        String line = scanner.nextLine();
        return parseStudent(line);
    }

}
