import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minq = new PriorityQueue<>((a,b)->a-b);
        PriorityQueue<Integer> maxq = new PriorityQueue<>((a,b)->b-a);

        int current = Integer.parseInt(br.readLine());

        for(int i=1;i<N;i++){
            sb.append(current+"\n");
            int next = Integer.parseInt(br.readLine());
            // maxq에는 현재값 이하값들을 넣어야해.
            if(next<=current){
                maxq.add(next);
            }else{
                minq.add(next);
            }
            if(maxq.size()>minq.size()){
                minq.add(current);
                current = maxq.poll();
            }else if(minq.size()>maxq.size()+1){
                maxq.add(current);
                current = minq.poll();
            }
        }
        sb.append(current);
        System.out.println(sb);

    }
}