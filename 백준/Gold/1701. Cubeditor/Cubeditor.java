import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        int length = str.length();

        int max = 0;

        for(int i=1;i<length;i++){ // 패턴의 길이
            int cnt = 0;
            for(int j=0;j<length;j++){
                if(i+j >= length){
                    max = Math.max(max,cnt);
                    break;
                }
                if(str.charAt(i+j) == str.charAt(j)){
                    cnt++;
                }else{
                    max = Math.max(max,cnt);
                    cnt =0;
                }
            }
        }

        System.out.println(max);
    }
}