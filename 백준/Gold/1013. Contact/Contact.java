import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        for(int tc = 0 ; tc < testCase; tc++){
            String str = br.readLine();
            boolean match = str.matches("^(100+1+|01)+");
            if(match){
                sb.append("YES\n");
            }else{
                sb.append("NO\n");
            }

        }

        System.out.println(sb);
    }
}