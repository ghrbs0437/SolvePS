
# [가장 먼 노드](https://school.programmers.co.kr/learn/courses/30/lessons/49189)

> 링크: <https://school.programmers.co.kr/learn/courses/30/lessons/49189>   
> 레벨: `3`   
> 태그: `그래프`
> 날짜: 2024-06-07

 ## 풀이
- 배열의 크기가 20000으로, n^2의 시간복잡도로는 해결이 어려움

> 전형적인 BFS 문제
> `모든 노드를 방문할때까지 연결된 모든 정점을 탐색`

- 시간복잡도: O(nlogn) 
- 최종점수: 100/100

### 코드
```java
class Solution {
    public int solution(int n, int[][] edge) {

        HashMap<Intege2r,ArrayList<Integer>> hmap = new HashMap<>();
        
        for(int i=0;i<edge.length;i++){
            if(hmap.get(edge[i][0]-1)==null){
                hmap.put(edge[i][0]-1, new ArrayList<Integer>());
            }
            if(hmap.get(edge[i][1]-1)==null){
                hmap.put(edge[i][1]-1, new ArrayList<Integer>());
            }
            hmap.get(edge[i][0]-1).add(edge[i][1]-1);
            hmap.get(edge[i][1]-1).add(edge[i][0]-1);
        }
        
        int[] distance = new int[n];
        ArrayList<Integer> list1 = hmap.get(0);
        for(Integer i : list1){
            distance[i] = 1;
        }
        
        HashSet<Integer> visit = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visit.add(0);
        while(true){
            if(queue.size()==0){
                break;
            } 
            int size = queue.size();
            // 큐에 든거 다 사용해봄..
            for(int i=0;i<size;i++){
                int next = queue.poll();
                ArrayList<Integer> nlist = hmap.get(next);
                // 그중에 연결되어 있는점을 찾는다.
                for(Integer temp : nlist){ 
                    if(visit.contains(temp)){
                        continue;
                    }
                    distance[temp] = distance[next] + 1;
                    queue.add(temp);
                    visit.add(temp);
                }
            }
            
        }        
        
        int count = 1;
        int max = -1;
        for(int i:distance){
            if(max == i){
                count++;
            }
            if(max<i){
                max = i;
                count=1;
            }
        }
        
        
        return count;
    }
}
```