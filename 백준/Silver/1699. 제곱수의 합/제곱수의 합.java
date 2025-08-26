import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int[] map = new int[num+1];

        for(int i=1;;i++){
            if(i*i>num){
                break;
            }
            map[i*i] =1;
        }

        for(int i=1;i<=num;i++){

            int min = 10000;
            for(int j=1;j<i;j++){
                min = Math.min(min,map[i-j] + map[j]);
            }

            if(map[i]!=0){
                map[i] = Math.min(min,map[i]);
            }else{
                map[i] = min;
            }
        }
        System.out.println(map[num]);
    }
}