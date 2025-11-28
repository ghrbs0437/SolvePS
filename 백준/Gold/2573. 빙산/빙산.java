import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

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



        int time = 0;
        tt : while(true){

            boolean done = true;

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(map[i][j] !=0){
                        done = false;
                    }
                }
            }
            if(done){
                System.out.println(0);
                return;
            }


            boolean flag = false;
            boolean[][] visits = new boolean[N][M];

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(map[i][j]!=0){
                        if(visits[i][j]){
                            continue;
                        }
                        if(flag){
                            break tt;
                        }

                        Queue<Integer> yq = new ArrayDeque<>();
                        Queue<Integer> xq = new ArrayDeque<>();
                        yq.add(i);
                        xq.add(j);

                        while(!yq.isEmpty()){
                            int cy = yq.poll();
                            int cx = xq.poll();

                            if(visits[cy][cx]){
                                continue;
                            }
                            visits[cy][cx] = true;

                            for(int[] direction : directions){
                                int dy = direction[0];
                                int dx = direction[1];
                                int ny = cy + dy;
                                int nx = cx + dx;

                                if(ny< 0||nx<0|| ny>=N || nx>=M){
                                    continue;
                                }
                                if(map[ny][nx]!=0 && !visits[ny][nx]){
                                    yq.add(ny);
                                    xq.add(nx);
                                }
                            }

                        }
                        flag = true;
                    }
                }
            }

            time++;
            int[][] mask = new int[N][M];
            int[][] next = new int[N][M];
            for(int i=0;i<N;i++) {
                for (int j = 0; j < M; j++) {
                    for (int[] direction : directions) {
                        int  ny = i + direction[0];
                        int nx = j + direction[1];
                        if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
                            continue;
                        }
                        if (map[ny][nx] == 0) {
                            mask[i][j]++;
                        }
                    }
                    next[i][j] = Math.max(map[i][j]-mask[i][j],0);
                }
            }
            map = next;

        }
        System.out.println(time);




    }
}
