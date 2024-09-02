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
        strs = br.readLine().split(" ");
        ArrayList<Integer> avoidList = new ArrayList<>();
        for(int i=1;i<=Integer.parseInt(strs[0]);i++){
            avoidList.add(Integer.parseInt(strs[i])-1);
        }


        ArrayList<ArrayList<Integer>> partyList = new ArrayList<>();
        for(int i=0;i<M;i++){
            strs = br.readLine().split(" ");
            ArrayList<Integer> party = new ArrayList<>();
            int size = Integer.parseInt(strs[0]);
            int valA = Integer.parseInt(strs[1])-1;
            for(int j=1;j<=size;j++){
                int valB = Integer.parseInt(strs[j])-1;
                party.add(valB);
                union(valA,valB,parents);
            }
            partyList.add(party);
        }
        
        ArrayList<Integer> avoidGroupList = new ArrayList<>();
        for(int i : avoidList){
            avoidGroupList.add(findRoot(i,parents));
        }

        int cnt =0;
        for(ArrayList<Integer> party : partyList){
            cnt++;
            for(int i : party){
                if(avoidGroupList.contains(findRoot(i,parents))){
                    cnt--;
                    break;
                }
            }
        }
        System.out.println(cnt);

    }

    public static boolean union(int a,int b,int[] parents){
        int rootA = findRoot(a,parents);
        int rootB = findRoot(b,parents);
        if(rootA == rootB){
            return false;
        }
        parents[rootA] += parents[rootB];
        parents[rootB] = rootA;
        return true;
    }
    public static int findRoot(int a, int[]parents){
        if(parents[a]<0){
            return a;
        }
        return parents[a] = findRoot(parents[a],parents);
    }


    public static int[] init(int size){
        int[] arr = new int[size];
        for(int i=0;i<size;i++){
            arr[i] = -1;
        }
        return arr;
    }
}