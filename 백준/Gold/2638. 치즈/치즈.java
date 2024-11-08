import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int ySize = Integer.parseInt(strs[0]);
        int xSize = Integer.parseInt(strs[1]);

        int[][] map = new int[ySize][xSize];
        int cnt = 0;
        for(int i=0;i<ySize;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<xSize;j++){
                map[i][j] = Integer.parseInt(strs[j]);
                if(map[i][j]==1){
                    cnt++;
                }
            }
        }
        // 바깥치즈에서 출발해서 인접영역
        int step = 0 ;
        while(cnt>0){
            step++;
            boolean[][] visits = checkOuterSpace(map);
            cnt -= removeCheeze(map,visits);
        }
        System.out.println(step);

    }

    public static int removeCheeze(int[][] map, boolean[][] visits){
        int remove = 0;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j] == 1){
                    int cnt = 0;
                    for(int[] direction : directions){
                        int dy = direction[0];
                        int dx = direction[1];
                        int ny = i + dy;
                        int nx = j + dx;
                        if(ny<0||nx<0||ny>= map.length||nx>=map[0].length){
                            continue;
                        }
                        if(visits[ny][nx]){
                            cnt++;
                        }
                    }
                    if(cnt>=2){
                        map[i][j] = 0;
                        remove++;
                    }
                }
            }
        }
        return remove;
    }


    public static boolean[][] checkOuterSpace(int[][] map){
        Queue<Integer> yq = new ArrayDeque<>();
        Queue<Integer> xq = new ArrayDeque<>();
        yq.add(0);
        xq.add(0);

        boolean[][] visits = new boolean[map.length][map[0].length];
        while(!xq.isEmpty()){
            int size = xq.size();
            for(int i=0;i<size;i++){
                int cy = yq.poll();
                int cx = xq.poll();

                if(visits[cy][cx]){
                    continue;
                }
                visits[cy][cx] = true;
                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = cy+dy;
                    int nx = cx+dx;



                    if(ny<0||nx<0||ny>= map.length||nx>=map[0].length){
                        continue;
                    }
                    if(map[ny][nx]==0){
                        xq.add(nx);
                        yq.add(ny);
                    }
                }

            }

        }
        return visits;
    }

}