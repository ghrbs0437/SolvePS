import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int K = Integer.parseInt(strs[1]);

        int[] map = new int[N];

        strs = br.readLine().split(" ");
        int[] diff = new int[N];
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(strs[i]);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1;i<N;i++){
            pq.add(map[i] - map[i-1]);
        }
        int answer = 0 ;
        for(int i=0;i<N-K;i++){
            answer += pq.poll();
        }
        System.out.println(answer);


    }
}