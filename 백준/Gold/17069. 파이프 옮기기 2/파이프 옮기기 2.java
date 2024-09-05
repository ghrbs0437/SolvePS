import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int mapSize = Integer.parseInt(br.readLine());
        int[][] map = new int[mapSize][mapSize];

        long[][] rowSelection = new long[mapSize][mapSize];
        long[][] columnSelection = new long[mapSize][mapSize];
        long[][] crossSelection = new long[mapSize][mapSize];


        //파이프를 밀 수 있는 방향은 총 3가지가 있으며, →, ↘, ↓ 방향 회전은 45도만 회전시킬 수 있음
        for(int i=0;i<mapSize;i++){
            String[] strs = br.readLine().split(" ");
            for(int j=0;j<mapSize;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        rowSelection[0][0] =1;

        for(int i=0;i<mapSize;i++){
            for(int j=0;j<mapSize;j++){
                if(map[i][j] == 1){
                    continue;
                }
                propergation(i,j,map,rowSelection,columnSelection,crossSelection);
            }
        }

        System.out.println(
                rowSelection[mapSize-1][mapSize-2] +
                columnSelection[mapSize-2][mapSize-1]+
                crossSelection[mapSize-2][mapSize-2]);
    }

    public static void propergation(int y,int x,int[][] map, long[][] rowSelection,long[][] columnSelection ,long[][] crossSelection){
        propergationRow(y,x,map,rowSelection,columnSelection,crossSelection);
        propergationColumn(y,x,map,rowSelection,columnSelection,crossSelection);
        propergationCross(y,x,map,rowSelection,columnSelection,crossSelection);
    }

    public static void propergationRow(int y,int x, int[][] map,long[][] rowSelection,long[][] columnSelection ,long[][] crossSelection){
        if(x+2 < rowSelection[0].length && map[y][x+2]==0){
            rowSelection[y][x+1] += rowSelection[y][x];
        }
        if(x+2 < rowSelection[0].length
                && y+1 < rowSelection.length
                && map[y+1][x+1] == 0
                && map[y][x+2] == 0
                && map[y+1][x+2] == 0){
            crossSelection[y][x+1] += rowSelection[y][x];
        }
    }

    public static void propergationColumn(int y,int x, int[][] map, long[][] rowSelection,long[][] columnSelection ,long[][] crossSelection){
        if(y+2 < rowSelection.length && map[y+2][x]==0){
            columnSelection[y+1][x] += columnSelection[y][x];
        }
        if(x+1 < rowSelection[0].length
                && y+2 < rowSelection.length
                && map[y+1][x+1] == 0
                && map[y+2][x] == 0
                && map[y+2][x+1] == 0){
            crossSelection[y+1][x] += columnSelection[y][x];
        }
    }

    public static void propergationCross(int y,int x, int[][] map , long[][] rowSelection,long[][] columnSelection ,long[][] crossSelection){
        // 1. 가로이동
        if(y+1< rowSelection.length && x+2< rowSelection[0].length
                && map[y+1][x+2] == 0){
            rowSelection[y+1][x+1] += crossSelection[y][x];
        }


        // 2. 세로이동
        if(y+2<rowSelection.length && x+1< rowSelection[0].length
                && map[y+2][x+1] == 0){
            columnSelection[y+1][x+1] += crossSelection[y][x];
        }

        // 3. 대각이동
        if(y+2< rowSelection.length && x+2<rowSelection[0].length
                && map[y+1][x+2] == 0
                && map[y+2][x+1] == 0
                && map[y+2][x+2] == 0){
            crossSelection[y+1][x+1] += crossSelection[y][x];
        }

    }

}