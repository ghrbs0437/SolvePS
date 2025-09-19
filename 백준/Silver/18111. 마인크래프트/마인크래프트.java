import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);
        int B = Integer.parseInt(strs[2]);

        int[][] map = new int[N][M];
        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(strs[j]);
                max = Math.max(max,map[i][j]);
                min = Math.min(min,map[i][j]);
            }
        }

        int answerT = Integer.MAX_VALUE;
        int answerH = -1;
        for(int h=max; h>=min ; h--){
            // 최종 완성될 높이
            int time = 0;
            int block = B;
            for(int i=0;i<N;i++){
                for(int j=0; j<M; j++){
                    if( map[i][j] > h){
                        time += 2 * (map[i][j] - h);
                        block += (map[i][j] - h);
                    }else if(map[i][j] == h){

                    }else{
                        time += h-map[i][j];
                        block -= (h - map[i][j]);
                    }
                }
            }

            if(block<0){
                continue;
            }

            if(answerT > time){
                answerT = time;
                answerH = h;
            }
        }

        System.out.println(answerT + " " +answerH);


    }
}