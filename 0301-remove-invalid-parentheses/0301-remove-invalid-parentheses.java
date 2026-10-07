class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        int left = 0, right = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0)
                    left--;
                else
                    right++;
            }
        }
        dfs(s, 0, left, right, 0, new StringBuilder(), ans, set);
        return ans;
    }
    private void dfs(String s, int index, int left, int right, int balance,
                     StringBuilder path, List<String> ans, Set<String> set) {

        if (index == s.length()) {
            if (left == 0 && right == 0 && balance == 0) {
                String str = path.toString();
                if (set.add(str))
                    ans.add(str);
            }
            return;
        }
        char c = s.charAt(index);

        if (c == '(' && left > 0) {
            dfs(s, index + 1, left - 1, right, balance, path, ans, set);
        }

        if (c == ')' && right > 0) {
            dfs(s, index + 1, left, right - 1, balance, path, ans, set);
        }
        path.append(c);
        if (c == '(') {
            dfs(s, index + 1, left, right, balance + 1, path, ans, set);
        } else if (c == ')') {
            if (balance > 0) {
                dfs(s, index + 1, left, right, balance - 1, path, ans, set);
            }
        } else {
            dfs(s, index + 1, left, right, balance, path, ans, set);
        }
        path.deleteCharAt(path.length() - 1);
    }
}