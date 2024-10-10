import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int[][] minDist = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
               map[i][j] = 10000000;
               minDist[i][j] = 10000000;
            }
        }
        for(int i=0;i<M;i++){
            String[] strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0])-1;
            int end = Integer.parseInt(strs[1])-1;
            int cost = Integer.parseInt(strs[2]);
            map[start][end] = Math.min(map[start][end],cost);
            minDist[start][end] = Math.min(map[start][end],cost);
        }

        for(int k=0;k<N;k++){
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(i==j){
                        continue;
                    }
                    minDist[i][j] = Math.min(minDist[i][k] + minDist[k][j],minDist[i][j]);
                }
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(minDist[i][j] >= 10000000){
                    System.out.print(0+" ");
                }else{
                    System.out.print(minDist[i][j]+" ");
                }
            }
            System.out.println();
        }



    }
}