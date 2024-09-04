import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());


        int[] arr = new int[3];

        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");
            int red = Integer.parseInt(strs[0]);
            int green = Integer.parseInt(strs[1]);
            int blue = Integer.parseInt(strs[2]);

            int next0 = Math.min(arr[1],arr[2])+red;
            int next1 = Math.min(arr[0],arr[2])+green;
            int next2 = Math.min(arr[0],arr[1])+blue;
            arr[0] = next0;
            arr[1] = next1;
            arr[2] = next2;
        }
        int min = Integer.MAX_VALUE;
        for(int i : arr){
            if(min>i)
                min = i;
        }
        System.out.println(min);
    }
}