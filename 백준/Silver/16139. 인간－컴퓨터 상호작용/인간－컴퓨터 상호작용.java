import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int N = Integer.parseInt(br.readLine());

        int[][] sum = new int[str.length()+1][26];

        for(int i=1;i<=str.length();i++){

            int index = str.charAt(i-1)-'a';
            for(int j=0;j<26;j++){
                if(j == index){
                    sum[i][j] = sum[i-1][j] + 1;
                }else{
                    sum[i][j] = sum[i-1][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");
            int index = strs[0].charAt(0)-'a';
            int start = Integer.parseInt(strs[1]);
            int end = Integer.parseInt(strs[2])+1;
            int cnt = sum[end][index] - sum[start][index];
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);

    }
}