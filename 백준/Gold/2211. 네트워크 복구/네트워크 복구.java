import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        ArrayList<Token>[] alist = new ArrayList[N];
        for(int i=0;i<N;i++){
            alist[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0])-1;
            int end = Integer.parseInt(strs[1])-1;
            int cost = Integer.parseInt(strs[2]);

            Token token1 = new Token(start, end , cost);
            alist[start].add(token1);
            Token token2 = new Token(end, start , cost);
            alist[end].add(token2);
        }

        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
            return a.value - b.value;
        });

        boolean[] visits = new boolean[N];
        int[] distance = new int[N];
        Arrays.fill(distance,Integer.MAX_VALUE);
        pq.addAll(alist[0]);
        distance[0] = 0;
        HashSet<Token> tokens = new HashSet<>();
        ArrayList<Token> answer = new ArrayList<>();
        while(!pq.isEmpty()){
            Token token = pq.poll();
            int directDistance = 0;
            int routeDistance = 0;
            directDistance = distance[token.end];
            routeDistance = distance[token.start] + token.value;
            if(directDistance > routeDistance){
                distance[token.end] = routeDistance;
                pq.addAll(alist[token.end]);
                
                for(int i=0;i<answer.size();i++){
                    if(answer.get(i).start == token.end
                    || answer.get(i).end == token.end){
                        answer.remove(i);
                        i--;
                    }
                }
                answer.add(token);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append("\n");
        for(Token t : answer){
            sb.append(t.start+1).append(" ").append(t.end+1).append("\n");
        }
        System.out.println(sb);
    }

    public static class Token{
        int start;
        int end;
        int value;

        Token(int start, int end, int value){
            this.start = start;
            this.end = end;
            this.value = value;
        }


    }
}