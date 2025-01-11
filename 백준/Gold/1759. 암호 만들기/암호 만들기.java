import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int length = Integer.parseInt(strs[0]);
        int size = Integer.parseInt(strs[1]);
        strs = br.readLine().split(" ");
        ArrayList<Character> alist = new ArrayList<>();
        for(int i=0;i<size;i++){
            alist.add(strs[i].charAt(0));
        }
        // 암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성되어 있다고 알려져 있다. 또
        Collections.sort(alist);
        getAnswer(length,alist,"",0,-1);
        System.out.println(sb);
    }

    static StringBuilder sb = new StringBuilder();

    public static void getAnswer(int length,ArrayList<Character> alist, String str, int cnt, int index){
        if(cnt==length){
            if(getCnt(str)>0 && length - getCnt(str)>=2){
                sb.append(str).append("\n");
            }
            return;
        }
        for(int i=index+1;i<alist.size();i++){
            getAnswer(length,alist,str+alist.get(i),cnt+1,i);
        }
    }

    public static int getCnt(String str){
        int cnt = 0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='a'||str.charAt(i)=='e'||str.charAt(i)=='i'||str.charAt(i)=='o'||str.charAt(i)=='u'){
                cnt++;
            }
        }
        return cnt;
    }
}