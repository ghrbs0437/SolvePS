import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {

    public static int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int K = Integer.parseInt(strs[1]);

        int[] map = new int[100001];
        Arrays.fill(map,Integer.MAX_VALUE);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(N);

        int cnt = 0;
        int amount = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i=0;i<size;i++){

                int current = queue.poll();
                if(current == K){
                    amount++;
                    continue;
                }

                if(map[current] < cnt){
                    continue;
                }

                map[current] = cnt;
                if(current-1>=0){
                    queue.add(current-1);
                }
                if(current+1<=100000){
                    queue.add(current+1);
                }
                if(current*2<=100000){
                    queue.add(current*2);
                }
            }
            if(amount!=0){
                break;
            }
            cnt++;
        }
        System.out.println(cnt);
        System.out.println(amount);

    }
}
