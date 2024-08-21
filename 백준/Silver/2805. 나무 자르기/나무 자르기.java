import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        strs = br.readLine().split(" ");
        int[] arr = new int[N];

        int maxWoodLength = 0;
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(strs[i]);
            if(maxWoodLength<arr[i]) {
                maxWoodLength = arr[i];
            }
        }

        int min = 0;
        int max = maxWoodLength;
        int answer = 0;

        checkCycle : while(min<=max) {
            long height = ((long)max+(long)min)/2l;
            int sum = 0;
            for(int i=0;i<N;i++) {
                if(arr[i]<height) {
                    continue;
                }
                sum+=arr[i]-height;
                if(sum<0) { // 오버플로우 발생.. 톱의 높이를 높여라.
                    min = (int)height;
                    continue checkCycle;
                }
            }
            if(sum<M) { // 높의 높이를 낮춰라.
                max = (int)height-1;
            }else if(sum>=M){ // 톱의 높이를 높여라.
                answer = Math.max((int)height, answer);
                min = (int)height+1;
            }
        }

        System.out.println(answer);



    }
}