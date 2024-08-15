import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        ArrayDeque<Character> stack = new ArrayDeque<>();

        String str = br.readLine();

        boolean pass = false;
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(c==' '){
                while(!stack.isEmpty()){
                    sb.append(stack.poll());
                }
                sb.append(c);
                continue;
            }
            if(c=='<'){
                while(!stack.isEmpty()){
                    sb.append(stack.poll());
                }
                sb.append(c);
                pass = true;
                continue;
            }
            if(c=='>'){
                sb.append(c);
                pass = false;
                continue;
            }

            if(pass){
                sb.append(c);
            }else{
                stack.push(c);
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.poll());
        }
        System.out.println(sb);
    }
}