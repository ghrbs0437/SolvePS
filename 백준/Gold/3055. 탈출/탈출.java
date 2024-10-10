import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int ySize = Integer.parseInt(strs[0]);
        int xSize = Integer.parseInt(strs[1]);

        char[][] map = new char[ySize][xSize];
        Queue<Position> waterQ = new LinkedList<>();
        Queue<Position> moveQ = new LinkedList<>();

        for(int i=0;i<ySize;i++){
            String str = br.readLine();
            for(int j=0;j<xSize;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] =='*'){
                    waterQ.add(new Position(i,j));
                }else if(map[i][j] == 'S'){
                    moveQ.add(new Position(i,j));
                }
            }
        }

        int time = 0;
        boolean[][] dochiVisit = new boolean[ySize][xSize];
        while(!moveQ.isEmpty()){
            time++;
            int waterSize = waterQ.size();
            for(int i=0;i<waterSize;i++){
                Position pos = waterQ.poll();
                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = pos.y + dy;
                    int nx = pos.x + dx;
                    if(ny<0||nx<0||ny>=ySize||nx>=xSize){
                        continue;
                    }
                    if(map[ny][nx]=='.'||map[ny][nx]=='S'){
                        map[ny][nx] = '*';
                        waterQ.add(new Position(ny,nx));
                    }
                }
            }

            int dochiSize = moveQ.size();
            for(int i=0;i<dochiSize;i++){
                Position pos = moveQ.poll();
                if(dochiVisit[pos.y][pos.x]){
                    continue;
                }
                dochiVisit[pos.y][pos.x] = true;
                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = pos.y + dy;
                    int nx = pos.x + dx;
                    if(ny<0||nx<0||ny>=ySize||nx>=xSize){
                        continue;
                    }
                    if(map[ny][nx]=='.'){
                        moveQ.add(new Position(ny,nx));
                    }
                    if(map[ny][nx]=='D'){
                        System.out.println(time);
                        return;
                    }
                }
            }
        }

        System.out.println("KAKTUS");


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