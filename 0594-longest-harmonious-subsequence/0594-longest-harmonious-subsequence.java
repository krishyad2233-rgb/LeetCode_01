import java.util.HashMap;
import java.util.Map;
class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int maxLength = 0;
        for (int num : freq.keySet()) {
            if (freq.containsKey(num + 1)) {
                int length = freq.get(num) + freq.get(num + 1);
                maxLength = Math.max(maxLength, length);
            }
        }
        return maxLength;
    }
}