import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {


    public static int[][] directions = {{1,1},{1,0},{0,1},{1,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        final int MAP_SIZE = 19;
        int[][] map = new int[MAP_SIZE][MAP_SIZE];

        for(int i=0;i<MAP_SIZE;i++){
            String[] strs = br.readLine().split(" ");
            for(int j=0;j<MAP_SIZE;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        boolean[][][] visits = new boolean[MAP_SIZE][MAP_SIZE][4];

        for(int i=0;i<MAP_SIZE;i++){
            for(int j=0;j<MAP_SIZE;j++){
                int current = map[i][j];
                if(current==0){
                    continue;
                }
                for(int k=0;k<directions.length;k++){
                    int[] direction = directions[k];
                    if(visits[i][j][k]){
                        continue;
                    }
                    visits[i][j][k] = true;
                    int cy = i;
                    int cx = j;
                    int dy = direction[0];
                    int dx = direction[1];
                    int length =1;
                    int ty = i;
                    int tx = j;
                    while(true){
                        cy+=dy;
                        cx+=dx;
                        if(cy>=MAP_SIZE||cx>=MAP_SIZE||cx<0){
                            break;
                        }
                        if(map[cy][cx]==current){
                            visits[cy][cx][k] = true;
                            length++;
                            if(cx<tx){
                                ty = cy;
                                tx = cx;
                            }
                        }else{
                            break;
                        }
                        if(length>5){
                            break;
                        }
                    }
                    if(length==5){
                        sb.append(current+"\n").append(ty+1).append(" ").append(tx+1);
                        System.out.println(sb.toString());
                        return;
                    }
                }
            }
        }
        System.out.println(0);
    }
}