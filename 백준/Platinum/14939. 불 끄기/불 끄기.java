import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Main {

    static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1},{0,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int mapSize = 10;
        char[][] maps = new char[mapSize][mapSize];

        for(int i=0;i<mapSize;i++){
            String str = br.readLine();
            for(int j=0; j<mapSize;j++){
                maps[i][j] = str.charAt(j);
            }
        }
        int answer = getAns(maps);

        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }

        // 두줄을 보고.. 윗줄이 다 꺼져있으면 다음스탭 가능 아니면 손절

    }

    public static int getAns(char[][] maps){
        Queue<char[][]> queue = new ArrayDeque<>();
        Queue<Integer> counter = new ArrayDeque<>();
        counter.add(0);
        queue.add(genMap(maps));

        for(int i=0;i<10;i++) {
            for (int j = 0; j < 10; j++) {
                int size = queue.size();
                for(int k=0;k<size;k++){
                    int count = counter.poll();
                    char[][] poll = queue.poll();
                    char[][] gmap1 = genMap(poll);
                    click(gmap1,i,j);
                    // 누른것과 안누른거.
                    char[][] gmap2 = genMap(poll);
                    if(i>=1){
                        if(gmap1[i-1][j]=='#'){
                            queue.add(gmap1);
                            counter.add(count+1);
                        }
                        if(gmap2[i-1][j]=='#'){
                            queue.add(gmap2);
                            counter.add(count);
                        }
                    }else{
                        queue.add(gmap1);
                        counter.add(count+1);
                        queue.add(gmap2);
                        counter.add(count);
                    }
                }
            }
        }

        int answer =Integer.MAX_VALUE;
        cc: for(char[][] map :queue){
            int c = counter.poll();
            for(int i=0;i<10;i++){

                if(map[9][i] == 'O'){
                    continue cc;
                }
            }
            answer = Math.min(c,answer);
        }
        return answer;
    }
    public static void printMap(char[][] map){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static char[][] genMap(char[][] maps){
        int ySize = maps.length;
        int xSize = maps[0].length;
        char[][] nMap = new char[ySize][xSize];
        for(int i=0;i<ySize;i++){
            for(int j=0;j<xSize;j++){
                nMap[i][j] = maps[i][j];
            }
        }
        return nMap;
    }

    public static void click(char[][] maps, int y, int x){
        for(int[] direction : directions){
            int dy = direction[0];
            int dx = direction[1];
            int ny = y + dy;
            int nx = x + dx;
            if(ny<0||nx<0||ny>= maps.length||nx>=maps[0].length){
                continue;
            }
            maps[ny][nx] = toggle(maps[ny][nx]);
        }
    }

    public static char toggle(char c){
        if(c=='O'){
            return '#';
        }else{
            return 'O';
        }
    }
}