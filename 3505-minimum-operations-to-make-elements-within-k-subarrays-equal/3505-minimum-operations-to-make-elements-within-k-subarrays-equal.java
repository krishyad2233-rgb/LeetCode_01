import java.util.*;
class Solution {
    class MultiSet {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int size = 0;
        long sum = 0;
        void add(int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            size++;
            sum += val;
        }
        void remove(int val) {
            int count = map.get(val);
            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
            size--;
            sum -= val;
        }
        int first() {
            return map.firstKey();
        }
        int last() {
            return map.lastKey();
        }
    }
    MultiSet low = new MultiSet();
    MultiSet high = new MultiSet();
    void rebalance() {
        int target = (low.size + high.size + 1) / 2;
        while (low.size > target) {
            int val = low.last();
            low.remove(val);
            high.add(val);
        }
        while (low.size < target) {
            int val = high.first();
            high.remove(val);
            low.add(val);
        }
    }
    void insert(int val) {
        if (low.size == 0 || val <= low.last()) {
            low.add(val);
        } else {
            high.add(val);
        }
        rebalance();
    }
    void erase(int val) {
        if (low.map.containsKey(val)) {
            low.remove(val);
        } else {
            high.remove(val);
        }
        rebalance();
    }
    long getCost() {
        long median = low.last();
        return median * low.size - low.sum
                + high.sum - median * high.size;
    }
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];
        for (int i = 0; i < n; i++) {
            insert(nums[i]);
            if (i >= x) {
                erase(nums[i - x]);
            }
            if (i >= x - 1) {
                cost[i - x + 1] = getCost();
            }
        }
        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][n + 1];
        for (int j = 1; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }
        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                dp[j][i] = dp[j][i - 1];
                if (i >= x && dp[j - 1][i - x] != INF) {
                    dp[j][i] = Math.min(
                        dp[j][i],
                        dp[j - 1][i - x] + cost[i - x]
                    );
                }
            }
        }
        return dp[k][n];
    }
}