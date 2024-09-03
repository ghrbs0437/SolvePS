import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Solution {


    public static boolean FINISHED = false;

    public static int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for(int tc=1;tc<=testCase;tc++){
            String[] strs = br.readLine().split(" ");
            int ySize = Integer.parseInt(strs[0]);
            int xSize = Integer.parseInt(strs[1]);
            int yPos = Integer.parseInt(strs[2]);
            int xPos = Integer.parseInt(strs[3]);
            int time = Integer.parseInt(strs[4]);

            int[][] map = new int[ySize][xSize];
            for(int i=0;i<ySize;i++){
                strs = br.readLine().split(" ");
                for(int j=0;j<xSize;j++){
                    map[i][j] = Integer.parseInt(strs[j]);
                }
            }
            boolean[][] check = new boolean[map.length][map[0].length];
            DFS(map,check,new boolean[map.length][map[0].length],yPos,xPos,time);

            int cnt=0;
            for(int i=0;i<map.length;i++){
                for(int j=0;j<map[0].length;j++){
                    if(check[i][j]){
                        cnt++;
                    }
                }
            }
            sb.append("#"+tc+" "+cnt+"\n");
        }
        System.out.println(sb);
    }


    public static void DFS(int[][] map,boolean[][] check,boolean[][] visits,int y,int x,int time){
        if(time == 0){
            return;
        }

        check[y][x] = true;


        for(int[] direction : TernalType.of(map[y][x]-1).direction){
            int ny = y + direction[0];
            int nx = x + direction[1];

            if(ny<0||nx<0||ny>=map.length||nx>=map[0].length){
                continue;
            }
            if(map[ny][nx]==0){
                continue;
            }
            if(visits[ny][nx]){
                continue;
            }

            for(int[] ndirection : TernalType.of(map[ny][nx]-1).direction){
                if(ndirection[0] == -direction[0] && ndirection[1] == -direction[1]){
                    visits[ny][nx] = true;
                    DFS(map,check,visits,ny,nx,time-1);
                    visits[ny][nx] = false;
                }
            }
        }
    }

    public enum TernalType{
        A(new int[][]{{-1,0},{1,0},{0,-1},{0,1}}),
        B(new int[][]{{-1,0},{1,0}}),
        C(new int[][]{{0,-1},{0,1}}),
        D(new int[][]{{-1,0},{0,1}}),
        E(new int[][]{{1,0},{0,1}}),
        F(new int[][]{{1,0},{0,-1}}),
        G(new int[][]{{-1,0},{0,-1}});
        int[][] direction;
        TernalType(int[][] direction){
            this.direction = direction;
        }

        public static TernalType of(int ordinal){
            for(TernalType type : TernalType.values()){
                if(type.ordinal() == ordinal){
                    return type;
                }
            }
            return null;
        }
    }
}