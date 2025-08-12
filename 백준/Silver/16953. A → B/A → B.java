import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");

        int A = Integer.parseInt(strs[0]);
        int B = Integer.parseInt(strs[1]);

        int cnt = 0;
        while(true){
            cnt++;
            if(A>B){
                System.out.println(-1);
                return;
            }
            if(B % 2 == 0){
                B /=2;
            }else if(B % 10 == 1){
                B /=10;
            }else{
                System.out.println(-1);
                return;
            }

            if(A==B){
                System.out.println(cnt+1);
                return;
            }
        }

    }
}