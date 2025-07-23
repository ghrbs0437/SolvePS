import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        int size = Integer.parseInt(br.readLine());
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);


        int sum = 0;

        for(int i=1;i<size;i+=2){
            if(arr[i-1] <= 0 && arr[i] <= 0 ){
                sum += arr[i-1] * arr[i];
            }else if(arr[i-1] <= 0 && arr[i] > 0){

                if((size - i) % 2 == 0){
                    sum += arr[i-1];
                    i--;
                }else{
                    sum += Math.max(arr[i - 1] * arr[i], arr[i - 1] + arr[i]);
                }
            }else if(arr[i-1] > 0 && arr[i] > 0){
                if((size - i) % 2 == 0){
                    sum += arr[i-1];
                    i--;
                }else{
                    sum += Math.max(arr[i - 1] * arr[i], arr[i - 1] + arr[i]);
                }
            }

            if(size - 2 == i){
                sum += arr[i+1];
            }

        }

        if(size == 1){
            sum = arr[0];
        }




        System.out.println(sum);

    }

}