import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    //표기식은 알파벳 대문자와 +, -, *, /, (, )로만 이루어져
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        int[][] arr = new int[N][M];
        for(int i=0;i<N;i++){
            String line = br.readLine();
            for(int j=0;j<M;j++){
                arr[i][j] =line.charAt(j) - '0';
            }
        }

        BFS(arr,N,M,new boolean[N][M]);
    }

    // arr[0][0] -> arr[N][M];
    public static void BFS(int[][] arr,int N,int M,boolean[][] visit){
        Queue<Integer> yQueue = new LinkedList<>();
        Queue<Integer> xQueue = new LinkedList<>();

        int[][] delta = {{0,1},{0,-1},{-1,0},{1,0}};
        xQueue.add(0);
        yQueue.add(0);
        int count = 0;
        while(!xQueue.isEmpty()){
            int size = xQueue.size();
            count++;
            for(int i=0;i<size;i++){
                int x = xQueue.poll();
                int y = yQueue.poll();
                if(y ==N-1 && x==M-1){
                    System.out.println(count);
                    return;
                }
                if(visit[y][x]){
                    continue;
                }

                visit[y][x] = true;
                for(int j=0;j<4;j++){
                    if(y+delta[j][0]<0 || y+delta[j][0]>=N || x+delta[j][1]<0 || x+delta[j][1]>=M){
                        continue;
                    }
                    int nextX = x+delta[j][1];
                    int nextY = y+delta[j][0];
                    if(arr[nextY][nextX] == 1){
                        xQueue.add(nextX);
                        yQueue.add(nextY);
                    }
                }
            }
        }
    }
}