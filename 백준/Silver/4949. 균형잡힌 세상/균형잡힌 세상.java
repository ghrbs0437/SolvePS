import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();



        tc : while(true){
            int small = 0;
            int big = 0;

            String str = br.readLine();
            if(str.equals(".")){
                break;
            }
            ArrayDeque<Character> stack = new ArrayDeque<>();

            for(int i=0;i<str.length();i++){
                char c = str.charAt(i);
                if(c == '('){
                    stack.push(c);
                }else if(c == '['){
                    stack.push(c);
                }else if(c == ']'){
                    if(!stack.isEmpty()&&stack.peek() =='['){
                        stack.poll();
                    }else{
                        sb.append("no\n");
                        continue tc;
                    }
                }else if(c == ')'){
                    if(!stack.isEmpty()&&stack.peek() =='('){
                        stack.poll();
                    }else{
                        sb.append("no\n");
                        continue tc;
                    }
                }
            }
            if(stack.isEmpty()){
                sb.append("yes\n");
            }else{
                sb.append("no\n");
            }
        }
        System.out.println(sb);
    }
}