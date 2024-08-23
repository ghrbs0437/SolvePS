import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
 
public class Solution {
 
    static int MIN = Integer.MAX_VALUE;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int testCase = Integer.parseInt(br.readLine());
         
        for(int tc =1; tc<=testCase;tc++) {
            MIN = Integer.MAX_VALUE;
            String[] strs = br.readLine().split(" ");
             
            int[] ticketPrices = new int[strs.length];
            for(int i=0;i<strs.length;i++) {
                ticketPrices[i] = Integer.parseInt(strs[i]);
            }
             
            strs = br.readLine().split(" ");
             
            int[] countMonth = new int[strs.length];
             
            for(int i=0;i<strs.length;i++) {
                countMonth[i] = Integer.parseInt(strs[i]);
            }
             
            getAns(ticketPrices,countMonth,0,0);
            sb.append("#"+tc+" "+MIN+"\n");
        }
         
        System.out.println(sb);
    }
         
    // 
    public static void getAns(int[] ticketPrices, int[] countMonth, int currentMonth, int sum) {
        if(currentMonth>=12) {
            if(MIN>sum) {
                MIN = sum;
            }
            return;
        }
         
        getAns(ticketPrices,countMonth,currentMonth+1,sum + ticketPrices[0] * countMonth[currentMonth]);
        getAns(ticketPrices,countMonth,currentMonth+1,sum + ticketPrices[1]);
        getAns(ticketPrices,countMonth,currentMonth+3,sum + ticketPrices[2]);
        getAns(ticketPrices,countMonth,currentMonth+12,sum + ticketPrices[3]);
         
    }
     
     
}