import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int K = Integer.parseInt(strs[1]);

        strs = br.readLine().split(" ");
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(strs[i]);
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    K--;
                    if(K==0){
                        System.out.println(arr[j] +" "+ arr[j+1]);
                        return;
                    }
                }
            }
        }
        System.out.println(-1);
    }
}