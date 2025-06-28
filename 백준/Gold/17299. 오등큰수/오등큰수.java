import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] answer = new int[N];

        String[] strs = br.readLine().split(" ");

        int[] count = new int[1000001];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(strs[i]);
            count[arr[i]]++;
        }

        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
            return a.count - b.count;
        });
        
        for(int i=0;i<N;i++){
            Token token = new Token(i,count[arr[i]]);
            pq.add(token);

            // 이번 인덱스의 정답을 구하기 위해서 Token화 하는것..
            // 토큰화한다음엔???
            // 현재까지 있는 토큰이랑 현재 위치에서 값을 확인해보자.
            // 현재 토큰이랑 현재 위치의 값이랑 비교했는데, 현재 위치 값이 더 크면 ?? 이 토큰은 소모된다.

            while(true){
                if(pq.isEmpty()){
                    break;
                }
                Token peek = pq.peek();
                if(peek.count < count[arr[i]]){
                    peek = pq.poll();
                    answer[peek.index] = arr[i];
                }else{
                    break;
                }
            }
        }

        for(Token token : pq){
            answer[token.index] = -1;
        }


        StringBuilder sb = new StringBuilder();
        for(int i : answer){
            sb.append(i).append(" ");
        }
        System.out.println(sb);


    }

    public static class Token{
        int index = 0;
        int count = 0;

        Token(int index, int count){
            this.index = index;
            this.count = count;
        }
    }
}