// Backtracking solution with maintaining visited elements
// T.C = O(n*n!); n is length of the array nums
// S.C = O(n*n!); n is length of the array nums
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backtrack(nums, result, new ArrayList<>(), visited);
        return result;
    }

    private void backtrack(int[] nums, List<List<Integer>> result, List<Integer> comb, boolean[] visited){
        // if combination has length same as nums array, add it to result and return
        if(comb.size()==nums.length){
            result.add(new ArrayList<>(comb));
            return;
        }
        // iterate through all the elements of nums array, and add only unvisited elements to combination
        for(int i=0; i<nums.length; i++){
            if(visited[i]) continue; // check if the element was vistied already
            visited[i] = true; // mark it as visited
            comb.add(nums[i]); // add it to combination
            backtrack(nums, result, comb, visited); // perform backtrack on the combination
            comb.remove(Integer.valueOf(nums[i])); // remove the element from combination
            visited[i] = false; // mark it as unvisited
        }
    }
}

// Backtracking solution
// T.C = O(n*n!); n is length of the array nums
// S.C = O(n*n!); n is length of the array nums
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, result, new ArrayList<>());
        return result;
    }

    private void backtrack(int[] nums, List<List<Integer>> result, List<Integer> comb){
        if(comb.size()==nums.length){
            result.add(new ArrayList<>(comb));
            return;
        }

        for(int i: nums){
            if(comb.contains(i)) continue;
            comb.add(i);
            backtrack(nums, result, comb);
            comb.remove(Integer.valueOf(i));
        }
    }
}