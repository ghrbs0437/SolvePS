import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int length = str.length();

        int[] arr = new int[length+1];

        if(str.charAt(0) =='0'){
            System.out.println(0);
            return;
        }
        arr[0] = 1;
        arr[1] = 1;
        for(int i=1;i<length;i++){
            char c = str.charAt(i);
            char p = str.charAt(i-1);

            if(c=='0'){ // 해석의 여지가 없음
                if(p=='1'|| p=='2'){
                    arr[i+1] = arr[i-1];
                }else{
                    System.out.println(0);
                    return;
                }
            }else{
                if(p == '2'){
                    if(c-'0' >0 && c-'0'<=6){ // 1~6이면
                        arr[i+1] = arr[i] + arr[i-1];
                    }else{
                        arr[i+1] = arr[i];
                    }
                }else if(p == '1'){

                    arr[i+1] = arr[i] + arr[i-1];
                }else{
                    arr[i+1] = arr[i];
                }
            }
            arr[i+1] %=1000000;
        }
        System.out.println(arr[length]);
//        System.out.println(Arrays.toString(arr));


    }

}