package common;

import java.util.Arrays;
import java.util.List;

public class ArrayUtils {

    // Print an integer array with a label
    public static void printArray(String label, int[] arr) {
        System.out.println(label + ": " + Arrays.toString(arr));
    }

    // Print a 2D integer array with a label
    public static void print2DArray(String label, int[][] matrix) {
        System.out.println(label + ":");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // Print a list of integers with a label
    public static void printList(String label, List<Integer> list) {
        System.out.println(label + ": " + list);
    }

    // Print a list of lists (2D List) with a label (For problems like LC 78
    // Subsets)
    public static void print2DList(String label, List<List<Integer>> listOfLists) {
        System.out.println(label + ":");
        for (List<Integer> list : listOfLists) {
            System.out.println(list);
        }
    }
}