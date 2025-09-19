import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] strs = br.readLine().split(" ");
        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
            return a.number - b.number;
        });
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            Token token = new Token(i,Integer.parseInt(strs[i]));
            pq.add(token);
        }

        int number = pq.peek().number;
        int order = 0;
        while(!pq.isEmpty()){
            Token poll = pq.poll();
            if(number == poll.number){
                arr[poll.index] = order;
            }else{
                order++;
                arr[poll.index] = order;
            }
            number = poll.number;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++){
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);

    }

    public static class Token{
        int index;
        int number;
        Token(int index, int number){
            this.index = index;
            this.number = number;
        }
    }
}