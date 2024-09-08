import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;

public class Main {

    public static int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int ySize = Integer.parseInt(strs[0]);
        int xSize = Integer.parseInt(strs[1]);

        char[][] map = new char[ySize][xSize];
        int[][] distanceMap = new int[ySize][xSize];
        ArrayList<Bird> birds = new ArrayList<>();
        Queue<Integer> iceX = new ArrayDeque<>();
        Queue<Integer> iceY = new ArrayDeque<>();
        boolean[][] iceVisit = new boolean[ySize][xSize];
        for(int i=0;i<ySize;i++){
            String str = br.readLine();
            for(int j=0;j<xSize;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'L'){
                    birds.add(new Bird(i,j));
                    iceX.add(j);
                    iceY.add(i);
                }
                if(map[i][j] == '.'){
                    iceX.add(j);
                    iceY.add(i);
                }
            }
        }


        // 여기서 시간초과가 난다...

        int depth = 0 ;
        while(!iceX.isEmpty()){
            int size = iceX.size();
            for(int i=0;i<size;i++){
                int x = iceX.poll();
                int y = iceY.poll();

                if(iceVisit[y][x]){
                    continue;
                }
                iceVisit[y][x] = true;
                distanceMap[y][x] = depth;
                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];

                    int ny = y + dy;
                    int nx = x + dx;
                    if(ny<0||nx<0||ny>=map.length||nx>=map[0].length){
                        continue;
                    }
                    if(iceVisit[ny][nx]){
                        continue;
                    }
                    iceY.add(ny);
                    iceX.add(nx);
                }
            }
            depth++;
        }




//        depth에 대해 이분탐색..
        int time = 0;
        int dTime = depth;
        HashSet<Integer> hset = new HashSet<>();
        Bird bird1 = birds.get(0);
        Bird bird2 = birds.get(1);
        int minTime = Integer.MAX_VALUE;
        while(true){
            if(dTime!=1){
                dTime>>=1;
            }
            if(hset.contains(time)){
                break;
            }
            hset.add(time);

            if(DBFS(distanceMap,time,bird1.y,bird1.x,bird2.y,bird2.x)){
//                System.out.println(time);
                minTime = Math.min(minTime,time);
                time -=dTime;
            }else{
                time +=dTime;
            }
        }
        System.out.println(minTime);
    }

    public static boolean DBFS(int[][] distanceMap,int power, int startY, int startX, int endY,int endX){
        Queue<Integer> qx = new ArrayDeque<>();
        Queue<Integer> qy = new ArrayDeque<>();

        qy.add(startY);
        qx.add(startX);
        boolean[][] visits = new boolean[distanceMap.length][distanceMap[0].length];

        while(!qy.isEmpty()){
            int size = qy.size();
            for(int i=0;i<size;i++) {
                int cx = qx.poll();
                int cy = qy.poll();

                if (visits[cy][cx]) {
                    continue;
                }
                visits[cy][cx] = true;
                if(cy == endY && cx == endX){
                    return true;
                }
                for (int[] direction : directions) {
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = cy + dy;
                    int nx = cx + dx;

                    if (ny < 0 || nx < 0 || ny >= distanceMap.length || nx >= distanceMap[0].length) {
                        continue;
                    }
                    if (visits[ny][nx]) {
                        continue;
                    }
                    if (distanceMap[ny][nx] > power) {
                        continue;
                    }
                    qy.add(ny);
                    qx.add(nx);
                }
            }
        }
        return false;
    }




    public static int BFS(char[][] map, int y, int x){
        Queue<Integer> qx = new ArrayDeque<>();
        Queue<Integer> qy = new ArrayDeque<>();
        qy.add(y);
        qx.add(x);
        int depth = 1;
        boolean[][] visits = new boolean[map.length][map[0].length];
        tc : while(!qy.isEmpty()){
            int size = qy.size();
            for(int i=0;i<size;i++){
                int cx = qx.poll();
                int cy = qy.poll();

                if(visits[cy][cx]){
                    continue;
                }


                visits[cy][cx] = true;

                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = cy + dy;
                    int nx = cx + dx;

                    if(ny<0||nx<0||ny>= map.length||nx>= map[0].length){
                        continue;
                    }
                    if(visits[ny][nx]){
                        continue;
                    }
                    if(map[ny][nx]=='X'){
                        qy.add(ny);
                        qx.add(nx);
                    }
                    if(map[ny][nx] == '.'){
                        break tc;
                    }
                }
            }
            depth++;
        }
        return depth;
    }


    public static class Bird{
        int y;
        int x;
        Bird(int y,int x){
            this.y = y;
            this.x = x;
        }
    }
}