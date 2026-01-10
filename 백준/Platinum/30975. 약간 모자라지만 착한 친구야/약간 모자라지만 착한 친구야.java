import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int ANSWER = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        int[] constraints = new int[N+1];
        strs = br.readLine().split(" ");

        for(int i=0;i<N;i++){
            constraints[i] = Integer.parseInt(strs[i]) - 1;
            if(constraints[i] == i){
                constraints[i] = -1; // 언제든 상관없음.
            }
        }
        constraints[N] = -1;

        int mapSize = N+1;
        int[][] map = new int[mapSize][mapSize];
        for(int i=0;i<mapSize;i++){
            for(int j=0;j<mapSize;j++){
                map[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i=0;i<M;i++){
            strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0]) - 1;
            int end = Integer.parseInt(strs[1]) - 1;
            int weight = Integer.parseInt(strs[2]);
            map[start][end] = Math.min(weight,map[start][end]);
        }

        for(int i=0;i<mapSize;i++){
            for(int j=0;j<mapSize;j++){
                if(map[i][j] == Integer.MAX_VALUE){
                    map[i][j] = 0;
                }
            }
        }

//        for(int i=0;i<mapSize;i++){
//            for(int j=0;j<mapSize;j++){
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }
        

        int[][] dp = new int[mapSize][1<<mapSize];
        for(int i=0;i<mapSize;i++){
            for(int j=0;j< (1<<mapSize);j++){
                dp[i][j] = -1;
            }
        }
        getAns(map,dp,constraints,N,1<<N,mapSize);

//        for(int i=0;i<mapSize;i++){
//            for(int j=0;j< (1<<mapSize);j++){
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }

        int answer = dp[N][1<<N];
        if(answer==100000000){
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }


    }

    // 0번은 1번보다 늦게 찍혀야해.
    //

    public static void getAns(int[][] map,int[][] dp, int[] constraints, int current, int status,int N){
        if(status == (1<<N)-1){
//            System.out.println(current+" " + (N-1));
            if(map[current][N-1]==0){
                dp[current][status] = 100000000;
                return;
            }
            dp[current][status] = map[current][N-1];
            return;
        }

        if(dp[current][status] != -1){
            return;
        }
        dp[current][status] =100000000;
        for(int i=0;i<N;i++){
//            System.out.println("@");
            if(map[current][i]==0){
                continue;
            }
            if((status & 1<<i) != 0){ // 방문한 적이 있는 거는 스킵.
                continue;
            }
            if(
                    constraints[i] != -1 &&
                    ( (1<<constraints[i]) & status) == 0){
//                System.out.println(constraints[i] +" " + status);
//                System.out.println("#$");
                continue;
            }
            int nextStatus = status | 1<<i;
            getAns(map,dp,constraints,i,nextStatus,N);
            dp[current][status] = Math.min(
                    dp[current][status],
                    dp[i][nextStatus] + map[current][i]
                    );
        }
    }
}