import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        if (k == 0) return -1;
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }
        long[][] dist = new long[n][k + 1];
        for (long[] d : dist) {
            Arrays.fill(d, Long.MAX_VALUE);
        }
        PriorityQueue<long[]> pq = new PriorityQueue<>(
            Comparator.comparingLong(a -> a[0])
        );
        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long cost = cur[0];
            int node = (int) cur[1];
            int count = (int) cur[2];
            if (cost != dist[node][count]) continue;
            if (node == n - 1) return (int) cost;
            for (int[] e : adj[node]) {
                int next = e[0];
                int weight = e[1];
                int newCount = labels.charAt(node) == labels.charAt(next)
                    ? count + 1 : 1;
                if (newCount > k) continue;
                long newCost = cost + weight;
                if (newCost < dist[next][newCount]) {
                    dist[next][newCount] = newCost;
                    pq.offer(new long[]{newCost, next, newCount});
                }
            }
        }
        return -1;
    }
}