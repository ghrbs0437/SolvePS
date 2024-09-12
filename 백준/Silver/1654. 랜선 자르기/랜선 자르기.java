import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] strs = br.readLine().split(" ");

        // 현재 있는 랜선
        int K = Integer.parseInt(strs[0]);
        // 필요한 랜선 개수
        int N = Integer.parseInt(strs[1]);


        long[] lengths = new long[N];
        long min = Long.MAX_VALUE;
        for(int i=0;i<K;i++){
            lengths[i] = Long.parseLong(br.readLine());
            min = Math.min(min,lengths[i]);
        }
        if(N<K){
            System.out.println(min);
            return;
        }


        long answer =  0;
        long answerLength = 1;
        long danswer = Long.MAX_VALUE;

        HashSet<Long> hset = new HashSet<>();
        tc : while(true){
            if(danswer!=1){
                danswer>>=1;
            }
            if(hset.contains(answerLength)){
                break;
            }
            if(answerLength<0){
                break;
            }

            if(danswer == 1){
                hset.add(answerLength);
            }



            long sum = 0;
            for(long length : lengths){
                sum += length / answerLength;
                if(sum<0){
                    continue tc;
                }
            }

//            System.out.println(answerLength + " " +sum);
            if(sum>=N){
                answer = Math.max(answer,answerLength);
                answerLength +=danswer;
            }else{
                answerLength -=danswer;
            }

        }
        System.out.println(answer);
    }
}
