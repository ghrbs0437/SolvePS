import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long size = Long.parseLong(br.readLine());
        long[] arr = new long[(int)size];
        String[] strs = br.readLine().split(" ");
        for (int i = 0; i < size; i++) {
            arr[i] = Long.parseLong(strs[i]);
        }
        Arrays.sort(arr);

        long answer = Long.MAX_VALUE;
        long[] answerArr = new long[3];
        for (long k = size - 1; k >= 2; k--) {
            for (long i = 0; i <= k-2 ; i++) {

                long min = i;
                long max = k;
                while (true) {
                    if (max <= min) {
                        break;
                    }
                    long middle = (max + min) / 2;
//                    System.out.println(max + " " + middle + " " + min);
                    long val = (arr[(int)i] + arr[(int)middle] + arr[(int)k]);
                    if (Math.abs(val) < answer) {
                        answer = Math.abs(val);
                        answerArr[0] = arr[(int)i];
                        answerArr[1] = arr[(int)middle];
                        answerArr[2] = arr[(int)k];
                    }

                    if (val < 0) {
                        min = middle;
                    } else if (val > 0) {
                        max = middle;
                    } else {
                        break;
                    }
                    if(max<min+2){
                        break;
                    }
                }
            }
        }
        System.out.println(answerArr[0] + " " + answerArr[1] + " " + answerArr[2]);
    }
}