import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 도시의 개수
        int N = Integer.parseInt(br.readLine());
        // 버스의 개수
        int M = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];

        // 갈수있는 정점의 모음
        HashMap<Integer,HashSet<Integer>> hmap = new HashMap<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(arr[i][j] == 0){
                    arr[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        /**
         * 정점간에 간선이 한개가 아닐수 있다는점......................................................때문에 고생
         *
         */

        for(int i=0;i<M;i++) {
            String[] strs = br.readLine().split(" ");
            int x = Integer.parseInt(strs[0])-1; // 출발지
            int y = Integer.parseInt(strs[1])-1; // 도착지
            int cost = Integer.parseInt(strs[2]); // 비용
            if(arr[x][y]>cost){
                arr[x][y] = cost;
            }
            if(hmap.get(x)==null){
                hmap.put(x,new HashSet<>());
            }
//            if(hmap.get(y)==null){
//                hmap.put(y,new HashSet<>());
//            }
            hmap.get(x).add(y);
//            hmap.get(y).add(x);
        }



        String[] strs = br.readLine().split(" ");

        int start = Integer.parseInt(strs[0])-1;
        int end = Integer.parseInt(strs[1])-1;
        if(start == end){
            System.out.println(0);
            return;
        }


        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->{
            return a.value-b.value;
        });
        boolean[] visits = new boolean[N];
        visits[start] = true;
        for(Integer next : hmap.get(start)){
            pq.add(new Node(next,arr[start][next]));
        }



        while(!pq.isEmpty()){
            Node node = pq.poll(); // 다음 최소비용 탐색..
            if(visits[node.index]){ // 방문했다면 그건 무시해
                continue;
            }
            // 방문처리
            visits[node.index] = true;
            if(node.index == end){
                System.out.println(arr[start][end]);
                return;
            }
            // 비용갱신
            HashSet<Integer> nextSet = hmap.get(node.index);
            if(nextSet==null){
                continue;
            }
            
            for(Integer tmp : nextSet){
                if(visits[tmp]){
                    continue;
                }
                int delayRoute = arr[start][node.index] + arr[node.index][tmp]; // 전에서 -> 거쳐 오는거리와 직접오는거리 비교
//                System.out.println("   "+ delayRoute);
                int directRoute = arr[start][tmp];

                if(delayRoute <directRoute){
                    arr[start][tmp] = delayRoute;
                     // 비용갱신 되는거면 걘 넣어야지..
                }
                pq.add(new Node(tmp,arr[start][tmp]));
            }
        }


    }
    public static class Node{
        int value;
        int index;
        Node(int index, int value){
            this.index = index;
            this.value = value;
        }

        public String toString(){
            return "["+index+ " :" + value+"]";
        }
    }
}