import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int start = 1;
        int end = 1;
        
        if(n==1){
            return 1;
        }
        
        while(true){
            int subSum = sum(start,end);
            if(start>n){
                break;
            }
            if(subSum<n){
                end++;
            }else if(subSum>n){
                start++;
            }else if(subSum == n){
                answer++;
                start++;
            }
        }
        
        
        
        return answer;
    }
    
    public int sum(int a, int b){
        int sum = 0;
        for(int i=a;i<b;i++){
            sum+=i;
        }
        return sum;
    }
    
}