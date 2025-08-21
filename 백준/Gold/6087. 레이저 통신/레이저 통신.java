import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static int ANSWER = 0;

    static int [][] directions = {{-1,0},{1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        int W = Integer.parseInt(strs[0]);
        int H = Integer.parseInt(strs[1]);
        char[][] map = new char[H][W];
        int[][][] dist = new int[H][W][4];
        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;
        for(int i=0;i<H;i++){
            String str = br.readLine();
            for(int j=0;j<W;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'C'){
                    if(startX == -1){
                        startY = i;
                        startX = j;
                    }else{
                        endY = i;
                        endX = j;
                    }
                }
            }
        }

        Queue<Integer> yq = new ArrayDeque<>();
        Queue<Integer> xq = new ArrayDeque<>();
        Queue<Integer> dq = new ArrayDeque<>();


        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                for(int k=0;k<4;k++){
                    dist[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        for(int i=0;i<4;i++){
            yq.add(startY);
            xq.add(startX);
            dq.add(i);
            dist[startY][startX][i] = 0;
        }

        wh: while(!yq.isEmpty()){
            int size = yq.size();
            for(int i=0;i<size;i++){
                int cy = yq.poll();
                int cx = xq.poll();
                int cd = dq.poll();

                for(int j=0;j<4;j++){

                    int dy = directions[j][0];
                    int dx = directions[j][1];
                    int ny = cy + dy;
                    int nx = cx + dx;

                    if(ny<0||nx<0||ny>=map.length||nx>=map[0].length){
                        continue;
                    }

                    if(map[ny][nx]=='*'){
                        continue;
                    }

                    if(cd==j){
                        if(dist[ny][nx][j] > dist[cy][cx][cd]){
                            dist[ny][nx][j] = dist[cy][cx][cd];
                            yq.add(ny);
                            xq.add(nx);
                            dq.add(j);
                        }
                    }else{
                        if(dist[ny][nx][j] > dist[cy][cx][cd]+1){
                            dist[ny][nx][j] = dist[cy][cx][cd]+1;
                            yq.add(ny);
                            xq.add(nx);
                            dq.add(j);
                        }
                    }
                }

            }
        }
        int min = Integer.MAX_VALUE;
        for(int i=0;i<4;i++){
            min = Math.min(min,dist[endY][endX][i]);
        }
        System.out.println(min);

    }

    public static class Token{
        int y;
        int x;
        int cnt;
    }
}