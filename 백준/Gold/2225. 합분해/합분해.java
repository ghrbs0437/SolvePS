import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int K = Integer.parseInt(strs[1]);

        int[][] dp = new int [N+1][K+1]; // N을 만드는데 K번 .

        for(int i=0;i<=N;i++){
            dp[i][1] = 1;
        }

        for(int i=2 ; i<=K;i++){ // i번 수를 사용했어.
            for(int j=0;j<=N;j++){ // 만드려는 수
                for(int k=0;k<=j;k++){ // 탐색할 인덱스 범위
                    dp[j][i] += dp[j-k][i-1];
                    dp[j][i] %= 1000000000;
                }
            }
        }

        System.out.println(dp[N][K]%1000000000);

    }
}