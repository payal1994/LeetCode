/*
Given an array of integers and a number k, find the maximum sum of a subarray of size k. 

Examples: 

Input  : arr[] = {100, 200, 300, 400}
         k = 2
Output : 700

Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}
         k = 4 
Output : 39
We get maximum sum by adding subarray {4, 2, 10, 23}
of size 4.

Input  : arr[] = {2, 3}
         k = 3
Output : Invalid
There is no subarray of size 3 as size of whole
array is 2. 
*/

public static int SubArray(int[] a, int k) {
        int i=0;
        int j=0;
        int max=0;
  //    int min=46212;
        int sum=0;
        int output[]= new  int[a.length];
        while(j<a.length)
        {
            while((j-i+1)<=k){
                sum=sum+a[j];
                j++;
            }
            if((j-i)==k){
                max=Math.max(max,sum);
              //min=Math.min(min,sum)
                sum=sum-a[i];
            }
            i++;

        }
        return sum;
    }
