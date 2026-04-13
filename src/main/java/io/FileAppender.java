package io;

import collection.List;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс для записи результатов в файл в режиме добавления
 */
public class FileAppender {

    private String fileName;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public FileAppender(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Запись в файл списка объектов
     * @param list
     */
    public void write(List list) {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(sdf.format(new Date()) + "\n");
            for(Object o : list) {
                fileWriter.write(o.toString() + "\n");
            }
        } catch (IOException ex) {
            System.err.println("Ошибка добавления в файл: " + ex.getMessage());
        }
    }

    /**
     * Запись в файл строки
     * @param s
     */
    public void write(String s) {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(sdf.format(new Date()) + "\n");
            fileWriter.write(s + "\n");
        } catch (IOException ex) {
            System.err.println("Ошибка добавления в файл: " + ex.getMessage());
        }
    }

}
