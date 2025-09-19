import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        double d = Math.round(N * 0.15);
        int offset = (int)d;

        int answer = 0;
        for(int i=0+offset;i<N-offset;i++){
            answer += arr[i];
        }
        answer =(int)Math.round((double)answer/(N-2*offset));
        System.out.println(answer);
    }
}