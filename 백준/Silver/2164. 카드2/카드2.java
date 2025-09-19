import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for(int i=1;i<=N;i++){
            dq.add(i);
        }

        int num =0;
        while(true){
            num = dq.pollFirst();
            if(dq.isEmpty()){
                break;
            }
            dq.addLast(dq.pollFirst());
        }
        System.out.println(num);


    }
}