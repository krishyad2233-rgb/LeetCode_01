class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        boolean sorted = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) { sorted = false; break; }
        }
        if (sorted) return 0;
        int g = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                if (g == -1) g = i;
                else g &= i;
            }
        }
        return g == -1 ? 0 : g;
    }
}