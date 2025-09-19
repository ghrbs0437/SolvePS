import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        int[] parents = init(N);
        for(int i=0;i<M;i++){
            strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0])-1;
            int end = Integer.parseInt(strs[1])-1;
            union(parents,start,end);
        }
        HashSet<Integer> hset = new HashSet<>();
        for(int i=0;i<N;i++){
            hset.add(findRoot(parents,i));
        }
        System.out.println(hset.size());

    }

    public static int[] init(int size){
        int[] parents = new int[size];
        for(int i=0;i<size;i++){
            parents[i] = i;
        }
        return parents;
    }
    public static int findRoot(int[] parents, int a){
        if(parents[a] == a){
            return a;
        }

        return parents[a] = findRoot(parents,parents[a]);
    }

    public static boolean union(int[] parents, int a, int b){
        int rootA = findRoot(parents,a);
        int rootB = findRoot(parents,b);

        if(rootB == rootA){
            return false;
        }
        parents[rootB] = rootA;
        return true;
    }
}
