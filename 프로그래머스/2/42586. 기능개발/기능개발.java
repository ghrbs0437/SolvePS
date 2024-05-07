import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        
        int size = progresses.length;
        ArrayList<Integer> answerList = new ArrayList<>();
        
        int j=0;
        while(true){
            for(int i=j;i<size;i++){
                progresses[i]+=speeds[i];
            }
            int releaseUnit=0;
            for(int i=j;i<size;i++){
                if(progresses[i]>=100){
                    releaseUnit++;
                    continue;
                }else{
                    break;
                }
            }
            if(releaseUnit==0){
                continue;
            }else{
                answerList.add(releaseUnit);
                j+=releaseUnit;    
            }

            if(j>=size){
                break;
            }
        }
        
        for(Integer in : answerList){
            System.out.println(in);
        }
        answer = new int[answerList.size()];
        for(int i=0;i<answerList.size();i++){
            answer[i]=answerList.get(i);
        }
        
        return answer;
    }
}