import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        // 수의 가짓수
        int N = Integer.parseInt(strs[0]);
        // 고르는 숫자개수
        int M = Integer.parseInt(strs[1]);

        ArrayList<Integer> alist = new ArrayList<>();
        strs = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            alist.add(Integer.parseInt(strs[i]));
        }

        Collections.sort(alist);

        getAns(M,alist,new boolean[alist.size()],new ArrayList<>(),0);


        System.out.println(sb);
    }

    public static void getAns(int M , ArrayList<Integer> numbers, boolean[] visits, ArrayList<Integer> orders, int select){

        if(M==select){
            for(int c: orders){
                sb.append(c+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0;i<numbers.size();i++){
            if(visits[i]){
                continue;
            }
            visits[i] = true;
            orders.add(numbers.get(i));
            getAns(M,numbers,visits,orders,select+1);
            orders.remove(orders.size()-1);
            visits[i] = false;
        }
    }

}