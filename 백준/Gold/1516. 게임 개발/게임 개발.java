import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); // 건물의 종류




        HashSet<Integer>[] nexts = new HashSet[N];
        HashSet<Integer>[] posts = new HashSet[N];
        for(int i=0;i<N;i++){
            nexts[i] = new HashSet<>();
            posts[i] = new HashSet<>();
        }

        int[] time = new int[N];

        Queue<Integer> starts = new ArrayDeque<>();

        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");

            time[i] = Integer.parseInt(strs[0]);
            if(strs.length==2){
                starts.add(i);
            }
            for(int j=1;j< strs.length;j++){
                int num = Integer.parseInt(strs[j])-1;
                if(num>=0){
                    posts[i].add(num);
                    nexts[num].add(i);
                }
            }
        }
        int[] answer = new int[N];

        while(!starts.isEmpty()){
            Integer build = starts.poll(); // 인덱스..
            answer[build] +=time[build];
//            answer[build] = Math.max(answer[build], answer[build] + time[build]);

            for(Integer next : nexts[build]){
                posts[next].remove(build);
                answer[next] = Math.max(answer[build],answer[next]);

                if(posts[next].isEmpty()){
                    starts.add(next);
                }
            }
        }


        for(int i=0;i<N;i++){
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb);





    }
}