import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        

        for (int i = 0; i < testCase; i++) {
            int commandSize = Integer.parseInt(br.readLine());
            DoublePriorityQueue dpq = new DoublePriorityQueue();
            for (int j = 0; j < commandSize; j++) {
                String str = br.readLine();
                int index = Integer.parseInt(str.substring(str.indexOf(" ") + 1));
                if (str.charAt(0) == 'I') {
                    dpq.add(index);
                } else {
                    if (index == -1) {
                        dpq.pollMin();
                    } else {
                        dpq.pollMax();
                    }
                }
            }
            dpq.update();
            if(dpq.maxpq.isEmpty() || dpq.minpq.isEmpty()){
                System.out.println("EMPTY");
            }else{
                System.out.println(new StringBuilder().append(dpq.maxpq.poll()).append(" ").append(dpq.minpq.poll()));
            }

        }

    }


    public static class DoublePriorityQueue {
        private PriorityQueue<Integer> minpq = new PriorityQueue<>();
        private PriorityQueue<Integer> maxpq = new PriorityQueue<>(Collections.reverseOrder());
        private HashMap<Integer, Integer> inCount = new HashMap<>();

        DoublePriorityQueue() {
        }

        public void add(int i) {
            minpq.add(i);
            maxpq.add(i);
            inCount.put(i,inCount.getOrDefault(i,0)+1);
        }

        public void pollMin() {

            while(!minpq.isEmpty()){
                int max = minpq.poll();
                if(inCount.get(max)==null){ // null이라는건 이미 없는값이니까 무시해야함..
                    continue;
                }
                inCount.put(max,inCount.get(max)-1);
                if(inCount.get(max)==0){
                    inCount.remove(max);
                }
                break;
            }

        }
        public void pollMax() {
            while(!maxpq.isEmpty()){
                int max = maxpq.poll();
                if(inCount.get(max)==null){
                    continue;
                }
                inCount.put(max,inCount.get(max)-1);
                if(inCount.get(max)==0){
                    inCount.remove(max);
                }
                break;
            }
        }

        public void update() {
            while(!maxpq.isEmpty()){
                int max = maxpq.peek();
                if(inCount.get(max)==null){
                    maxpq.poll();
                    continue;
                }
                break;
            }

            while(!minpq.isEmpty()){
                int max = minpq.peek();
                if(inCount.get(max)==null){
                    minpq.poll();
                    continue;
                }
                break;
            }

        }

    }
}