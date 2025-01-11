import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[26];
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<str.length();j++){
                arr[str.charAt(j) - 'A'] += tenPow(str.length()-1-j);
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for(int i : arr){
            if(i!=0){
                pq.add(i);
            }
        }
        int answer =0;
        int val = 9;
        while(!pq.isEmpty()){
            answer +=pq.poll() * (val--);
        }
        System.out.println(answer);

    }
    public static int tenPow(int a){
        int val = 1;
        for(int i=0;i<a;i++){
            val*=10;
        }
        return val;
    }
}