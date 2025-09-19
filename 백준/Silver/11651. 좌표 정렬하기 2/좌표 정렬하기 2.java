import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Token> tokens =new ArrayList<>();
        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");

            int x = Integer.parseInt(strs[0]);
            int y = Integer.parseInt(strs[1]);

            tokens.add(new Token(y,x));
        }

        Collections.sort(tokens,(a, b)->{
            if(a.y==b.y){
                return a.x-b.x;
            }
            return a.y - b.y;
        });

        StringBuilder sb = new StringBuilder();
        for(Token token : tokens){
            sb.append(token.x).append(" ").append(token.y).append("\n");
        }
        System.out.println(sb);


    }

    public static class Token{
        int y;
        int x;
        Token(int y,int x){
            this.y = y;
            this.x = x;
        }
    }
}