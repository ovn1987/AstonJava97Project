package validator;

import model.Student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class StudentValidator {

    // Константы параметров студентов
    private static final int MIN_GROUP = 1;
    private static final int MAX_GROUP = 4;
    private static final double MIN_SCORE = 2.0;
    private static final double MAX_SCORE = 5.0;
    private static final int MIN_RECORD = 100_000;
    private static final int MAX_RECORD = 999_999;
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_WORD_COUNT = 5;

    private StudentValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static boolean validate(Student student) {
        return student != null &&
                validateFullName(student.getFullName()) &&
                validateGroupNumber(student.getGroupNumber()) &&
                validateAverageScore(student.getAverageScore()) &&
                validateRecordNumber(student.getRecordStudentBookNumber());
    }

    // Валидация фио
    public static boolean validateFullName(String fullName) {
        if (fullName == null) {
            return false;
        }

        String trimmed = fullName.trim();
        if (trimmed.isEmpty()) {
            return false;
        }

        if (trimmed.length() > MAX_NAME_LENGTH) {
            return false;
        }

        String[] words = trimmed.split("\\s+");
        if (words.length < 2 || words.length > MAX_WORD_COUNT) {
            return false;
        }

        for (String word : words) {
            if (!isValidWord(word)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidWord(String word) {
        // Проверка на слова из дефисов
        if (isOnlyHyphens(word)) {
            return false;
        }

        // Начало или конец не начинаются/заканчиваются дефисом
        if (word.startsWith("-") || word.endsWith("-")) {
            return false;
        }

        // Проверка русских символов
        for (char c : word.toCharArray()) {
            if (!isRussianLetter(c) && c != '-') {
                return false;
            }
        }

        // Разбиение по дефису и проверка на заглавные
        String[] parts = word.split("-");
        for (String part : parts) {
            if (!isCapitalizedCorrectly(part)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isOnlyHyphens(String word) {
        for (char c : word.toCharArray()) {
            if (c != '-') return false;
        }
        return true;
    }

    private static boolean isCapitalizedCorrectly(String part) {
        if (part.isEmpty()) return false;

        char first = part.charAt(0);
        if (!isRussianUpperCase(first)) return false;

        for (int i = 1; i < part.length(); i++) {
            if (!isRussianLowerCase(part.charAt(i))) return false;
        }
        return true;
    }

    private static boolean isRussianLetter(char c) {
        return (c >= 'А' && c <= 'Я') ||
                (c >= 'а' && c <= 'я') ||
                c == 'Ё' || c == 'ё';
    }

    private static boolean isRussianUpperCase(char c) {
        return (c >= 'А' && c <= 'Я') || c == 'Ё';
    }

    private static boolean isRussianLowerCase(char c) {
        return (c >= 'а' && c <= 'я') || c == 'ё';
    }

    // Валидация номера группы
    public static boolean validateGroupNumber(int groupNumber) {
        return groupNumber >= MIN_GROUP && groupNumber <= MAX_GROUP;
    }

    // Валидация среднего балла
    public static boolean validateAverageScore(double averageScore) {
        if (averageScore < MIN_SCORE || averageScore > MAX_SCORE) {
            return false;
        }
        // Количество знаков после запятой
        BigDecimal bd = BigDecimal.valueOf(averageScore).stripTrailingZeros();
        int scale = bd.scale();
        return scale <= 2;
    }

    // Валидация зачетной книжки
    public static boolean validateRecordNumber(int recordNumber) {
        return recordNumber >= MIN_RECORD && recordNumber <= MAX_RECORD;
    }

    // Логирование ошибок
    public static boolean validateWithMessage(Student student, StringBuilder errorMessage) {
        if (student == null) {
            errorMessage.append("Ошибка. Студент не может быть null");
            return false;
        }

        boolean isValid = true;

        if (!validateFullName(student.getFullName())) {
            errorMessage.append("Ошибка. ФИО должно содержать только русские буквы, каждое слово с заглавной, максимальная длина 50 символов. ");
            isValid = false;
        }

        if (!validateGroupNumber(student.getGroupNumber())) {
            errorMessage.append("Ошибка. Номер группы должен быть от ").append(MIN_GROUP).append(" до ").append(MAX_GROUP).append(". ");
            isValid = false;
        }

        if (!validateAverageScore(student.getAverageScore())) {
            errorMessage.append("Ошибка. Средний балл должен быть от ").append(MIN_SCORE).append(" до ").append(MAX_SCORE).append(" с не более чем 2 знаками после запятой. ");
            isValid = false;
        }

        if (!validateRecordNumber(student.getRecordStudentBookNumber())) {
            errorMessage.append("Неверный номер зачетки. Должен быть 6-значным числом от ").append(MIN_RECORD).append(" до ").append(MAX_RECORD).append(". ");
            isValid = false;
        }

        return isValid;
    }
}