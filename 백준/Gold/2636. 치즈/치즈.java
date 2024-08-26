import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {


    static int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] strs = br.readLine().split(" ");

        int ySize = Integer.parseInt(strs[0]);
        int xSize = Integer.parseInt(strs[1]);


        int[][] map = new int[ySize][xSize];

        int remain = 0;
        for(int i=0;i<ySize;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<xSize;j++){
                map[i][j] = Integer.parseInt(strs[j]);
                if(map[i][j]==1){
                    remain++;
                }
            }
        }


        int depth = 0;
        int answer = 0;
        while(true){
            depth++;
            int delCnt = deleteCheese(map);
            if(remain-delCnt==0){
                answer = remain;
                break;
            }else{
                remain-=delCnt;
            }
        }
        sb.append(depth+"\n"+answer);
        System.out.println(sb);
    }

    public static void printMap(int[][] map){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                System.out.print(map[i][j]+ " " );
            }
            System.out.println();
        }

    }

    public static int deleteCheese(int[][] map){
        Queue<Integer> xq = new ArrayDeque<>();
        Queue<Integer> yq = new ArrayDeque<>();
        xq.add(0);
        yq.add(0);
        int cnt = 0 ;
        boolean[][] visits = new boolean[map.length][map[0].length];
        while(!xq.isEmpty()){
            int size = xq.size();
            for(int i=0;i<size;i++){
                int cx = xq.poll();
                int cy = yq.poll();
                if(visits[cy][cx]){
                    continue;
                }
                visits[cy][cx] = true;
                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = cy + dy;
                    int nx = cx + dx;
                    if(ny<0||nx<0||ny>= map.length||nx>=map[0].length){
                        continue;
                    }
                    if(map[ny][nx]==1){
                        cnt++;
                        map[ny][nx] = 0;
                        visits[ny][nx] = true;
                    }else{
                        xq.add(nx);
                        yq.add(ny);
                    }
                }
            }
        }
        return cnt;
    }
}