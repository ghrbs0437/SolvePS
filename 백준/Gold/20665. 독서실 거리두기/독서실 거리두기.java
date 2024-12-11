import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]); // 좌석개수
        int T = Integer.parseInt(strs[1]); // 예약자 수
        int P = Integer.parseInt(strs[2]); // 좌석 번호

        int answer = 0;

        boolean[] using = new boolean[N];

        PriorityQueue<Reservation> startTimePQ = new PriorityQueue<>((a,b)-> {
            if(a.start == b.start){
                return a.end - b.end;
            }
            return a.start-b.start;
        });

        PriorityQueue<Reservation> endTimePQ = new PriorityQueue<>((a,b)->a.end-b.end); // 종료시간 오름차순

        for(int i=0;i<T;i++) {
            strs = br.readLine().split(" ");

            int startTime = (strs[0].charAt(0) - '0') * 10 * 60
                    + (strs[0].charAt(1) - '0') * 60
                    + (strs[0].charAt(2) - '0') * 10
                    + (strs[0].charAt(3) - '0');

            int endTime = (strs[1].charAt(0) - '0') * 10 * 60
                    + (strs[1].charAt(1) - '0') * 60
                    + (strs[1].charAt(2) - '0') * 10
                    + (strs[1].charAt(3) - '0');
            
            if(startTime!=endTime){
                Reservation reservation = new Reservation(startTime,endTime);
                startTimePQ.add(reservation);
            }
        }

        for(int i=9*60 ; i<21*60 ; i++){
            while(true){
                if(endTimePQ.isEmpty()){
                    break;
                }
                if(endTimePQ.peek().end>i){
                    break;
                }
                Reservation end = endTimePQ.poll();
                using[end.seatIndex] = false;
            }
            while(true){
                if(startTimePQ.isEmpty()){
                    break;
                }
                if(startTimePQ.peek().start>i){
                    break;
                }
                Reservation start = startTimePQ.poll();
                endTimePQ.add(start);
                start.seatIndex = getNextSeat(using);
                using[start.seatIndex] = true;
            }

            if(!using[P-1]){
                answer++;
            }
        }

        System.out.println(answer);
    }


    public static int getNextSeat(boolean[] using){
        int index = 0;
        int max = -1;

        for(int i=0;i<using.length;i++){
            if(using[i]){
                continue;
            }

            int mTmp = i;
            int pTmp = i;
            int cnt = 0;
            while(true){
                if(using[mTmp]||using[pTmp]){
                    break;
                }else{
                    cnt++;
                    if(mTmp>0) mTmp--;
                    if(pTmp<using.length-1) pTmp++;
                }
                if(mTmp==0 && pTmp==using.length-1) break;
            }
            if(cnt>max){
                max = cnt;
                index = i;
            }
        }
//        System.out.println(index);
        return index;
    }

    public static class Reservation{
        int start;
        int end;
        int seatIndex;
        Reservation(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}