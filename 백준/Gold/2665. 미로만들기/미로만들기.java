import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] map = new int[N * N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i * N + j] = str.charAt(j) - '0';
            }
        }

        int[] dist = new int[N * N]; // 벽을 부순 횟수를 저장
        for (int i = 0; i < N * N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.index == N * N - 1) {
                System.out.println(current.cost);
                return;
            }

            if (dist[current.index] < current.cost) {
                continue;
            }

            for (int[] direction : directions) {
                int ny = current.index / N + direction[0];
                int nx = current.index % N + direction[1];

                if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
                    continue;
                }

                int nextIndex = ny * N + nx;
                int nextCost = current.cost + (map[nextIndex] == 0 ? 1 : 0);

                if (nextCost < dist[nextIndex]) {
                    dist[nextIndex] = nextCost;
                    pq.add(new Node(nextIndex, nextCost));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int index;
        int cost;

        Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }
}