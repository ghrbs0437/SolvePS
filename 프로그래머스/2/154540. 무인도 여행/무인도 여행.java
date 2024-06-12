import java.util.*;

class Solution {
    int xsize = 0;
    int ysize = 0;
    public int[] solution(String[] maps) {
        xsize = maps[0].length();
        ysize = maps.length;
        
        int[][] graph = new int[ysize][xsize];
        
        for(int i=0;i<ysize;i++){
            for(int j=0;j<xsize;j++){
                char a = maps[i].charAt(j);
                if(a=='X'){
                    graph[i][j] = 0;
                }else{
                    graph[i][j] = a-'0';
                }
            }
        }
        
        int[][] visit = new int[ysize][xsize];
        ArrayList<Integer> alist = new ArrayList<>();
        for(int i=0;i<ysize;i++){
            for(int j=0;j<xsize;j++){
                if(graph[i][j]==0){
                    continue;
                }
                if(visit[i][j]==1){
                    continue;
                }                
                alist.add(propergation(graph,visit,i,j));
            }
        }
        
        int[] answer = {};
        if(alist.size()==0){
            answer = new int[1];
            answer[0] = -1;
            return answer;
        }
        
        Collections.sort(alist);
        answer = new int[alist.size()];
        for(int i=0;i<alist.size();i++){
            answer[i] = alist.get(i);
        }
        return answer;
    }
    
    public int propergation(int[][] graph,int[][] visit,int y,int x){
        
        if(x==-1){
            return 0;
        }
        if(y==-1){
            return 0;
        }
        if(x==xsize){
            return 0;
        }
        if(y==ysize){
            return 0;
        }
        
        if(graph[y][x]==0){
            return 0;
        }
        if(visit[y][x]==1){
            return 0;
        }
        
        visit[y][x] = 1;
        // System.out.println(y+" " +x + " " +graph[y][x]);
        return graph[y][x] + propergation(graph,visit,y+1,x) + propergation(graph,visit,y-1,x) 
            + propergation(graph,visit,y,x+1) + propergation(graph,visit,y,x-1);
    }
}