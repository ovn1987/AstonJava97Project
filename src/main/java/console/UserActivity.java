package console;

import filler.ConsoleFiller;
import filler.FileFiller;
import filler.RandomFiller;
import collection.List;
import frequency.StudentFrequency;
import io.FileAppender;
import model.Student;
import sorting.BasicStudentSortingStrategy;
import sorting.ExcludeOddStudentSortingStrategy;
import sorting.StudentField;
import sorting.StudentSorter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserActivity {

    private Scanner scanner = new Scanner(System.in);

    private List<Student> list = new List<>();

    private FileAppender fileAppender = new FileAppender("results.txt");

    public void interactWithUser() {
        int answer = -1;
        while (answer != 0) {
            if (list.isEmpty()) {
                System.out.println("Список студентов пуст");
            } else {
                System.out.println("Список студентов: ");
                list.stream().forEach(System.out::println);
            }
            System.out.println();
            answer = -1;
            while (answer < 0 || answer > 3) {
                System.out.println("0 - выход, 1 - сортировка, 2 - получение нового списка, 3 - поиск");
                System.out.println("Ваш выбор:");
                answer = tryScanInt();
            }
            if (answer == 1) {
                sort();
            } else if (answer == 2) {
                fill();
            } else if (answer == 3) {
                search();
            }
        }
    }

    /**
     * Сортировка студентов
     */
    private void sort() {
        if (list.isEmpty()) {
            System.out.println("Пустой список нет смысла сортировать");
        } else {
            int answerSort = -1;
            while (answerSort < 4 || answerSort > 7) {
                System.out.println("4 - по имени, 5 - по номеру группы, 6 - по среднему баллу, 7 - по номеру зачетки");
                System.out.println("Ваш выбор:");
                answerSort = tryScanInt();
            }
            int answerSortType = -1;
            if (answerSort > 4) {
                while (answerSortType < 8 || answerSortType > 9) {
                    System.out.println("8 - обычная сортировка, 9 - сортировка только чётных чисел");
                    System.out.println("Ваш выбор:");
                    answerSortType = tryScanInt();
                }
            }
            StudentSorter.setStudentSortingStrategy(answerSortType == 9 ?
                    new ExcludeOddStudentSortingStrategy() : new BasicStudentSortingStrategy());
            StudentSorter.sort(list, StudentField.values()[answerSort - 4]);

            fileAppender.write(list); // добавление результата сортировки в файл
        }
    }

    /**
     * Формирование нового списка студентов
     */
    private void fill() {
        int count = -1;
        while (count <= 0) {
            System.out.println("Введите желаемое количество студентов:");
            count = tryScanInt();
        }
        int answerEnterType = -1;
        while (answerEnterType < 4 || answerEnterType > 6) {
            System.out.println("4 - чтение из файла, 5 - ввод вручную, 6 - псевдослучайный генератор");
            System.out.println("Ваш выбор:");
            answerEnterType = tryScanInt();
        }
        if (answerEnterType == 4) {
            System.out.println("Введите имя файла:");
            scanner.nextLine();
            String fileName = scanner.nextLine();
            list = new FileFiller(fileName).fill(count);
        } else if (answerEnterType == 5) {
            list = new ConsoleFiller(scanner).fill(count);
        } else {
            list = new RandomFiller().fill(count);
        }
    }

    /**
     * Поиск студента в списке, вывод количества совпадений
     */
    private void search() {
        if (list.isEmpty()) {
            System.out.println("В пустом списке нет смысла что-либо искать");
        } else {
            System.out.print("Искомый студент: ");
            Student toSearch = new ConsoleFiller(scanner).fill(1).get(0);
            int result = StudentFrequency.getStudentFrequencyConcurrently(list, toSearch);
            String s = "Количество найденных: " + result;
            System.out.println(s);
            System.out.println();
            fileAppender.write(s);
        }
    }

    /**
     * Считывание целого числа с защитой от неправильного ввода
     * @return считанное целое число или -1 в случае неправильного ввода
     */
    private int tryScanInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.err.println("Введите целое число!");
            scanner.nextLine();
            return -1;
        }
    }

}
