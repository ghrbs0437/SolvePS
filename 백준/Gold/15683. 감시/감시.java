import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int ySize = Integer.parseInt(strs[0]);
        int xSize = Integer.parseInt(strs[1]);

        int[][] map = new int[ySize][xSize];
        ArrayList<CCTV> cctvList = new ArrayList<>();
        for(int i=0;i<ySize;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<xSize;j++){
                map[i][j] = Integer.parseInt(strs[j]);
                if(map[i][j]==0 || map[i][j]==6){
                    continue;
                }
                CCTV cctv = new CCTV(i,j,map[i][j]);
                cctvList.add(cctv);
            }
        }

        for(CCTV cctv : cctvList){
            cctv.init();
        }
        getAns(cctvList,map,0);
        System.out.println(MIN);

    }

    static int MIN = Integer.MAX_VALUE;

    public static void getAns(ArrayList<CCTV> cctvList, int[][] map, int depth){
        if(depth == cctvList.size()){
            int cnt = 0;
            for(int i=0;i<map.length;i++){
                for(int j=0;j<map[0].length;j++){
                    if(map[i][j]==0){
                        cnt++;
                    }
                }
            }
            if(MIN>cnt){
                MIN = cnt;
            }
            return;
        }

        CCTV cctv = cctvList.get(depth);



        for(int[][] directions : cctv.directions){
            int[][] newMap = copyMap(map);
            for(int[] direction : directions){
                int ny = cctv.y;
                int nx = cctv.x;
                int dy = direction[0];
                int dx = direction[1];
                while(true) {
                    ny += dy;
                    nx += dx;
                    if (ny < 0 || nx < 0 || ny >= newMap.length || nx >= newMap[0].length) {
                        break;
                    }
                    if (newMap[ny][nx] == 0) {
                        newMap[ny][nx] = -1;
                    } else if (newMap[ny][nx] == 6) {
                        break;
                    } else {
                        continue;
                    }
                }
            }
            getAns(cctvList,newMap,depth+1);
        }

    }

    public static int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};

    public static int[][] copyMap(int[][] map){
        int[][] newMap = new int[map.length][map[0].length];

        for(int i=0;i< map.length;i++){
            for(int j=0;j<map[0].length;j++){
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }

    public static class CCTV{
        int y;
        int x;
        int type;
        ArrayList<int[][]> directions = new ArrayList<>();
        CCTV(int y,int x,int type){
            this.y = y;
            this.x = x;
            this.type = type;
        }

        void init(){
            switch(type){
                case 1:
                    directions.add(new int[][]{{-1,0}});
                    directions.add(new int[][]{{1,0}});
                    directions.add(new int[][]{{0,1}});
                    directions.add(new int[][]{{0,-1}});
                    break;
                case 2:
                    directions.add(new int[][]{{0,-1},{0,1}});
                    directions.add(new int[][]{{-1,0},{1,0}});
                    break;
                case 3:
                    directions.add(new int[][]{{-1,0},{0,1}});
                    directions.add(new int[][]{{-1,0},{0,-1}});
                    directions.add(new int[][]{{1,0},{0,-1}});
                    directions.add(new int[][]{{1,0},{0,1}});
                    break;
                case 4:
                    directions.add(new int[][]{{-1,0},{0,1},{0,-1}});
                    directions.add(new int[][]{{-1,0},{1,0},{0,1}});
                    directions.add(new int[][]{{-1,0},{1,0},{0,-1}});
                    directions.add(new int[][]{{1,0},{0,1},{0,-1}});
                    break;
                case 5:
                    directions.add(new int[][]{{-1,0},{1,0},{0,-1},{0,1}});
                    break;
            }
        }
    }

}