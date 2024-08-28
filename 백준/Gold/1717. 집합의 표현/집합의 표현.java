import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Main {

    static HashMap<Integer, ArrayList<Integer>> hmap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        int[] parents = init(N+1);
        for(int i=0;i<M;i++){
            strs = br.readLine().split(" ");
            int command = Integer.parseInt(strs[0]);
            int start = Integer.parseInt(strs[1]);
            int end = Integer.parseInt(strs[2]);

            if(command==0){
                union(start,end,parents);
            }
            if(command==1){
                boolean b = check(start,end,parents);
                if(b){
                    sb.append("YES\n");
                }else{
                    sb.append("NO\n");
                }
            }
        }
        System.out.println(sb);
    }
    public static boolean check(int start,int end,int[] parent){
        return findRoot(start,parent) == findRoot(end,parent);
    }

    public static void union(int start,int end,int[] parent){
        int rootA = findRoot(start,parent);
        int rootB = findRoot(end,parent);
        if(rootA==rootB){
            return;
        }else{
            parent[rootB] = rootA;
        }
    }

    public static int findRoot(int start,int[] parent){
        if(parent[start]==start){
            return start;
        }else{
            parent[start] = findRoot(parent[start],parent);
            return parent[start];
        }
    }


    public static int[] init(int size){
        int[] arr = new int[size];
        for(int i=0;i<size;i++){
            arr[i] = i;
        }
        return arr;
    }
}