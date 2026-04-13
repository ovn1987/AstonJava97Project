package filler;

import model.Student;

import collection.List;

public interface DataFiller {
    List<Student> fill(int size);
}