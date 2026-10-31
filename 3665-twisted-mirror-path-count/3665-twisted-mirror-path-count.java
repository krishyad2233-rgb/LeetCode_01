class Solution {
    static final int MOD = 1000000007;
    int m, n;
    int[][] grid;
    int[] memo;
    int[] path;
    int resolve(int r, int c, int dir) {
        int len = 0;
        int res = -1;
        while (r < m && c < n) {
            if (grid[r][c] == 0) {
                res = r * n + c;
                break;
            }
            int state = (r * n + c) * 2 + dir;
            if (memo[state] != -2) {
                res = memo[state];
                break;
            }
            path[len++] = state;
            if (dir == 0) {
                r++;
                dir = 1;
            } else {
                c++;
                dir = 0;
            }
        }
        for (int i = len - 1; i >= 0; i--) {
            memo[path[i]] = res;
        }
        return res;
    }
    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        if (m == 1 && n == 1) return 1;
        if (grid[0][0] == 1) return 0;
        memo = new int[2 * m * n];
        java.util.Arrays.fill(memo, -2);
        path = new int[2 * m * n];
        long[][] dp = new long[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 || dp[i][j] == 0) {
                    continue;
                }
                if (j + 1 < n) {
                    int next = resolve(i, j + 1, 0);
                    if (next != -1) {
                        int nr = next / n;
                        int nc = next % n;
                        dp[nr][nc] = (dp[nr][nc] + dp[i][j]) % MOD;
                    }
                }
                if (i + 1 < m) {
                    int next = resolve(i + 1, j, 1);
                    if (next != -1) {
                        int nr = next / n;
                        int nc = next % n;
                        dp[nr][nc] = (dp[nr][nc] + dp[i][j]) % MOD;
                    }
                }
            }
        }
        return (int) dp[m - 1][n - 1];
    }
}