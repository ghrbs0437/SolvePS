import javax.xml.stream.events.Characters;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Main {


    static String STR_ANSWER = "0";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine().trim();

        char[] charArray = str.toCharArray();
        String[] arr = new String[charArray.length];
        int sum = 0;
        boolean hasZero = false;
        for(int i=0;i< charArray.length;i++){
            arr[i] = String.valueOf(charArray[i]);
            if(charArray[i]=='0'){
                hasZero = true;
            }
            sum+=(charArray[i]-'0');
            sum%=3;
        }

        if(!hasZero || sum%3!=0){
            System.out.println("-1");
            return;
        }

        Arrays.sort(arr, Comparator.reverseOrder());
        for(String s : arr){
            sb.append(s);
        }
        System.out.println(sb);
    }
}