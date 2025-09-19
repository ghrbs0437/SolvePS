import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][2];
        for(int i=0;i<N;i++){

            String[] strs = br.readLine().split(" ");

            map[i][0] = Integer.parseInt(strs[0]);
            map[i][1] = Integer.parseInt(strs[1]);

        }

        int[] arr = new int[N];
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++){
            int cnt = 0;
            for(int j=0;j<N;j++){
                if(map[i][0] < map[j][0] && map[i][1] < map[j][1]){
                    cnt++;
                }
            }
            arr[i] = cnt+1;
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb);
    }

}