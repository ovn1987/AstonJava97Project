package console;

import filler.ConsoleFiller;
import filler.FileFiller;
import filler.RandomFiller;
import collection.List;
import io.FileAppender;
import model.Student;
import sorting.BasicStudentSortingStrategy;
import sorting.ExcludeOddStudentSortingStrategy;
import sorting.StudentField;
import sorting.StudentSorter;

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
            while (answer < 0 || answer > 2) {
                System.out.println("0 - выход, 1 - сортировка, 2 - получение нового списка");
                System.out.println("Ваш выбор:");
                answer = scanner.nextInt();
            }
            if (answer == 1) {
                int answerSort = -1;
                while (answerSort < 3 || answerSort > 6) {
                    System.out.println("3 - по имени, 4 - по номеру группы, 5 - по среднему баллу, 6 - по номеру зачетки");
                    System.out.println("Ваш выбор:");
                    answerSort = scanner.nextInt();
                }
                int answerSortType = -1;
                if (answerSort > 3) {
                    while(answerSortType < 7 || answerSortType > 8) {
                        System.out.println("7 - обычная сортировка, 8 - сортировка только чётных чисел");
                        System.out.println("Ваш выбор:");
                        answerSortType = scanner.nextInt();
                    }
                }
                StudentSorter.setStudentSortingStrategy(answerSortType == 8 ?
                        new ExcludeOddStudentSortingStrategy() : new BasicStudentSortingStrategy());
                StudentSorter.sort(list, StudentField.values()[answerSort - 3]);
                if (!list.isEmpty()) {
                    fileAppender.write(list); // добавление результата сортировки в файл
                }
            } else if (answer == 2) {
                int count = -1;
                while (count < 0) {
                    System.out.println("Введите желаемое количество студентов:");
                    count = scanner.nextInt();
                }
                int answerEnterType = -1;
                while (answerEnterType < 3 || answerEnterType > 5) {
                    System.out.println("3 - чтение из файла, 4 - ввод вручную, 5 - псевдослучайный генератор");
                    System.out.println("Ваш выбор:");
                    answerEnterType = scanner.nextInt();
                }
                if (answerEnterType == 3) {
                    System.out.println("Введите имя файла:");
                    scanner.nextLine();
                    String fileName = scanner.nextLine();
                    list = new FileFiller(fileName).fill(count);
                } else if (answerEnterType == 4) {
                    list = new ConsoleFiller(scanner).fill(count);
                } else {
                    list = new RandomFiller().fill(count);
                }
            }
        }
    }

}
