
# [기능개발](https://school.programmers.co.kr/learn/courses/30/lessons/42627)

> 링크: <https://school.programmers.co.kr/learn/courses/30/lessons/42627>   
> 레벨: `3`   
> 태그: `힙`
> 날짜: 2024-05-17

 ## 풀이
- jobs의 길이가 500이하이므로, O(n^2)정도는 무난하고, O(n^3)까지도 가능할수 있음. 
- `최소값`을 찾는게 핵심이므로, Heap이 제일 좋은 자료구조.

❗ heap이 비어있어도 남은 작업이 있을 수 있음 (쉬는시간 존재가능성) 

> 선행 작업이 끝난 시점에서, 작업이 들어왔던 것 중 작업요구량이 제일 적은것을 다음 task로 할당.. 모든 jobs을 처리할떄까지 반복
> 1. jobs을 `요청시간`, `처리시간` 순으로 정렬
> 2. 첫 요청을 Heap에 넣음
> 3. 현재 작업을 처리
> 4. 작업동안 처리요청이 들어온 건을 Heap에 넣음
> 5. 처리가 완료된 이후, `heap이 비지 않은경우`, `heap이 비어있으나 아직 jobs전체를 처리하지 못한경우` 3~5 반복
> 6. 처리가 완료된 이후, Heap이 비어있고, 모든 jobs을 처리했으면 return;



- 시간복잡도: O(nlogn) 
- 최종점수: 100/100

### 코드
```java
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
```