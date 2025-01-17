import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]); // 물병의 개수
        int K = Integer.parseInt(strs[1]); // 한번에 옮길 수 있는 양,
        // K개를 넘지 않는 비어있는 물병의 개수
        // 최대 K개의 물이 들어있는 물병
        // 같은 양의 물을 두 개 고른다.
        // 한개의 물병에 다른 한 쪽에 있는 물을 모두 붓는다.


        int num = N;
        while(true){

            String s = Integer.toBinaryString(num);
            int cnt = 0;
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)=='1'){
                    cnt++;
                }
            }
            if(cnt<=K){
                break;
            }
            num++;
        }
        System.out.println(num-N);

    }

}