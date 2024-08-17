import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        ArrayList<Character> startStr = new ArrayList<>();
        ArrayList<Character> endStr = new ArrayList<>();

        for(int i=0;i<S.length();i++){
            startStr.add(S.charAt(i));
        }
        for(int i=0;i<T.length();i++){
            endStr.add(T.charAt(i));
        }


        while(true){
            if(endStr.size()==startStr.size()){
                break;
            }
            if(endStr.get(endStr.size()-1)=='A'){
                endStr.remove(endStr.size()-1);
            }else if(endStr.get(endStr.size()-1)=='B'){
                endStr.remove(endStr.size()-1);
                Collections.reverse(endStr);
            }
        }

        for(int i=0;i<endStr.size();i++){
            if(startStr.get(i)!=endStr.get(i)){
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);
        // 뒤에 A가 있다는것은. (가능했다면) 뒤에 A를 붙이는 연산을 수행했다는 의미

    }
}