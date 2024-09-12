import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//t
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        HashSet<Integer> tripPlan = new HashSet<>();
        String [] strs = br.readLine().split(" ");
        for(int i=0;i<M;i++){
            tripPlan.add(Integer.parseInt(strs[i])-1);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(Integer.parseInt(strs[0])-1);

        boolean[] visits = new boolean[N];
        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i=0;i<size;i++){
                int current = queue.poll();
                if(visits[current]){
                    continue;
                }
                visits[current] = true;

                for(int j=0;j<map.length;j++){
                    if(map[current][j] == 1&&!visits[j]){
                        queue.add(j);
                    }
                }
            }
        }
        for(int trip : tripPlan){
            if(!visits[trip]){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");

    }
}