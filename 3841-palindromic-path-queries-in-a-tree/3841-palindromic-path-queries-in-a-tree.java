import java.util.*;
class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        char[] chars = s.toCharArray();
        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] euler = new int[n];
        int timer = 0;
        int[] stack = new int[n];
        int[] iter = new int[n];
        int top = 0;
        stack[top++] = 0;
        parent[0] = -1;
        while (top > 0) {
            int u = stack[top - 1];
            if (iter[u] == 0) {
                tin[u] = timer;
                euler[timer++] = u;
            }
            if (iter[u] < adj[u].size()) {
                int v = adj[u].get(iter[u]++);
                if (v != parent[u]) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    stack[top++] = v;
                }
            } else {
                tout[u] = timer;
                top--;
            }
        }
        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;
        int[][] up = new int[LOG][n];
        for (int i = 0; i < n; i++) {
            up[0][i] = parent[i] == -1 ? 0 : parent[i];
        }
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }
        int[] fenwick = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int u = euler[i];
            int delta = 1 << (chars[u] - 'a');
            update(fenwick, tin[u] + 1, delta);
            update(fenwick, tout[u] + 1, delta);
        }
        List<Boolean> answer = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            int u = Integer.parseInt(parts[1]);
            if (parts[0].equals("update")) {
                char c = parts[2].charAt(0);
                int delta = (1 << (chars[u] - 'a')) ^ (1 << (c - 'a'));
                update(fenwick, tin[u] + 1, delta);
                update(fenwick, tout[u] + 1, delta);
                chars[u] = c;
            } else {
                int v = Integer.parseInt(parts[2]);
                int lca = getLCA(u, v, depth, up, LOG, parent);
                int mask = prefix(fenwick, tin[u] + 1)
                        ^ prefix(fenwick, tin[v] + 1)
                        ^ (1 << (chars[lca] - 'a'));
                answer.add((mask & (mask - 1)) == 0);
            }
        }
        return answer;
    }
    private void update(int[] bit, int i, int val) {
        while (i < bit.length) {
            bit[i] ^= val;
            i += i & -i;
        }
    }
    private int prefix(int[] bit, int i) {
        int res = 0;
        while (i > 0) {
            res ^= bit[i];
            i -= i & -i;
        }
        return res;
    }
    private int getLCA(int u, int v, int[] depth, int[][] up, int LOG, int[] parent) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[u] - depth[v];
        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }
        if (u == v) return u;
        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }
        return parent[u];
    }
}