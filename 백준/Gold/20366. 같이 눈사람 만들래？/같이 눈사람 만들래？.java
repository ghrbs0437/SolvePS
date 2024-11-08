import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        String[] strs = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(strs[i]);
        }
        Arrays.sort(arr);


        int answer = Integer.MAX_VALUE;
        for(int i=0;i<N-1;i++){
            for(int j=i+1;j<N;j++){
                int size = arr[i] + arr[j];

                int start = 0;
                int end = N-1;
                while(true){
                    if(start>=end){
                        break;
                    }
                    if(start==i||start==j){
                        start++;
                        continue;
                    }
                    if(end==i||end==j){
                        end--;
                        continue;
                    }

                    int diff = arr[start] + arr[end];

                    if(size>=diff){
                        start++;
                    }else{
                        end--;
                    }
                    answer = Math.min(Math.abs(size-diff),answer);
                }
            }
        }
        System.out.println(answer);
    }
}