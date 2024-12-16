import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int size = Integer.parseInt(br.readLine());
        int[][] map = new int[size][size];
        int[][] answer = new int[size][size];
        for(int i=0; i<size;i++){
            String[] strs = br.readLine().split(" ");
            for(int j=0;j<size;j++){
                map[i][j] = Integer.parseInt(strs[j]);
                answer[i][j] = map[i][j];
            }
        }


        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                for(int k=0;k<size;k++){
                    if(answer[j][i] == 1 && answer[i][k]==1){
                        answer[j][k] = 1;
                    }
                }
            }
        }
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}