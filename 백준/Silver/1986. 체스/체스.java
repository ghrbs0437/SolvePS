import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    static int[][] qdirections = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
    static int[][] knightMove = {{-2,-1},{-2,1},{2,1},{2,-1},{1,-2},{1,2},{-1,2},{-1,-2}};
    static int[][] pawnMove = {{1,-1},{1,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");

        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);

        int[][] arr = new int[N][M];


        // 퀸 4
        // 나이트 3
        // 폰 2
        // 위험 1
        // 안전 0


        //퀸
        s = br.readLine().split(" ");
        int size = Integer.parseInt(s[0]);
        for (int i = 1; i <= size * 2; i += 2) {
            int y = Integer.parseInt(s[i]) - 1;
            int x = Integer.parseInt(s[i + 1]) - 1;
            arr[y][x] = 4;
        }

        s = br.readLine().split(" ");
        size = Integer.parseInt(s[0]);
        for (int i = 1; i <= size * 2; i += 2) {
            int y = Integer.parseInt(s[i]) - 1;
            int x = Integer.parseInt(s[i + 1]) - 1;
            arr[y][x] = 3;
        }

        s = br.readLine().split(" ");
        size = Integer.parseInt(s[0]);
        for (int i = 1; i <= size * 2; i += 2) {
            int y = Integer.parseInt(s[i]) - 1;
            int x = Integer.parseInt(s[i + 1]) - 1;
            arr[y][x] = 2;
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(arr[i][j]==0 || arr[i][j]==1){
                    continue;
                }

                int y = i;
                int x = j;
                //퀸
                if(arr[i][j]==4){
                    int curY = y;
                    int curX = x;
                    q : for(int[] direction : qdirections){
                        int nextY = curY + direction[0];
                        int nextX = curX + direction[1];
                        while(true){
                            if(nextY<0||nextX<0||nextY>=N||nextX>=M){
                                continue q;
                            }
                            if(arr[nextY][nextX]==2||arr[nextY][nextX]==3||arr[nextY][nextX]==4){
                                continue q;
                            }
                            arr[nextY][nextX] =1;
                            nextY +=direction[0];
                            nextX +=direction[1];
                        }
                    }
                }

                // 나이트
                if(arr[i][j]==3){
                    for(int[] direction : knightMove){
                        int nextY = y+direction[0];
                        int nextX = x+direction[1];
                        if(nextY<0||nextX<0||nextY>=N||nextX>=N){
                            continue;
                        }
                        if(arr[nextY][nextX]==0){
                            arr[nextY][nextX]=1;
                        }
                    }

                }


                //폰
//                if(arr[i][j]==2){
//                    for(int[] direction : pawnMove){
//                        int nextY = y+direction[0];
//                        int nextX = x+direction[1];
//                        if(nextY<0||nextX<0||nextY>=N||nextX>=N){
//                            continue;
//                        }
//                        if(arr[nextY][nextX]==0){
//                            arr[nextY][nextX]=1;
//                        }
//                    }
//                }
                // 근데 폰은 상대말을 못잡는대...

            }
        }

        int cnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(arr[i][j]==0){
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
//        printArr(arr);

    }


    public static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}