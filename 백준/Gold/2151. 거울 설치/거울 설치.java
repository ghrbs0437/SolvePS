import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};

    static int ANSWER = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int size = Integer.parseInt(br.readLine());

        char[][] map = new char[size][size];


        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;

        for(int i=0;i<size;i++){
            String str = br.readLine();
            for(int j=0;j<size;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] =='#'){
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

        for(int[] direction : directions){
            int dy = direction[0];
            int dx = direction[1];
            DFS(map,startY,startX,endY,endX,dy,dx,0);
        }
        System.out.println(ANSWER);

    }

    public static void DFS(char[][] map ,int y, int x,int endY,int endX, int dy, int dx, int mirror) {
        if(mirror>ANSWER){
            return;
        }
        if(endY == y && endX == x){
            ANSWER = mirror;
            return;
        }


        int cy = y;
        int cx = x;

        int ny = dy + cy;
        int nx = dx + cx;

        if (ny < 0 || nx < 0 || ny >= map.length || nx >= map.length) {
            return;
        }

        if (map[ny][nx] == '!') {
            map[ny][nx] = '/';
            // x y 반전 -1
            DFS(map,ny,nx,endY,endX,-dx,-dy,mirror+1);
            map[ny][nx] = '|';
            // xy 반전
            DFS(map,ny,nx,endY,endX,dx,dy,mirror+1);
            map[ny][nx] = '.';

            DFS(map,ny,nx,endY,endX,dy,dx,mirror);
            map[ny][nx] = '!';
        }else if(map[ny][nx] == '/'){
            DFS(map,ny,nx,endY,endX,-dx,-dy,mirror);
        }else if(map[ny][nx] == '|'){
            DFS(map,ny,nx,endY,endX,dx,dy,mirror);
        }else if(map[ny][nx] =='.'|| map[ny][nx] =='#'){
            DFS(map,ny,nx,endY,endX,dy,dx,mirror);
        }

    }
}