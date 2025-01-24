package common;

import java.util.Arrays;

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
}