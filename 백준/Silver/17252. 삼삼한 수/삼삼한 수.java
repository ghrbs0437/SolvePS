import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Integer> alist = new ArrayList<>();
        int i = 1;
        while(true){
            alist.add(i);
            i*=3;
            if(i<0){
                break;
            }
        }
        Collections.sort(alist,Collections.reverseOrder());

        int num = Integer.parseInt(br.readLine());
        if(num==0){
            System.out.println("NO");
            return;
        }

        for(int delimiter : alist){
            if(num>=delimiter){
                num-=delimiter;
            }
        }
        if(num==0){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

    }
}