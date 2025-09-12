import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] strs = br.readLine().split(" ");
        int[] arr = new int[N];

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(strs[i]);
        }

        HashSet<Integer> hset = new HashSet<>();


        int answer = 0 ;
        ff: for(int i=0;i<N;i++){
            for(int j=0;j<i;j++){
                hset.add(arr[i-1]+arr[j]);
            }
            for(int j=0;j<i;j++){
                if(hset.contains(arr[i]-arr[j])){
                    answer++;
                    continue ff;
                }
            }
        }
        System.out.println(answer);

    }
}