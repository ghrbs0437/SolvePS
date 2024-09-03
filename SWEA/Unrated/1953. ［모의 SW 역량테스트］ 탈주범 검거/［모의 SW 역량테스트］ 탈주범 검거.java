import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Solution {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
 
        for(int tc=1;tc<=testCase;tc++){
            String[] strs = br.readLine().split(" ");
            int ySize = Integer.parseInt(strs[0]);
            int xSize = Integer.parseInt(strs[1]);
            int yPos = Integer.parseInt(strs[2]);
            int xPos = Integer.parseInt(strs[3]);
            int time = Integer.parseInt(strs[4]);
 
            int[][] map = new int[ySize][xSize];
            for(int i=0;i<ySize;i++){
                strs = br.readLine().split(" ");
                for(int j=0;j<xSize;j++){
                    map[i][j] = Integer.parseInt(strs[j]);
                }
            }
            int answer = BFS(map,time,yPos,xPos);
            sb.append("#"+tc+" " +answer+"\n");
        }
        System.out.println(sb);
    }
 
    public static int BFS(int[][] map, int depth,int y,int x){
        Queue<Integer> xq = new ArrayDeque<>();
        Queue<Integer> yq = new ArrayDeque<>();
        xq.add(x);
        yq.add(y);
        int cnt = 0;
        boolean[][] visits = new boolean[map.length][map[0].length];
        while(true){
            if(xq.isEmpty()){
                break;
            }
            if(depth==0){
                break;
            }
            depth--;
            int size = xq.size();
            for(int i=0;i<size;i++){
                int cx = xq.poll();
                int cy = yq.poll();
                if(map[cy][cx]==0 || visits[cy][cx]){
                    continue;
                }
 
                visits[cy][cx]=true;
                cnt++;
                int[][] directions = TernalType.of(map[cy][cx]-1).direction;
                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = cy+dy;
                    int nx = cx+dx;
                    if(ny<0||nx<0||ny>= map.length||nx>=map[0].length){
                        continue;
                    }
                    if(map[ny][nx]==0){
                        continue;
                    }
                    if(visits[ny][nx]){
                        continue;
                    }
                    for(int[] ndirection : TernalType.of(map[ny][nx]-1).direction){
                        if(ndirection[0] == -dy && ndirection[1]==-dx){
                            xq.add(nx);
                            yq.add(ny);
                            break;
                        }
                    }
                }
            }
 
        }
        return cnt;
    }
 
    public static enum TernalType{
        A(new int[][]{{-1,0},{1,0},{0,-1},{0,1}}),
        B(new int[][]{{-1,0},{1,0}}),
        C(new int[][]{{0,-1},{0,1}}),
        D(new int[][]{{-1,0},{0,1}}),
        E(new int[][]{{1,0},{0,1}}),
        F(new int[][]{{1,0},{0,-1}}),
        G(new int[][]{{-1,0},{0,-1}});
        int[][] direction;
        TernalType(int[][] direction){
            this.direction = direction;
        }
 
        public static TernalType of(int ordinal){
            for(TernalType type : TernalType.values()){
                if(type.ordinal() == ordinal){
                    return type;
                }
            }
            return null;
        }
    }
}