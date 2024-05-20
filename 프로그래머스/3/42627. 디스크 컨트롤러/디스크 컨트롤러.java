import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<TaskJob> pq = new PriorityQueue<>();
        Arrays.sort(jobs,(a,b)->{
            if(a[0]!=b[0]){
                return a[0]-b[0];
            }else{
                return a[1]-b[1];
            }
            
        });
        pq.add(new TaskJob(jobs[0][0],jobs[0][1]));
        
        int index = 0;
        int currentTime = jobs[0][0];
        int answer = 0;
  
        while(true){
           
            // 1. 작업수행
            TaskJob tj = pq.peek();
            currentTime += tj.runningTime;
            answer += currentTime - tj.takeTime;
            pq.remove();
            // 2. 이번 작업동안 추가로 들어온 Task
            for(int i=index+1; i<jobs.length;i++){
                if(jobs[i][0] <= currentTime){
                    pq.add(new TaskJob(jobs[i][0],jobs[i][1]));
                    index++;
                }else{
                    break;
                }
            }
            
            // 3. 작업이 끝난 후 다음 Task 확인
            if(pq.size()==0){
                if(index == jobs.length-1){
                    return answer/jobs.length;
                }
                else{ // 현재 부여받은 Task가 없어도 끝이 아닐수 있음.
                    index++;
                    pq.add(new TaskJob(jobs[index][0],jobs[index][1]));
                    currentTime  = jobs[index][0];
                    
                }
            }      
        }
    }
    
    public static class TaskJob implements Comparable {
        int takeTime;
        int runningTime;
        public TaskJob(int tT, int rT){
            this.takeTime = tT;
            this.runningTime = rT;
        }
        
        @Override
        public int compareTo(Object o){
            return this.runningTime - ((TaskJob)o).runningTime;
        }
        @Override
        public String toString(){
            return  "["+takeTime + "," + runningTime + "]";
        }
    }
}