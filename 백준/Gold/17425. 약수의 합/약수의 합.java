import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        // F(A) 는 A의 약수를 모두 더한값
        // G(X) 는 X 이하의 모든 수에 대해 F(A)의 합


        int maxM = 1000000;
        int[] f = new int[maxM+1];
        long[] g = new long[maxM+1];

        for(int i=1;i<=maxM;i++){
            int cnt = 1;
            while(true){
                if(cnt * i > maxM){
                    break;
                }
                f[cnt * i] += i;
                cnt++;
            }
        }


        for(int i=1;i<=maxM;i++){
            g[i] = g[i-1] + f[i];
        }
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<tc;i++){
            int num = Integer.parseInt(br.readLine());
            sb.append(g[num]).append("\n");
        }
        System.out.println(sb);

    }

}