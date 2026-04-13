package filler;

import model.Student;

import collection.List;

/**
 * Интерфейс заполнителя, который будет заполнять кастомный список студентами, вводимыми с консоли
 */
public interface DataFiller {
    List<Student> fill(int size);
}