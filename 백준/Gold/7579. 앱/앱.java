import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        strs = br.readLine().split(" ");
        int[] memory = new int[N+1];
        int[] cost = new int[N+1];
        for(int i=1;i<=N;i++){
            memory[i] = Integer.parseInt(strs[i-1]);
        }
        strs = br.readLine().split(" ");
        int maxCost = 0;
        for(int j=1;j<=N;j++){
            cost[j] = Integer.parseInt(strs[j-1]);
            maxCost = Math.max(maxCost,cost[j]);
        }

        int[][] map = new int[N+1][(N+1)*(maxCost+1)]; // 코스트에서 최대의 메모리를 골라

        for(int i=1;i<=N;i++){
            for(int j=0;j<map[0].length;j++){
                if(j - cost[i]<0){
                   map[i][j] = map[i-1][j];
                   continue;
                }
                map[i][j] =  Math.max(map[i-1][j], map[i-1][j-cost[i]]+memory[i]);
            }
        }

        for(int i=0;i< map[0].length;i++){
            if(map[N][i]>=M){
                System.out.println(i);
                break;
            }
//            System.out.println(map[N][i]);
        }

//        for(int i=0;i<map.length;i++){
//            for(int j=0;j<map[0].length;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
    }
}