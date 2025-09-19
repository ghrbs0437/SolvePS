import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());


        ArrayList<Token> tokens = new ArrayList<>();
        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");
            int age = Integer.parseInt(strs[0]);
            String name = strs[1];
            tokens.add(new Token(age,i,name));
        }
        Collections.sort(tokens,(a,b)->{
            if(a.age==b.age){
                return a.index - b.index;
            }
            return a.age-b.age;
        });

        StringBuilder sb = new StringBuilder();
        for(Token t : tokens){
            sb.append(t.age).append(" ").append(t.name).append("\n");
        }
        System.out.println(sb);

    }

    public static class Token{
        int age;
        int index;
        String name;

        Token(int age,int index, String name){
            this.age = age;
            this.index = index;
            this.name = name;
        }
    }
}