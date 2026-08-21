class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] boxes = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {

                char num = board[r][c];

                if (num == '.') {
                    continue;
                }

                int boxIndex = (r / 3) * 3 + (c / 3);

                if (rows[r].contains(num) ||
                    cols[c].contains(num) ||
                    boxes[boxIndex].contains(num)) {
                    return false;
                }

                rows[r].add(num);
                cols[c].add(num);
                boxes[boxIndex].add(num);
            }
        }
        return true;
    }
}