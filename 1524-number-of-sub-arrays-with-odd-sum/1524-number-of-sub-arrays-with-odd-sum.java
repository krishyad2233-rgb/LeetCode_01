class Solution {
    public int numOfSubarrays(int[] arr) {
        long odd = 0, even = 1;
        long sum = 0;
        long mod = 1000000007;
        for (int x : arr) {
            sum += x;
            if (sum % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        return (int)((odd * even) % mod);
    }
}