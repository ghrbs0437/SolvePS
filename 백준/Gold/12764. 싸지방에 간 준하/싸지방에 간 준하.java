import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        PriorityQueue<Computer> using = new PriorityQueue<>((a,b)->{
            return a.end - b.end;
        });
        PriorityQueue<Computer> waiting = new PriorityQueue<>((a,b)->{
            return a.index - b.index;
        });

        StringBuilder sb = new StringBuilder();
        int computerIndex = 0;
        ArrayList<User> alist = new ArrayList<>();
        for(int i=0;i<X;i++){
            String[] strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0]);
            int end = Integer.parseInt(strs[1]);
            User user = new User(start,end);
            alist.add(user);
        }
        Collections.sort(alist,(a, b)->a.start-b.start);

        for(User user : alist){
            if(using.peek()==null){
                using.add(new Computer(computerIndex,user.end,1));
                computerIndex++;
            }else{
                while(using.peek()!=null && using.peek().end<= user.start){
                    waiting.add(using.poll());
                }
                if(waiting.isEmpty()){
                    Computer computer = new Computer(computerIndex,user.end,1);
                    computerIndex++;
                    using.add(computer);
                    continue;
                }
                Computer com = waiting.poll();
                com.end = user.end;
                com.cnt++;
                using.add(com);
            }
        }


        sb.append(computerIndex+"\n");
        int[] answer = new int[computerIndex];
        while(!using.isEmpty()){
            Computer com = using.poll();
            answer[com.index] = com.cnt;
        }
        while(!waiting.isEmpty()){
            Computer com = waiting.poll();
            answer[com.index] = com.cnt;
        }
        for(int i : answer){
            sb.append(i+" ");
        }
        System.out.println(sb);
    }

    public static class User{
        int start;
        int end;
        User(int start,int end){
            this.start = start;
            this.end = end;
        }
    }
    public static class Computer{
        int index;
        int end;
        int cnt;
        Computer(int index,int end,int cnt){
            this.index = index;
            this.end = end;
            this.cnt = cnt;
        }
    }
}