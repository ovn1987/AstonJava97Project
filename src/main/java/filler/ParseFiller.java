package filler;

import model.Student;

/**
 * Абстрактный класс - предок заполнителей, которые будут распарсивать строки и получать из них студентов
 * Ожидает формат строки: ФИО,номер группы,средний балл,номер зачётки
 */
public abstract class ParseFiller implements DataFiller {

    /**
     * Анализ строки определённого формата для получения студента из нее
     * @param line строка ожидаемого формата
     * @return студент - результат парсинга
     */
    protected Student parseStudent(String line) {
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
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

}
