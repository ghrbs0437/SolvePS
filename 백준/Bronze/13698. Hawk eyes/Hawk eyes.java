import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int[] arr = new int[4];
        arr[0] = 1;
        arr[3] = 2;
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            int tmp = 0;
            switch(c){
                case 'A':
                    tmp = arr[0];
                    arr[0] = arr[1];
                    arr[1] = tmp;
                    break;
                case 'B':
                    tmp = arr[0];
                    arr[0] = arr[2];
                    arr[2] = tmp;
                    break;
                case 'C':
                    tmp = arr[0];
                    arr[0] = arr[3];
                    arr[3] = tmp;
                    break;
                case 'D':
                    tmp = arr[1];
                    arr[1] = arr[2];
                    arr[2] = tmp;
                    break;
                case 'E':
                    tmp = arr[1];
                    arr[1] = arr[3];
                    arr[3] = tmp;
                    break;
                case 'F':
                    tmp = arr[2];
                    arr[2] = arr[3];
                    arr[3] = tmp;
                    break;
            }
        }
        int bIdx = 0;
        int sIdx = 0;
        for(int i=0;i<4;i++){
            if(arr[i]==1){
                sIdx = i;
            }
            if(arr[i] ==2){
                bIdx = i;
            }
        }
        System.out.println(sIdx +1);
        System.out.println(bIdx +1);


        // 가장 왼쪽에 작은컵 // 가장 오른쪽에 큰컵
    }
}