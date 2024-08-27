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
        int range = Integer.parseInt(strs[2])-1;

        Map map = new Map(ySize,xSize);
        map.range = range;

        int playTime = 0;
        for(int i=0;i<ySize;i++) {
            strs = br.readLine().split(" ");
            for(int j=0;j<xSize;j++) {
                map.map[i][j] = Integer.parseInt(strs[j]);
                if(map.map[i][j]==1){
                    if(playTime<ySize-i){
                        playTime = ySize-i;
                    }
                }
            }
        }
        map.playTime = playTime;

        map.play();
        System.out.println(map.MAX);

    }

    public static class Map{
        final int[][] map;
        final int ySize;
        final int xSize;
        int playTime;

        int range;
        int MAX = 0;
        ArrayList<int[]> archerCase = new ArrayList<>();
        Map(int ySize,int xSize){
            this.map = new int[ySize][xSize];
            this.ySize = ySize;
            this.xSize = xSize;
            permutation(0,0,new boolean[xSize]);
        }

        private void permutation(int selectCnt,int index,boolean[] select){

            if(selectCnt==3){
                int[] archersCase = new int[3];
                int selectNum = 0;
                for(int i=0;i<select.length;i++){
                    if(select[i]){
                        archersCase[selectNum] = i;
                        selectNum++;
                    }
                }
                archerCase.add(archersCase);
                return;
            }
            if(index == select.length){
                return;
            }
            select[index] = true;
            permutation(selectCnt+1,index+1,select);
            select[index] = false;
            permutation(selectCnt,index+1,select);

        }



        void play() {
            for(int i=0;i<archerCase.size();i++){
                int[][] gameMap = cloneMap(map);
                int[] archersXPos = archerCase.get(i);
                ArrayList<Archer> archers = new ArrayList<>();
                for(int xPos : archersXPos){
                    Archer archer = new Archer();
                    archer.xPos = xPos;
                    archer.yPos = ySize-1;
                    archer.range = this.range;
                    archers.add(archer);
                }

                int killed = 0;
                for(int j=playTime;j>=0;j--){
                    for(Archer archer : archers){
                        archer.shoot(gameMap);
                    }
                    killed+= moveMonsterAndGetKilled(gameMap);
                }
                if(MAX<killed){
                    MAX = killed;
                }
            }
        }
        private int moveMonsterAndGetKilled(int[][] map){
            int cnt = 0 ;
            for(int i=ySize-1;i>=1;i--){
                for(int j=0;j<xSize;j++){
                    if(map[i][j] == -1){
                        cnt++;
                        map[i][j] = 0;
                    }
                    if(map[i-1][j]== -1){
                        cnt++;
                        map[i-1][j] = 0;
                    }

                    map[i][j] = map[i-1][j];
                    map[i-1][j] = 0;
                }
            }
            return cnt;
        }

        int[][] cloneMap(int[][] map){
            int[][] newMap = new int[ySize][xSize];
            for(int i=0;i<ySize;i++){
                for(int j=0;j<xSize;j++){
                    newMap[i][j] = map[i][j];
                }
            }
            return newMap;
        }
    }

    public static class Archer{
        int xPos;
        int yPos;
        int range;
        public void shoot(int[][] map) {
            for(int i=0;i<=range;i++) { // 탐색 range..
                for(int x=xPos-i;x<=xPos+i;x++) {
                    int y = yPos - i + Math.abs(x-xPos);
                    if(x<0 ||x>=map[0].length||y<0||y>=map.length){
                        continue;
                    }
                    if(map[y][x]!=0) {
                        map[y][x]=-1;
                        return;
                    }
//                    x 가 xPos - i  에서 xPos+ i 까지 가능하다..i;
//                    xPos-i 에서 1을 줄일때마다 y값을 1얻을 수 있다..
                }
            }
        }
    }
}