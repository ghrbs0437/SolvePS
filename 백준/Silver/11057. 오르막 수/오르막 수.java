import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N+1][10];

        for(int i=0;i<=9;i++){
            map[1][i] = 1;
        }

        for(int i=1;i<=N;i++){
            for(int j=9;j>=0;j--){
                for(int k=j;k>=0;k--){
                    map[i][j] += map[i-1][k] % 10007;
                }
            }
        }
        int answer = 0;
        for(int i=0;i<10;i++){
            answer+=map[N][i];
        }
        System.out.println(answer%10007);

    }
}