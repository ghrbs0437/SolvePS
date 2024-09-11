
# [게임 맵 최단거리](https://school.programmers.co.kr/learn/courses/30/lessons/1844)

> 링크: <https://school.programmers.co.kr/learn/courses/30/lessons/1844>   
> 레벨: `2`   
> 태그: `DFS/BFS`, ``    
> 날짜: 2024-05-28

## 풀이


> 1. 그래프에서 각 값을 ~까지의 이동횟수로 표현
> 2. 현재 위치에서 상하좌우중, 이동가능한 위치 식별
> 3. 이동가능하다면, 그 위치를 방문한 적이 있다면 `현재위치의 값+1`과 `현재 가지고 있는 그 위치의 값`중 최소값으로 설정. 이때 최소값이 변경된다면 그 위치에서 재탐색
> 4. 더이상 이동할 수 없거나, 최소값의 변경되지 않을때까지 반복

- 시간복잡도: O(n) (n은 배열의 전체크기)
- 최종점수: 100/100

### 코드
```java
class Solution {
    public int solution(int[][] maps) {
        int xSize = maps[0].length;
        int ySize = maps.length;
        
        // if(xSize == 1 && ySize==1){
        //     return 0;
        // }
        
        
        int[][] valueMaps = new int[ySize][xSize];
        int[][] visitMaps = new int[ySize][xSize];
        for(int i=0;i<ySize;i++){
            for(int j=0;j<xSize;j++){
                valueMaps[i][j] = maps[i][j];
                visitMaps[i][j] = 0;
            }
        }
        
        
        
        Queue<Position> queue = new LinkedList<>();
        Position start = new Position(0,0);
        queue.add(start);
        visitMaps[0][0] = 1;
        while(true){
            Position current = queue.poll();
            int x = current.x;
            int y = current.y;
            
            if(x-1>=0 && maps[y][x-1]!=0){ // 이동가능한 왼쪽이면
                if((valueMaps[y][x-1] > valueMaps[y][x]+1) || visitMaps[y][x-1] == 0){ // 이동점의 값이 현재위치값+1 보다 크면 최적화.. 또는 처음 방문하는곳이면 
                    valueMaps[y][x-1] = valueMaps[y][x]+1;
                    visitMaps[y][x-1] = 1;
                    queue.add(new Position(x-1,y));
                }
            }
            
            if(x+1<xSize && maps[y][x+1]!=0){ // 이동가능한 오른쪽이면
                if(valueMaps[y][x+1] > valueMaps[y][x]+1 || visitMaps[y][x+1] == 0){ // 이동점의 값이 현재위치값+1 보다 크면 최적화.. 또는 처음 방문하는곳이면 
                    valueMaps[y][x+1] = valueMaps[y][x]+1;
                    visitMaps[y][x+1] = 1;
                    queue.add(new Position(x+1,y));
                }
            }
            
            
            if(y-1>=0 && maps[y-1][x]!=0){ // 이동가능한 위쪽이면
                if(valueMaps[y-1][x] > valueMaps[y][x]+1 || visitMaps[y-1][x] == 0){ // 이동점의 값이 현재위치값+1 보다 크면 최적화.. 또는 처음 방문하는곳이면 
                    valueMaps[y-1][x] = valueMaps[y][x]+1;
                    visitMaps[y-1][x] = 1;
                    queue.add(new Position(x,y-1));
                }
            }
            
            if(y+1<ySize && maps[y+1][x]!=0){ // 이동가능한 아래쪽이면
                if(valueMaps[y+1][x] > valueMaps[y][x]+1 || visitMaps[y+1][x] == 0){ // 이동점의 값이 현재위치값+1 보다 크면 최적화.. 또는 처음 방문하는곳이면 
                    valueMaps[y+1][x] = valueMaps[y][x]+1;
                    visitMaps[y+1][x] = 1;
                    queue.add(new Position(x,y+1));
                }
            }
            

            if(queue.isEmpty()){
                break;
            }
        }
      
        int answer = valueMaps[ySize-1][xSize-1];
        if(answer==1){
            return -1;
        }
        return answer;
       
    }
    
    public class Position{
        int x;
        int y;
        Position(int X,int Y){
            x = X;
            y = Y;
        }
    }
}
```