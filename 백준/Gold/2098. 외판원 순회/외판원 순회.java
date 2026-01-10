
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }
//        for(int i=0;i<N;i++){
//            for(int j=0;j<N;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }

        int[][] dp = new int[N][1<<N]; // [현재위치][방문상태]
        for(int i=0;i<N;i++){
            for(int j=0;j< 1<<N;j++){
                dp[i][j] = -1;
            }
        }

        getAns(map,dp,1,0,N);
        
        System.out.println(dp[0][1]);


    }
    public static void getAns(int[][] map, int[][] dp, int visit, int current,int N){
        if(visit == (1<< N) -1){
            if(map[current][0]==0){
                dp[current][visit] = 100000000;
            }else{
                dp[current][visit] = map[current][0];
            }
            return;
        }
        if(dp[current][visit]!=-1){
            return;
        }
        dp[current][visit] = 100000000;
        for(int i=0;i<N;i++){
            if(map[current][i]==0){
                continue;
            }
            if( (visit & 1<<i) !=0){
                continue;
            }

            int next = visit | (1<<i);
            getAns(map,dp,next,i,N);
            dp[current][visit] = Math.min(
                    dp[current][visit], map[current][i]+ dp[i][next]
            );
        }
    }
}