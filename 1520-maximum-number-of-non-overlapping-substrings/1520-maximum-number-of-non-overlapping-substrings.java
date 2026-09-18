import java.util.*;
class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, n);
        Arrays.fill(last, -1);
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = i;
        }
        List<String> ans = new ArrayList<>();
        int previousEnd = -1;
        for (int i = 0; i < n; i++) {
            if (i != first[s.charAt(i) - 'a']) {
                continue;
            }
            int end = getEnd(s, i, first, last);
            if (end == -1) {
                continue;
            }
            if (i > previousEnd) {
                ans.add(s.substring(i, end + 1));
            } else {
                ans.set(ans.size() - 1,
                        s.substring(i, end + 1));
            }
            previousEnd = end;
        }
        return ans;
    }
    private int getEnd(String s, int start,
                       int[] first, int[] last) {
        int end = last[s.charAt(start) - 'a'];
        for (int i = start; i <= end; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] < start) {
                return -1;
            }
            end = Math.max(end, last[c]);
        }
        return end;
    }
}