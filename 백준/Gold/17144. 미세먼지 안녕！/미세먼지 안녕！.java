import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int ySize =Integer.parseInt(strs[0]);
        int xSize =Integer.parseInt(strs[1]);
        int time =Integer.parseInt(strs[2]);


        GameMap gm = new GameMap();
        int[][] map = new int[ySize][xSize];
        for(int i=0;i<ySize;i++) {
            strs = br.readLine().split(" ");
            for(int j=0;j<xSize;j++) {
                map[i][j] = Integer.parseInt(strs[j]);
                if(map[i][j] ==-1){
                    gm.airConditionerY.add(i);
                }
            }
        }
        gm.setMap(map);


        for(int i=0;i<time;i++){
            gm.spread();
            gm.circulate();
        }
        int cnt = 0;
        for(int i=0;i<ySize;i++){
            for(int j=0;j<xSize;j++) {
//                System.out.print(gm.map[i][j]+" ");
                cnt+=map[i][j];
            }
//            System.out.println("");
        }

        System.out.println(cnt+2);
    }

    public static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};


    public static class GameMap{
        int[][] map;
        int[][] _dmap;
        ArrayList<Integer> airConditionerY = new ArrayList<Integer>(2);
        // 역방향
        static int[][] upperDirections = {{-1,0},{0,1},{1,0},{0,-1}};
        static int[][] lowerDirections = {{1,0},{0,1},{-1,0},{0,-1}};

        public void setMap(int[][] map){
            this.map = map;
            _dmap = new int[map.length][map[0].length];
        }

        public void circulate(){
            int upperX = 0;
            int upperY = airConditionerY.get(0);
            int upperDirectionIndex = 0;
            int lowerX = 0;
            int lowerY = airConditionerY.get(1);
            int lowerDirectionIndex = 0;

            int cy = upperY;
            int cx = upperX;
            while(upperDirectionIndex < upperDirections.length){
                int[] upperDirection = upperDirections[upperDirectionIndex];
                int ny = cy + upperDirection[0];
                int nx = cx + upperDirection[1];
                if(ny<0 || nx<0|| ny>=map.length || nx>=map[0].length || ny>upperY){
                    upperDirectionIndex++;
                    continue;
                }
//                System.out.println(ny+" "+nx+" ");
                if(map[ny][nx]==-1){
                    map[cy][cx] = 0;
                }else{
                    if(map[cy][cx]!=-1){
                        map[cy][cx] = map[ny][nx];
                    }
                }
                cy = ny;
                cx = nx;
            }


            cy = lowerY;
            cx = lowerX;
            while(lowerDirectionIndex < lowerDirections.length){
                int[] lowerDirection = lowerDirections[lowerDirectionIndex];
                int ny = cy + lowerDirection[0];
                int nx = cx + lowerDirection[1];

                if(ny<0 || nx<0|| ny>=map.length || nx>=map[0].length || ny<lowerY){
                    lowerDirectionIndex++;
                    continue;
                }
//                System.out.println(ny+" "+nx+" ");
                if(map[ny][nx]==-1){
                    map[cy][cx] = 0;
                }else{
                    if(map[cy][cx]!=-1){
                        map[cy][cx] = map[ny][nx];
                    }
                }
                cy = ny;
                cx = nx;
            }

        }

        public void spread(){
            for(int i=0;i<map.length;i++){
                for(int j=0;j<map[0].length;j++){
                    _dmap[i][j]=0;
                }
            }
            for(int i=0;i<map.length;i++){
                for(int j=0;j<map[i].length;j++){
                    if(map[i][j]>0){
                        int cy = i;
                        int cx = j;
                        int nextValue = map[i][j];
                        for(int[] direction : directions){
                            int dy = direction[0];
                            int dx = direction[1];
                            int ny = cy + dy;
                            int nx = cx + dx;
                            if(ny<0 || nx<0||ny>=map.length || nx>=map[0].length){
                                continue;
                            }
                            if(map[ny][nx]==-1){ // 공기청정기
                                continue;
                            }
                            _dmap[ny][nx] += map[i][j]/5;
                            nextValue-= map[i][j]/5;
                        }
                        map[i][j] = nextValue;
                    }
                }
            }

            for(int i=0;i<map.length;i++){
                for(int j=0;j<map[0].length;j++){
                    map[i][j] += _dmap[i][j];
                }
            }
        }

    }
}