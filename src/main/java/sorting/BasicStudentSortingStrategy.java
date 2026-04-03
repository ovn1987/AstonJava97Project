package sorting;

import model.Student;

import java.util.Comparator;
import java.util.List;

public class BasicStudentSortingStrategy extends StudentSortingStrategy{
    private Comparator<Student> comparator;
    @Override
    public void sort(List<Student> students, StudentField fieldToSortBy) {
        comparator = getComparator(fieldToSortBy);
        mergeSort(students, 0, students.size()-1);
    }

    private void mergeSort(List<Student> students, int startIndex, int endIndex){
        if (startIndex < endIndex){
            int middleIndex = (startIndex + endIndex) / 2;
            mergeSort(students, startIndex, middleIndex);
            mergeSort(students, middleIndex+1, endIndex);

            merge(students, startIndex, middleIndex, endIndex);
        }
    }

    private void merge(List<Student> students, int startIndex, int middleIndex, int endIndex){
        List<Student> leftList = List.copyOf(students.subList(startIndex, middleIndex+1));
        List<Student> rightList = List.copyOf(students.subList(middleIndex+1, endIndex+1));


        int leftPointer = 0;
        int rightPointer = 0;
        int sortedListPointer = startIndex;

        while(leftPointer < leftList.size() && rightPointer < rightList.size()){
            if(comparator.compare(leftList.get(leftPointer), rightList.get(rightPointer)) <= 0){
                students.set(sortedListPointer, leftList.get(leftPointer));
                leftPointer++;
            } else {
                students.set(sortedListPointer, rightList.get(rightPointer));
                rightPointer++;
            }
            sortedListPointer++;
        }

        while (leftPointer < leftList.size()){
            students.set(sortedListPointer, leftList.get(leftPointer));
            leftPointer++;
            sortedListPointer++;
        }
        while (rightPointer < rightList.size()){
            students.set(sortedListPointer, rightList.get(rightPointer));
            rightPointer++;
            sortedListPointer++;
        }
    }
}
