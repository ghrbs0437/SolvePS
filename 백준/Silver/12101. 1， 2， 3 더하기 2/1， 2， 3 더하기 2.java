import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static ArrayList<String> alist = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");

        int[] arr = {1,2,3};


        int n = Integer.parseInt(strs[0]);
        int k = Integer.parseInt(strs[1]);

        getAns(n,0,"");

        if(alist.size()==0||alist.size()<=k-1){
            System.out.println(-1);
        }else{
            System.out.println(alist.get(k-1).substring(1));
        }

    }

    public static void getAns(int upperBound,int sum,String s){
        if(sum ==upperBound){
            alist.add(s);
            return;
        }
        if(sum>upperBound){
            return;
        }

        getAns(upperBound,sum+1,s+"+1");
        getAns(upperBound,sum+2,s+"+2");
        getAns(upperBound,sum+3,s+"+3");
    }
}