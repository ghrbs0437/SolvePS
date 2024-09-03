import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        // 접시의 수 (2~300만)
        int N = Integer.parseInt(strs[0]);
        // 초밥의 가지수 2~3000
        int D = Integer.parseInt(strs[1]);
        // 연속접시수 2~3000
        int K = Integer.parseInt(strs[2]);
        // 쿠폰번호
        int C = Integer.parseInt(strs[3]);

        // 할인행사에 무조건 참여한다. 즉 연속된 접시를 무조건 쓴다..
        // 연속된 접시에서 나타날 수 있는 가짓수의 최대값...


        // 초밥의 가지수에 해당하는 숫자를 기록하는 배열.
        int[] combination = new int[D+1];

        // 원형큐 대신으로 사용할 배열
        int[] overRange = new int[K];

        int currentCnt = 0;
        int maxCnt = 0;

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for(int i=0;i<N;i++){
            int value = Integer.parseInt(br.readLine());
            combination[value]++;
            if(combination[value] == 1){
                currentCnt++;
            }

            if(deque.size()<K){
                overRange[i] = value;
                deque.add(value);
            }else{
                int removeVal = deque.poll();
                combination[removeVal]--;
                if(combination[removeVal]==0){
                    currentCnt--;
                }
                deque.add(value);
            }

            if(combination[C]==0){
                if(maxCnt<currentCnt+1){
                    maxCnt = currentCnt+1;
                }
            }else{
                if(maxCnt<currentCnt){
                    maxCnt = currentCnt;
                }
            }
        }

        for(int i=0;i<K;i++){
//            System.out.println(deque);
            int value = overRange[i];
            int removeVal = deque.poll();
            combination[removeVal]--;
            if(combination[removeVal]==0){
                currentCnt--;
            }
            if(combination[value]==0){
                currentCnt++;
            }
            combination[value]++;
            deque.add(value);

            if(combination[C] ==0){
                if(maxCnt<currentCnt+1){
                    maxCnt = currentCnt+1;
                }
            }else{
                if(maxCnt<currentCnt){
                    maxCnt = currentCnt;
                }
            }
        }

        System.out.println(maxCnt);

    }
}