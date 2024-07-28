import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

    static int answer = 0;
    static HashSet<Integer> visitset ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");

        // 지역의 개수
        int n = Integer.parseInt(split[0]);
        // 수색 범위
        int m = Integer.parseInt(split[1]);
        // 길의 개수
        int r = Integer.parseInt(split[2]);

        split = br.readLine().split(" ");
        int[] items = new int[n];
        for(int i=0;i<n;i++){
            items[i] = Integer.parseInt(split[i]);
        }

        int[][] map = new int[n][n];
        for(int i=0;i<r;i++){
            String[] strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0])-1;
            int end = Integer.parseInt(strs[1])-1;
            int use = Integer.parseInt(strs[2]);
            map[start][end] = use;
            map[end][start] = use;
        }


//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println("");
//        }

        // 1. 모든 출발지에서 갈 수 잇는 모든 경우의쌍을 구하고 아이템의 합 구하기
        // 2.

        int sum = 0;
        for(int i=0;i<n;i++){
            // 예은이의 출발지점 i..
            HashSet<Integer> visits = new HashSet<>();
            visits.add(i);
            explore(map,i,visits,m);

            int subSum = 0 ;
            for(Integer tmp : visits){
                subSum += items[tmp];
            }
            if(subSum>sum){
                sum = subSum;
            }
        }
        System.out.println(sum);


    }

    public static void explore(int[][] map, int current, HashSet<Integer> visits,int activePoint){
//        System.out.println(total);
        for(int i=0;i<map[current].length;i++){
            if(map[current][i]!=0){ // 거기랑 연결된거중에..
                if(activePoint-map[current][i]>=0){
                    visits.add(i);
                    explore(map,i,visits,activePoint-map[current][i]);
                }else{
                    continue;
                }
            }
        }

    }

}