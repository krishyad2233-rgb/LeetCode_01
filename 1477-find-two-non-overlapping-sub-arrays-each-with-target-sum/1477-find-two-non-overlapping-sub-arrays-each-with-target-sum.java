import java.util.*;
class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = Integer.MAX_VALUE / 2;
        int[] best = new int[n];
        Arrays.fill(best, INF);
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, -1);
        long prefixSum = 0;
        int minLength = INF;
        int answer = INF;
        for (int i = 0; i < n; i++) {
            prefixSum += arr[i];
            long required = prefixSum - target;
            if (map.containsKey(required)) {
                int startIndex = map.get(required) + 1;
                int length = i - startIndex + 1;
                if (startIndex > 0 && best[startIndex - 1] != INF) {
                    answer = Math.min(
                        answer,
                        length + best[startIndex - 1]
                    );
                }
                minLength = Math.min(minLength, length);
            }
            best[i] = minLength;
            map.put(prefixSum, i);
        }
        return answer == INF ? -1 : answer;
    }
}