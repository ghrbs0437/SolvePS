import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        char[][] map = new char[N][M];

        int startY = 0;
        int startX = 0;
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] =='I'){
                    startY = i;
                    startX = j;
                }
            }
        }

        boolean[][] visits = new boolean[N][M];
        Queue<Integer> yq = new ArrayDeque<>();
        Queue<Integer> xq = new ArrayDeque<>();
        yq.add(startY);
        xq.add(startX);
        int answer =0 ;
        while(!yq.isEmpty()){
            int size = yq.size();
            for(int i=0;i<size;i++){
                int cy = yq.poll();
                int cx = xq.poll();

                if(visits[cy][cx]){
                    continue;
                }
                if(map[cy][cx] == 'P'){
                    answer++;
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

                    if(map[ny][nx]=='O'){
                        yq.add(ny);
                        xq.add(nx);
                    }else if(map[ny][nx] =='P'){
                        yq.add(ny);
                        xq.add(nx);
                    }
                }
            }
        }
        if(answer==0){
            System.out.println("TT");
        }else{
            System.out.println(answer);
        }




    }
}