package filler;

import model.Student;

import java.util.List;

public interface DataFiller {
    List<Student> fill(int size);
}