import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 2칸씩 끊어보자...
        // f1 -> 1
        // f2 -> 2
        // f3 -> 3
        // fn -> fn-1에서 1개ㅔ짜리를 두는방법 + fn-2에서 두개짜리를 두는 방법.. 두개는 2종류가 있다.. 즉 2fn-2?
        // 인데... fn-1에서 1개를 추가로 두면 fn-2에서 두개를 두는 방법과 중복이 된다................... ?? 이걸 어케바로알지

        int N = Integer.parseInt(br.readLine());

        if(N<=3){
            System.out.println(N);
            return;
        }
        long[] arr = new long[N+1];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;

        for(int i=4;i<=N;i++){
            arr[i] = Long.sum(arr[i-1], arr[i-2]);
            arr[i] = arr[i]%10007;
        }
//        System.out.println(arr[N]);
        System.out.println(arr[N]%10007);
    }
}