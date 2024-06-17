import java.util.*;


class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        int size = arr.length;
        int cur = arr[0];
        for(int i=1;i<size;i++){
            cur = GCD(cur,arr[i]);
        }
        return cur;
    }
    
    public int GCD(int v1, int v2){
        if(v1 == 1){
            return v2;
        }
        if(v2 == 1){
            return v2;
        }
        
        for(int i=2;;i++){
            if(i % v1 == 0 && i % v2 == 0){
                return i;
            }
        }
        
    }
}