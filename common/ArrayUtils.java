package common;

import java.util.Arrays;
import java.util.List;

public class ArrayUtils {

    // Print an integer array with a label
    public static void printArray(String label, int[] arr) {
        System.out.println(label + ": " + Arrays.toString(arr));
    }

    // ✅ Print a character array with a label
    public static void printCharArray(String label, char[] arr) {
        System.out.println(label + ": " + Arrays.toString(arr));
    }

    // Print a 2D integer array with a label
    public static void print2DArray(String label, int[][] matrix) {
        System.out.println(label + ":");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // ✅ Print a 2D character array with a label
    public static void print2DCharArray(char[][] matrix) {
        System.out.println("Board:");
        for (char[] row : matrix) {
            for (char ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }

    // Print a list of integers with a label
    public static void printList(String label, List<Integer> list) {
        System.out.println(label + ": " + list);
    }

    // ✅ Print a 2D List of Integers (For problems like LC 78: Subsets)
    public static void print2DList(String label, List<List<Integer>> listOfLists) {
        System.out.println(label + ":");
        for (List<Integer> list : listOfLists) {
            System.out.println(list);
        }
    }

    // ✅ Print a 2D List of Strings (For problems like LC 131: Palindrome
    // Partitioning)
    public static void print2DStringList(String label, List<List<String>> listOfLists) {
        System.out.println(label + ":");
        for (List<String> list : listOfLists) {
            System.out.println(list);
        }
    }
}