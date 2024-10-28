import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int length1 = str1.length();
        int length2 = str2.length();

        int[][] answer = new int[length1+1][length2+1];



        for(int i=1;i<=length1;i++){
            for(int j=1;j<=length2;j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    answer[i][j] = answer[i-1][j-1] + 1;
                }else{
                    answer[i][j] = Math.max(answer[i-1][j], answer[i][j-1]);
                }
            }
        }
        System.out.println(answer[length1][length2]);
    }

}