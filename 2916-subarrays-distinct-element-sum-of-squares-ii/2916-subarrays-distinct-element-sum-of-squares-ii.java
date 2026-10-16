class Solution {
static final int MOD = 1000000007;
public int sumCounts(int[] nums) {
    int n = nums.length;
    int size = 4 * n;
    long[] sum = new long[size];
    long[] sq = new long[size];
    long[] lazy = new long[size];
    java.util.HashMap<Integer, Integer> last = new java.util.HashMap<>();
    long ans = 0;
    for (int i = 0; i < n; i++) {
        int prev = last.getOrDefault(nums[i], -1);
        update(1, 0, n - 1, prev + 1, i, 1, sum, sq, lazy);
        ans = (ans + sq[1]) % MOD;
        last.put(nums[i], i);
    }
    return (int) ans;
}
void update(int node, int l, int r, int ql, int qr, long val,
            long[] sum, long[] sq, long[] lazy) {
    if (ql > r || qr < l) return;
    if (ql <= l && r <= qr) {
        apply(node, l, r, val, sum, sq, lazy);
        return;
    }
    push(node, l, r, sum, sq, lazy);
    int mid = l + (r - l) / 2;
    update(node * 2, l, mid, ql, qr, val, sum, sq, lazy);
    update(node * 2 + 1, mid + 1, r, ql, qr, val, sum, sq, lazy);
    sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
    sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
}
void apply(int node, int l, int r, long val,
           long[] sum, long[] sq, long[] lazy) {
    long len = r - l + 1L;
    long v = val % MOD;
    sq[node] = (sq[node] + 2L * v % MOD * sum[node] % MOD
                + len % MOD * v % MOD * v % MOD) % MOD;
    sum[node] = (sum[node] + len % MOD * v % MOD) % MOD;
    lazy[node] = (lazy[node] + v) % MOD;
}
void push(int node, int l, int r,
          long[] sum, long[] sq, long[] lazy) {
    if (lazy[node] == 0 || l == r) return;
    int mid = l + (r - l) / 2;
    apply(node * 2, l, mid, lazy[node], sum, sq, lazy);
    apply(node * 2 + 1, mid + 1, r, lazy[node], sum, sq, lazy);
    lazy[node] = 0;
}
}
