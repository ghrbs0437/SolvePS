import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int mapSize = Integer.parseInt(br.readLine());
        char[][] map = new char[mapSize][mapSize];

        int coreX = 0;
        int coreY = 0;
        for(int i=0;i<mapSize;i++) {
            String line = br.readLine();
            for(int j=0;j<mapSize;j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'B') {
                    coreX+=j;
                    coreY+=i;
                }
            }
        }
        coreY /=3;
        coreX /=3;

        int dy = 0;
        int dx = 0;

        if(coreX+1<mapSize&&map[coreY][coreX+1]=='B'){
            dx = 1;
        }else{
            dy = 1;
        }

        int[][][] moveCntMap = new int[mapSize][mapSize][2];
        for(int i=0;i<mapSize;i++){
            for(int j=0;j<mapSize;j++){
                moveCntMap[i][j][0] = Integer.MAX_VALUE;
                moveCntMap[i][j][1] = Integer.MAX_VALUE;
            }
        }

        getAns(coreY,coreX,dy,dx,map,moveCntMap,0);

        if(ANSWER== Integer.MAX_VALUE){
            System.out.println(0);
        }else{
            System.out.println(ANSWER);
        }
    }

    static int ANSWER = Integer.MAX_VALUE;

    static int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};

    public static void getAns(int coreY, int coreX, int dy, int dx, char[][] map, int[][][] moveCntMap,int currentMoveCnt){
        if(currentMoveCnt>=ANSWER){
            return;
        }
        if(dy==0){
            if(currentMoveCnt>=moveCntMap[coreY][coreX][0]){
                return;
            }
            moveCntMap[coreY][coreX][0] = currentMoveCnt;
        }else{
            if(currentMoveCnt>=moveCntMap[coreY][coreX][1]){
                return;
            }
            moveCntMap[coreY][coreX][1] = currentMoveCnt;
        }

        if(isEnd(coreY,coreX,dy,dx,map)){
            ANSWER = Math.min(ANSWER,currentMoveCnt);
            return;
        }

        for(int[] direction : directions){
            if(movable(coreY,coreX,dy,dx,direction,map)){
                getAns(coreY+direction[0], coreX + direction[1], dy, dx, map, moveCntMap, currentMoveCnt+1);
            }
        }
        if(rotatable(coreY,coreX,map)){
            getAns(coreY,coreX,dx,dy,map,moveCntMap, currentMoveCnt+1);
        }

    }

    public static boolean isEnd(int coreY, int coreX, int dy, int dx, char[][] map){
        // 실제 이동은 이미 된거니까 범위검증 필요 x
        if(map[coreY-dy][coreX-dx] == 'E'
                && map[coreY][coreX] =='E'
                && map[coreY+dy][coreX+dx] == 'E'){
            return true;
        }
        return false;
    }

    public static boolean movable(int coreY,int coreX, int dy, int dx, int[] direction, char[][] map){
        int[] xs = { coreX - dx, coreX, coreX+dx};
        int[] ys = { coreY - dy, coreY, coreY+dy};

        for(int i=0;i<3;i++){
            if(xs[i] + direction[1] <0
                    || xs[i] + direction[1] >= map[0].length
                    || ys[i] + direction[0] <0
                    || ys[i] + direction[0] >= map.length){
                return false;
            }
            if(map[ys[i]+direction[0]][xs[i]+direction[1]] =='1'){
                return false;
            }
        }
        return true;
    }

    public static boolean rotatable(int coreY, int coreX, char[][] map){
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                if(coreY + i < 0
                        || coreX + j < 0
                        || coreY + i >= map.length
                        || coreX + j >= map[0].length){
                    return false;
                }
                if(map[coreY+i][coreX+j] == '1'){
                    return false;
                }
            }
        }
        return true;
    }




}