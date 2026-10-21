class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        char[] sorted = s.toCharArray();
        java.util.Arrays.sort(sorted);
        if (java.util.Arrays.equals(arr, sorted)) {
            return 0;
        }
        if (n == 2) {
            return -1;
        }
        int l = 0;
        int r = n - 1;
        while (l < n && arr[l] == sorted[l]) l++;
        while (r >= 0 && arr[r] == sorted[r]) r--;
        if (l != 0 || r != n - 1) {
            return 1;
        }
        char mn = sorted[0];
        char mx = sorted[n - 1];
        boolean hasMnPrefix = false;
        for (int i = 0; i <= n - 2; i++) {
            if (arr[i] == mn) {
                hasMnPrefix = true;
                break;
            }
        }
        boolean hasMxSuffix = false;
        for (int i = 1; i <= n - 1; i++) {
            if (arr[i] == mx) {
                hasMxSuffix = true;
                break;
            }
        }
        if (hasMnPrefix || hasMxSuffix) {
            return 2;
        }
        return 3;
    }
}