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
        List<Student> leftList = students.subList(startIndex, middleIndex);
        List<Student> rightList = students.subList(middleIndex+1, endIndex);

        int leftPointer = startIndex;
        int rightPointer = middleIndex+1;
        int sortedListPointer = startIndex;

        while(leftPointer <= middleIndex || rightPointer <= endIndex){
            if(comparator.compare(leftList.get(leftPointer), rightList.get(rightPointer)) <= 0){
                students.set(sortedListPointer, leftList.get(leftPointer));
                leftPointer++;
            } else {
                students.set(sortedListPointer, rightList.get(rightPointer));
                rightPointer++;
            }
            sortedListPointer++;
        }

        while (leftPointer <= middleIndex){
            students.set(sortedListPointer, leftList.get(leftPointer));
            leftPointer++;
            sortedListPointer++;
        }
        while (rightPointer <= endIndex){
            students.set(sortedListPointer, rightList.get(rightPointer));
            rightPointer++;
            sortedListPointer++;
        }
    }
}
