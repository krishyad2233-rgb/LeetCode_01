import java.util.*;
class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long mod = 1000000007L;
        long[] bits = new long[32];
        for (int num : nums) {
            for (int b = 0; b < 32; b++) {
                if (((num >> b) & 1) != 0) {
                    bits[b]++;
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < k; i++) {
            long num = 0;
            for (int b = 31; b >= 0; b--) {
                if (bits[b] > 0) {
                    num |= (1L << b);
                    bits[b]--;
                }
            }
            ans = (ans + num * num % mod) % mod;
        }
        return (int) ans;
    }
}