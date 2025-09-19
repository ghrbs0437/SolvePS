import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        tc : for(int tc = 0; tc<T;tc++){
            String[] strs = br.readLine().split(" ");
            int N = Integer.parseInt(strs[0]);
            int M = Integer.parseInt(strs[1]);

            strs = br.readLine().split(" ");
            PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
                return b-a;
            });
            Queue<Token> queue = new ArrayDeque<>();
            for(int i=0;i<N;i++){
                int num = Integer.parseInt(strs[i]);
                queue.add(new Token(i,num));
                pq.add(num);
            }
            int cnt = 0;
            while(true){
                Token t = queue.poll();
                if(pq.peek() == t.score){
                    cnt++;
                    pq.poll();
                    if(t.index == M){
                        sb.append(cnt).append("\n");
                        break;
                    }
                }else{
                    queue.add(t);
                }
            }
        }
        System.out.println(sb);
    }

    public static class Token{
        int index;
        int score;
        Token(int index, int score){
            this.index = index;
            this.score = score;
        }
    }
}