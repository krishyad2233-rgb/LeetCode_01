class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        long[] last = new long[26];
        long total = 0;
        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            long newSubseq = (total + 1) % MOD;
            total = (total + newSubseq - last[idx] + MOD) % MOD;
            last[idx] = newSubseq;
        }
        return (int) total;
    }
}