class Solution {
    String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String convert(int n) {
        if (n < 20) return ones[n];
        if (n < 100) return tens[n / 10] + (n % 10 != 0 ? " " + ones[n % 10] : "");
        if (n < 1000) return ones[n / 100] + " Hundred" + (n % 100 != 0 ? " " + convert(n % 100) : "");
        return "";
    }
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        String[] units = {"", "Thousand", "Million", "Billion"};
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (num > 0) {
            int part = num % 1000;
            if (part != 0) {
                String current = convert(part);
                if (!units[i].isEmpty()) {
                    current += " " + units[i];
                }
                if (result.length() == 0) {
                    result.insert(0, current);
                } else {
                    result.insert(0, current + " ");
                }
            }
            num /= 1000;
            i++;
        }
        return result.toString().trim();
    }
}