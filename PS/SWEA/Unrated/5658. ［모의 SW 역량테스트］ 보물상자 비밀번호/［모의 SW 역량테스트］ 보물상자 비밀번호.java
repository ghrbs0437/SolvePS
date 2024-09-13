import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Solution {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int testCase = Integer.parseInt(br.readLine());
        for(int tc = 1;tc<=testCase;tc++){
            String[] strs = br.readLine().split(" ");
            int N = Integer.parseInt(strs[0]);
            int K = Integer.parseInt(strs[1]);
 
            String number = br.readLine();
            ArrayList<String> alist = new ArrayList<>();
            HashSet<String> hset = new HashSet<>();
            for(int index = 0 ;index<number.length()/4;index++){
                for (int j = 0; j < 4; j++) {
                    int offSet = j * (number.length() / 4);
                    StringBuilder sNumber = new StringBuilder();
                    for (int i = index; i < index+(number.length()/4); i++) {
                        sNumber.append(number.charAt( (i + offSet) % number.length()));
                    }
                    if(!hset.contains(sNumber.toString())){
                        hset.add(sNumber.toString());
                        alist.add(sNumber.toString());
                    }
                }
            }
            alist.sort(Comparator.reverseOrder());
            sb.append("#"+tc+" "+make16(alist.get(K-1))+"\n");
        }
        System.out.println(sb);
    }
 
    public static long make16(String str){
        return Long.parseLong(str,16);
    }
 
}