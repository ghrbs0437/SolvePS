import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        // 가수의 수
        int N = Integer.parseInt(strs[0]);
        // PD의 수
        int M = Integer.parseInt(strs[1]);

        ArrayList<Singer> singers = new ArrayList<>();

        for(int i=0;i<N;i++){
            singers.add(new Singer(i+1));
        }

        for(int i=0;i<M;i++){
            strs = br.readLine().split(" ");
            for(int j=1;j<strs.length-1;j++){
                int current = Integer.parseInt(strs[j])-1;
                int next = Integer.parseInt(strs[j+1])-1;
                singers.get(current).nexts.add(singers.get(next));
                singers.get(next).indgree++;
            }
        }

        PriorityQueue<Singer> pq = new PriorityQueue<>((a,b)-> a.indgree - b.indgree);

        for(Singer singer : singers){
            if(singer.indgree==0){
                pq.add(singer);
            }
        }


        int cnt = 0;

        while(!pq.isEmpty()){
            Singer singer = pq.poll();
            sb.append(singer.number+"\n");
            cnt++;
            for(Singer next : singer.nexts){
                next.indgree--;
                if(next.indgree==0){
                    pq.add(next);
                }
            }
        }

        if(cnt==N){
            System.out.println(sb);
        }else{
            System.out.println(0);
        }







    }

    public static class Singer{
        int number;
        int indgree;
        ArrayList<Singer> nexts = new ArrayList<>();

        Singer(int number){
            this.number = number;
        }
    }
}