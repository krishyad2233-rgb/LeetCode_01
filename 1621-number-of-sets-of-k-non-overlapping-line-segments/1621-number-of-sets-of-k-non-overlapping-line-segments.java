class Solution {
    public int numberOfSets(int n, int k) {
        final int MOD = 1_000_000_007;
        long[][] dp = new long[n][k + 1];
        long[][] prefix = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            prefix[i][0] = i + 1;
        }
        for (int j = 1; j <= k; j++) {
            for (int i = 1; i < n; i++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] =
                    (dp[i][j] + prefix[i - 1][j - 1]) % MOD;
                prefix[i][j] =
                    (prefix[i - 1][j] + dp[i][j]) % MOD;
            }
        }
        return (int) dp[n - 1][k];
    }
}