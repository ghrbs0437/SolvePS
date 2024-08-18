import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int size = Integer.parseInt(br.readLine());

        String[] strs = br.readLine().split(" ");

        int[] arr = new int[size];
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<size;i++){
            arr[i] = Integer.parseInt(strs[i]);
            sum+=arr[i];
            if(min>arr[i]){
                min = arr[i];
            }
        }
        // min 에서 sum 범위에서 boolean false를 찾아라.
        //
        boolean[] b = new boolean[sum+1];
        getAns(0,0,arr,b);

        for(int i=0;i<=sum;i++){
            if(!b[i]){
                System.out.println(i);
                return;
            }
        }
        System.out.println(sum+1);


    }
    public static void getAns(int depth, int sum,int[] arr,boolean[] b){
        if(depth == arr.length){
            b[sum] = true;
            return;
        }
        getAns(depth+1,sum,arr,b);
        getAns(depth+1,sum+arr[depth],arr,b);
    }
}