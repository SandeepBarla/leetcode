package solutions.top_interview_questions.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.ArrayUtils;
import common.Solution;

// T.C = O(n*logn); n is number of intervals
// S.C = O(n); n is number of intervals
public class LC_56_MergeIntervals implements Solution {
    public int[][] merge(int[][] intervals) {
        // sort intervals based on left range
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> result = new ArrayList<>();
        int[] prevInterval = intervals[0];
        // iterate through all the rows (intervals)
        for (int i = 1; i < intervals.length; i++) {
            int[] currInterval = intervals[i];
            // if left range of current interval is greater than right range of previous
            // interval
            // those two are overlapping intervals merge them
            if (prevInterval[1] >= currInterval[0]) {
                prevInterval[1] = Math.max(prevInterval[1], currInterval[1]);
            } else {
                // else add the previous interval to result
                // and update the current interval to prev interval
                result.add(prevInterval);
                prevInterval = intervals[i];
            }
        }
        result.add(prevInterval);
        return result.toArray(new int[result.size()][]);
    }

    @Override
    public void run() {
        int[][] intervals = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };

        // Using ArrayUtils to print the array
        ArrayUtils.print2DArray("Input Array", intervals);

        ArrayUtils.print2DArray("Output Merged Array", merge(intervals));
    }

}
