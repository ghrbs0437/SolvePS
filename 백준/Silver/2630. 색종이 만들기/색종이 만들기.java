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


        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        for(int i=0;i<N;i++){

            String[] strs = br.readLine().split(" ");

            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        dq(map,0,0,N-1,N-1);
        System.out.println(white);
        System.out.println(blue);

    }

    public static int blue = 0;
    public static int white = 0;

    public static void dq(int[][] map, int y1, int x1, int y2, int x2){

        int num = map[y1][x1];

        for(int i=y1;i<=y2;i++){
            for(int j=x1;j<=x2;j++){
                if(num!=map[i][j]){
                    int ny = (y1+y2) /2;
                    int nx = (x1+x2) /2;
                    dq(map,y1,x1,ny,nx);
                    dq(map,ny+1,x1,y2,nx);
                    dq(map,y1,nx+1,ny,x2);
                    dq(map,ny+1,nx+1,y2,x2);
                    return;
                }
            }
        }
        if(num==0){
            white++;
        }else{
            blue++;
        }
    }
}