import java.util.*;
class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] squares = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        Arrays.sort(squares);
        long ans = 0;
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            ans -= squares[i];
        }
        for (int i = n / 2; i < n; i++) {
            ans += squares[i];
        }
        return ans;
    }
}