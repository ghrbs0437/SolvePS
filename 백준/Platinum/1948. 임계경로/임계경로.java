import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static int ANSWER = 0 ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); // 도시의 개수
        int M = Integer.parseInt(br.readLine()); // 도로의 개수

        Node[] nodes = new Node[N];

        HashMap<Integer, HashMap<Integer,Integer>> startToEndCost = new HashMap<>();

        for(int i=0;i<N;i++){
            startToEndCost.put(i,new HashMap<>());
            nodes[i] = new Node(i);
        }

        for(int i=0;i<M;i++){
            String[] strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0])-1;
            int end = Integer.parseInt(strs[1])-1;
            int value = Integer.parseInt(strs[2]);

            startToEndCost.get(start).put(end,value);

        }

        String[] strs = br.readLine().split(" ");
        int start = Integer.parseInt(strs[0])-1;
        int end = Integer.parseInt(strs[1])-1;


        DFS(nodes,start,end,startToEndCost);
        reverseDFS(nodes,end,start,new boolean[N]);

        sb.append(nodes[end].maxCost).append("\n").append(ANSWER);
        System.out.println(sb);

    }

    public static void reverseDFS(Node[] nodes, int current, int start, boolean[] visits){

        if(current == start){
            return ;
        }
        if(visits[current]){
            return;
        }
        visits[current] = true;
        for( int key : nodes[current].past){
            ANSWER++;
            reverseDFS(nodes,key,start,visits);
        }

    }


    // 경우에 따라 중복된 노드를 탐색할 수 있음이 문제가 된다..
    public static void DFS(Node[] nodes, int current, int end, HashMap<Integer, HashMap<Integer,Integer>> startToEndCost){

        if(current == end){
            return;
        }

        HashMap<Integer, Integer> hashMap = startToEndCost.get(current);
        int currentMaxCost = nodes[current].maxCost;

        for(int key : hashMap.keySet()){
            int cost = hashMap.get(key);

            if(currentMaxCost + cost > nodes[key].maxCost){ // 최대비용이 될 수 있는 가능성이 있는 노드들만 보자..
                nodes[key].past = new HashSet<>();
                nodes[key].maxCost = currentMaxCost + cost;
                nodes[key].past.add(current);
                DFS(nodes,key,end,startToEndCost);
            }else if (currentMaxCost + cost == nodes[key].maxCost){
                nodes[key].past.add(current);
            }else{
                continue;
            }
        }

    }

    public static class Node{

        int index;

        int maxCost;
        HashSet<Integer> past = new HashSet<>();

        Node(int index){
            this.index = index;
        }

    }
}