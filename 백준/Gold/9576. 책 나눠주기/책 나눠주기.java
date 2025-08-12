import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static int answer = 0;

    public static int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++){

            String[] strs =  br.readLine().split(" ");

            int N = Integer.parseInt(strs[0]);
            int M = Integer.parseInt(strs[1]);


            Command[] commands = new Command[M];
            Book[] books = new Book[N];
            for(int i=0;i<N;i++){
                books[i] = new Book();
            }

            for(int i=0;i<M;i++){
                strs = br.readLine().split(" ");
                int a = Integer.parseInt(strs[0])-1; // 책번호 범위 시작
                int b = Integer.parseInt(strs[1])-1; // 책번호 범위 끝
                commands[i] = new Command(a, b);

                for(int j=a;j<=b;j++){
                    books[j].pq.add(commands[i]);
                }

            }


            int cnt = 0;
            for(int i=0;i<N;i++){
                Book book = books[i];
                while(!book.pq.isEmpty()){
                    Command poll = book.pq.poll();
                    if(poll.execute){
                        continue;
                    }else{
                        poll.execute = true;
                        cnt++;
                        break;
                    }
                }
            }
            System.out.println(cnt);

        }
    }

    public static class Book{
        PriorityQueue<Command> pq = new PriorityQueue<>((a,b)->{
            return a.end - b.end;
        });
    }

    public static class Command{
        int start;
        int end;

        boolean execute = false;
        Command(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}