import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    static int ANSWER = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        long target = Integer.parseInt(br.readLine());
        int N1 = Integer.parseInt(br.readLine());
        String[] strs = br.readLine().split(" ");
        long[] arr1 = new long[N1];
        long[] sum1 = new long[N1];

        for(int i=0;i<N1;i++){
            arr1[i] = Integer.parseInt(strs[i]);
            if(i==0){
                sum1[i] = arr1[i];
            }else{
                sum1[i] = sum1[i-1] + arr1[i];
            }
        }

        int N2 = Integer.parseInt(br.readLine());
        strs = br.readLine().split(" ");
        long[] arr2 = new long[N2];
        long[] sum2 = new long[N2];

        for(int i=0;i<N2;i++){
            arr2[i] = Integer.parseInt(strs[i]);
            if(i==0){
                sum2[i] = arr2[i];
            }else{
                sum2[i] = sum2[i-1] + arr2[i];
            }
        }

        ///////////////////////////////////////////////////

        ArrayList<Long> alist1 = new ArrayList<>();
        for(int i=0;i<N1;i++){
            for(int j=0;j<=i;j++){
                if(i==j){
                    alist1.add(sum1[i]);
                }else{
                    alist1.add(sum1[i] - sum1[j]);
                }
            }
        }

        ArrayList<Long> alist2 = new ArrayList<>();
        for(int i=0;i<N2;i++){
            for(int j=0;j<=i;j++){
                if(i==j){
                    alist2.add(sum2[i]);
                }else{
                    alist2.add(sum2[i] - sum2[j]);
                }

            }
        }

        Collections.sort(alist1);
        Collections.sort(alist2,Collections.reverseOrder());

        int index1 = 0;
        int index2 = 0;
        long answer = 0;
        while(true) {
            if (index1 >= alist1.size()) {
                break;
            }
            if (index2 >= alist2.size()) {
                break;
            }
            long sum = alist1.get(index1) + alist2.get(index2);

            if (sum < target) {
                index1++;
            } else if (sum == target) {
                int cnt1 = 0;
                while(index1+cnt1<alist1.size()){
                    if(alist1.get(index1).equals(alist1.get(index1+cnt1))){
                        cnt1++;
                    }else{
                        break;
                    }
                }

                int cnt2 = 0;
                while(index2 + cnt2 < alist2.size()){
                    if(alist2.get(index2).equals(alist2.get(index2+cnt2))){
                        cnt2++;
                    }else{
                        break;
                    }
                }
                answer+= (long)cnt1 * (long)cnt2;
                index1 +=cnt1;
                index2 +=cnt2;

            } else {
                index2++;
            }
        }
        System.out.println(answer);

    }
}