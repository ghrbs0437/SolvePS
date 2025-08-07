import java.util.*;


class Solution {
    
    int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        
        
        int mapSize = 102;
        
        characterX = characterX*2;
        characterY = characterY*2;
        itemX = itemX*2;
        itemY = itemY*2;
        
        
        int[][] map = new int[mapSize][mapSize];
        
        for(int i=0;i<rectangle.length;i++){
            int x1 = rectangle[i][0]*2;
            int y1 = rectangle[i][1]*2;
            int x2 = rectangle[i][2]*2;
            int y2 = rectangle[i][3]*2;
            
            for(int j=x1+1;j<x2;j++){
                for(int k=y1+1;k<y2;k++){
                    map[k][j] = -1;
                }
            }
            
            for(int j=x1;j<=x2;j++){
                if(map[y1][j] != -1){
                    map[y1][j] = 1;
                }
            }
                        
            for(int j=x1;j<=x2;j++){
                if(map[y2][j] != -1){
                    map[y2][j] = 1;
                }
            }
            
            for(int j=y1; j<=y2;j++){
                if(map[j][x1] !=-1){
                    map[j][x1] = 1;
                }
            }
            
            for(int j=y1;j<=y2;j++){
                if(map[j][x2] !=-1){
                    map[j][x2] = 1;
                }
            }  
        }
        
        Queue<Integer> xq = new ArrayDeque<>();
        Queue<Integer> yq = new ArrayDeque<>();
        xq.add(characterX);
        yq.add(characterY);
        boolean[][] visits = new boolean[mapSize][mapSize];
        
        wh : while(!xq.isEmpty()){
            int size = xq.size();
            for(int i=0;i<size;i++){
                int cx = xq.poll();    
                int cy = yq.poll();
                
                if(cx == itemX && cy == itemY){
                    break wh;
                }
                
                if(visits[cy][cx]){
                    continue;
                }
                visits[cy][cx] = true;
                
                
                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    
                    int ny = cy + dy;
                    int nx = cx + dx;
                    
                    if(ny<0||nx<0||ny>=mapSize||nx>=mapSize){
                        continue;
                    }
                    
                    if(map[ny][nx]==1){
                        xq.add(nx);
                        yq.add(ny);
                    }
                }        
            }
            
            answer++;
        }
        
//         for(int i=0;i<mapSize;i++){
//             for(int j=0;j<mapSize;j++){
//                 System.out.print(map[i][j]+" ");
//             }
//             System.out.println("");
//         }
        
     
        return answer/2;
    }
}