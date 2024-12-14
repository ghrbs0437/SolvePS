import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            ANSWER = Integer.MAX_VALUE;
            String[] strs = br.readLine().split(" ");
            int xSize = Integer.parseInt(strs[0]);
            int ySize = Integer.parseInt(strs[1]);
            if(xSize==0 && ySize ==0){
                break;
            }

            char[][] map = new char[ySize][xSize];
            ArrayList<Position> dirtyList = new ArrayList<>();
            dirtyList.add(new Position(0,0));
            int dirtySize = 0;
            for(int i=0;i<ySize;i++) {
                String str = br.readLine();
                for (int j = 0; j <xSize; j++) {
                    map[i][j] = str.charAt(j);
                    if(map[i][j] == 'o'){
                        dirtyList.get(0).y = i;
                        dirtyList.get(0).x = j;
                        dirtySize++;
                    }else if(map[i][j] =='*'){
                        dirtyList.add(new Position(i, j));
                        dirtySize++;
                    }
                }
            }

            int[][] distance = new int[dirtySize][dirtySize];
            for(int[] dis : distance){
                Arrays.fill(dis,Integer.MAX_VALUE);
            }
            for(int i=0;i<dirtySize;i++){

                boolean[][] visited = new boolean[ySize][xSize];
                Position position = dirtyList.get(i);
                Queue<Integer> xq = new ArrayDeque<>();
                Queue<Integer> yq = new ArrayDeque<>();
                yq.add(position.y);
                xq.add(position.x);
                int depth = 0 ;
                while(!yq.isEmpty()){
                    int size = yq.size();
                    depth ++;
                    for(int j=0;j<size;j++){
                        int cy = yq.poll();
                        int cx = xq.poll();
                        if(visited[cy][cx]){
                            continue;
                        }
                        visited[cy][cx] = true;
                        for(int[] direction : directions){
                            int dy = direction[0];
                            int dx = direction[1];
                            int ny = cy + dy;
                            int nx = cx + dx;
                            if(ny<0||nx<0||ny>=ySize||nx>=xSize){
                                continue;
                            }
                            if(map[ny][nx]=='x'){
                                continue;
                            }
                            if(visited[ny][nx]){
                                continue;
                            }
                            if(map[ny][nx] == '*' || map[ny][nx] =='o'){
                                // 거리 측정해서 거리값에 넣장...
                                for(int k = 0; k<dirtySize;k++){
                                    if(dirtyList.get(k).y == ny && dirtyList.get(k).x == nx){
                                        distance[i][k] = Math.min(depth,distance[i][k]);
                                    }
                                }
                            }
                            yq.add(ny);
                            xq.add(nx);
                        }
                    }
                }
                // 여기까지하면 각 점까지의 모든 거리를 측정했다...
            }

            boolean[] visits = new boolean[dirtySize];
            visits[0] = true;
            getAns(distance,visits,0,1,0);
            if(ANSWER == Integer.MAX_VALUE){
                sb.append(-1).append("\n");
            }else{
                sb.append(ANSWER).append("\n");
            }

        }

        System.out.println(sb);
    }


    static int ANSWER = Integer.MAX_VALUE;
    public static void getAns(int[][] distance,boolean[] visits, int current,int depth, int value){
        if(value>=ANSWER){
            return;
        }
        if(depth >= distance.length){
            ANSWER = Math.min(value,ANSWER);
            return;
        }

        for(int i=0;i<distance.length;i++){
            if(i==current){
                continue;
            }

            if(distance[current][i] != Integer.MAX_VALUE && !visits[i]){
                visits[i] = true;
                getAns(distance,visits,i,depth+1,value+distance[current][i]);
                visits[i] = false;
            }
        }
    }

    public static class Position{
        int y;
        int x;
        Position(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}