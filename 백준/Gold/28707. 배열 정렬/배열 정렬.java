import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] strs = br.readLine().split(" ");

        StringBuilder start = new StringBuilder();
        for(int i=0;i<N;i++){
            start.append((Integer.parseInt(strs[i])-1));
        }
        int M = Integer.parseInt(br.readLine());

        ArrayList<Command> alist = new ArrayList<>();

        for(int j=0;j<M;j++){
            strs = br.readLine().split(" ");
            int s = Integer.parseInt(strs[0])-1;
            int e = Integer.parseInt(strs[1])-1;
            int c = Integer.parseInt(strs[2]);
            Command command = new Command(s,e,c);
            alist.add(command);
        }

        HashMap<String,Integer> hmap = new HashMap<>();
        hmap.put(start.toString(),0);
        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
            return a.cost - b.cost;
        });
        pq.add(new Token(start.toString(),0));

        int answer = Integer.MAX_VALUE;
        while(!pq.isEmpty()){
            Token poll = pq.poll();
//            System.out.println(hmap);
            if(isNonDecreasing(poll.state)){
                answer = Math.min(answer,hmap.get(poll.state));
                break;
            }

            for(Command command : alist){
                String next = useCommand(command, poll.state);
                Integer directRoute = hmap.get(next);
                Integer delayRoute = hmap.get(poll.state) + command.cost;

                if(directRoute == null){
                    hmap.put(next,delayRoute);
                    pq.add(new Token(next,delayRoute));
                }else if(directRoute > delayRoute){
                    hmap.put(next,delayRoute);
                    pq.add(new Token(next,delayRoute));
                }
            }
        }

        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }

    }

    public static boolean isNonDecreasing(String str){
        int length = str.length();
        int num = -1;
        for(int i=0;i<length;i++){
            if(num> str.charAt(i)-'0'){
                return false;
            }
            num = str.charAt(i)-'0';
        }
        return true;
    }

    public static String useCommand(Command command, String str){
        char[] charArray = str.toCharArray();


        char temp = charArray[command.start];
        charArray[command.start] = charArray[command.end];
        charArray[command.end] = temp;


        String ret = String.valueOf(charArray);

//        System.out.println("str" + str);
//        System.out.println("ret" + ret);
        return ret;
    }


    public static class Token{
        String state;
        int cost;

        Token(String state, int cost){
            this.state = state;
            this.cost = cost;
        }
    }
    public static class Command{
        int start;
        int end;
        int cost;

        Command(int start,int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}