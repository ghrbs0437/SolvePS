import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        // 최대 사용할 수 있는 회의의 개수를 출력하라.

        ArrayList<Conversation> alist = new ArrayList<Conversation>();
        for(int i=0; i<n; i++){
            String[] strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0]);
            int end = Integer.parseInt(strs[1]);
            alist.add(new Conversation(start, end));
        }
        Collections.sort(alist, (a,b)->{
            if(a.end== b.end){
                return a.start - b.start;
            }
            return a.end - b.end;
        });
        int currentTime = -1;
        int cnt = 0;
        for(Conversation c : alist){
            if(currentTime<=c.start){
//                System.out.println(c);
                currentTime = c.end;
                cnt++;
            }
        }
        System.out.println(cnt);


    }

    public static class Conversation{
        int start;
        int end;
        Conversation(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}