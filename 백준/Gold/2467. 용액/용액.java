import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] strs = br.readLine().split(" ");
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        for(int i=0;i<n;i++){
            int val = Integer.parseInt(strs[i]);
            if(val>0){
                list1.add(val);
            }else if(val<0){
                list2.add(val);
            }
        }
        Collections.sort(list1); // 양수
        Collections.sort(list2); // 음수
        if(list1.isEmpty()){
            System.out.println(list2.get(list2.size()-2)+" " + list2.get(list2.size()-1));
        }else if(list2.isEmpty()){
            System.out.println(list1.get(0)+" "+list1.get(1));
        }else{
            int list1Index = 0;
            int list2Index = list2.size()-1;
            int min = Integer.MAX_VALUE;
            int ansVal1 = 0;
            int ansVal2 = 0;

            if(list1.size()>2){
                if(min > list1.get(0) + list1.get(1)){
                    min = list1.get(0) + list1.get(1);
                    ansVal1 = list1.get(0);
                    ansVal2 = list1.get(0);
                }
            }
            if(list2.size()>2){
                if(min > Math.abs(list2.get(list2.size()-2))+ Math.abs(list2.get(list2.size()-1))){
                    min = Math.abs(list2.get(list2.size()-2))+ Math.abs(list2.get(list2.size()-1));
                    ansVal2 = list2.get(list2.size()-2);
                    ansVal1 = list2.get(list2.size()-1);
                }
            }


            while(true){
                if(list1Index==list1.size()
                || list2Index==-1){
                    break;
                }
                int val1 = list1.get(list1Index);
                int val2 = list2.get(list2Index);

                int absValue = Math.abs(val1+val2);
                if(min > absValue){
                    min = absValue;
                    ansVal1 = val1;
                    ansVal2 = val2;
                }

                if(val1+val2==0){
                    System.out.println(val2 + " " +val1);
                    return;
                }else if(val1+val2<0){
                    list1Index++;
                }else if(val1+val2>0){
                    list2Index--;
                }

            }
            System.out.println(ansVal2+" " + ansVal1);
            return;
        }
    }
}