import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] max = new int[3];
        int[] min = new int[3];
        String[] strs  = br.readLine().split(" ");
        max[0] = Integer.parseInt(strs[0]);
        max[1] = Integer.parseInt(strs[1]);
        max[2] = Integer.parseInt(strs[2]);

        min[0] = Integer.parseInt(strs[0]);
        min[1] = Integer.parseInt(strs[1]);
        min[2] = Integer.parseInt(strs[2]);
        for(int i=1;i<N;i++){
            strs  = br.readLine().split(" ");
            int num1 = Integer.parseInt(strs[0]);
            int num2 = Integer.parseInt(strs[1]);
            int num3 = Integer.parseInt(strs[2]);
            int nm0 = Math.max(max[0] + num1,max[1] + num1);
            int nm1 = Math.max(Math.max(max[0] + num2,max[1] + num2), max[2] + num2);
            int nm2 = Math.max(max[1] + num3,max[2] + num3);
            max[0] = nm0;
            max[1] = nm1;
            max[2] = nm2;

            nm0 = Math.min(min[0] + num1,min[1] + num1);
            nm1 = Math.min(Math.min(min[0] + num2,min[1] + num2), min[2] + num2);
            nm2 = Math.min(min[1] + num3,min[2] + num3);
            min[0] = nm0;
            min[1] = nm1;
            min[2] = nm2;
            
        }

        int ans1 = 0;
        int ans2 = Integer.MAX_VALUE;
        for(int i : max){
            if(ans1<i){
                ans1 = i;
            }
        }
        for(int i:min){
            if(ans2>i){
                ans2 = i;
            }
        }
        System.out.println(ans1 + " " +ans2);

    }
}