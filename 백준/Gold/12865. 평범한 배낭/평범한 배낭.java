import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        // 물건의 수
        int N = Integer.parseInt(strs[0]);
        // 가용 무게
        int carryWeight = Integer.parseInt(strs[1]);

        int[][] arr = new int[N+1][carryWeight+1];

        for(int i=1;i<=N;i++){
            strs = br.readLine().split(" ");
            int weight = Integer.parseInt(strs[0]);
            int value = Integer.parseInt(strs[1]);

            for(int j=1;j<=carryWeight;j++) {
                if (j < weight) { // 못넣어 pass
                     arr[i][j] = arr[i-1][j];
                } else {
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i - 1][j - weight] + value);

                }

            }

        }
        System.out.println(arr[N][carryWeight]);


    }
}