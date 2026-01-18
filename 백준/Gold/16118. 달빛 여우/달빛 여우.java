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

        // 여우의 이동경로는 그냥 다익스트라 하면 되는데..
        // 늑대의 이동경로는 2차원 다익스트라? 해야할거같은데
        // 늑대의 이동경로 MAP은 2차원 배열이다.. [][2] :  [][0] 은 두배속으로 이동했을때 최단경로, [][1]은 반배속으로 이동했을 떄 최단경로..
        // 여우와 비교할떄는 그냥 적은값을 비교하면 되는데 늑대의 이동경로 최단거리를 측정할떄 사용해야 할거야...

        ArrayList<Token>[] lines = new ArrayList[N+1];
        for(int i=0;i<=N;i++){
            lines[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++){

            strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0]);
            int end = Integer.parseInt(strs[1]);
            long cost = Long.parseLong(strs[2])*2;

            Token token1 = new Token(start,end,cost);
            lines[start].add(token1);
            Token token2 = new Token(end,start,cost);
            lines[end].add(token2);
        }

        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
            return Long.compare(a.cost, b.cost);
        });

        long[] foxDistanceMap = new long[N+1];
        boolean[] visits = new boolean[N+1];

        for(Token t : lines[1]){
            pq.add(t);
            foxDistanceMap[t.end] = t.cost;
        }
        visits[1] = true;

        while(!pq.isEmpty()){
            Token token = pq.poll();

            if(visits[token.end]){
                continue;
            }

            visits[token.end] = true;

            for(Token t : lines[token.end]){
                long directRoute = foxDistanceMap[t.end];
                long delayRoute = foxDistanceMap[token.end] + t.cost;

                if(directRoute == 0 || directRoute > delayRoute){
                    foxDistanceMap[t.end] = delayRoute;
                    pq.add(new Token(-1,t.end,foxDistanceMap[t.end]));
                }
            }
        }

        long[][] wolfDistanceMap = new long[N+1][2];
        boolean[][] wolfVisits = new boolean[N+1][2];
        // [][0] = 두배속이동 , [][1] = 절반속도 이동

        for(int i=0;i<=N;i++){
            for(int j=0;j<2;j++){
                wolfDistanceMap[i][j] = -1;
            }
        }
        for(Token t : lines[1]){
            Token token = new Token(t.start, t.end, t.cost / 2,true);
            pq.add(token);
            wolfDistanceMap[t.end][0] = t.cost / 2;
        }
        wolfVisits[1][1] = true;


        while(!pq.isEmpty()){
            Token poll = pq.poll();
//
//            for(int j=0;j<2;j++){
//                for(int i=0;i<=N;i++){
//                    System.out.print(wolfDistanceMap[i][j]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();

            if(wolfVisits[poll.end][poll.twice?0:1]){
                continue;
            }
            wolfVisits[poll.end][poll.twice?0:1] = true;


            for(Token t : lines[poll.end]){

                long directRoute = wolfDistanceMap[t.end][poll.twice?1:0]; // 이전에 두배로 뛰어왔으면 이번에 비교해야하는건 다음자리가 절반속도로 오는거..
                long delayRoute = wolfDistanceMap[poll.end][poll.twice?0:1] + (poll.twice? t.cost*2:t.cost/2);

                if(directRoute == -1 || directRoute >delayRoute){
                    wolfDistanceMap[t.end][poll.twice?1:0] = delayRoute;
                    pq.add(new Token(-1,t.end,wolfDistanceMap[t.end][poll.twice?1:0],!poll.twice));
                }
            }

        }

        long[] wolfs = new long[N+1];
        for(int i=0;i<=N;i++){
            wolfs[i] = Math.min(wolfDistanceMap[i][0], wolfDistanceMap[i][1]);
            if(wolfs[i] == -1){
                wolfs[i] = Math.max(wolfDistanceMap[i][0], wolfDistanceMap[i][1]);
            }
        }
        int answer = 0;

        for(int i=2;i<=N;i++){
            if(wolfs[i] > foxDistanceMap[i]){
                answer++;
            }
        }

//        for(int j=0;j<2;j++){
//            for(int i=0;i<=N;i++){
//                System.out.print(wolfDistanceMap[i][j]+" ");
//            }
//            System.out.println();
//        }

//        System.out.println(Arrays.toString(foxDistanceMap));
//        System.out.println(Arrays.toString(wolfs));
        System.out.println(answer);
//        System.out.println(Arrays.toString(wolfDistanceMap));

    }

    public static class Token{
        int start;
        int end;
        long cost;

        boolean twice = false;

        Token(int start, int end, long cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        Token(int start, int end, long cost, boolean twice){
            this.start = start;
            this.end = end;
            this.cost = cost;
            this.twice = twice;
        }

    }
}