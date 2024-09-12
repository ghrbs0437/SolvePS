import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int ans =Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        int Y = Integer.parseInt(strs[0]);
        int X = Integer.parseInt(strs[1]);


        int[][] maps = new int[Y][X];
        int[][] maps2 = new int[Y][X];
        boolean[][] visits = new boolean[Y][X];
        // ture면 이동가능한거!
        for(int i=0;i<Y;i++){
            String str = br.readLine();
            for(int j=0;j<X;j++){
                if(str.charAt(j)=='1'){
                    maps[i][j] = -1;
                    maps2[i][j] = -1;
                }else{
                    maps[i][j] = Integer.MAX_VALUE;
                    maps2[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        maps[0][0] = 1;

//        for(int i=0;i<Y;i++){
//            for(int j=0;j<X;j++){
//                System.out.print(maps[i][j]+ " " );
//            }
//            System.out.println();
//        }


        Queue<PositionToken> queue = new ArrayDeque<>();
        queue.add(new PositionToken(0,0,true));
        int val1 = Integer.MAX_VALUE;
        int ans = 1;
        wh : while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                PositionToken token = queue.poll();

//              System.out.println(token);
                visits[token.y][token.x] = true;
                if(token.y == Y-1 && token.x == X-1){
                    val1 = ans;
                    break wh;
                }

                for(int[] direction : directions){
                    int nextY = token.y+ direction[0];
                    int nextX = token.x+ direction[1];

                    if(nextY < 0 || nextY >= Y || nextX < 0 || nextX >= X){
                        continue;
                    }

                    // 방문안한거중에, 일단 내가 치트사용이 가능한지 확인
                    if(maps[nextY][nextX]!=-1){ // 치트없이 갈수있는곳
                        if(maps[nextY][nextX]<=maps[token.y][token.x]+1){ // 갈필요가 없는곳은 안넣는다.
                            continue;
                        }
                        maps[nextY][nextX] = maps[token.y][token.x]+1;
                        queue.add(new PositionToken(nextX,nextY, token.cheatable));
                    }else{
                        if(token.cheatable){
                            queue.add(new PositionToken(nextX,nextY, false));
                        }
                    }
                }
            }
            ans++;
        }


        int val2 = Integer.MAX_VALUE;
        queue = new ArrayDeque<>();
        queue.add(new PositionToken(X-1,Y-1,true));

        ans = 1;
        wh:while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                PositionToken token = queue.poll();

//              System.out.println(token);
                visits[token.y][token.x] = true;
                if(token.y == 0 && token.x == 0){
//                    System.out.println(ans);
                    val2 = ans;
                    break wh;
                }

                for(int[] direction : directions){
                    int nextY = token.y+ direction[0];
                    int nextX = token.x+ direction[1];

                    if(nextY < 0 || nextY >= Y || nextX < 0 || nextX >= X){
                        continue;
                    }

                    // 방문안한거중에, 일단 내가 치트사용이 가능한지 확인
                    if(maps2[nextY][nextX]!=-1){ // 치트없이 갈수있는곳
                        if(maps2[nextY][nextX]<=maps2[token.y][token.x]+1){ // 갈필요가 없는곳은 안넣는다.
                            continue;
                        }
                        maps2[nextY][nextX] = maps2[token.y][token.x]+1;
                        queue.add(new PositionToken(nextX,nextY, token.cheatable));
                    }else{
                        if(token.cheatable){
                            queue.add(new PositionToken(nextX,nextY, false));
                        }
                    }
                }
            }
            ans++;
        }
        if(val1 ==Integer.MAX_VALUE && val2 == Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }else{
            System.out.println(Math.min(val1,val2));
        }

    }



    public static class PositionToken{
        int x;
        int y;
        boolean cheatable;
        public PositionToken(int x, int y,boolean cheatable){
            this.x = x;
            this.y = y;
            this.cheatable = cheatable;
        }

        public String toString(){
            return "["+y+","+x+"]";
        }
    }

}