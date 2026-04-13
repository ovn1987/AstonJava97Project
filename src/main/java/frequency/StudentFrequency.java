package frequency;

import model.Student;

import collection.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Класс, реализующий многопоточный поиск в коллекции и определение количества вхождений элемента
 */
public class StudentFrequency {

    /**
     * Многопоточный поиск
     * @param students список студентов
     * @param student искомый студент
     * @return количество вхождений
     */
    public static int getStudentFrequencyConcurrently(List<Student> students, Student student) {
        if (students == null) {
            throw new NullPointerException("Аргумент students не должен быть null.");
        }
        if (students.isEmpty()) {
            return 0;
        }
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int amountOfSubtasks = Math.min(students.size(), availableProcessors);
        List<Future<Integer>> results = new List<>();
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

    /**
     * Разделение списка на маленькие списки по числу ядер процессора
     * @param students исходный список
     * @param partitions количество результирующих списков
     * @return список результирующих списков
     */
    private static List<List<Student>> partitionList(List<Student> students, int partitions){
        List<List<Student>> partitionedStudents = new List<>();
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

    /**
     * Подсчет количества вхождений в отдельном маленьком списке
     * @param students
     * @param student
     * @return
     */
    private static int getStudentFrequency(List<Student> students, Student student) {
        int counter = 0;
        for(Student studentToCheck: students){
            if(studentToCheck.equals(student)){
                counter++;
            }
        }
        return counter;

//        return (int)students.stream().filter(s -> s.equals(student)).count();
    }
}
