class Solution {
    public int countCommas(int n) {
        long commas = 0;
        long threshold = 1000;
        while (threshold <= n) {
            commas += (long) n - threshold + 1;
            threshold *= 1000;
        }
        return (int) commas;
    }
}