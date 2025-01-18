import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        ArrayList<String> alist = new ArrayList<>();
        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split("\\.");
            int aClass = Integer.parseInt(strs[0]);
            int bClass = Integer.parseInt(strs[1]);
            int cClass = Integer.parseInt(strs[2]);
            int dClass = Integer.parseInt(strs[3]);
            StringBuilder sb = new StringBuilder();
            String aString = zeroPadding(Integer.toBinaryString(aClass));
            String bString = zeroPadding(Integer.toBinaryString(bClass));
            String cString = zeroPadding(Integer.toBinaryString(cClass));
            String dString = zeroPadding(Integer.toBinaryString(dClass));
            sb.append(aString).append(bString).append(cString).append(dString);
            alist.add(sb.toString());
        }
        StringBuilder mask = new StringBuilder();
        StringBuilder network = new StringBuilder();
        boolean flag = false;
        for(int i=0;i<alist.get(0).length();i++){

            String s = alist.get(0);

            for(int j=0;j<alist.size();j++){
                if(s.charAt(i) != alist.get(j).charAt(i)){
                    flag = true;
                }
            }
            if(flag){
                mask.append(0);
                network.append(0);
            }else{
                mask.append(1);
                network.append(alist.get(0).charAt(i));
            }
        }
        StringBuilder answer = new StringBuilder();
        answer
                .append(toInt(network.substring(0,8))).append(".")
                .append(toInt(network.substring(8,16))).append(".")
                .append(toInt(network.substring(16,24))).append(".")
                .append(toInt(network.substring(24,32))).append("\n")
                .append(toInt(mask.substring(0,8))).append(".")
                .append(toInt(mask.substring(8,16))).append(".")
                .append(toInt(mask.substring(16,24))).append(".")
                .append(toInt(mask.substring(24,32))).append("\n");

        System.out.println(answer);
    }

    public static String zeroPadding(String s){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<8-s.length();i++){
            sb.append(0);
        }
        sb.append(s);
        return sb.toString();
    }

    public static String toInt(String s){
        int sum =0 ;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)=='1'){
                sum+=1<<s.length()-1-i;
            }
        }
        return String.valueOf(sum);
    }
}