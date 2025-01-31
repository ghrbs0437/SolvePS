import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numSize = Integer.parseInt(br.readLine());
        String[] strs = br.readLine().split(" ");
        int[] maxArr = new int[4];

        for(int i=0;i<numSize;i++){
            int number = Integer.parseInt(strs[i]);
            boolean flag = true;
            for(int j=0;j<4;j++){
                if(number > maxArr[j]){
                    maxArr[j] = number;
                    flag = false;
                    break;
                }
            }
            if(flag){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");

    }

}