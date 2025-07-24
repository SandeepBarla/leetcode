package solutions.top_interview_questions.medium;

import java.util.HashSet;
import java.util.Set;

import common.ArrayUtils;
import common.Solution;

public class LC_73_SetMatrixZeroes implements Solution {
    // T.C = O(m*n)
    // S.C = O(m+n)
    public void setZeroesUsingHashSet(int[][] matrix) {
        Set<Integer> row = new HashSet<>();
        Set<Integer> col = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (!row.contains(i))
                        row.add(i);
                    if (!col.contains(j))
                        col.add(j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row.contains(i) || col.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // T.C = O(m*n)
    // S.C = O(1)
    public void setZeroesUsingFirstRowAndCol(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;
        // check if first row has zero
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }
        // check if first col has zero
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }
        // when cell is zero mark the appropriate first row and col cells as zero
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // mark the cells as zero according to the first row and col cells
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // mark the first row and col cells as zero if needed
        if (firstRowHasZero) {
            for (int i = 0; i < n; i++)
                matrix[0][i] = 0;
        }
        if (firstColHasZero) {
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
        }
    }

    @Override
    public void run() {
        int[][] matrix = {
                { 1, 1, 1 },
                { 1, 0, 1 },
                { 1, 1, 1 }
        };
        ArrayUtils.print2DArray("Input", matrix);
        setZeroesUsingHashSet(matrix);
        ArrayUtils.print2DArray("Output of setZeroesUsingHashSet:", matrix);
        int[][] matrix2 = {
                { 1, 1, 1 },
                { 1, 0, 1 },
                { 1, 1, 1 }
        };
        ArrayUtils.print2DArray("Input", matrix2);
        setZeroesUsingFirstRowAndCol(matrix2);
        ArrayUtils.print2DArray("Output of setZeroesUsingFirstRowAndCol:", matrix2);
    }
}
