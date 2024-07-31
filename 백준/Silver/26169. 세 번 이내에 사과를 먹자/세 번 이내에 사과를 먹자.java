import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] arr = new int[5][5];

        String[] strs;
        for(int i=0;i<5;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<5;j++){
                arr[i][j] = Integer.parseInt(strs[j]);
            }
        }

        strs = br.readLine().split(" ");

        int y = Integer.parseInt(strs[0]);
        int x = Integer.parseInt(strs[1]);

        boolean b = DFS(arr,y,x,0,0);
        if(b){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }

    public static boolean DFS(int[][] arr,int y,int x,int cnt,int depth){
        arr[y][x] = -1;

        if(depth>2){
            return cnt>=2;
        }
        boolean b = false;
        for(int[] direction : directions){
            int newCnt = cnt;
            int nextX = x+direction[1];
            int nextY = y+direction[0];
            if(nextX<0||nextY<0||nextX==5||nextY==5){
                continue;
            }
            int[][] newArr = copyArr(arr);
            if(arr[nextY][nextX]==-1){
                continue;
            }
            if(arr[nextY][nextX]==1){
                newCnt++;
            }
            b = b||DFS(newArr,nextY,nextX,newCnt,depth+1);
        }
        return b;
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
        System.out.println();
    }
}