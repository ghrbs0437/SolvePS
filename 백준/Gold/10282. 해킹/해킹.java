import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    static int ANSWER = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int tc = 0; tc<testCase;tc++){
            String[] strs = br.readLine().split(" ");

            int N = Integer.parseInt(strs[0]);
            int D = Integer.parseInt(strs[1]);
            int C = Integer.parseInt(strs[2]);


            ArrayList<Token>[] lines = new ArrayList[N+1];
            for(int i=0;i<=N;i++){
                lines[i] = new ArrayList<>();
            }

            for(int i=0;i<D;i++) {
                strs = br.readLine().split(" ");

                int end = Integer.parseInt(strs[0]);
                int start = Integer.parseInt(strs[1]);
                int cost = Integer.parseInt(strs[2]);

                Token token = new Token(start, end, cost);
                lines[start].add(token);
            }

            dijkstraSolve(lines,C,N);

        }
    }

    public static void dijkstraSolve(ArrayList<Token>[] lines, int start, int N){
        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
            return a.cost - b.cost;
        });

        boolean[] visits = new boolean[N+1];
        int[] costsMap = new int[N+1];

        for(Token t : lines[start]){
            pq.add(t);
            costsMap[t.end] = t.cost;
        }
        visits[start] = true;

        while(!pq.isEmpty()){
            Token poll = pq.poll();

            if(visits[poll.end]){
                continue;
            }
            visits[poll.end] = true;

            for(Token t : lines[poll.end]){
                int directRoute = costsMap[t.end];
                int delayRoute = costsMap[poll.end] + t.cost;

                if(directRoute == 0 || directRoute > delayRoute){
                    costsMap[t.end] = delayRoute;
                    pq.add(new Token(-1,t.end,costsMap[t.end]));
                }
            }
        }
        int cnt = 0;
        int max = 0;
        for(int i=1;i<=N;i++){
            if(visits[i]){
                cnt++;
            }
            max = Math.max(costsMap[i],max);
        }
        System.out.println(cnt+" " +max);
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
}