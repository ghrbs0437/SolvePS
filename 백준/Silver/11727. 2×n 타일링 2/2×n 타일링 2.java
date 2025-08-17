import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        dp[0] = 0;
        dp[1] = 1;
        if(N>=2){
            dp[2] = 3;
        }
        // dp[2] = dp[1]
        for(int i=3;i<=N;i++){
            dp[i] = (dp[i-1] + 2*dp[i-2])%10007;
        }
        System.out.println(dp[N]);

    }
}