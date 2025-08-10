import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        char[][] maps = new char[N][M];


        Position start = null;
        Position end = null;
        ArrayList<Position> trees = new ArrayList<>();
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                maps[i][j] = str.charAt(j);
                if(maps[i][j] == 'V'){
                    start = new Position(i,j);
                }else if(maps[i][j] =='J'){
                    end = new Position(i,j);
                }else if(maps[i][j] =='+'){
                    Position tree = new Position(i,j);
                    trees.add(tree);
                }
            }
        }

        Queue<Integer> yq = new ArrayDeque<>();
        Queue<Integer> xq = new ArrayDeque<>();


        boolean[][] visits = new boolean[N][M];
        int[][] distance = new int[N][M];
        for(Position tree : trees){
            yq.add(tree.y);
            xq.add(tree.x);
        }

        int cnt = 0;
        while(!xq.isEmpty()) {
            int size = xq.size();
            for (int i = 0; i < size; i++) {
                int cy = yq.poll();
                int cx = xq.poll();

                if (visits[cy][cx]) {
                    continue;
                }
                visits[cy][cx] = true;
                distance[cy][cx] = cnt;

                for (int[] direction : directions) {
                    int dy = direction[0];
                    int dx = direction[1];

                    int ny = cy + dy;
                    int nx = cx + dx;

                    if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
                        continue;
                    }
                    yq.add(ny);
                    xq.add(nx);
                }
            }
            cnt++;
        }

        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
            return - (a.value - b.value);
        });

        pq.add(new Token(start.y, start.x, distance[start.y][start.x]));
        visits = new boolean[N][M];

        while(!pq.isEmpty()){
            Token token = pq.poll();
            int cy = token.y;
            int cx = token.x;
            int cv = token.value;
            if(cy == end.y && cx == end.x){
                System.out.println(cv);
                break;
            }

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
                int nv = Math.min(cv, distance[ny][nx]);
                Token nt = new Token(ny,nx,nv);
                pq.add(nt);
            }

        }

    }

    public static class Token{
        int y;
        int x;
        int value;

        Token(int y,int x,int value){
            this.y = y;
            this.x = x;
            this.value = value;
        }
    }

    public static class Position{
        int y;
        int x;

        Position(int y, int x){
            this.y = y;
            this.x = x;
        }

    }
}