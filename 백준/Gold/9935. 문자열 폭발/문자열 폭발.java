import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str =br.readLine();
        String boomStr = br.readLine();


        ArrayList<Character> alist = new ArrayList<>();

        str :
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            alist.add(c);

            if(c==boomStr.charAt(boomStr.length()-1)){
                for(int j=0;j<boomStr.length();j++){
                    if(alist.size()<boomStr.length()){
                        continue str;
                    }
                    if(alist.get(alist.size()-1-j) != boomStr.charAt(boomStr.length()-1-j)){
                       continue str;
                    }
                }
                for(int j=0;j<boomStr.length();j++){
                    alist.remove(alist.size()-1);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if(alist.isEmpty()){
            sb.append("FRULA");
        }else{
            for(Character c : alist){
                sb.append(c);
            }
        }
        System.out.println(sb);

    }
}