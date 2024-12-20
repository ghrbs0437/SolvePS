import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int ySize = Integer.parseInt(strs[0]);
        int xSize = Integer.parseInt(strs[1]);

        int[][] map = new int[ySize][xSize];
        for(int i=0;i<ySize;i++){
            String str = br.readLine();
            for(int j=0;j<xSize;j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }


        int[][] distance = new int[ySize][xSize];
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                distance[i][j] =30000000;
            }
        }
        distance[0][0] = 1;

        Queue<Token> queue = new ArrayDeque<>();
        Queue<Token> queue2 = new ArrayDeque<>();
        queue.add(new Token(0,0,1));

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                Token t = queue.poll();

                int cy = t.y;
                int cx = t.x;
                int moveCnt = t.moveCnt;

                for (int[] direction : directions) {
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = cy + dy;
                    int nx = cx + dx;

                    if (ny < 0 || nx < 0 || ny >= ySize || nx >= xSize) {
                        continue;
                    }
                    if (map[ny][nx] == 1) {
                        if(distance[ny][nx] <= moveCnt+1){
                            continue;
                        }else{
                            distance[ny][nx] = moveCnt + 1;
                            queue2.add(new Token(ny, nx,  moveCnt + 1));
                        }
                    } else {
                        if(distance[ny][nx]<=moveCnt+1){
                            continue;
                        }else{
                            distance[ny][nx] = moveCnt + 1;
                            queue.add(new Token(ny, nx, moveCnt + 1));
                        }
                    }
                }
            }
        }

        while(!queue2.isEmpty()){
            int size = queue2.size();
            for(int i=0;i<size;i++){
                Token t = queue2.poll();
                int cy = t.y;
                int cx = t.x;
                int moveCnt = t.moveCnt;



                for (int[] direction : directions) {
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = cy + dy;
                    int nx = cx + dx;

                    if (ny < 0 || nx < 0 || ny >= ySize || nx >= xSize) {
                        continue;
                    }

                    if (map[ny][nx] == 0) {
                        if(distance[ny][nx]<=moveCnt+1){
                            continue;
                        }else{
                            distance[ny][nx] = moveCnt + 1;
                            queue2.add(new Token(ny, nx, moveCnt + 1));
                        }
                    }
                }
            }
        }
        int answer = distance[ySize-1][xSize-1];
        if(answer==30000000){
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }

    }

    public static class Token{
        int y;
        int x;
        int moveCnt;

        Token(int y, int x, int moveCnt) {
            this.y = y;
            this.x = x;
            this.moveCnt = moveCnt;
        }
    }

}