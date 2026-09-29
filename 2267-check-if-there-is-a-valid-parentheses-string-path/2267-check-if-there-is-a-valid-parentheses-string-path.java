class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if ((m + n - 1) % 2 != 0) return false;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;
        Boolean[][][] dp = new Boolean[m][n][m + n];
        return solve(grid, 0, 0, 0, dp);
    }
    private boolean solve(char[][] grid, int r, int c, int balance, Boolean[][][] dp) {
        int m = grid.length;
        int n = grid[0].length;
        if (grid[r][c] == '(')
            balance++;
        else
            balance--;
        if (balance < 0) return false;
        int remaining = (m - 1 - r) + (n - 1 - c);
        if (balance > remaining) return false;
        if (r == m - 1 && c == n - 1)
            return balance == 0;
        if (dp[r][c][balance] != null)
            return dp[r][c][balance];
        boolean ans = false;
        if (r + 1 < m)
            ans = solve(grid, r + 1, c, balance, dp);
        if (!ans && c + 1 < n)
            ans = solve(grid, r, c + 1, balance, dp);
        return dp[r][c][balance] = ans;
    }
}