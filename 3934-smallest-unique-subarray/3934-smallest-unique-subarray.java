import java.util.*;
class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long base1 = 911382323L;
        long base2 = 972663749L;
        long mod1 = 1000000007L;
        long mod2 = 1000000009L;
        long[] pow1 = new long[n + 1];
        long[] pow2 = new long[n + 1];
        long[] pref1 = new long[n + 1];
        long[] pref2 = new long[n + 1];
        pow1[0] = 1;
        pow2[0] = 1;
        for (int i = 0; i < n; i++) {
            pow1[i + 1] = (pow1[i] * base1) % mod1;
            pow2[i + 1] = (pow2[i] * base2) % mod2;
            long val = nums[i] + 1000000001L;
            pref1[i + 1] = (pref1[i] * base1 + val) % mod1;
            pref2[i + 1] = (pref2[i] * base2 + val) % mod2;
        }
        int low = 1, high = n, ans = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUnique(mid, n, pref1, pref2, pow1, pow2, mod1, mod2)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
    private boolean hasUnique(int len, int n, long[] pref1, long[] pref2, long[] pow1, long[] pow2, long mod1, long mod2) {
        Map<Long, Integer> freq = new HashMap<>();
        for (int i = 0; i + len <= n; i++) {
            long h1 = (pref1[i + len] - (pref1[i] * pow1[len]) % mod1 + mod1) % mod1;
            long h2 = (pref2[i + len] - (pref2[i] * pow2[len]) % mod2 + mod2) % mod2;
            long key = (h1 << 32) | h2;
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }
        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}
