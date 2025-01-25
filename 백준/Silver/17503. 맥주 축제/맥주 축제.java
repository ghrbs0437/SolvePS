import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int n = Integer.parseInt(strs[0]); // 기간
        int m = Integer.parseInt(strs[1]); // 선호도
        int k = Integer.parseInt(strs[2]); // 종류

        PriorityQueue<Beer> pq = new PriorityQueue<>((a,b)->{
            if(a.power == b.power){
                return a.preference - b.preference;
            }
            return a.power - b.power;
        });

        for(int i=0;i<k;i++){
            strs = br.readLine().split(" ");
            pq.add(new Beer(Integer.parseInt(strs[0]), Integer.parseInt(strs[1])));
        }

        PriorityQueue<Beer> pq2 = new PriorityQueue<>((a,b)->a.preference-b.preference);

        int power = 0;
        int preference = 0;

        while(!pq.isEmpty()){

            if(pq2.size()==n){
                Beer remove = pq2.poll();
                preference-= remove.preference;
            }

            Beer add = pq.poll();
            preference += add.preference;
            pq2.add(add);

            if(preference>=m&&pq2.size()==n){
                System.out.println(add.power);
                return;
            }
        }
        System.out.println(-1);

    }

    public static class Beer{
        int power;
        int preference;
        Beer(int preference, int power){
            this.preference = preference;
            this.power = power;
        }
    }
}