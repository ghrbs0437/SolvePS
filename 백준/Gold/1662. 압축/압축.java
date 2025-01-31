import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        Queue<Character> queue = new ArrayDeque<>();

        for(int i=0;i<str.length();i++){
            queue.add(str.charAt(i));
        }

        int ans = getAns2(queue);
        System.out.println(ans);

    }



    public static int getAns2(Queue<Character> queue){

        int cnt = 0;
        while(true){
            if (queue.isEmpty()) {
                return cnt;
            }
            Character poll = queue.poll();

            if(poll==')'){
                break;
            }

            if(!queue.isEmpty()){
                Character peek = queue.peek();
                if(peek=='('){
                    int powN = poll-'0';
                    queue.poll();
                    int powM = getAns2(queue);
                    cnt+=powN*powM;
                }else{
                    cnt++;
                }
            }else{
                cnt++;
                break;
            }
        }
        return cnt;

    }




    public static String getAns(Queue<Character> queue){

        StringBuilder sb = new StringBuilder();
        while(true){
            if (queue.isEmpty()) {
                return sb.toString();
            }
            Character poll = queue.poll();

            if(poll==')'){
                break;
            }

            if(!queue.isEmpty()){
                Character peek = queue.peek();
                if(peek=='('){
                    int cnt = poll-'0';
                    queue.poll();
                    String temp = getAns(queue);
                    for(int i=0; i<cnt; i++){
                        sb.append(temp);
                    }
                }else{
                    sb.append(poll);
                }
            }else{
                sb.append(poll);
                break;
            }
        }
        return sb.toString();

    }

}