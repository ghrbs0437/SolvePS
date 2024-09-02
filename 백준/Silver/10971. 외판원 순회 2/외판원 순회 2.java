import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int mapSize = Integer.parseInt(br.readLine());
        int[][] map = new int[mapSize][mapSize];
        for(int i=0;i<mapSize;i++) {
            String[] strs = br.readLine().split(" ");
            for (int j = 0; j <mapSize; j++) {
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }
        boolean[] visits = new boolean[mapSize];
        for(int i=0;i<mapSize;i++){
            visits[i] = true;
            DFS(map,visits,i,i,0,0);
            visits[i] = false;
        }
        System.out.println(MIN);
    }
    static int MIN = Integer.MAX_VALUE;

    public static void DFS(int[][] map,boolean[] visits,int start, int currentIndex,int depth,int sum){
        if(depth == map.length-1){
            if(map[currentIndex][start]==0){
                return;
            }
            
            if(sum+map[currentIndex][start]<MIN){
                MIN = sum+map[currentIndex][start];
            }
            return;
        }
        for(int i=0;i< map.length;i++){
            if(visits[i]){
                continue;
            }
            if(map[currentIndex][i]==0){
                continue;
            }
            visits[i] = true;
            DFS(map,visits,start,i,depth+1,sum + map[currentIndex][i]);
            visits[i] = false;
        }

    }
}