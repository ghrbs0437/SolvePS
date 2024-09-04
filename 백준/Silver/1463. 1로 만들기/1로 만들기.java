import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(br.readLine());

        getAns(number,0);
        System.out.println(MIN);
    }
    static int MIN = Integer.MAX_VALUE;

    public static void getAns(int number,int depth){
        if(number==1){
            if(depth<MIN){
                MIN= depth;
            }
            return;
        }

        if(depth>MIN){
            return;
        }


        if(number%3==0){
            getAns(number/3,depth+1);
        }
        if(number%2==0){
            getAns(number/2,depth+1);
        }

        getAns(number-1,depth+1);
    }

}