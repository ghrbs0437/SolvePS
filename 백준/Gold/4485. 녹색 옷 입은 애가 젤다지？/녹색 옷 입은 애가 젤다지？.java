import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int vx, vy, rupee;
        public Node(int vx, int vy, int rupee) {
            this.vx = vx;
            this.vy = vy;
            this.rupee = rupee;
        }
    }
    private static int N;
    private static int[][] cave;
    private static int[][] dist;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int count = 0;
        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            StringBuilder sb = new StringBuilder();
            count++;
            sb.append("Problem").append(" ").append(count).append(": ");
            cave = new int[N][N];
            dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
            dist[0][0] = 0;
            dijkstra();
            sb.append(dist[N-1][N-1]);
            System.out.println(sb);
        }
    }

    private static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.rupee - o2.rupee);
        pq.add(new Node(0, 0, cave[0][0]));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            for(int k = 0; k < 4; k++) {
                int nx = cur.vx + dx[k];
                int ny = cur.vy + dy[k];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(cur.rupee + cave[nx][ny] < dist[nx][ny]) {
                    dist[nx][ny] = cur.rupee + cave[nx][ny];
                    pq.offer(new Node(nx, ny, dist[nx][ny]));
                }
            }
        }
    }
}