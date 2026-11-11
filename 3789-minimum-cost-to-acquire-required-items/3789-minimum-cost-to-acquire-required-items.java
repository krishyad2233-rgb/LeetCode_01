public class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long n1 = Math.max(0, need1);
        long n2 = Math.max(0, need2);
        long ans = Long.MAX_VALUE;
        long[] candidates = {0, n1, n2};
        for (long x : candidates) {
            long rem1 = Math.max(0, n1 - x);
            long rem2 = Math.max(0, n2 - x);
            long currentCost = x * costBoth + rem1 * cost1 + rem2 * cost2;
            ans = Math.min(ans, currentCost);
        }
        return ans;
    }
}
