import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //  삼각형이 형성되어서는 안된다..

        int test_case = Integer.parseInt(br.readLine());
        tc: for(int tc =0; tc<test_case;tc++){

            String[] split = br.readLine().split(" ");
            int V = Integer.parseInt(split[0]);
            int E = Integer.parseInt(split[1]);

            int[] points = new int[V];
            HashMap<Integer, ArrayList<Integer>> hmap = new HashMap<>();
            for(int i=0;i<E;i++){
                split = br.readLine().split(" ");
                int start = Integer.parseInt(split[0])-1;
                int end = Integer.parseInt(split[1])-1;
                if(hmap.get(start)==null){
                    hmap.put(start,new ArrayList<>());
                }
                hmap.get(start).add(end);
                if(hmap.get(end)==null){
                    hmap.put(end,new ArrayList<>());
                }
                hmap.get(end).add(start);
            }


            boolean[] visits = new boolean[V];
            for(int j=0;j<V;j++){
                int tmps = j;
                Queue<Integer> queue = new LinkedList<>();
                queue.add(tmps);
                visits[tmps] = true;

                while(!queue.isEmpty()){

                    int size = queue.size();

                    for(int i=0;i<size;i++) {
                        int start = queue.poll();
                        if(hmap.get(start)!=null){
                            for (int tmp : hmap.get(start)) {
                                if(visits[tmp]){
                                    if(points[tmp] == points[start]){
                                        System.out.println("NO");
                                        continue tc;
                                    }
                                }else{
                                    points[tmp] = (points[start]+1)%2;
                                    queue.add(tmp);
                                    visits[tmp] = true;
                                }
                            }
                        }
                    }
                }
            }

            System.out.println("YES");

        }


    }
}