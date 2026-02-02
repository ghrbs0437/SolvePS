import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static StringBuilder sb = new StringBuilder();

    public static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]); // 맵크기
        int Q = Integer.parseInt(strs[1]); // 시전횟수

        int size = 1<<N;

        int[][] map = new int[size][size];

        for(int i=0;i<size;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<size;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        strs = br.readLine().split(" ");
        int[] magic = new int[Q];
        for(int i=0;i<Q;i++){
            magic[i] = Integer.parseInt(strs[i]);
            FireStorm(map,magic[i]);
        }
        getAns(map);
    }

    public static void getAns(int[][] map){
        int sum = 0;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map.length;j++){
                sum += map[i][j];
            }
        }

        ArrayDeque<Integer> yq = new ArrayDeque<>();
        ArrayDeque<Integer> xq = new ArrayDeque<>();
        boolean[][] visits = new boolean[map.length][map.length];
        int maxCnt = 0;
        for(int i=0;i<map.length;i++) {
            for (int j = 0; j < map.length; j++) {
                if (visits[i][j]) {
                    continue;
                }
                if (map[i][j] != 0) {
                    yq.add(i);
                    xq.add(j);
                    int cnt = 0;
                    while (!yq.isEmpty()) {
                        int size = yq.size();

                        for (int k = 0; k < size; k++) {
                            int cy = yq.poll();
                            int cx = xq.poll();
                            if (visits[cy][cx]) {
                                continue;
                            }
                            cnt++;
                            visits[cy][cx] = true;

                            for (int[] direction : directions) {
                                int dy = direction[0];
                                int dx = direction[1];

                                int ny = cy + dy;
                                int nx = cx + dx;

                                if (ny < 0 || nx < 0 || ny >= map.length || nx >= map.length) {
                                    continue;
                                }
                                if (map[ny][nx] != 0) {
                                    yq.add(ny);
                                    xq.add(nx);
                                }
                            }
                        }
                    }
                    maxCnt = Math.max(maxCnt,cnt);
                }
            }
        }
        System.out.println(sum);
        System.out.println(maxCnt);
    }

    public static void FireStorm(int[][] map, int level){
        // 1. 나누고
        // 2. 돌리고
        // 3. 각 판마다 판단해라.

        int size = 1<<level;
        int blockCnt = map.length / size;

        int[][] temp = new int[map.length][map.length];
        for(int i = 0; i < blockCnt; i++){
            for(int j=0 ; j < blockCnt ; j++){
                int startY = i*size;
                int startX = j*size;
                int endY = (i+1)*size;
                int endX = (j+1)*size;
                rotate(map,temp,startY,startX,endY,endX);
            }
        }
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                map[i][j] = temp[i][j];
            }
        }

        fuze(map);

//        for(int i=0;i<map.length;i++){
//            for(int j=0;j<map[0].length;j++){
//                System.out.print(map[i][j]+" " );
//            }
//            System.out.println();
//        }
//        System.out.println();



    }

    public static void fuze(int[][] map){
        int[][] temp = new int[map.length][map.length];

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map.length;j++){
                if(map[i][j]==0){
                    continue;
                }
                int cnt = 0;
                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];

                    int ny = i + dy;
                    int nx = j + dx;
                    if(ny<0 || nx<0|| ny >= map.length || nx >= map.length){
                        continue;
                    }
                    if(map[ny][nx] != 0){
                        cnt++;
                    }
                }
                if(cnt<3){
                    temp[i][j] = map[i][j] - 1;
                }else{
                    temp[i][j] = map[i][j];
                }
            }
        }

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map.length;j++){
                map[i][j] = temp[i][j];
            }
        }
    }

    public static void rotate(int[][] map, int[][] temp, int startY, int startX, int endY, int endX){
//        System.out.println(startY +" " + startX);
//        for(int i = startY; i < endY ; i++){
//            for(int j = startX ; j < endX ; j++){
//                temp[endX - 1 - j + startY][endY + startX - 1 - i] = map[i][j];
//            }
//        }

        for(int i = startY; i < endY ; i++){
            for(int j = startX ; j < endX ; j++){
                temp[j - startX + startY][endY + startX - 1 - i] = map[i][j];
            }
        }
    }

}