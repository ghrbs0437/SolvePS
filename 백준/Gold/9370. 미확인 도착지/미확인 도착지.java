
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

    public static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        for(int tc=0;tc<testCase;tc++) {
            String[] strs = br.readLine().split(" ");

            int N = Integer.parseInt(strs[0]); // 노드 개수
            int M = Integer.parseInt(strs[1]); // 도로 개수
            int T = Integer.parseInt(strs[2]); // 목적지 후보 개수
            strs = br.readLine().split(" ");

            int s = Integer.parseInt(strs[0]); // 출발지
            int g = Integer.parseInt(strs[1]); // 한 경로의 시작점
            int h = Integer.parseInt(strs[2]); // 한 경로의 끝점

            ArrayList<Token>[] line = new ArrayList[N + 1];
            for (int i = 0; i < N + 1; i++) {
                line[i] = new ArrayList<>();
            }

            int temp = -1;
            for (int i = 0; i < M; i++) {
                strs = br.readLine().split(" ");
                int start = Integer.parseInt(strs[0]);
                int end = Integer.parseInt(strs[1]);
                int value = Integer.parseInt(strs[2]);
                Token token = new Token(start, end, value);
                Token token1 = new Token(end, start, value);
                line[start].add(token);
                line[end].add(token1);
                if(start == g && end == h
                    || start == h && end == g){
                    temp = value;
                }
            }

            // 1. 시작점 -> 체크포인트 다익스트라 이후 반대체크포인트 -> 목적지 다익스트라


            PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
                return a.value - b.value;
            });

            int[] sDistance = new int[N+1];
            int[] gDistance = new int[N+1];
            int[] hDistance = new int[N+1];



            for(Token t : line[s]){
                pq.add(t);
                sDistance[t.end] = t.value;
            }


            boolean[] visits = new boolean[N+1];
            visits[s] = true;

            while(!pq.isEmpty()){
                Token token = pq.poll();

                if(visits[token.end]){
                    continue;
                }
                visits[token.end] = true;
                for(Token t : line[token.end]){
                    int directRoute = sDistance[t.end];
                    int delayRoute = sDistance[token.end] + t.value;

                    if(directRoute == 0 || directRoute > delayRoute){
                        sDistance[t.end] = delayRoute;
                        pq.add(new Token(-1,t.end,sDistance[t.end]));
                    }
                }
            }
//            System.out.println(Arrays.toString(sDistance));


            visits = new boolean[N+1];
            visits[g] = true;
            for(Token t : line[g]){
                pq.add(t);
                gDistance[t.end] = t.value;
            }

            while(!pq.isEmpty()){
                Token token = pq.poll();

                if(visits[token.end]){
                    continue;
                }
                visits[token.end] = true;
                for(Token t : line[token.end]){
                    int directRoute = gDistance[t.end];
                    int delayRoute = gDistance[token.end] + t.value;

                    if(directRoute == 0 || directRoute > delayRoute){
                        gDistance[t.end] = delayRoute;
                        pq.add(new Token(-1,t.end,gDistance[t.end]));
                    }
                }
            }

//            System.out.println(Arrays.toString(gDistance));


            visits = new boolean[N+1];
            visits[h] = true;
            for(Token t : line[h]){
                pq.add(t);
                hDistance[t.end] = t.value;
            }

            while(!pq.isEmpty()){
                Token token = pq.poll();

                if(visits[token.end]){
                    continue;
                }
                visits[token.end] = true;
                for(Token t : line[token.end]){
                    int directRoute = hDistance[t.end];
                    int delayRoute = hDistance[token.end] + t.value;

                    if(directRoute == 0 || directRoute > delayRoute){
                        hDistance[t.end] = delayRoute;
                        pq.add(new Token(-1,t.end,hDistance[t.end]));
                    }
                }
            }
//            System.out.println(Arrays.toString(hDistance));
            sDistance[s] = 0;
            hDistance[h] = 0;
            gDistance[g] = 0;

//            System.out.println();
            boolean[] answer = new boolean[N+1];
            for(int i=0;i<T;i++){
                int target = Integer.parseInt(br.readLine());
                if(target == g || target == h){
                    if(sDistance[target] == sDistance[g] + temp
                            || sDistance[target] == sDistance[h] + temp){
                        answer[target] = true;
                    }
                }else if(
                        sDistance[target] == sDistance[g] + hDistance[target] + temp
                                || sDistance[target] == sDistance[h] + gDistance[target] + temp
                ){
                    answer[target] = true;
                }
            }
//            System.out.println(Arrays.toString(answer));

            for(int i=1;i<=N;i++){
                if(answer[i]){
                    sb.append(i).append(" ");
                }
            }
            sb.append("\n");

        }
        System.out.println(sb);
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