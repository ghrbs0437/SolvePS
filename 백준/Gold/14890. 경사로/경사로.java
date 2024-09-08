import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int mapSize = Integer.parseInt(strs[0]);
        int lineSize = Integer.parseInt(strs[1]);

        int[][] map = new int[mapSize][mapSize];

        for(int i=0;i<mapSize;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<mapSize;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        int cnt = 0;
        for(int i=0;i<mapSize;i++){
            if(putable(map,0,i,0,1,lineSize)
                    || putable(map,mapSize-1,i,0,-1,lineSize)){
                cnt++;
            }
            if(putable(map,i,0,1,0,lineSize)
                    || putable(map,i,mapSize-1,-1,0,lineSize)){
                cnt++;
            }
        }
        System.out.println(cnt);
    }
    public static boolean putable(int[][] map, int x, int y,int dy,int dx, int lineSize){
//         map[y][x] 에서 출발한다.. dy dx 를 계속해서 더해..\
        int cx = x;
        int cy = y;

        int stackCnt = 1;
        while(true){
            int ny = cy + dy;
            int nx = cx + dx;
            if(ny>=map.length||nx>= map.length || ny<0 || nx<0){
                break;
            }

            if(map[ny][nx]== map[cy][cx]){ // 경사로를 둘 필요가 없으면
                cy += dy;
                cx += dx;
                stackCnt ++;
            }else if(map[ny][nx] +1 == map[cy][cx]){ // 경사로 둬야하는경우
                for(int i=0;i<lineSize;i++){
                    if(ny+dy*i >= map.length || nx+dx*i>=map.length || ny+dy*i<0 || nx+dx*i<0){
                        return false;
                    }
                    if(map[cy][cx] == map[ny+dy*i][nx+dx*i] + 1){

                    }else{
                        return false;
                    }
                }
                stackCnt = 0;
                cy += dy * (lineSize);
                cx += dx * (lineSize);
            }else if(map[ny][nx] -1 == map[cy][cx]){
                if(stackCnt >= lineSize){
                    cy += dy;
                    cx += dx;
                    stackCnt = 1;
                }else{
                    return false;
                }
            }
            else{ // 폐급
                return false;
            }
        }
//        System.out.println(y + " " + x +" pos" );
        return true;
    }
}