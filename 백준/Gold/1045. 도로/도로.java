import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        char[][] map = new char[N][N];

        HashMap<Integer,ArrayList<Node>> hmap = new HashMap<>();

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->{
            if(a.start == b.start){
                return a.end-b.end;
            }
            return a.start-b.start;
        });

        for(int i=0;i<N;i++){
            hmap.put(i,new ArrayList<>());
        }


        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=i;j<N;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'Y'){
                    Node node = new Node(i,j);
                    pq.add(node);
                    hmap.get(i).add(node);
                }
            }
        }

        int[] parents = init(N);
        PriorityQueue<Node> lefts = new PriorityQueue<>((a,b)->{
            if(a.start == b.start){
                return a.end - b.end;
            }
            return a.start - b.start;
        });

        PriorityQueue<Node> answers = new PriorityQueue<>((a,b)->{
            if(a.start == b.start){
                return a.end - b.end;
            }
            return a.start - b.start;
        });


        int cnt = 0;
        while(!pq.isEmpty()){
            if(cnt == N-1){
                break;
            }

            Node node = pq.poll();
            boolean union = union(parents, node.start, node.end);
            if(union){
                answers.add(node);
                cnt++;
            }else{
                lefts.add(node);
            }
        }

        if(cnt != N-1){
            System.out.println(-1);
            return;
        }

        lefts.addAll(pq);

        int size = M- answers.size();
        for(int i=0;i<size;i++){
            if(lefts.isEmpty()){
                System.out.println(-1);
                return;
            }
            answers.add(lefts.poll());
        }

        int[] answer = new int[N];
        for(Node node : answers){
            answer[node.end]++;
            answer[node.start]++;
        }

        for(int i=0;i<N;i++){
            sb.append(answer[i]+" ");
        }
        System.out.println(sb);




    }

    public static int[] init(int size){
        int[] parent = new int[size];
        for(int i=0;i<size;i++){
            parent[i] = i;
        }
        return parent;
    }

    public static int findRoot(int[] parents, int a){
        if(parents[a] == a){
            return a;
        }

        return parents[a] = findRoot(parents,parents[a]);
    }

    public static boolean union(int[] parents, int a, int b){
        int rootA = findRoot(parents,a);
        int rootB = findRoot(parents,b);

        if(rootA == rootB){
            return false;
        }

        parents[rootB] = rootA;
        return true;
    }

    public static class Node{
        int start;
        int end;

        Node(int start,int end){
            this.start = start;
            this.end = end;
        }
    }
}
