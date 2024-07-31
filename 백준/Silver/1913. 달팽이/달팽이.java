import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        int startX = N/2;
        int startY = N/2;
        int dlength = 1;
        int remains = 1;
        int directionIndex = 0;


        int answerX = 0;
        int answerY = 0;
        for (int i = 1; i <= N * N; i++) {
                if (remains == 0) {
                    directionIndex = (directionIndex + 1) % 4;
                    if(directionIndex==0 || directionIndex==2){
                        dlength++;
                    }
                    remains = dlength;
                }
                arr[startY][startX] = i;
                if(i == M){
                    answerX = startX;
                    answerY = startY;
                }
                startY += directions[directionIndex][0];
                startX += directions[directionIndex][1];
                remains--;

        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sb.append(arr[i][j]).append(" ");
            }
            if(i==N-1){
                break;
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
        System.out.println((answerY+1) + " "+ (answerX+1));

    }
    public static int[][] copyArr(int[][] arr){
        int[][] newArr = new int[arr.length][arr[0].length];
        for(int i=0;i< arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                newArr[i][j] = arr[i][j];
            }
        }
        return newArr;
    }

    public static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
//        System.out.println();
    }
}