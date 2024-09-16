import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 문제가 불명확한데.. 서브트리는 고려하지 않는다.
        StringBuilder sb = new StringBuilder();
        int tc = 0;
        while(true){

            tc++;
            String[] strs = br.readLine().split(" ");

            int point = Integer.parseInt(strs[0]);
            int line = Integer.parseInt(strs[1]);

            if(point==0 && line ==0){
                break;
            }

            int[] parents = init(point+1);

            for(int i=0;i<line;i++){
                strs = br.readLine().split(" ");
                int start = Integer.parseInt(strs[0]);
                int end = Integer.parseInt(strs[1]);
//                System.out.println(Arrays.toString(parents));
                if(!union(start,end,parents)){
                    union(0,start,parents);
                    union(0,end,parents);
                }
            }

//            System.out.println(Arrays.toString(parents));
            HashSet<Integer> hset = new HashSet<>();
            for(int i=1;i<=point;i++){
                if(findRoot(i,parents)==0){
                    continue;
                }
                hset.add(findRoot(i,parents));
            }
            sb.append("Case "+tc+": ");
            if(hset.size()==0){
                sb.append("No trees.");
            }else if(hset.size()==1){
                sb.append("There is one tree.");
            }else{
                sb.append("A forest of "+hset.size()+" trees.");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    public static int[] init(int size){
        int[] parents = new int[size];
        for(int i=0;i<size;i++){
            parents[i] = i;
        }
        return parents;
    }

    public static int findRoot(int value, int[] parents){
        if(value == parents[value]){
            return value;
        }
        return parents[value] = findRoot(parents[value],parents);
    }
    public static boolean union(int a,int b, int[] parents){
        int rootA = findRoot(a,parents);
        int rootB = findRoot(b,parents);
        if(rootB == rootA){
            return false;
        }
        if(rootA == 0){
            parents[rootB] = 0;
            return false;
        }
        if(rootB == 0){
            parents[rootA] = 0;
            return false;
        }
        parents[rootB] = rootA;
        return true;
    }
}