import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        double[][] map = new double[n][n];
        double[][] distance = new double[n][n];

        for(int i=0;i<n;i++){
            String[] strs = br.readLine().split(" ");
            double x = Double.parseDouble(strs[0]);
            double y = Double.parseDouble(strs[1]);
            map[i][0] = x;
            map[i][1] = y;
        }

        PriorityQueue<Token> pq = new PriorityQueue<>((a,b)->{
            int aa = (int)(a.distance*10000);
            int bb = (int)(b.distance*10000);
            return aa - bb;
        });

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                double sqrt = Math.sqrt(
                        (map[i][0] - map[j][0]) * (map[i][0] - map[j][0])
                                + (map[i][1] - map[j][1]) * (map[i][1] - map[j][1]));
                distance[i][j] = sqrt;
                distance[j][i] = sqrt;
                Token token = new Token(i,j,sqrt);
                pq.add(token);
            }
        }

        int[] parents = init(n);

        double answer = 0;
        while(!pq.isEmpty()){
            Token poll = pq.poll();
            boolean union = union(parents, poll.idx1, poll.idx2);
            if(union){
                answer+=poll.distance;
            }
        }
        System.out.println(Math.round(answer*100)/100.0);
    }

    public static class Token{
        int idx1;
        int idx2;
        double distance;
        Token(int idx1,int idx2){
            this.idx1 = idx1;
            this.idx2 = idx2;
        }


        Token(int idx1,int idx2, double distance){
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.distance = distance;
        }
    }


    public static int[] init(int size){
        int[] arr = new int[size];
        for(int i=0;i<size;i++){
            arr[i] = i;
        }
        return arr;
    }

    public static int findRoot(int[] parents, int value){
        if(value == parents[value]){
            return value;
        }
        return parents[value] = findRoot(parents,parents[value]);
    }

    public static boolean union(int[] parents, int a, int b){
        int rootA = findRoot(parents,a);
        int rootB = findRoot(parents,b);

        if(rootA == rootB){
            return false;
        }

        parents[rootB] = rootA;
        return true;

    }

}