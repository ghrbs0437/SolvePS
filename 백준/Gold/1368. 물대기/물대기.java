import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] digCost = new int[N];
        int[] linkCost = new int[N];
        int minCost = Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            digCost[i] = Integer.parseInt(br.readLine());
            linkCost[i] = digCost[i];
            minCost = Math.min(minCost,digCost[i]);
        }

        int[] parents = init(N+1);

        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->a.cost - b.cost);
        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");
            for(int j=i+1;j<N;j++){
                pq.add(new Token(i,j,Integer.parseInt(strs[j])));
            }
        }
        for(int i=0;i<N;i++){
            pq.add(new Token(N,i,digCost[i]));
        }

        int cnt = 0;
        int answer = 0;
        while(cnt!=N){
            Token token = pq.poll();
            if(union(token.start,token.end,parents)){
                // 결합에 성공했으면?
                cnt++;
                answer += token.cost;
            }
        }

        System.out.println(answer);

    }

    public static int[] init(int size){
        int[] arr = new int[size];
        for(int i=0;i<size;i++){
            arr[i] = i;
        }
        return arr;
    }

    public static int findRoot(int value, int[] parents){
        if(value == parents[value]){
            return value;
        }
        return parents[value] = findRoot(parents[value],parents);
    }

    public static boolean union(int val1, int val2, int[] parents){
        int rootA = findRoot(val1,parents);
        int rootB = findRoot(val2,parents);
        if(rootA == rootB){
            return false;
        }
        parents[rootB] = rootA;
        return true;
    }

    public static class Token{
        int start;
        int end;
        int cost;

        Token(int start,int end,int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}