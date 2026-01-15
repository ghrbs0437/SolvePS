import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Line>[] alists = new ArrayList[N+1];
        for(int i=0;i<=N;i++){
            alists[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            String[] strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0]);
            int end = Integer.parseInt(strs[1]);
            int cost = Integer.parseInt(strs[2]);
            alists[start].add(new Line(start,end,cost));
        }

        String[] strs = br.readLine().split(" ");
        int start = Integer.parseInt(strs[0]);
        int end = Integer.parseInt(strs[1]);

        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
            return a.cost - b.cost;
        });
        boolean[] visits = new boolean[N+1];
        int[] distanceMap = new int[N+1];
        Node[] nodeMap = new Node[N+1];
        for(int i=0;i<=N;i++){
            nodeMap[i] = new Node(0,0);
        }

        for(Line l : alists[start]){
            pq.add(new Token(l.end,l.cost));
            if(distanceMap[l.end] == 0 || distanceMap[l.end] > l.cost){
                distanceMap[l.end] = l.cost;
                nodeMap[l.end] = new Node(start,l.end);
            }
        }
        visits[start] = true;

        while(!pq.isEmpty()){

            Token poll = pq.poll();

            if(visits[poll.end]){
                continue;
            }
            visits[poll.end] = true;

            for(Line l : alists[poll.end]){
                int directRoute = distanceMap[l.end];
                int delayRoute = distanceMap[poll.end] + l.cost;

                if(directRoute == 0 || directRoute > delayRoute){
                    distanceMap[l.end] = delayRoute;
                    pq.add(new Token(l.end,distanceMap[l.end]));
                    nodeMap[l.end] = new Node(poll.end,l.end);
                }
            }
        }

//        System.out.println(Arrays.toString(distanceMap));

        int current = end;
        ArrayList<Integer> ans = new ArrayList<>();
        while(true){
            ans.add(nodeMap[current].index);
//            sb.append(nodeMap[current].index).append(" ");
            if(nodeMap[current].before == start){
                ans.add(start);
                break;
            }
            current = nodeMap[current].before;
        }

        sb.append(distanceMap[end]).append("\n").append(ans.size()).append("\n");
        for(int i=ans.size()-1;i>=0;i--){
            sb.append(ans.get(i)).append(" ");
        }
        System.out.println(sb);


    }

    public static class Token{
        int end;
        int cost;

        Token(int end, int cost){
            this.end = end;
            this.cost = cost;
        }
    }

    public static class Line{
        int start;
        int end;
        int cost;
        Line(int start,int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    public static class Node{
        int before; //
        int index;

        Node(int before, int index){
            this.before = before;
            this.index = index;
        }
    }
}