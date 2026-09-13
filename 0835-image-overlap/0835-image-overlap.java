class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int maxOverlap = 0;
        for (int rowShift = -(n - 1); rowShift <= n - 1; rowShift++) {
            for (int colShift = -(n - 1); colShift <= n - 1; colShift++) {
                int overlap = 0;
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {
                        int newRow = r + rowShift;
                        int newCol = c + colShift;
                        if (newRow >= 0 && newRow < n &&
                            newCol >= 0 && newCol < n &&
                            img1[r][c] == 1 &&
                            img2[newRow][newCol] == 1) {
                            overlap++;
                        }
                    }
                }
                maxOverlap = Math.max(maxOverlap, overlap);
            }
        }
        return maxOverlap;
    }
}