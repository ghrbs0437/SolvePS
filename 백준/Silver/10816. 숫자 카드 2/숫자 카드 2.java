import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<Integer,Integer> hmap = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        String[] strs = br.readLine().split(" ");

        for(int i=0;i<N;i++){
            int num = Integer.parseInt(strs[i]);
            if(hmap.get(num)==null){
                hmap.put(num,0);
            }
            hmap.put(num,hmap.get(num)+1);
        }

        int M = Integer.parseInt(br.readLine());
        strs = br.readLine().split(" ");

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++){
            int num = Integer.parseInt(strs[i]);
            if(hmap.get(num)==null){
                sb.append(0).append(" ");
            }else{
                sb.append(hmap.get(num)).append(" ");
            }
        }
        System.out.println(sb);

    }
}