import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();


        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        strs = br.readLine().split(" ");
        int[] map = new int[N];
        int[] sum = new int[N+1];
        for(int i=0;i<N;i++){
            map[i] = Integer.parseInt(strs[i]);
        }
        for(int i=1;i<=N;i++){
            sum[i] = sum[i-1] + map[i-1];
        }
        int answer = 0;
        for(int i=0;i<=N;i++){
            for(int j=0;j<i;j++){
                if(sum[i] - sum[j] == M){
                    answer++;
                }
            }
        }
        System.out.println(answer);

    }
}