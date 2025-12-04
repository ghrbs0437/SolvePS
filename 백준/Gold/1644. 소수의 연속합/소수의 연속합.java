import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Main {

    public static ArrayList<String> alist = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        int num = Integer.parseInt(strs[0]);

        ArrayList<Integer> prime = new ArrayList<>();

        int size = 4000000;
        boolean[] arr = new boolean[size+1];
        arr[0] = true;
        arr[1] = true;
        for(int i=2;i<=size;i++){
            if(arr[i]){ // 소수가 아니면
                continue;
            }else{ // 소수이면
                prime.add(i);
                for(int j=i+i;j<=size;j+=i){
                    arr[j] = true;
                }
            }
        }
        int answer = 0;
        int sum = 0;
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        for(int i=0;;i++){
            if(i==prime.size() || prime.get(i)>num){
                break;
            }
            dq.addLast(prime.get(i));

            sum+= prime.get(i);
            if(sum>num){
                while(sum>num){

                    Integer n = dq.removeFirst();
                    sum-= n;
                }
            }
            if(sum == num){
                answer++;
            }
        }
        System.out.println(answer);

    }
}