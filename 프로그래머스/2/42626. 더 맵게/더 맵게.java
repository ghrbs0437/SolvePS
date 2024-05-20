import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i:scoville){
            pq.add(i);
        }
        
        while(true){    
            if(pq.peek()>=K){
                return answer;
            }
            if(pq.size()<2){
                break;
            }
            answer++;
            int min = pq.poll();
            int min2 = pq.poll();
            pq.add(min + min2*2);
        }
        return -1;
        
//         Arrays.sort(scoville);
//         int index = 0;
//         while(true){
//             if(scoville[index]>=K){
//                 break;
//             }
//             if(index==scoville.length-1){
//                 return -1;
//             }
//             scoville[index+1] = scoville[index] + scoville[index+1]*2;
//             index++;
//         }
//         for(int i:scoville){
//             System.out.println(i);
//         }
        
//         return index;
    }
}