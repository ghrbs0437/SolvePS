import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);


        ArrayList<Token>[] lines = new ArrayList[N+1];
        for(int i=0;i<=N;i++){
            lines[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++){
            strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0]);
            int end = Integer.parseInt(strs[1]);
            int cost = Integer.parseInt(strs[2]);

            Token token = new Token(start,end,cost);
            Token token1 = new Token(end,start,cost);
            lines[start].add(token);
            lines[end].add(token1);
        }

        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
            return a.cost - b .cost;
        });

        boolean[] visits = new boolean[N+1];
        int[] distanceMap = new int[N+1];

        for(int i=0;i<N+1;i++){
            distanceMap[i] = -1;
        }

        for(Token t : lines[1]){
            pq.add(t);
            distanceMap[t.end] = t.cost;
        }
        visits[1] = true;


        while(!pq.isEmpty()){
            Token poll = pq.poll();

            if(visits[poll.end]){
                continue;
            }
            visits[poll.end] = true;

            for(Token t : lines[poll.end]){
                int directRoute = distanceMap[t.end];
                int delayRoute = distanceMap[poll.end] + t.cost;

                if(directRoute== -1 || directRoute > delayRoute){
                    distanceMap[t.end] = delayRoute;
                    pq.add(new Token(-1,t.end,distanceMap[t.end]));
                }
            }
        }

        System.out.println(distanceMap[N]);


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