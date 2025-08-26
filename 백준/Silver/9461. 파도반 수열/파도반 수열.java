import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        long[] map = new long[101];
        map[0] = 1;
        map[1] = 1;
        map[2] = 1;
        map[3] = 2;
        map[4] = 2;
        map[5] = 3;
        for(int i= 6; i<101;i++){
            map[i] = map[i-1] + map[i-5];
        }
        StringBuilder sb = new StringBuilder();

        for(int t = 0; t<tc; t++){
            int num = Integer.parseInt(br.readLine())-1;
            sb.append(map[num]).append("\n");
        }
        System.out.println(sb);

    }

}