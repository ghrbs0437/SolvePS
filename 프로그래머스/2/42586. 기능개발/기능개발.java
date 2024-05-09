import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int currentIndex = 0;
        ArrayList<Integer> alist = new ArrayList<>();
        while(true){
            // 릴리즈일 구하기..
            if(currentIndex==progresses.length){
                System.out.println(alist);
                int[] arr = new int[alist.size()];
                for(int i=0;i<alist.size();i++){
                    arr[i] = alist.get(i);
                }
                return arr;
            }
            int restJob = 100 - progresses[currentIndex];
            int day = 0;
            day += restJob/speeds[currentIndex];
            if(restJob%speeds[currentIndex]!=0){
                day+=1;
            }    
            // 릴리즈단위 구하기..
            int count =0;
            for(;currentIndex<progresses.length;currentIndex++){
                if(progresses[currentIndex] + speeds[currentIndex] * day <100){
                    break;
                }else{
                    count++;
                }
            }
            alist.add(count);
        }
        
        
        
    }
}