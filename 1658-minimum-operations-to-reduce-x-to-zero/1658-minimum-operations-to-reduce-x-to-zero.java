class Solution {
    public int minOperations(int[] nums, int x) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        int target = total - x;
        if (target < 0) {
            return -1;
        }
        if (target == 0) {
            return nums.length;
        }
        int left = 0;
        int sum = 0;
        int maxLength = -1;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum > target && left <= right) {
                sum -= nums[left];
                left++;
            }
            if (sum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }
        if (maxLength == -1) {
            return -1;
        }
        return nums.length - maxLength;
    }
}