class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }
    private void backtrack(int[] candidates, int target, int index,
                           List<Integer> current,
                           List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(current));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                current.add(candidates[i]);
                backtrack(
                    candidates,
                    target - candidates[i],
                    i,
                    current,
                    ans
                );
                current.remove(current.size() - 1);
            }
        }
    }
}