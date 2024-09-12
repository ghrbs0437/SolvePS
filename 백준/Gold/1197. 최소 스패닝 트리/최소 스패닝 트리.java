import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int V = Integer.parseInt(strs[0]);
        int E = Integer.parseInt(strs[1]);
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->{
            return a.weight-b.weight;
        });

        int[] parents = init(V);
        for(int i=0;i<E;i++){
            strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0]);
            int end = Integer.parseInt(strs[1]);
            int weight = Integer.parseInt(strs[2]);
            pq.add(new Node(start,end,weight));
        }

        int cnt = 0;
        int value = 0;
        while(true){
            if(cnt == V-1){
                break;
            }
            Node node = pq.poll();
            int start = node.start;
            int end = node.end;
            if(union(start,end,parents)){
                int weight = node.weight;
                value+=weight;
                cnt++;
            }
        }
        System.out.println(value);

    }

    public static int[] init(int Size){
        int[] parents = new int[Size+1];
        for(int i=0;i<=Size;i++){
            parents[i] = i;
        }
        return parents;
    }

    public static int findRoot(int value, int[] parents){
        if(value == parents[value]){
            return value;
        }
        return parents[value] = findRoot(parents[value],parents);
    }


    public static boolean union(int val1,int val2, int[] parents){
        int rootA = findRoot(val1,parents);
        int rootB = findRoot(val2,parents);
        if(rootA == rootB){
            return false;
        }
        parents[rootB] = rootA;
        return true;
    }

    public static class Node{
        int start;
        int end;
        int weight;

        Node(int start,int end,int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}