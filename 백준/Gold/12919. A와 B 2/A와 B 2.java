import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static boolean POSSIBLE = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();
        getAns(S,T,reverse(T));

        if(POSSIBLE){
            System.out.println(1);
        }else{
            System.out.println(0);
        }
    }
    public static void getAns(String start, String end,String reverseEnd){
        if(POSSIBLE){
            return;
        }
        
        if(!checkVaild(start,end,reverseEnd)){
            return;
        }
        if(start.length()==end.length()){
            if(start.equals(end)){
                POSSIBLE = true;
            }
            return;
        }
        getAns(reverse(start+"B"),end,reverseEnd);
        getAns(start+"A",end,reverseEnd);
    }


    public static String reverse(String s){
        char[] reverseChar = new char[s.length()];
        for(int i=0;i<s.length();i++){
            reverseChar[i]=s.charAt(s.length()-1-i);
        }
        return String.valueOf(reverseChar);
    }

    public static boolean checkVaild(String start,String end,String reverseEnd){
        if(end.contains(start)||reverseEnd.contains(start)){
            return true;
        }
        return false;
    }
}