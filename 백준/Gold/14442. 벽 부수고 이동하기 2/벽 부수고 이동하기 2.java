import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static int [][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);
        int K = Integer.parseInt(strs[2]);

        int[][] map = new int[N][M];
        boolean[][][] visits = new boolean[N][M][K+1];
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = str.charAt(j) -'0';
            }
        }

        Queue<Integer> yq = new ArrayDeque<>();
        Queue<Integer> xq = new ArrayDeque<>();
        Queue<Integer> zq = new ArrayDeque<>();
        yq.add(0);
        xq.add(0);
        zq.add(K);


        int move = 0;
        while(!xq.isEmpty()){
            move++;
            int size = xq.size();

            for(int i=0;i<size;i++){

                Integer cy = yq.poll();
                Integer cx = xq.poll();
                Integer cz = zq.poll();

                if(visits[cy][cx][cz]){
                    continue;
                }
                visits[cy][cx][cz] = true;

                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = cy + dy;
                    int nx = cx + dx;
                    if(ny<0||nx<0||ny>=N||nx>=M){
                        continue;
                    }
                    if(ny == N-1 && nx == M-1){
                        System.out.println(move+1);
                        return;
                    }

                    if(map[ny][nx]==0){
                        xq.add(nx);
                        yq.add(ny);
                        zq.add(cz);
                    }else if(map[ny][nx]==1&&cz>0){
                        xq.add(nx);
                        yq.add(ny);
                        zq.add(cz-1);
                    }
                }

            }
        }
        if(N==1 && M==1){
            System.out.println(1);
        }else{
            System.out.println(-1);
        }


    }
}
