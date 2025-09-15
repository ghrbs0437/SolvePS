import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] strs = br.readLine().split(" ");
        int[] arr = new int[N];

        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
            return a.value - b.value;
        });
        int[] answer = new int[N];

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(strs[i]);
            while(!pq.isEmpty()){
                if(pq.peek().value>=arr[i]){
                    break;
                }else{
                    Token token = pq.poll();
                    answer[token.index] = arr[i];
                }
            }
            Token t = new Token(i,arr[i]);
            pq.add(t);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++){
            if(answer[i]==0){
                sb.append(-1+" ");
            }else{
                sb.append(answer[i]+" ");
            }
        }
        System.out.println(sb);



    }

    public static class Token{
        int index;
        int value;
        Token(int index,int value){
            this.index = index;
            this.value = value;
        }
    }
}