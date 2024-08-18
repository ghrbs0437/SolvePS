import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int answer = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int S = Integer.parseInt(strs[1]);

        strs = br.readLine().split(" ");

        int[] arr = new int[strs.length];
        for(int i=0;i<strs.length;i++){
            arr[i] = Integer.parseInt(strs[i]);
        }
        getAns(0,arr,0,S,0);
        System.out.println(answer);
    }

    public static void getAns(int depth,int[] arr,int sum,int target,int selCnt){
        if(depth==arr.length){
            if(sum==target&&selCnt!=0){
                answer++;
            }
            return;
        }
        getAns(depth+1,arr,sum+arr[depth],target,selCnt+1);
        getAns(depth+1,arr,sum,target,selCnt);

    }
}