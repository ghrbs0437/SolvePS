import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        int[] parents = init(N);
        for(int i=0;i<M;i++){
            strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0]);
            int end = Integer.parseInt(strs[1]);
            if(!union(start,end,parents)){
                System.out.println(i+1);
                return;
            }
        }
        System.out.println(0);
    }

    public static int[] init(int size){
        int[] parents = new int[size];
        for(int i=0;i<size;i++){
            parents[i] = i;
        }
        return parents;
    }

    public static int findRoot(int value , int[] parents){
        if(value == parents[value]){
            return value;
        }

        return parents[value] = findRoot(parents[value],parents);
    }

    public static boolean union(int a, int b, int[] parents){
        int rootA = findRoot(a,parents);
        int rootB = findRoot(b,parents);
        if(rootA == rootB){
            return false;
        }
        parents[rootB] = rootA;
        return true;
    }

}