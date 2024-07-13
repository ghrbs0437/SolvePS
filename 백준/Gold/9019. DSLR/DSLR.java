import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());



        tk : for(int i = 0;i<testCase;i++) {
            String[] str = br.readLine().split(" ");
            int current = Integer.parseInt(str[0]);
            int end = Integer.parseInt(str[1]);
            Queue<Integer> queue1 = new LinkedList<>();
            Queue<String> queue2 = new LinkedList<>();
            queue1.add(current);
            queue2.add("");
            int[] visit = new int[10010];
            visit[current] = 1;
            while(true){
                int size = queue1.size();
                for (int j = 0; j < size; j++) {
                    int value =  queue1.poll();
                    String answer = queue2.poll();
                    if (value == end) {
                        System.out.println(answer);
                        continue tk;
                    }
                    int d = D(value);
                    if(visit[d]==0){
                        visit[d]=1;
                        queue1.add(d);
                        queue2.add(answer+"D");
                    }
                    int s = S(value);
                    if(visit[s]==0){
                        visit[s]=1;
                        queue1.add(s);
                        queue2.add(answer+"S");
                    }
                    int l = L(value);
                    if(visit[l]==0){
                        visit[l]=1;
                        queue1.add(l);
                        queue2.add(answer+"L");
                    }
                    int r = R(value);
                    if(visit[r]==0){
                        visit[r]=1;
                        queue1.add(r);
                        queue2.add(answer+"R");
                    }
                }
            }
        }

    }

    public static int D(int number){
        return (number*2)%10000;
    }

    public static int S(int number){
        return (number==0)?9999:number-1;
    }
    public static int L(int number){
        int tmp = number/1000;
        return (number*10)%10000 + tmp;
    }
    public static int R(int number){
        int tmp = number%10;
        return number/10 +tmp*1000;
    }

}