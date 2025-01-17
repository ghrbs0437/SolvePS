import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]); // 물병의 개수
        int K = Integer.parseInt(strs[1]); // 한번에 옮길 수 있는 양,

        int num = N;
        while(true){
            if(Integer.bitCount(num) <=K) break;
            num++;
        }
        System.out.println(num-N);
    }

}