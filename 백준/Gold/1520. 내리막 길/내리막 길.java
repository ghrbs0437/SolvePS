import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static int answer = 0;

    public static int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        int[][] map = new int[N][M];

        for(int i=0;i<N;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        int[][] cost = new int[N][M];
        cost[0][0] = 1;
        Token token = new Token(0,0,map[0][0]);

        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
            return b.value - a.value;
        });
        pq.add(token);

        boolean[][] visits = new boolean[N][M];
        while(!pq.isEmpty()){
            Token poll = pq.poll();

            int cy = poll.y;
            int cx = poll.x;
            if(visits[cy][cx]){
                continue;
            }
            visits[cy][cx] = true;
            for(int[] direction : directions){
                int dy = direction[0];
                int dx = direction[1];

                int ny = cy + dy;
                int nx = cx + dx;
                if(ny<0||nx<0||ny>=N||nx>=M){
                    continue;
                }
                if(map[ny][nx] >= map[cy][cx]){
                    continue;
                }
                cost[ny][nx] += cost[cy][cx];
                Token next = new Token(ny,nx,map[ny][nx]);
                pq.add(next);
            }
        }

        System.out.println(cost[N-1][M-1]);
    }

    public static class Token{
        int y;
        int x;
        int value;

        Token(int y, int x,int value){
            this.y = y;
            this.x = x;
            this.value = value;
        }
    }
}