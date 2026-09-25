import java.util.*;
class Solution {
    private int index = 0;
    public List<String> braceExpansionII(String expression) {
        index = 0;
        Set<String> result = parse(expression);
        return new ArrayList<>(result);
    }
    private Set<String> parse(String s) {
        Set<String> result = new TreeSet<>();
        Set<String> current = new TreeSet<>();
        current.add("");
        while (index < s.length() && s.charAt(index) != '}') {
            char ch = s.charAt(index);
            if (ch == ',') {
                result.addAll(current);
                current = new TreeSet<>();
                current.add("");
                index++;
            }
            else if (ch == '{') {
                index++; 
                Set<String> inside = parse(s);
                index++; 
                current = combine(current, inside);
            }
            else {
                Set<String> letter = new TreeSet<>();
                letter.add(String.valueOf(ch));
                current = combine(current, letter);
                index++;
            }
        }
        result.addAll(current);
        return result;
    }
    private Set<String> combine(Set<String> a, Set<String> b) {
        Set<String> result = new TreeSet<>();
        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }
        return result;
    }
}