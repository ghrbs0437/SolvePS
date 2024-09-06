import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int gate = Integer.parseInt(br.readLine());
        int plain = Integer.parseInt(br.readLine());

        int[] parents = init(gate+1);
        for(int i=0;i<plain;i++){
            int maxGate = Integer.parseInt(br.readLine());
            if(maxGate>gate+1){
                maxGate = gate;
            }
            int root = findRoot(maxGate,parents);
            if(!useGate(root,parents)){
                System.out.println(i);
                return;
            }
        }
        System.out.println(plain);
    }

    public static int[] init(int size){
        int[] parents = new int[size];
        for(int i=0;i<size;i++){
            parents[i] = i;
        }
        return parents;
    }

    public static int findRoot(int value, int[] parents) {
        if (value == parents[value]) {
            return value;
        }
        return parents[value] = findRoot(parents[value], parents);
    }

    public static boolean useGate(int a, int[] parents){
        int rootA = findRoot(a,parents);
        if(rootA == 0){
            return false;
        }
        parents[rootA] = rootA-1;
        return true;
    }
}