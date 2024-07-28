import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static int[][][] shapes = {
            // 일자블록
            {{0,0},{0,1},{0,2},{0,3}},
            {{0,0},{1,0},{2,0},{3,0}},

            // 네모블록
            {{0,0},{0,1},{1,1},{1,0}},

            // ㄱ블록
            {{0,0},{0,1},{0,2},{1,2}},
//            {{0,1},{1,1},{2,1},{0,2}}, // ???
            {{1,0},{1,1},{1,2},{0,2}}, // ???


            {{0,0},{1,0},{1,1},{1,2}},
            {{0,0},{0,1},{1,0},{2,0}},

            {{0,0},{0,1},{0,2},{1,0}},
            {{0,0},{0,1},{1,1},{2,1}},

            {{2,0},{2,1},{1,1},{0,1}}, // ok
            {{0,0},{1,0},{2,0},{2,1}},

            // 꼬인블록
            {{0,0},{1,0},{1,1},{2,1}},
            {{0,1},{0,2},{1,1},{1,0}},
            {{0,0},{0,1},{1,1},{1,2}},
            {{0,1},{1,1},{1,0},{2,0}},

            // ㅗ 블록
            {{0,1},{1,1},{1,0},{1,2}},
            {{1,0},{0,1},{1,1},{2,1}},
            {{0,0},{0,1},{0,2},{1,1}},
            {{0,0},{1,0},{1,1},{2,0}}
    };



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 연산의 개수
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);
        int[][] arr = new int[N][M];

        for(int i=0;i<N;i++){
            split = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                arr[i][j] = Integer.parseInt(split[j]);
            }
        }

//        for(int i=0;i<N;i++){
//            for(int j=0;j<M;j++){
//                System.out.print(arr[i][j]+" ");
//            }
//            System.out.println();
//        }
        //(1,1) 에서, (M,N)까지 가능하네요

        ArrayList<Tetromino> alist = new ArrayList<>();
        for(int[][] sha : shapes){
            alist.add(new Tetromino(sha));
        }
        int max = 0 ;
        for(Tetromino tetromino : alist){
            int val = tetromino.getMaxValInMap(arr);
            if(val>max){
                max = val;
            }
//            System.out.println("val = " + val + ":\n "+tetromino);
        }

        System.out.println(max);

    }

    /**
     * 좌상단을 0,0으로 잡고진행..
     */
    public static class Tetromino{
        int[][] shape;
        public Tetromino(int[][] shape){
            this.shape = shape;
        }

        public int getMaxValInMap(int[][] arr){
            int ySize = arr.length;
            int xSize = arr[0].length;

            int maxSum = 0;
            for(int i=0;i<ySize;i++){
                task: for(int j=0;j<xSize;j++){
                    int subSum = 0 ;
                    for(int dv = 0;dv<4;dv++){
                        int ny = i + shape[dv][0];
                        int nx = j + shape[dv][1];
                        if(ny<0 || nx<0 || ny>=ySize || nx>=xSize){
                            continue task;
                        }
                        subSum += arr[ny][nx];
                    }
                    if(maxSum<subSum){
//                        System.out.println("I : " + i + "  J :" + j);
                        maxSum = subSum;
                    }
                }
            }
            return maxSum;
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<shape.length;i++){
                sb.append(shape[i][0]).append(" ").append(shape[i][1]);
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}