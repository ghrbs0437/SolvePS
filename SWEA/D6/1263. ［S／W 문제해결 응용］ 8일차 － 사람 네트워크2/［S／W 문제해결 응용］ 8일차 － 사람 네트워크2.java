import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for(int tc=1;tc<=testCase;tc++){
            String[] strs = br.readLine().split(" ");
            int mapSize = Integer.parseInt(strs[0]);
            int[][] map = new int[mapSize][mapSize];
            int[][] minDist = new int[mapSize][mapSize];
            for(int i=0;i<mapSize*mapSize;i++){
                int y = i/mapSize;
                int x = i%mapSize;
                map[y][x] = Integer.parseInt(strs[i+1]);
                if(map[y][x]==1){
                    minDist[y][x] = map[y][x];
                }else{
                    minDist[y][x] = 10000;
                }
            }

            for(int k=0;k<mapSize;k++){
                for(int i=0;i<mapSize;i++){
                    for(int j=0;j<mapSize;j++){
                        minDist[i][j] = Math.min(minDist[i][k] + minDist[k][j],minDist[i][j]);
                    }
                }
            }

            int answer = Integer.MAX_VALUE;
            for(int i=0;i<mapSize;i++){
                int sum  = 0;
                for(int j=0;j<mapSize;j++){
                    if(i==j){
                        continue;
                    }
                    sum += minDist[i][j];
                }
                answer = Math.min(answer,sum);
            }
            sb.append("#"+tc+" " +answer+"\n");
        }
        System.out.println(sb);

    }
}