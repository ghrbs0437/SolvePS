import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int E = Integer.parseInt(strs[1]);


        ArrayList<Token>[] map = new ArrayList[N+1];
        for(int i=0;i<=N;i++){
            map[i] = new ArrayList<>();
        }
        for(int i=0;i<E;i++){
            strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0]);
            int end = Integer.parseInt(strs[1]);
            int value = Integer.parseInt(strs[2]);
            map[start].add(new Token(start,end,value));
            map[end].add(new Token(end,start,value));
        }

        strs = br.readLine().split(" ");
        int v1 = Integer.parseInt(strs[0]);
        int v2 = Integer.parseInt(strs[1]);

        // v1 == 1 이고 v2 == N인경우.
        int ans = 0;
        if(v1==1 && v2 == N){
            int[] sMap = dijkstra(map,1);
            if(sMap[N]==Integer.MAX_VALUE){
                System.out.println(-1);
                return;
            }
            ans = sMap[N];
        }else if(v1==1){
            int[] sMap = dijkstra(map,1);
            int[] v2Map = dijkstra(map,v2);

            if(sMap[v2]==Integer.MAX_VALUE
                || sMap[N]==Integer.MAX_VALUE){
                System.out.println(-1);
                return;
            }

            ans = sMap[v2] + v2Map[N];
        }else if(v2==N){
            int[] sMap = dijkstra(map,1);
            int[] v1Map = dijkstra(map,v1);

            if(sMap[v1]==Integer.MAX_VALUE
                    || sMap[N]==Integer.MAX_VALUE){
                System.out.println(-1);
                return;
            }

            ans = sMap[v1] + v1Map[N];
        }else{
            int[] sMap = dijkstra(map,1);
            int[] v1Map = dijkstra(map,v1);
            int[] v2Map = dijkstra(map,v2);


            if(sMap[v1]==Integer.MAX_VALUE
                    || sMap[N]==Integer.MAX_VALUE
                    || sMap[v2] == Integer.MAX_VALUE){
                System.out.println(-1);
                return;
            }
            ans = Math.min(
                    sMap[v1] + v1Map[v2] + v2Map[N],
                    sMap[v2] + v2Map[v1] + v1Map[N]);
        }

        System.out.println(ans);


    }

    public static int[] dijkstra(ArrayList<Token>[] map, int start){
        int[] distance = new int[map.length];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->a.value-b.value);
        for(Token t : map[start]){
            pq.add(t);
        }

        while(!pq.isEmpty()){
            Token token = pq.poll();
            int directDistance = 0;
            int routeDistance = 0;
            directDistance = distance[token.end];
            routeDistance = distance[token.start] + token.value;
            if(directDistance > routeDistance){
                distance[token.end] = distance[token.start] + token.value;
                for(Token t : map[token.end]){
                    pq.add(t);
                }
            }
        }

        return distance;
    }


    public static class Token{
        int start;
        int end;
        int value;
        Token(int start,int end, int value){
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }
}