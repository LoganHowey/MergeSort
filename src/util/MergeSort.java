package util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class MergeSort {

    public static <Type> void sort(Type[] toSort, Comparator<Type> comparator){
        mergeSort(toSort, 0, toSort.length, comparator);
    }

    private static <Type> void mergeSort(Type[] toSort, int low, int high, Comparator<Type> comparator){
        int i;
        int middle;

        if (low < high){
            middle = (low + high)/2;
            mergeSort(toSort, low, middle, comparator);
            mergeSort(toSort, middle+1, high, comparator);
            merge(toSort, low, middle, high, comparator);
        }
    }

    private static <Type> void merge(Type[] toSort, int low, int middle, int high, Comparator<Type> comparator){

        int i;
        Queue<Type> buffer1 = new LinkedList<>();
        Queue<Type> buffer2 = new LinkedList<>();

        for (i = low; i < middle; i++) buffer1.add(toSort[i]);
        for (i = middle; i <= high; i++) buffer2.add(toSort[i]);

        i = low;
        while (buffer1.size() != 0 || buffer2.size() != 0){
            if (comparator.compare(buffer1.peek(), buffer2.peek()) <=0){
                toSort[i++] = buffer1.remove();
            }
            else {
                toSort[i++] = buffer2.remove();
            }
            while (buffer1.size() != 0) toSort[i++] = buffer1.remove();
            while (buffer2.size() != 0) toSort[i++] = buffer2.remove();
        }
    }

    public static void main(String[] args) throws Exception {
        String[] data = {"This", "is", "an", "Example", "of", "sorting", "Strings", "Test"};

        sort(data, Comparator.naturalOrder());
        System.out.println(Arrays.toString(data));
    }
}
