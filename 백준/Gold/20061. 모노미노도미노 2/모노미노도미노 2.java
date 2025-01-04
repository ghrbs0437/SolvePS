import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] green = new int[10][4];
        int[][] blue = new int[10][4];

        for (int i = 0; i < N; i++) {
            String[] strs = br.readLine().split(" ");
            int code = Integer.parseInt(strs[0]);
            int y = Integer.parseInt(strs[1]);
            int x = Integer.parseInt(strs[2]);

            if(code==1){
                insertBlock(y,x,green);
                insertBlock(x,y,blue);
            }else if(code==2){
                insertBlock(y,x,y,x+1,green);
                insertBlock(x,y,x+1,y,blue);
            }else if(code==3){
                insertBlock(y,x,y+1,x,green);
                insertBlock(x,y,x,y+1,blue);
            }else{
                System.out.println("??");
            }
            removeLine(green);
            removeLine(blue);
            after(green);
            after(blue);
        }

        int cnt = 0;
        for(int i=0;i<10;i++){
            for(int j=0;j<4;j++){
                if(green[i][j]==1){
                    cnt++;
                }
                if(blue[i][j]==1){
                    cnt++;
                }
            }
        }

        System.out.println(ANSWER);
        System.out.println(cnt);
    }

    static int ANSWER = 0;
    public static void after(int[][] map) {
        int cnt = 0;
        for (int i = 4; i <= 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] == 1) {
                    cnt++;
                    break;
                }
            }
        }
        for (int j = 9; j >= cnt; j--) {
            for (int k = 0; k < 4; k++) {
                map[j][k] = map[j-cnt][k];
            }
        }
    }

    public static void removeLine(int[][] map){
        for(int i=5;i<10;i++){
            if(map[i][0]==1 && map[i][1]==1 && map[i][2]==1 && map[i][3]==1){
                for(int j=i;j>=1;j--){
                    for(int k=0;k<4;k++){
                        map[j][k] = map[j-1][k];
                    }
                }
                ANSWER++;
                i--;
            }
        }
    }


    public static void insertBlock(int y,int x, int[][] map){
        for(int i=0;i<=10;i++){
            if(i==10){
                map[i-1][x] = 1;
                return;
            }

            if(map[i][x] == 1){
                map[i-1][x] = 1;
                return;
            }
        }
    }

    public static void insertBlock(int y1,int x1, int y2, int x2, int[][] map){

        int move = 0;
        while(true){
            move++;
            if( move + y2 >= 10
                    || move + y1 >= 10
                    || map[y2+move][x2] == 1
                    || map[y1+move][x1] == 1){
                map[y2 + move - 1][x2] = 1;
                map[y1 + move - 1][x1] = 1;
                break;
            }
        }
    }


}