class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;
        int[][] up = new int[LOG][n + 1];
        long[][] sum = new long[LOG][n];
        int[] stack = new int[n];
        int top = -1;
        int[] next = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }
            next[i] = top == -1 ? n : stack[top];
            stack[++top] = i;
        }
        for (int i = 0; i < n; i++) {
            up[0][i] = next[i];
            sum[0][i] = (long) (next[i] - i) * nums[i];
        }
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                int mid = up[j - 1][i];
                if (mid < n) {
                    up[j][i] = up[j - 1][mid];
                    sum[j][i] = sum[j - 1][i] + sum[j - 1][mid];
                } else {
                    up[j][i] = n;
                    sum[j][i] = sum[j - 1][i];
                }
            }
        }
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        long ans = 0;
        for (int r = 0; r < n; r++) {
            int low = 0, high = r;
            int first = r + 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                long maxSum = 0;
                int cur = mid;
                for (int j = LOG - 1; j >= 0; j--) {
                    if (up[j][cur] <= r) {
                        maxSum += sum[j][cur];
                        cur = up[j][cur];
                    }
                }
                maxSum += (long) (r - cur + 1) * nums[cur];
                long cost = maxSum - (prefix[r + 1] - prefix[mid]);
                if (cost <= k) {
                    first = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            ans += r - first + 1;
        }
        return ans;
    }
}