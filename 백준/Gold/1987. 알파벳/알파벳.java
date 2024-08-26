import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;

public class Main {


    public static boolean FINISHED = false;

    public static int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" " );
        int ySize = Integer.parseInt(strs[0]);
        int xSize = Integer.parseInt(strs[1]);

        char[][] map = new char[ySize][xSize];
        for(int i=0;i<ySize;i++){
            String str = br.readLine();
            for(int j=0;j<xSize;j++){
                map[i][j] = str.charAt(j);
            }
        }
        getAns(new HashSet<Character>(),map,0,0,0);
        System.out.println(max+1);
    }

    static int max = 0;
    public static void getAns(HashSet<Character> container,char[][] map, int y,int x, int depth){
        if(depth>max){
            max = depth;
        }
        container.add(map[y][x]);
        for(int[] direction : directions){
            int dy = direction[0];
            int dx = direction[1];
            int ny = y + dy;
            int nx = x + dx;
            if(ny<0||nx<0||ny>=map.length||nx>=map[0].length){
                continue;
            }
            if(container.contains(map[ny][nx])){
                continue;
            }
            getAns(container,map,ny,nx,depth+1);
            container.remove(map[ny][nx]);
        }
    }
}