import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {


    public static int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int mapSize = Integer.parseInt(br.readLine());

        int[][] map = new int[mapSize][mapSize];
        for(int i=0;i<mapSize;i++){
            String[] strs = br.readLine().split(" ");
            for(int j=0;j<mapSize;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        int[][] memory = new int[mapSize][mapSize];
        for(int i=0;i<mapSize;i++){
            for(int j=0;j<mapSize;j++){
                // DFS 두번.. 증가하는방향 감소하는방향.
                if(memory[i][j]!=0){
                    continue;
                }
                DFS(map,memory,map[i][j],i,j,0);
            }
        }
        int max = 0;
        for(int i=0;i<mapSize;i++){
            for(int j=0;j<mapSize;j++){
                // DFS 두번.. 증가하는방향 감소하는방향.
                if(max<memory[i][j]){
                    max = memory[i][j];
                }
            }

        }
        System.out.println(max+1);


    }
    public static int DFS(int[][] map,int[][]memory, int pastValue,int y,int x,int cnt){
        boolean flag = true;
        for(int[] direction : directions){
            int dy = direction[0];
            int dx = direction[1];
            int ny = y+dy;
            int nx = x+dx;
            if(ny<0||nx<0||ny>= map.length||nx>=map[0].length){
                continue;
            }
            flag = false;
            if(map[ny][nx]>pastValue){
                if(memory[ny][nx]==0){
                    int value = DFS(map,memory,map[ny][nx],ny,nx,cnt+1);
                    if(memory[y][x]<value+1){
                        memory[y][x] = value+1;
                    }
                }else{
                    if(memory[y][x]<memory[ny][nx]+1){
                        memory[y][x] = memory[ny][nx]+1;
                    }
                }
            }
        }
        if(flag){
            return 1;
        }
        return memory[y][x];
    }
}