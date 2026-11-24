import java.math.BigInteger;
class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 1;
        for (int i = 0; i < n; i++) {
            BigInteger prod = BigInteger.ONE;
            long g = 0;
            long l = 1;
            for (int j = i; j < n; j++) {
                int x = nums[j];
                prod = prod.multiply(BigInteger.valueOf(x));
                g = gcd(g, x);
                l = (l / gcd(l, x)) * x;
                BigInteger value = BigInteger.valueOf(g)
                        .multiply(BigInteger.valueOf(l));
                if (prod.equals(value)) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}