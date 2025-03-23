import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] strs = br.readLine().split(" ");
        int[] people = new int[N+1];
        int[][] dp = new int[N+1][2];
        for(int i=1;i<=N;i++){
            people[i] = Integer.parseInt(strs[i-1]);
        }
        // 가능한 우수마을 선택지에서 인구수가 최대가 되어야한다.
        // 최상단이 우수마을인경우 아래에서 얻을수 있는 총합
        // 최상안이 우수마을이 아닌경우 아래에서 얻을 수 있는 총합
        // 이걸 DP 돌리면 가능하다..

        HashMap<Integer, ArrayList<Integer>> hmap = new HashMap<>();
        for(int i=0;i<=N;i++){
            hmap.put(i,new ArrayList<>());
        }

        for(int i=0;i<N-1;i++){
            strs = br.readLine().split(" ");
            int a = Integer.parseInt(strs[0]);
            int b = Integer.parseInt(strs[1]);
            hmap.get(a).add(b);
            hmap.get(b).add(a);
        }


        getAns(1,-1,hmap,dp,people);
        System.out.println(Math.max(dp[1][0],dp[1][1]));

    }

    public static void getAns(int current, int parent, HashMap<Integer,ArrayList<Integer>> hmap,int[][] dp, int[] people){
        dp[current][0] = 0;
        dp[current][1] = people[current];

        ArrayList<Integer> linked = hmap.get(current);
        for(Integer link : linked){
            if(link == parent){
                continue;
            }
            getAns(link,current,hmap,dp, people);
            dp[current][0] += Math.max(dp[link][0],dp[link][1]);
            dp[current][1] += dp[link][0];
        }

    }
}