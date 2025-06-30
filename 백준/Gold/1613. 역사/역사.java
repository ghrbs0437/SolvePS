import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int n = Integer.parseInt(strs[0]);
        int k = Integer.parseInt(strs[1]);

        int[][] maps = new int[n][n];

        for(int i=0;i<k;i++){
            strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0])-1;
            int end = Integer.parseInt(strs[1])-1;

            maps[start][end] = -1;
            maps[end][start] = 1;
        }

//
//       for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                System.out.print(maps[i][j]+" " );
//            }
//            System.out.println();
//        }

        // 맵의 연결을 재구성해..

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int l=0;l<n;l++){
                    if(maps[i][j] == 1 && maps[i][l] == -1 && maps[j][l] == 0){
                        maps[j][l] = -1;
                    }
                    if(maps[i][j] == -1 && maps[i][l] == 1 && maps[j][l] == 0){
                        maps[j][l] = 1;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int s = Integer.parseInt(br.readLine());
        for(int i=0;i<s;i++){
            strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0])-1;
            int end = Integer.parseInt(strs[1])-1;
            sb.append(maps[start][end]).append("\n");
        }
        System.out.println(sb);




    }

}