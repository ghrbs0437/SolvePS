import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str =br.readLine();
        String boomStr = br.readLine();

        char[] newStr = new char[str.length()];
        int newStrIndex = 0;
        char lastBoomChar = boomStr.charAt(boomStr.length()-1);
        str :
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            newStr[newStrIndex] = c;
            newStrIndex++;

            if(newStrIndex<boomStr.length()){
                continue;
            }

            if(c==lastBoomChar){
                for(int j=0;j<boomStr.length();j++){
                    if(newStr[newStrIndex-1-j] != boomStr.charAt(boomStr.length()-1-j)){
                       continue str;
                    }
                }
                for(int j=0;j<boomStr.length();j++){
                    newStr[newStrIndex-1] = 0;
                    newStrIndex--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if(newStrIndex==0){
            sb.append("FRULA");
        }else{
            for(int i=0;i<newStrIndex;i++){
                sb.append(newStr[i]);
            }
        }
        System.out.println(sb);

    }
}