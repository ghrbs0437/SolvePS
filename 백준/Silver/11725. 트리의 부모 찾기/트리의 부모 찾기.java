import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        HashMap<Integer,Tree> hmap = new HashMap<>();
        for(int i=0;i<N-1;i++){
            String[] strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0]);
            int end = Integer.parseInt(strs[1]);

            if(hmap.get(start)==null){
                hmap.put(start,new Tree(start));
            }
            if(hmap.get(end)==null){
                hmap.put(end,new Tree(end));
            }

            Tree tstart = hmap.get(start);
            Tree tend = hmap.get(end);
            tstart.link.add(tend);
            tend.link.add(tstart);
        }
        Tree root = hmap.get(1);
        root.setThisAsRoot();
        for(int i=2;i<=N;i++){
            sb.append(hmap.get(i).parent.value+"\n");
        }
        System.out.println(sb);
    }

    public static class Tree{
        int value;
        Tree parent;
        ArrayList<Tree> link = new ArrayList<>();

        Tree(int value){
            this.value = value;
        }

        public void setThisAsRoot(){
            for(Tree child:link){
                if(child==parent){
                    continue;
                }
                child.parent = this;
                child.setThisAsRoot();
            }
        }
    }
}