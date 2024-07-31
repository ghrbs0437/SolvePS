import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int M = Integer.parseInt(strs[0]);
        int N = Integer.parseInt(strs[1]);

        boolean[] arr = new boolean[1000001];
        Queue<Integer> queue = new LinkedList<>();
        arr[1] = true;
        for(int i=2; i<=1000;i++){
            if(arr[i]){
                continue;
            }
            queue.add(i);
            for(int j=1;;j++){
                if(i*j<=1000000){
                    arr[i*j] = true;
                }else{
                    break;
                }
            }
        }

        for(Integer i:queue){
            arr[i] = false;
        }

        int cnt = 0;
        for(int i=M;i<=N;i++){
            if(!arr[i]){
                cnt++;
                System.out.println(i);
            }
        }
    }
}