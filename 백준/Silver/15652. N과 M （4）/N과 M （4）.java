import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        // 수의 가짓수
        int N = Integer.parseInt(strs[0]);

        // 고르는 숫자개수
        int M = Integer.parseInt(strs[1]);

        getAns(N,M,1,0, new ArrayList<>());
        System.out.println(sb);
    }

    public static void getAns(int N, int M, int number, int select,ArrayList<Integer> alist){

        if(select == M){
            for(int i : alist){
                sb.append(i+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=number;i<=N;i++){
            ArrayList<Integer> next = new ArrayList<>();
            for(int cur : alist){
                next.add(cur);
            }
            next.add(i);
            getAns(N,M,i,select+1,next);
        }
    }

}