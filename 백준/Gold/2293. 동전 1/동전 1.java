import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int K = Integer.parseInt(strs[1]);

        int[] arr = new int[N];
        int[] answer = new int[K+1];


        HashSet<Integer> hset = new HashSet<>();
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
            hset.add(arr[i]);
        }

        Arrays.sort(arr);
        answer[0] = 0;

        for(int i=0;i<N;i++){
            for(int j=1;j<=K;j++){
                if(j-arr[i]<0){
                    continue;
                }
                if(j==arr[i]){
                    answer[j]++;
                }
                answer[j] += answer[j-arr[i]];
            }
        }
        
        System.out.println(answer[K]);
    }
}