import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    static int ANSWER = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);
        int K = Integer.parseInt(strs[2]);

        ArrayList<Token>[] tokens = new ArrayList[N+1];

        for(int i=0;i<=N;i++){
            tokens[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0]);
            int end = Integer.parseInt(strs[1]);
            int cost = Integer.parseInt(strs[2]);
            Token token = new Token(end,start,cost);
            tokens[end].add(token);
        }


        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
            return Long.compare(a.cost,b.cost);
        });
        boolean[] visits = new boolean[N+1];
        long[] distance = new long[N+1];
        for(int i=0;i<=N;i++){
            distance[i] = -1;
        }
        strs = br.readLine().split(" ");
        for(int i=0;i<K;i++){
            int start = Integer.parseInt(strs[i]);
            distance[start] = 0;
            pq.add(new Token(0,start,0));
        }
        distance[0] = 0;

        while(!pq.isEmpty()){
            Token poll = pq.poll();
            
            if(visits[poll.end]){
                continue;
            }

            visits[poll.end] = true;

            for(Token t : tokens[poll.end]){
                long directRoute = distance[t.end];
                long delayRoute = distance[poll.end] + t.cost;
                
                if(directRoute==-1 || directRoute>delayRoute){
                    distance[t.end] = delayRoute;
                    pq.add(new Token(-1,t.end,distance[t.end]));
                }
            }
        }
        long[] answer = new long[2];
        answer[1] = -1;
        for(int i=0;i<=N;i++){
            if(distance[i] > answer[1]){
                answer[0] = i;
                answer[1] = distance[i];
            }
        }
        System.out.println(answer[0]+"\n"+answer[1]);


    }

    public static class Token{
        int start;
        int end;
        long cost;

        Token(int start,int end, long cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}