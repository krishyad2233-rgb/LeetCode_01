import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;
        int maxFactor = 0;

        int[] freq = new int[n + 1];

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
            maxFactor = Math.max(maxFactor, item[0]);
        }

        int[] multiples = new int[maxFactor + 1];

        for (int i = 1; i <= maxFactor; i++) {
            int count = 0;

            for (int j = i; j <= maxFactor; j += i) {
                count += freq[j];
            }

            multiples[i] = count - 1;
        }

        long[][] deals = new long[n][2];
        int size = 0;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            int free = multiples[factor];

            if (free > 0 && (long) price < 2L * minPrice) {
                deals[size][0] = price;
                deals[size][1] = free;
                size++;
            }
        }

        Arrays.sort(deals, 0, size,
            (a, b) -> Long.compare(a[0], b[0]));

        long ans = 0;
        long remaining = budget;

        for (int i = 0; i < size; i++) {
            long price = deals[i][0];
            long count = deals[i][1];

            long buy = Math.min(count, remaining / price);

            ans += buy * 2;
            remaining -= buy * price;
        }

        ans += remaining / minPrice;

        return (int) ans;
    }
}