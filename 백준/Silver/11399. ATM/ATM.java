import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String[] strs = br.readLine().split(" ");

        int[] map = new int[N];
        for(int i=0;i<N;i++){
            map[i] = Integer.parseInt(strs[i]);
        }
        Arrays.sort(map);
        int sum = 0;

        for(int i=0;i<N;i++){
            sum += map[i];
            sum += map[i] * (N-1-i);
        }
        System.out.println(sum);

    }
}
