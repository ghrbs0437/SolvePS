import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // 문제를 푸는데는 1의 일수가 소요된다..

        PriorityQueue<Homework> pq = new PriorityQueue<>((a,b)->{
            if(a.deadLine == b.deadLine){
                return b.reward - a.reward;
            }
            return b.deadLine - a.deadLine;
        });

        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");

            int deadline = Integer.parseInt(strs[0])-1;
            int cupRamen = Integer.parseInt(strs[1]);
            Homework homework = new Homework(deadline,cupRamen);
            pq.add(homework);
        }
        int answer = 0;
        int day = 0;

        PriorityQueue<Integer> inner = new PriorityQueue<>((a,b)->b-a);
        while(!pq.isEmpty()){

            Homework homework = pq.poll();
            int deadLine = homework.deadLine;
            inner.add(homework.reward);
//            answer += homework.reward;
            day = deadLine;

            while(true){
                if(pq.isEmpty()){
                    break;
                }
                Homework next = pq.peek();
                if(next.deadLine == deadLine){
                    inner.add(pq.peek().reward);
                    pq.poll();
                }else{
                    for(int i=0;i <deadLine - next.deadLine;i++){
                        if(inner.isEmpty()){
                            break;
                        }
                        answer+=inner.poll();
                    }
                    break;
                }
            }


        }
        for(int i=0;i<day+1;i++){
            if(inner.isEmpty()){
                break;
            }
            answer+=inner.poll();
        }
        System.out.println(answer);
    }

    public static class Homework{
        int deadLine;
        int reward;
        boolean done = false;

        Homework(int deadLine, int reward){
            this.deadLine = deadLine;
            this.reward = reward;
        }
    }
}
