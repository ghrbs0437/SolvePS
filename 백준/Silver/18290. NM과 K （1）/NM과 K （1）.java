import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static int MAX = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        // y사이즈
        int N = Integer.parseInt(strs[0]);
        // x사이즈
        int M = Integer.parseInt(strs[1]);
        // 선택하는 칸의 개수
        int K = Integer.parseInt(strs[2]);

        int[][] map = new int[N][M];
        for(int i=0;i<N;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        getAns(map,new boolean[N][M],K,0,0);
        System.out.println(MAX);
    }

    public static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void getAns(int[][] map, boolean[][] visits, int maxCnt,int selectCnt, int sum){
        if(selectCnt == maxCnt){
            if(sum>MAX){
                MAX = sum;
            }
            return;
        }

        for(int i=0;i<map.length;i++){
            pos : for(int j=0;j<map[0].length;j++){
                if(visits[i][j]){
                    continue;
                }
                for(int[] direction : directions){
                    int y = i + direction[0];
                    int x = j + direction[1];
                    if(y<0 || x<0 || y>=map.length || x>=map[0].length){
                        continue;
                    } 
                    if(visits[y][x]){
                        continue pos;
                    }
                }
                visits[i][j] = true;
                getAns(map,visits,maxCnt,selectCnt+1,sum+map[i][j]);
                visits[i][j] = false;
            }
        }
    }
}