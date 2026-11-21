class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }
        int[] newParent = parent.clone();
        int[] last = new int[26];
        Arrays.fill(last, -1);
        dfs(0, children, newParent, last, s);
        List<Integer>[] updated = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            updated[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            updated[newParent[i]].add(i);
        }
        int[] answer = new int[n];
        calculate(0, updated, answer);
        return answer;
    }
    private void dfs(int node, List<Integer>[] children, int[] parent,
                     int[] last, String s) {
        int c = s.charAt(node) - 'a';
        int previous = last[c];
        if (node != 0 && previous != -1) {
            parent[node] = previous;
        }
        last[c] = node;
        for (int child : children[node]) {
            dfs(child, children, parent, last, s);
        }
        last[c] = previous;
    }
    private int calculate(int node, List<Integer>[] children, int[] answer) {
        int size = 1;
        for (int child : children[node]) {
            size += calculate(child, children, answer);
        }
        answer[node] = size;
        return size;
    }
}