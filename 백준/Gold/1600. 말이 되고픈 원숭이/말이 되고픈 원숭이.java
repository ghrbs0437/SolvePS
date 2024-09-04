import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {


    public static int[][] knightDirections = {{-2,1},{-2,-1},{2,1},{2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};
    public static int[][] moveDirection = {{-1,0},{1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        String[] strs = br.readLine().split(" ");
        int xSize = Integer.parseInt(strs[0]);
        int ySize = Integer.parseInt(strs[1]);
        int[][] map = new int[ySize][xSize];
        int[][][] costMap = new int[ySize][xSize][k+1];
        for(int i=0;i<ySize;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<xSize;j++){
                map[i][j] = Integer.parseInt(strs[j]);
                if(map[i][j]==1){
                    map[i][j] = -1;
                }
                costMap[i][j][0] = map[i][j];
            }
        }



        Queue<Integer> xq = new ArrayDeque<>();
        Queue<Integer> yq = new ArrayDeque<>();
        Queue<Integer> zq = new ArrayDeque<>();

        xq.add(0);
        yq.add(0);
        zq.add(0);
        boolean[][][] visits = new boolean[ySize][xSize][k+1];
        int depth = 0;
        while(!xq.isEmpty()){
            int size = xq.size();
            for(int i=0;i<size;i++){
                int cx = xq.poll();
                int cy = yq.poll();
                int cz = zq.poll();
                if(visits[cy][cx][cz]){
                    continue;
                }
                if(cy==ySize-1 && cx == xSize-1){
                    System.out.println(depth);
                    return;
                }

                visits[cy][cx][cz] = true;
                costMap[cy][cx][cz] = depth;

                for(int[] direction :moveDirection){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = cy + dy;
                    int nx = cx + dx;
                    if(ny<0||nx<0||ny>=ySize||nx>=xSize){
                        continue;
                    }
                    if(visits[ny][nx][cz]){
                        continue;
                    }
                    if(map[ny][nx]==-1){
                        continue;
                    }
                    costMap[ny][nx][cz] = costMap[cy][cx][cz] + 1;
                    xq.add(nx);
                    yq.add(ny);
                    zq.add(cz);
                }

                for(int[] direction :knightDirections){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = cy + dy;
                    int nx = cx + dx;
                    int nz = cz + 1;
                    if(ny<0||nx<0||ny>=ySize||nx>=xSize||nz>k){
                        continue;
                    }
                    if(visits[ny][nx][nz]){
                        continue;
                    }
                    if(map[ny][nx]==-1){
                        continue;
                    }
                    costMap[ny][nx][nz] = costMap[cy][cx][cz] + 1;
                    xq.add(nx);
                    yq.add(ny);
                    zq.add(nz);
                }

            }
            depth++;
        }
//        System.out.println(costMap[ySize-1][xSize-1][k]);
        System.out.println(-1);
    }
}