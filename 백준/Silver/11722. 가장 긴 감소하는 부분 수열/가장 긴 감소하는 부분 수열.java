import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] strs =br.readLine().split(" ");

        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(strs[i]);
        }

        int[] map = new int[n];
        map[0] = 1;
        for(int i=1;i<n;i++){
            int max = 0;
            for(int j=0;j<i;j++){
                if(arr[i] < arr[j]){
                    max = Math.max(max,map[j]);
                }
            }
            map[i] = max +1;
        }
        

        int max = 0;
        for(int i=0;i<n;i++){
            max = Math.max(map[i],max);
        }
        System.out.println(max);
    }

}