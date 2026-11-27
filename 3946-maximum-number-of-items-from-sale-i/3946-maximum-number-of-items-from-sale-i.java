class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
            minPrice = Math.min(minPrice, item[1]);
        }
        int[] count = new int[maxFactor + 1];
        for (int[] item : items) {
            count[item[0]]++;
        }
        int[] dp = new int[budget + 1];
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            int value = 0;
            for (int j = factor; j <= maxFactor; j += factor) {
                value += count[j];
            }
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + value);
            }
        }
        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, dp[b] + (budget - b) / minPrice);
        }
        return ans;
    }
}