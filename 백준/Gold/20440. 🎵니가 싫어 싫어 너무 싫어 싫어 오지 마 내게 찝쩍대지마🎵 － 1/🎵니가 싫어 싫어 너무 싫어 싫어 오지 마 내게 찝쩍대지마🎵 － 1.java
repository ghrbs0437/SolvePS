import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Event> alist = new ArrayList<>();
        for(int i=0; i<n; i++){
            String[] strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0]);
            int end = Integer.parseInt(strs[1]);
            alist.add(new Event(start,1));
            alist.add(new Event(end,-1));
        }
        Collections.sort(alist,(a, b)->a.time - b.time);

        int currentCnt = 0;
        int maxCnt = currentCnt;
        int currentTime = 0;

        int maxStart = 0;
        int maxEnd = 0;

        for(int i=0;i<alist.size()-1;i++) {
            if (alist.get(i).time == alist.get(i + 1).time && alist.get(i).dCnt + alist.get(i + 1).dCnt == 0) {
                alist.remove(i);
                alist.remove(i);
                i -= 2;
            }
        }

        boolean flag = true;
        for(Event e : alist){
            currentTime = e.time;
            currentCnt += e.dCnt;
            if(currentCnt > maxCnt){
                maxCnt = currentCnt;
                maxStart = currentTime;
                flag = true;
            }
            if(currentCnt == maxCnt-1){
                if(flag){
                    maxEnd = currentTime;
                    flag = false;
                }
            }
        }
        System.out.println(maxCnt);
        System.out.println(maxStart +" " + maxEnd);


    }

    public static class Event{
        int dCnt;
        int time;
        public Event(int time, int dCnt){
            this.time = time;
            this.dCnt = dCnt;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "dCnt=" + dCnt +
                    ", time=" + time +
                    '}';
        }
    }
}