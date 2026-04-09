package filler;

import model.Student;
import validator.StudentValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomFiller implements DataFiller {

    private static final double DOUBLE_LAST_NAME_PROB = 0.2;   // 20% на двойную фамилию
    private static final double NO_PATRONYM_PROB = 0.2;       // 20% без отчества
    private static final int MAX_ATTEMPTS_FACTOR = 3;         // множитель для генерации с запасом

    private final Random random = new Random();

    // Мужские фио
    private final String[] maleFirstNames = {
            "Иван", "Пётр", "Алексей", "Дмитрий", "Сергей", "Андрей", "Владимир", "Николай", "Михаил", "Александр"
    };
    private final String[] maleLastNames = {
            "Иванов", "Петров", "Сидоров", "Смирнов", "Кузнецов", "Попов", "Соколов", "Лебедев", "Козлов", "Новиков"
    };
    private final String[] malePatronyms = {
            "Иванович", "Петрович", "Сидорович", "Алексеевич", "Дмитриевич", "Михайлович",
            "Александрович", "Владимирович", "Николаевич", "Сергеевич"
    };
    private final String[] maleSecondParts = {"Петров", "Сидоров", "Кузнецов", "Смирнов", "Иванов"};

    // Женские фио
    private final String[] femaleFirstNames = {
            "Анна", "Мария", "Елена", "Татьяна", "Ольга", "Екатерина", "Наталья", "Юлия", "Ксения", "Светлана"
    };
    private final String[] femaleLastNames = {
            "Иванова", "Петрова", "Сидорова", "Смирнова", "Кузнецова", "Попова", "Соколова", "Лебедева", "Козлова", "Новикова"
    };
    private final String[] femalePatronyms = {
            "Ивановна", "Петровна", "Сидоровна", "Алексеевна", "Дмитриевна", "Михайловна",
            "Александровна", "Владимировна", "Николаевна", "Сергеевна"
    };
    private final String[] femaleSecondParts = {"Петрова", "Сидорова", "Кузнецова", "Смирнова", "Иванова"};

    //  Генерирует указанное количество валидных случайных студентов
    @Override
    public List<Student> fill(int size) {
        if (size <= 0) {
            return new ArrayList<>();
        }
        //  Генерация с запасом
        return IntStream.iterate(0, i -> i + 1)
                .mapToObj(i -> generateStudent())
                .filter(StudentValidator::validate)
                .limit(size)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    private Student generateStudent() {
        boolean isMale = random.nextBoolean();

        String lastName = isMale
                ? maleLastNames[random.nextInt(maleLastNames.length)]
                : femaleLastNames[random.nextInt(femaleLastNames.length)];

        // Двойная фамилия с вероятностью DOUBLE_LAST_NAME_PROB
        if (random.nextDouble() < DOUBLE_LAST_NAME_PROB) {
            String second = isMale
                    ? maleSecondParts[random.nextInt(maleSecondParts.length)]
                    : femaleSecondParts[random.nextInt(femaleSecondParts.length)];
            lastName = lastName + "-" + second;
        }

        String firstName = isMale
                ? maleFirstNames[random.nextInt(maleFirstNames.length)]
                : femaleFirstNames[random.nextInt(femaleFirstNames.length)];

        // Отсутствие отчества с вероятностью NO_PATRONYM_PROB
        String fullName;
        if (random.nextDouble() < NO_PATRONYM_PROB) {
            fullName = lastName + " " + firstName;
        } else {
            String patronym = isMale
                    ? malePatronyms[random.nextInt(malePatronyms.length)]
                    : femalePatronyms[random.nextInt(femalePatronyms.length)];
            fullName = lastName + " " + firstName + " " + patronym;
        }

        int group = 1 + random.nextInt(4);
        double rawScore = 2.0 + random.nextDouble() * 3.0;
        double score = Math.round(rawScore * 100.0) / 100.0; // Два знака
        int record = 100_000 + random.nextInt(900_000);

        return new Student.Builder(fullName, group, score, record).build();
    }
}