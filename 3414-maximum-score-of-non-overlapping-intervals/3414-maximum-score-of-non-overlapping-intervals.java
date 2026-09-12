import java.util.*;
class Solution {
    static class Interval {
        int l, r, w, idx;
        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }
    static class State {
        long score;
        List<Integer> indices;
        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> in = intervals.get(i);
            arr[i] = new Interval(
                in.get(0),
                in.get(1),
                in.get(2),
                i
            );
        }
        Arrays.sort(arr, (a, b) -> {
            if (a.r != b.r)
               return Integer.compare(a.r, b.r);
            if (a.l != b.l)
                return Integer.compare(a.l, b.l);
            return Integer.compare(a.idx, b.idx);
        });
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            ends[i] = arr[i].r;
        }
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            prev[i] = findPrevious(ends, arr[i].l, i);
        }
        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0, new ArrayList<>());
            }
        }
        for (int i = 1; i <= n; i++) {
            Interval cur = arr[i - 1];
            for (int k = 1; k <= 4; k++) {
                State skip = dp[i - 1][k];
                int p = prev[i - 1];
                State old = dp[p + 1][k - 1];
                List<Integer> newIndices =
                    new ArrayList<>(old.indices);
                newIndices.add(cur.idx);
                Collections.sort(newIndices);
                State take = new State(
                    old.score + cur.w,
                    newIndices
                );
                dp[i][k] = better(skip, take);
            }
        }
        List<Integer> ans = dp[n][4].indices;
        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }
        return result;
    }
    private int findPrevious(int[] ends, int start, int right) {
        int low = 0;
        int high = right - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (ends[mid] < start) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
    private State better(State a, State b) {
        if (a.score > b.score)
            return a;
        if (b.score > a.score)
            return b;
        if (lexicographicallySmaller(a.indices, b.indices))
            return a;
        return b;
    }
    private boolean lexicographicallySmaller(
        List<Integer> a,
        List<Integer> b
    ) {
        int n = Math.min(a.size(), b.size());
        for (int i = 0; i < n; i++) {
            int x = a.get(i);
            int y = b.get(i);
            if (x != y)
                return x < y;
        }
        return a.size() < b.size();
    }
}