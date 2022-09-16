package util;

import java.util.Arrays;
import java.util.Comparator;

class MergeSorter {

    public static <E> void sort(E[] a, Comparator<? super E> comp) {
        mergeSort(a, 0, a.length - 1, comp);
    }


    private static <E> void mergeSort(E[] a, int from, int high, Comparator<? super E> comp) {
        if (from == high)
            return;
        int mid = (from + high) / 2;
        // Sort the first and the second half
        mergeSort(a, from, mid, comp);
        mergeSort(a, mid + 1, high, comp);
        merge(a, from, mid, high, comp);
    }

    private static <E> void merge(E[] a, int from, int mid, int to, Comparator<? super E> comp) {
        int n = to - from + 1;
        Object[] values = new Object[n];

        int fromValue = from;

        int middleValue = mid + 1;

        int index = 0;

        while (fromValue <= mid && middleValue <= to) {
            if (comp.compare(a[fromValue], a[middleValue]) < 0) {
                values[index] = a[fromValue];
                fromValue++;
            } else {
                values[index] = a[middleValue];
                middleValue++;
            }
            index++;
        }

        while (fromValue <= mid) {
            values[index] = a[fromValue];
            fromValue++;
            index++;
        }
        while (middleValue <= to) {
            values[index] = a[middleValue];
            middleValue++;
            index++;
        }

        for (index = 0; index < n; index++)
            a[from + index] = (E) values[index];
    }

    public static void main(String[] args) {
        String[] data = {"This", "is", "an", "Example", "of", "sorting", "Strings", "Test"};
        sort(data, Comparator.naturalOrder());
        System.out.println(Arrays.toString(data));
    }
}
