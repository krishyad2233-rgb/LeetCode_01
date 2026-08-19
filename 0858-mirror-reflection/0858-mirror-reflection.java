class Solution {
    public int mirrorReflection(int p, int q) {
        int lcm = lcm(p, q);
        int m = lcm / q;
        int n = lcm / p;
        if (m % 2 == 0) {
            return 2;
        }
        if (n % 2 == 0) {
            return 0;
        }
        return 1;
    }
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}