import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];
        PriorityQueue<int[]> free = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]);
        });
        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }
        long time = 0;
        for (int i = 0; i < m; i++) {
            time = Math.max(time, i);
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] curr = busy.poll();
                free.offer(new int[]{servers[(int) curr[1]], (int) curr[1]});
            }
            if (free.isEmpty()) {
                time = busy.peek()[0];
                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] curr = busy.poll();
                    free.offer(new int[]{servers[(int) curr[1]], (int) curr[1]});
                }
            }
            int[] server = free.poll();
            ans[i] = server[1];
            busy.offer(new long[]{time + tasks[i], server[1]});
        }
        return ans;
    }
}