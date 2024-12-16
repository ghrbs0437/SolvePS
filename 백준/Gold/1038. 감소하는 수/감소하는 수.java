import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        ArrayList<String> container = new ArrayList<>();
        for(int i=10;i>0;i--){
            DFS(i,0,container,"");
        }
        if(num>=container.size()){
            System.out.println(-1);
        }else{
            System.out.println(container.get(container.size()-1 - num));
        }
        
    }

    public static void DFS(int length, int depth, ArrayList<String> container, String current) {
        if(depth == length){
            container.add(current);
        }

        if(current.isEmpty()){
            for(int i=9;i>=0;i--){
                DFS(length, depth+1, container, current+i);
            }
        }else{
            int cnt = current.charAt(current.length() - 1) - '0';
            for(int i=cnt-1;i>=0;i--){
                DFS(length, depth+1, container, current+i);
            }

        }

    }
}