package frequency;

import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StudentFrequency {
    public static int getStudentFrequencyConcurrently(List<Student> students, Student student){
        if (students == null){
            throw new NullPointerException("Аргумент students не должен пыть null.");
        }
        if (students.isEmpty()){
            return 0;
        }
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int amountOfSubtasks = Math.min(students.size(), availableProcessors);
        List<Future<Integer>> results = new ArrayList<>();
        int result;
        try(ExecutorService executor = Executors.newFixedThreadPool(amountOfSubtasks);){
            for(List<Student> sublist: partitionList(students, amountOfSubtasks)){
                results.add(executor.submit(() -> getStudentFrequency(sublist, student)));
            }
            result = results
                    .stream()
                    .map(integerFuture -> {
                        try {
                            return integerFuture.get();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .reduce((x, y) -> x + y)
                    .get();
        }
        return result;
    }

    private static List<List<Student>> partitionList(List<Student> students, int partitions){
        List<List<Student>> partitionedStudents = new ArrayList<>();
        int partitionSize = students.size() / partitions;
        int remainder = students.size() % partitions;
        int j = 0;
        for (int i = 0; i < students.size();){
            if (j < remainder){
                partitionedStudents.add(students.subList(i, Math.min(i+partitionSize+1, students.size())));
                i++;
            } else {
                partitionedStudents.add(students.subList(i, Math.min(i+partitionSize, students.size())));
            }
            i+=partitionSize;
            j++;
        }
        return partitionedStudents;
    }

    private static int getStudentFrequency(List<Student> students, Student student){
        int counter = 0;
        for(Student studentToCheck: students){
            if(studentToCheck.equals(student)){
                counter++;
            }
        }
        return counter;
    }
}
