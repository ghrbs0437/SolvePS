import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int MAX_WHITE = 0;
    static int MAX_BLACK = 0;
    static int CALL_CNT = 0;
    public static int[][] directions = {{-1,-1},{-1,1},{1,-1},{1,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                int val = Integer.parseInt(strs[j]);
                if(val==0){
                    map[i][j] = 1;
                }else{
                    map[i][j] = 0;
                }
            }
        }

        boolean[][] isWhite = new boolean[N][N];
        if(N/2==0){  // 짝수인경우
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if((i%2 + j%2)%2==0){
                        isWhite[i][j] = true;
                    }
                }
            }
        }else{
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if((i+j)%2==0){
                        isWhite[i][j] = true;
                    }
                }
            }
        }

//        for(int i=0;i<N;i++){
//            for(int j=0;j<N;j++){
//                System.out.print(isWhite[i][j]+" ");
//            }
//            System.out.println();
//        }





        // map 에 1은 비숍이 위치할 수 없는 장소..
        getAnsWhite(map,isWhite,0,0);
        getAnsBlack(map,isWhite,0,0);
        System.out.println(MAX_BLACK+MAX_WHITE);
//        System.out.println(CALL_CNT);

    }

    public static void getAnsWhite(int[][] map,boolean [][] isWhite, int cnt,int sumIJ){
//        CALL_CNT++;

        for(int i=sumIJ;i<map.length*map.length;i++){

            int y = i / map.length;
            int x = i % map.length;
            if(!isWhite[y][x]){
                continue;
            }
            if(map[y][x]==0){
                map[y][x]++;
                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = y;
                    int nx = x;
                    while(true){
                        ny = ny + dy;
                        nx = nx + dx;
                        if(ny<0 || nx <0 || ny>=map.length || nx>=map[0].length){
                            break;
                        }
                        map[ny][nx]++;
                    }
                }

                getAnsWhite(map,isWhite,cnt+1,i+1);

                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = y;
                    int nx = x;
                    while(true){
                        ny = ny + dy;
                        nx = nx + dx;
                        if(ny<0 || nx <0 || ny>=map.length || nx>=map[0].length){
                            break;
                        }
                        map[ny][nx]--;
                    }
                }
                map[y][x]-- ;
            }
        }
        if(cnt>MAX_WHITE){
            MAX_WHITE = cnt;
        }
    }

    public static void getAnsBlack(int[][] map,boolean [][] isWhite, int cnt,int sumIJ){
//        CALL_CNT++;

        for(int i=sumIJ;i<map.length*map.length;i++){

            int y = i / map.length;
            int x = i % map.length;
            if(isWhite[y][x]){
                continue;
            }
            if(map[y][x]==0){
                map[y][x]++;
                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = y;
                    int nx = x;
                    while(true){
                        ny = ny + dy;
                        nx = nx + dx;
                        if(ny<0 || nx <0 || ny>=map.length || nx>=map[0].length){
                            break;
                        }
                        map[ny][nx]++;
                    }
                }

                getAnsBlack(map,isWhite,cnt+1,i+1);

                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = y;
                    int nx = x;
                    while(true){
                        ny = ny + dy;
                        nx = nx + dx;
                        if(ny<0 || nx <0 || ny>=map.length || nx>=map[0].length){
                            break;
                        }
                        map[ny][nx]--;
                    }
                }
                map[y][x]-- ;
            }
        }
        if(cnt>MAX_BLACK){
            MAX_BLACK = cnt;
        }
    }

}