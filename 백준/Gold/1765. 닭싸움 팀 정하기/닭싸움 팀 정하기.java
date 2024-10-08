import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] parents = init(N + 1);
        HashMap<Integer, ArrayList<Integer>> hmap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            hmap.put(i, new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            String[] strs = br.readLine().split(" ");
            String command = strs[0];
            int v1 = Integer.parseInt(strs[1]);
            int v2 = Integer.parseInt(strs[2]);
            if (command.equals("F")) {
                union(v1, v2, parents);
            } else {
                hmap.get(v1).add(v2);
                hmap.get(v2).add(v1);
            }
        }
        Set<Integer> keyset = hmap.keySet();

        for (int key : keyset) {
            ArrayList<Integer> enemies = hmap.get(key);
            for(int enemy : enemies){
                ArrayList<Integer> enemyOfEnemies = hmap.get(enemy);
                for(int enemyOfEnemy : enemyOfEnemies){
                    union(key,enemyOfEnemy,parents);
                }
            }
        }


        HashSet<Integer> answer = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            answer.add(findRoot(i,parents));
        }

        System.out.println(answer.size());
//        System.out.println(Arrays.toString(parents));
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

        if(rootA == rootB){
            return false;
        }
        parents[rootB] = rootA;
        return true;
    }
}