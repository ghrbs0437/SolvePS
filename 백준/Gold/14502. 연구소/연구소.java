import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int ySize = Integer.parseInt(strs[0]);
        int xSize = Integer.parseInt(strs[1]);

        int[][] map = new int[ySize][xSize];

        ArrayList<Position> positions = new ArrayList<>();
        ArrayList<Position> virus = new ArrayList<>();
        for(int i=0;i<ySize;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<xSize;j++){
                map[i][j] = Integer.parseInt(strs[j]);
                if(map[i][j] == 0){
                    Position pos = new Position(i,j);
                    positions.add(pos);
                }else if(map[i][j] ==2){
                    Position pos = new Position(i,j);
                    virus.add(pos);
                }
            }
        }
        int size = positions.size();
        int answer = 0;
        for(int i=0;i<size-2;i++){
            for(int j=i+1;j<size-1;j++){
                for(int k=j+1;k<size;k++){
                    putBlock(positions.get(k),map);
                    putBlock(positions.get(j),map);
                    putBlock(positions.get(i),map);
                    int c = getSafeArea(virus,map);
                    answer = Math.max(c,answer);
                    removeBlock(positions.get(i),map);
                    removeBlock(positions.get(j),map);
                    removeBlock(positions.get(k),map);
                }
            }
        }
        System.out.println(answer);


    }

    public static int getSafeArea(ArrayList<Position> virus, int[][] map){
        Queue<Integer> xq = new LinkedList<>();
        Queue<Integer> yq = new LinkedList<>();

        for(Position pos : virus){
            yq.add(pos.y);
            xq.add(pos.x);
        }
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

                    if(ny<0||nx<0||nx>= map[0].length||ny>=map.length){
                        continue;
                    }
                    if(map[ny][nx]==0){
                        xq.add(nx);
                        yq.add(ny);
                    }
                }
            }
        }
        int cnt = 0;
        for(int i=0;i< map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(!visits[i][j]&&map[i][j] == 0){
                    cnt++;
                }
            }
        }
        return cnt;
    }


    public static void putBlock(Position position, int[][] map){
        map[position.y][position.x] = 1;
    }

    public static void removeBlock(Position position, int[][] map){
        map[position.y][position.x] = 0;
    }

    public static class Position{
        int y;
        int x;
        Position(int y,int x){
            this.y = y;
            this.x = x;
        }
    }

}