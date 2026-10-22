import java.util.*;
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) sorted[i] = i;
        if (Arrays.equals(nums, sorted)) return 0;
        int pos0 = -1;
        for (int i = 0; i < n; i++) if (nums[i] == 0) pos0 = i;
        if (pos0 == -1) return -1;
        boolean inc = true;
        for (int i = 0; i < n; i++) {
            if (nums[(pos0 + i) % n] != i) { inc = false; break; }
        }
        boolean dec = true;
        for (int i = 0; i < n; i++) {
            int idx = (pos0 - i) % n;
            if (idx < 0) idx += n;
            if (nums[idx] != i) { dec = false; break; }
        }
        int targetShift;
        int targetRev;
        if (inc) {
            targetShift = pos0;
            targetRev = 0;
        } else if (dec) {
            targetShift = pos0;
            targetRev = 1;
        } else {
            return -1;
        }
        
        int[][] dist = new int[2][n];
        for (int r = 0; r < 2; r++) Arrays.fill(dist[r], -1);
        Deque<int[]> q = new ArrayDeque<>();
        dist[0][0] = 0;
        q.add(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int rev = cur[0];
            int shift = cur[1];
            int d = dist[rev][shift];
            if (rev == targetRev && shift == targetShift) return d;
            if (rev == 0) {
                int ns1 = (shift + 1) % n;
                if (dist[0][ns1] == -1) {
                    dist[0][ns1] = d + 1;
                    q.add(new int[]{0, ns1});
                }
                int ns2 = (shift - 1) % n;
                if (ns2 < 0) ns2 += n;
                if (dist[1][ns2] == -1) {
                    dist[1][ns2] = d + 1;
                    q.add(new int[]{1, ns2});
                }
            } else {
                int ns1 = (shift - 1) % n;
                if (ns1 < 0) ns1 += n;
                if (dist[1][ns1] == -1) {
                    dist[1][ns1] = d + 1;
                    q.add(new int[]{1, ns1});
                }
                int ns2 = (shift + 1) % n;
                if (dist[0][ns2] == -1) {
                    dist[0][ns2] = d + 1;
                    q.add(new int[]{0, ns2});
                }
            }
        }
        return -1;
    }
}