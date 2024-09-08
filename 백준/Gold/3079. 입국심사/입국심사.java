import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] strs = br.readLine().split(" ");
        long N = Long.parseLong(strs[0]);
        long M = Long.parseLong(strs[1]);

        long[] counter = new long[(int)N];
        for(int i=0;i<N;i++){
            counter[i] = Long.parseLong(br.readLine());
        }


        long time = 0;
        long dTime = Long.MAX_VALUE;

        // 시간으로 각각의 카운트에 해당하는걸 나누면.. 해당시간만큼 지났을떄 카운터들에서 얼마나 했는지 알수 잇다
        // 배열의 크기가 총 10만... 탐색회수 1당 10만.
        HashSet<Long> hset = new HashSet<>();
        long answerTime = Long.MAX_VALUE-10000;
        tc : while(true){
            long coverage = 0;
            if(dTime!=1){
                dTime>>=1;
            }
            if(hset.contains(time)){
                break;
            }
            if(dTime==1){
                hset.add(time);
            }

            for(int i=0;i<N;i++){
                coverage += time / counter[i];
                if(coverage<0){
                    time -=dTime;
                    continue tc;
                }
            }
            if(coverage>=M){// 성공했어...
                if(answerTime>time){
                    answerTime = time;
                }
                time -=dTime;
            }else{
                time +=dTime;
            }
        }
        System.out.println(answerTime);
    }
}