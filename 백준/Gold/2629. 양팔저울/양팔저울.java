import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    static int ANSWER = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] weight = new int[N];
        String[] strs = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            weight[i] = Integer.parseInt(strs[i]);
        }

        int M = Integer.parseInt(br.readLine());
        strs = br.readLine().split(" ");
        int[] ball = new int[M];
        for(int i=0;i<M;i++){
            ball[i] = Integer.parseInt(strs[i]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++){
            sb.append(getAns(ball[i],weight)).append(" ");
        }
        System.out.println(sb);

    }

    public static String getAns(int ball, int[] weight){
        int N = weight.length;
        HashSet<Integer> hset = new HashSet<>();
        hset.add(weight[0]);
        for(int i=1;i<N;i++){
            HashSet<Integer> next = new HashSet<>();
            next.addAll(hset);
            next.add(weight[i]);
            for(Integer num : hset){
                next.add(num+weight[i]);
                next.add(Math.abs(num-weight[i]));
            }
            hset = next;
//            System.out.println(hset);
        }
        if(hset.contains(ball)){
            return "Y";
        }
        return "N";
    }
}