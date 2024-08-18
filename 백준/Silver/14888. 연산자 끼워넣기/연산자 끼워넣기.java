import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static int MAX_ANSWER = Integer.MIN_VALUE;
    public static int MIN_ANSWER = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int N = Integer.parseInt(br.readLine());
        String[] strs = br.readLine().split(" ");

        int[] numbers = new int[N];
        for(int i=0;i<N;i++){
            numbers[i] = Integer.parseInt(strs[i]);
        }

        strs = br.readLine().split(" ");
        int[] operators = new int[4];
        for(int i=0;i<4;i++){
            operators[i] = Integer.parseInt(strs[i]);
        }
        getAns(operators,numbers,1,numbers[0]);
        // 0 1 2 3 순서대로 + = * /
        System.out.println(MAX_ANSWER+"\n"+MIN_ANSWER);
    }

    public static void getAns(int[] operators,int[] numbers,int index, int number){
        for(int operator : operators){
            if(operator<0){
                return;
            }
        }

        if(index==numbers.length){
            if(MAX_ANSWER<number){
                MAX_ANSWER=number;
            }
            if(MIN_ANSWER>number){
                MIN_ANSWER=number;
            }
            return;
        }

        operators[0]--;
        getAns(operators,numbers,index+1,number+numbers[index]);
        operators[0]++;
        operators[1]--;
        getAns(operators,numbers,index+1,number-numbers[index]);
        operators[1]++;
        operators[2]--;
        getAns(operators,numbers,index+1,number*numbers[index]);
        operators[2]++;
        operators[3]--;
        getAns(operators,numbers,index+1,number/numbers[index]);
        operators[3]++;
    }
}