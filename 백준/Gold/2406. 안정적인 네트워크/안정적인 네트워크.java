import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        int n = Integer.parseInt(strs[0]);
        int m = Integer.parseInt(strs[1]);

        int[] parents = init(n);

        for(int i=0;i<m;i++){
            strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0])-1;
            int end = Integer.parseInt(strs[1])-1;
            union(parents,start,end);
        }

        int[][] map = new int[n][n];

        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
            return a.cost - b.cost;
        });


        for(int i=0;i<n;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                Token token = new Token(i,j,map[i][j]);
                pq.add(token);
            }
        }

        int answer = 0;
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            Token token = pq.poll();
            if(token.start == 0 || token.end == 0){
                continue;
            }
            if(union(parents,token.start,token.end)){
                answer+=token.cost;
                cnt++;
                sb.append(token.start+1).append(" ").append(token.end+1).append("\n");
            }
        }
        System.out.println(answer+" " +cnt);
        System.out.println(sb);

    }

    public static class Token{
        int start;
        int end;
        int cost;

        Token(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }


    public static int[] init(int size){
        int[] parents = new int[size];
        for(int i=0;i<size;i++){
            parents[i] = i;
        }
        return parents;
    }

    public static int findRoot(int[] parents, int value){
        if(parents[value] == value){
            return value;
        }
        return parents[value] = findRoot(parents,parents[value]);
    }

    public static boolean union(int[] parents, int a, int b){
        int rootA = findRoot(parents,a);
        int rootB = findRoot(parents,b);

        if(rootA == rootB){
            return false;
        }
        parents[rootB] = rootA;
        return true;
    }

}