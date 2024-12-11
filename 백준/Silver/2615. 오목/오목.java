import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {


    public static int[][] directions = {{1,1},{1,0},{0,1},{1,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[][] map = new int[19][19];

        for(int i=0;i<19;i++){
            String[] strs = br.readLine().split(" ");
            for(int j=0;j<19;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }


        boolean[][][] visits = new boolean[19][19][4];

        for(int i=0;i<19;i++){
            for(int j=0;j<19;j++){
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
                        if(cy>=19||cx>=19||cx<0){
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
                    }
                    if(length==5){
                        System.out.println(current);
                        System.out.println((ty+1) +" " +(tx+1));
                        return;
                    }
                }
            }
        }
        System.out.println(0);
    }

}