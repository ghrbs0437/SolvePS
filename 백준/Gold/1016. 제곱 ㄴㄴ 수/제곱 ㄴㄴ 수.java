import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        long min = Long.parseLong(strs[0]);
        long max = Long.parseLong(strs[1]);

        long maxN = (long)Math.floor(Math.sqrt(max));

        boolean[] visits = new boolean[(int)(max-min+1)];

//        System.out.println(maxN);
        for(long i = 2;i<=maxN;i++){
            long index = (min/(i*i))*(i*i); // index를 어떻게 찾아야하나? //
            while(index<=max) {
                if(index<min){
                    index+=i*i;
                    continue;
                }
                visits[(int)(index-min)] = true;
                index+=i*i;
            }
        }
        long cnt = 0;
        for(boolean b : visits){
            if(!b){
                cnt++;
            }
        }
        System.out.println(cnt);

    }
}