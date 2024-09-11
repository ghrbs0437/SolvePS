
# [순위](https://school.programmers.co.kr/learn/courses/30/lessons/49191)

> 링크: <https://school.programmers.co.kr/learn/courses/30/lessons/49191>   
> 레벨: `3`   
> 태그: `그래프`
> 날짜: 2024-06-09

 ## 풀이
배열 크기가 크지 않으므로, 인접행렬로 그래프 표현   
A가 이긴 B를 이긴 C같은 경우, A < B < C가 성립   
`이기는 경우` 와 `지는 경우` 각각 BFS 적용   
인접행렬의 각 row가 0이 n-1인경우 정확하게 순위를 매길 수 있음 (각 row에 해당하는 index의 순위를 알 수 있음)


- 시간복잡도: O(nlogn) 
- 최종점수: 100/100

### 코드
```java
class Solution {
    public int solution(int n, int[][] results) {
        
        int[][] players = new int[n][n];
        
        for(int[] result :results){
            players[result[0]-1][result[1]-1] = 1;
            players[result[1]-1][result[0]-1] = -1;
        }
        
        
        for(int i=0;i<n;i++){   
            winPropagation(i,players,n);
            losePropagation(i,players,n);
        }
        
        // for(int[] a : players){
        //     for(int i: a){
        //         System.out.print(i+" ");
        //     }
        //     System.out.println("");
        // }
        
        int answer = 0;
        for(int i=0;i<n;i++){
            int count = 0;
            for(int j=0;j<n;j++){
                if(players[i][j] !=0){
                    count++;
                }
            }
            if(count>=n-1){
                answer++;
            }
        }
        
        
        return answer;
    }
    
    public void winPropagation(int start, int[][] players,int number){
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visit = new HashSet<>();
        queue.add(start);
        while(queue.size()!=0){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int current = queue.poll();
                for(int j=0;j<number;j++){
                    if(visit.contains(j)){
                        continue;
                    }
                    
                    if(players[current][j]==1){
                        players[start][j] = 1;
                        queue.add(j); 
                    }
                }
                visit.add(current);
            }
        }
    }
    
    public void losePropagation(int start, int[][] players,int number){
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visit = new HashSet<>();
        queue.add(start);
        while(queue.size()!=0){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int current = queue.poll();
                for(int j=0;j<number;j++){
                    if(visit.contains(j)){
                        continue;
                    }
                    if(players[current][j]==-1){
                        players[start][j] = -1;
                        queue.add(j); 
                    }
                }
                visit.add(current);
            }
        }
    }
    

}
```