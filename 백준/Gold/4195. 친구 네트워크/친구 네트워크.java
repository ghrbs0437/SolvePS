import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for(int tc=1;tc<=testCase;tc++){
            MAXSIZE = 0;
            int F = Integer.parseInt(br.readLine());
            int hmapIndex = 1;
            int[] parents = init(201000);
            HashMap<String,Integer> hmap = new HashMap<>();

            for(int i=0;i<F;i++){
                String[] strs = br.readLine().split(" ");
                if(!hmap.containsKey(strs[0])){
                    hmap.put(strs[0],hmapIndex++);
                }
                if(!hmap.containsKey(strs[1])){
                    hmap.put(strs[1],hmapIndex++);
                }

                union(hmap.get(strs[0]), hmap.get(strs[1]),parents);
                sb.append(MAXSIZE+"\n");
            }
        }
        System.out.println(sb);

    }

    static int MAXSIZE = 0;

    public static int[] init(int size){
        int[] parents = new int[size];
        for(int i=0;i<size;i++){
            parents[i] = -1;
        }
        return parents;
    }

    public static int findRoot(int value, int[] parents){
        if(parents[value]<0){
            return value;
        }
        return parents[value] = findRoot(parents[value],parents);
    }

    public static boolean union(int value1, int value2, int[] parents){
        int rootA = findRoot(value1,parents);
        int rootB = findRoot(value2,parents);


        if(rootB!=rootA){
            parents[rootA] += parents[rootB];
            MAXSIZE = Math.abs(parents[rootA]);
            parents[rootB] = rootA;
            return true;
        }else if(rootB==rootA){
            MAXSIZE = Math.abs(parents[rootA]);
            return false;
        }
        return true;
    }

}