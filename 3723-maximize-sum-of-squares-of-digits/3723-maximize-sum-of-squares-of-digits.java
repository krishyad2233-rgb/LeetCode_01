class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) return "";
        if (sum == 0) return "";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int digit = Math.min(9, sum);
            result.append(digit);
            sum -= digit;
        }
        if (sum > 0) return "";
        return result.toString();
    }
}