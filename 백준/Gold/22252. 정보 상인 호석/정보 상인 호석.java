import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        HashMap<String, PriorityQueue<Integer>> hmap = new HashMap<>();
        long sum = 0;
        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");
            int command = Integer.parseInt(strs[0]);

            if(hmap.get(strs[1])==null){
                hmap.put(strs[1],new PriorityQueue<>((a,b)->b-a));
            }

            if(command==1){
                for(int j=3;j<strs.length;j++){
                    hmap.get(strs[1]).add(Integer.parseInt(strs[j]));
                }
            }else{
                int cnt = Integer.parseInt(strs[2]);
                while(true){
                    if(cnt==0){
                        break;
                    }
                    if(hmap.get(strs[1]).isEmpty()){
                        break;
                    }
                    sum+=hmap.get(strs[1]).poll();
                    cnt--;
                }
//                sum+=hmap.get(strs[1]).poll();
            }
        }

        System.out.println(sum);
    }

}