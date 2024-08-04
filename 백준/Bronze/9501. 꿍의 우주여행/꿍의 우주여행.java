import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for(int i=0;i<testCase;i++){
            int cnt =0;
            String[] strs = br.readLine().split(" ");
            int shipSize = Integer.parseInt(strs[0]);
            int distance = Integer.parseInt(strs[1]);

            for(int j=0;j<shipSize;j++){
                strs = br.readLine().split(" ");

                int speed = Integer.parseInt(strs[0]);
                int fuel = Integer.parseInt(strs[1]);
                int need = Integer.parseInt(strs[2]);

                double t = fuel / need + (fuel%need)/(need*(1.0));
//                System.out.println(t*speed);
                if(t*speed>=distance){
                        cnt++;
                }
            }
            System.out.println(cnt);
        }

    }
}