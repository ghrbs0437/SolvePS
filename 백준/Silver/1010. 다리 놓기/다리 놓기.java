import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int test_case = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=test_case;tc++){
            String[] strs = br.readLine().split(" ");
            int left = Integer.parseInt(strs[0]);
            int right = Integer.parseInt(strs[1]);
            BigInteger multiplayVal = BigInteger.ONE;
            for(int i=0;i<left;i++){
                multiplayVal = multiplayVal.multiply(BigInteger.valueOf(right - i));
                multiplayVal = multiplayVal.divide(BigInteger.valueOf(i+1));
            }
            sb.append(multiplayVal.toString()+"\n");
        }
        System.out.println(sb);
    }
}