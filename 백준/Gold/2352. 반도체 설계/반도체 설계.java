import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] map = new int[n];
        String str = br.readLine();
        String[] strs = str.split(" ");

        for(int i=0;i<n;i++){
            map[i] = Integer.parseInt(strs[i]);
        }

        int[] lis = new int[n];

        int lastIndex = 0;
        lis[0] = map[0];
        for(int i=1;i<n;i++){
            if(map[i] > lis[lastIndex]){
                lis[++lastIndex] = map[i];
            }else{
                // 탐색해서 교환하자.
                int low = 0;
                int high = lastIndex;
                int middle = (low + high)/2;
                while(true){
                    middle = (low + high)/2;
                    if(low == high){
                        break;
                    }
                    if(lis[middle] < map[i]){
                        // 증가해야하는거
                        low = middle + 1;
                    }else if(lis[middle] > map[i]){
                        high = middle;
                    }
                }
                lis[high] = map[i];
            }
        }
        System.out.println(lastIndex+1);

    }

}