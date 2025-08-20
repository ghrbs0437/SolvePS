import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int ANSWER = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        dq(map,0,0,N-1,N-1);

        sb.append(minus).append("\n").append(zero).append("\n").append(plus);
        System.out.println(sb);
    }
    static int plus = 0;
    static int minus = 0;
    static int zero = 0;

    public static void dq(int[][] map, int y1,int x1, int y2, int x2){
        int num = map[y1][x1];
        if(y1==y2 && x2==x1){
            if(num==0){
                zero++;
            }else if(num==1){
                plus++;
            }else{
                minus++;
            }
            return;
        }

        for(int i=y1;i<=y2;i++){
            for(int j=x1;j<=x2;j++){
                if(num != map[i][j]){
                    int dy = (y2 - y1) /3 +1;
                    int dx = (x2 - x1) /3 +1;
                    for(int ny = y1 ; ny <= y2; ny+=dy){
                        for(int nx = x1 ; nx <= x2; nx+=dx){
                            dq(map, ny, nx,ny+dy-1,nx+dx-1);
                        }
                    }
                    return;
                }
            }
        }

        if(num==0){
            zero++;
        }else if(num==1){
            plus++;
        }else{
            minus++;
        }
    }
}