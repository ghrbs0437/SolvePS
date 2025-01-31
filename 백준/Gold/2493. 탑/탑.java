import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] strs = br.readLine().split(" ");

        StringBuilder sb = new StringBuilder();

        PriorityQueue<Tower> pq = new PriorityQueue<>((a,b)-> a.height - b.height);
        int[] arr = new int[n];
        for(int i=n-1;i>=0;i--) {

            Tower tower = new Tower(i, Integer.parseInt(strs[i]));
            while (true) {
                if (pq.isEmpty()) {
                    break;
                }
                Tower peek = pq.peek();
                if (tower.height > peek.height) {
                    arr[peek.index] = tower.index + 1;
                    pq.poll();
                } else {
                    break;
                }
            }

            pq.add(tower);
        }
        for(int i=0;i<n;i++){
            sb.append(arr[i]+" ");
        }
        System.out.println(sb);

    }

    public static class Tower{
        int index;
        int height;

        Tower(int index, int height){
            this.index = index;
            this.height = height;
        }
    }
}