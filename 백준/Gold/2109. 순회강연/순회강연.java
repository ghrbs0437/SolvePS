import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Lesson> daypq = new PriorityQueue<>((a,b)->b.day-a.day);
        PriorityQueue<Lesson> moneypq = new PriorityQueue<>((a,b)->b.money - a.money);
        for(int i=0;i<n;i++){
            String[] strs = br.readLine().split(" ");
            int money = Integer.parseInt(strs[0]);
            int day = Integer.parseInt(strs[1]);
            daypq.add(new Lesson(day,money));
        }
        if(n==0){
            System.out.println(0);
            return;
        }
        int totalDay = daypq.peek().day;

        int[] money = new int[totalDay+1];

        for(int i=totalDay;i>0;i--){
            while(!daypq.isEmpty()&&daypq.peek().day>=i){
                moneypq.add(daypq.poll());
            }
            if(!moneypq.isEmpty()){
                money[i] = moneypq.poll().money;
            }
        }
        int sum = 0;
        for(int i :money){
            sum+=i;
        }
        System.out.println(sum);
    }

    public static class Lesson{
        int day;
        int money;
        Lesson(int day,int money){
            this.day = day;
            this.money = money;
        }
    }
}