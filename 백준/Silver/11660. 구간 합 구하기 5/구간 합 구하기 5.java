import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] strs = br.readLine().split(" ");
        // 표 사이즈
        int N = Integer.parseInt(strs[0]);
        // 구간합 몇번?
        int M = Integer.parseInt(strs[1]);

        int[][] map = new int[N][N];
        for(int i=0;i<N;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        if(N==1){
            for(int i=0;i<M;i++){
                System.out.println(map[0][0]);
            }
            return;
        }

        int[][] sums = new int[N][N];

        sums[0][0] = map[0][0];

        for(int i=1;i<N;i++){
            sums[0][i] = sums[0][i-1] + map[0][i];
            sums[i][0] = sums[i-1][0] + map[i][0];
        }

        for(int i=1;i<N;i++){
            for(int j=1;j<N;j++){
                sums[i][j] = sums[i][j-1] + sums[i-1][j] + map[i][j] - sums[i-1][j-1];
            }
        }



        for(int j=0;j<M;j++){
            strs = br.readLine().split(" ");
            int y1 = Integer.parseInt(strs[0])-1;
            int x1 = Integer.parseInt(strs[1])-1;
            int y2 = Integer.parseInt(strs[2])-1;
            int x2 = Integer.parseInt(strs[3])-1;

            int answer = 0;

            if(x1==0 && y1!=0){
                answer = sums[y2][x2] - sums[y1-1][x2];
            }
            if(y1==0 && x1!=0){
                answer = sums[y2][x2] - sums[y2][x1-1];
            }

            if(y1==0 && x1==0){
                answer = sums[y2][x2];
            }

            if(y1!=0 && x1!=0){
                answer = sums[y2][x2] - sums[y1-1][x2] - sums[y2][x1-1] + sums[y1-1][x1-1];
            }
            sb.append(answer+"\n");
        }
        System.out.println(sb);
    }
}