class Solution {
    public long countCommas(long n) {
        long ans = 0;
        if (n >= 1000L) {
            ans += n - 1000L + 1;
        }
        if (n >= 1000000L) {
            ans += n - 1000000L + 1;
        }
        if (n >= 1000000000L) {
            ans += n - 1000000000L + 1;
        }
        if (n >= 1000000000000L) {
            ans += n - 1000000000000L + 1;
        }
        if (n >= 1000000000000000L) {
            ans += n - 1000000000000000L + 1;
        }
        return ans;
    }
}