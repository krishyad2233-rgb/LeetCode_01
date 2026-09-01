class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int n= arr.length;
        int R=k;
        int L=0;
        int sum=0;
        double avg=0;
        int count=0;
        for(int i=0;i<R;i++){
            sum+=arr[i];
        }
        if(sum/k>=threshold){
            count++;
        }
        while(R<n){
            sum-=arr[L++];
            sum+=arr[R++];
            if(sum/k>=threshold){
                count++;
            }
        }
        return count;
    }
}