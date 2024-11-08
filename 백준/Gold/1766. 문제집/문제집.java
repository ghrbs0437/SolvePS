import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        HashMap<Integer,Token> tokenMap = new HashMap<>();
        for(int i=1;i<=N;i++){
            Token token = new Token();
            token.index = i;
            tokenMap.put(i,token);
        }

        for(int i=0;i<M;i++){
            strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0]);
            int end = Integer.parseInt(strs[1]);
            tokenMap.get(end).indegree++;
            tokenMap.get(start).next.add(end);
        }



        Set<Integer> keySet = tokenMap.keySet();
        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
            if(a.indegree==b.indegree){
                return a.index - b.index;
            }else{
                return a.indegree - b.indegree;
            }
        });
        for(Integer i : keySet){
            Token token = tokenMap.get(i);
            if(token.indegree==0){
                pq.add(token);
            }
        }

        while(!pq.isEmpty()){
            Token token = pq.poll();
            sb.append(token.index+" ");
            for(int nextI : token.next){
                Token nt = tokenMap.get(nextI);
                nt.indegree--;
                if(nt.indegree==0){
                    pq.add(nt);
                }
            }
        }

        System.out.println(sb);

    }

    public static class Token{
        int index;
        int indegree;
        ArrayList<Integer> next = new ArrayList<>();

    }
}