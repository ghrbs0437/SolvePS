import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        String[] strs = br.readLine().split(" ");

        int ySize = Integer.parseInt(strs[0]);
        int xSize = Integer.parseInt(strs[1]);

        char[][] map = new char[ySize][xSize];
        Queue<Position> dochi = new LinkedList<>();
        Queue<Position> waters = new LinkedList<>();
        for(int i=0;i<ySize;i++){
            String str = br.readLine();
            for(int j=0;j<xSize;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] =='S'){
                    Position start = new Position();
                    start.y = i;
                    start.x = j;
                    dochi.add(start);
                }else if(map[i][j] =='*'){
                    Position water = new Position();
                    water.y = i;
                    water.x = j;
                    waters.add(water);
                }
            }
        }

        int time = 0;
        while(true){
            time ++;

            if(dochi.isEmpty()){
                break;
            }

            int wSize = waters.size();
            for(int i=0;i<wSize;i++){
                Position water = waters.poll();
                int cy = water.y;
                int cx = water.x;
                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = cy + dy;
                    int nx = cx + dx;
                    if(ny<0||nx<0||ny>=ySize||nx>=xSize){
                        continue;
                    }
                    if(map[ny][nx]=='.'){
                        Position next = new Position();
                        next.y = ny;
                        next.x = nx;
                        map[ny][nx] = '*';
                        waters.add(next);
                    }
                }
            }

            int dSize = dochi.size();
            for(int i=0;i<dSize;i++){
                Position doc = dochi.poll();
                int cy = doc.y;
                int cx = doc.x;
                for(int [] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = cy + dy;
                    int nx = cx + dx;
                    if(ny<0||nx<0||ny>=ySize||nx>=xSize){
                        continue;
                    }
                    if(map[ny][nx]=='D'){
                        System.out.println(time);
                        return;
                    }
                    if(map[ny][nx]=='.'){
                        Position next = new Position();
                        next.y = ny;
                        next.x = nx;
                        map[ny][nx] = 'S';
                        dochi.add(next);
                    }
                }
            }
        }

        System.out.println("KAKTUS");

    }

    public static class Position{
        int y;
        int x;
    }
}