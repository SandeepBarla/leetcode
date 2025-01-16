class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backtrack(nums, result, new ArrayList<>(), visited);
        return result;
    }

    private void backtrack(int[] nums, List<List<Integer>> result, List<Integer> comb, boolean[] visited){
        if(comb.size()==nums.length){
            result.add(new ArrayList<>(comb));
            return;
        }

        for(int i=0; i<nums.length; i++){
            if(visited[i]) continue;
            visited[i] = true;
            comb.add(nums[i]);
            backtrack(nums, result, comb, visited);
            comb.remove(Integer.valueOf(nums[i]));
            visited[i] = false;
        }
    }
}

// Backtracking solution
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