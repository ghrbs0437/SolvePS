import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    static long[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        // S(n,m) = s(n-1,m) + s(n-3,m-1)
        arr = new long[N+1][K+1];
        for(int i=0;i<=N;i++){
            arr[i][0] = 1;
            arr[i][1] = i;
        }
        long answer = getAns(N,K);

        System.out.println(answer%1000000003);

    }

    public static long getAns(int N,int K){
        if (arr[N][K] != 0) {
            return arr[N][K];
        }

        if(N < 2 * K){
            return 0;
        }
        if(N == 2 * K){
            arr[N][K] = 2;
            return 2;
        }

        return arr[N][K] = getAns(N-1,K)%1000000003 + getAns(N-2,K-1)%1000000003;

    }
}